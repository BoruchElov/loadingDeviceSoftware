package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComtradeParser {

    private ComtradeParser() {
    }

    private static int analogueChannels;
    private static int digitalChannels;
    private static int totalAmountOfChannels;
    private static final ArrayList<AnalogueValue> analogueValues = new ArrayList<AnalogueValue>();
    private static int samplingFrequency;
    private static int numberOfSamples;
    private static String dateStamp;
    private static String dataType;

    record AnalogueValue(String name, double theValueOfA, double theValueOfB) {
    }


    public static CompletableFuture<Boolean> parseCFF(File cffFile) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();
        File cfgFile = null;
        File datFile = null;
        File[] filesArray = null;
        //Разделение .cff на составные части
        try {
            filesArray = separateCFF(cffFile);
            cfgFile = filesArray[0];
            datFile = filesArray[3];
        } catch (IOException e) {
            System.err.println("Ошибка при обработке .cff файла!");
            future.complete(false);
        }
        //Разбор полученного .cfg файла для дальнейшего анализа .dat файла
        settingsForDatFileAnalysis(cfgFile);
        //Интерпретация .dat файла на основании данных из .cfg файла и запись разобранных результатов в .txt файл
        try {
            readAllAnalogCounts(datFile);
        } catch (IOException e) {
            System.err.println("Ошибка при анализе .dat файла!");
            future.complete(false);
        }
        //Удаление промежуточных файлов
        try {
            for (File file : filesArray) {
                Files.deleteIfExists(file.toPath());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при удалении промежуточных файлов!");
            future.complete(false);
        }
        future.complete(true);
        return future;
    }


    /**
     * Метод для разделения файла в расширении .cff на составные части: .cfg, .inf, .hdr, .dat
     *
     * @param cffFile файл в формате .cff
     * @return массив файлов [.cfg, .inf, .hdr, .dat]
     * @throws IOException
     */
    private static File[] separateCFF(File cffFile) throws IOException {
        Path outDir = cffFile.toPath();
        String baseName = cffFile.getName().replaceFirst("\\.cff$", "");
        outDir = outDir.getParent().resolve(baseName);
        Files.createDirectories(outDir);

        Pattern header = Pattern.compile("(?m)^--- file type: (.+?) ---\\R");

        byte[] cffData = Files.readAllBytes(cffFile.toPath());
        String cffString = new String(cffData, StandardCharsets.ISO_8859_1);

        record SectionHeader(int headerStart, int headerEnd, String type) {
        }

        List<SectionHeader> sectionsHeaders = new ArrayList<>();
        Matcher matcher = header.matcher(cffString);
        while (matcher.find()) {
            sectionsHeaders.add(new SectionHeader(matcher.start(), matcher.end(), matcher.group(1).trim()));
        }

        // Для удобства: будем писать только 4 типа
        boolean cfgWritten = false, infWritten = false, hdrWritten = false, datWritten = false;

        for (int i = 0; i < sectionsHeaders.size(); i++) {
            SectionHeader cur = sectionsHeaders.get(i);
            int nextHeaderStart = (i + 1 < sectionsHeaders.size()) ? sectionsHeaders.get(i + 1).headerStart : cffData.length;

            if (cur.type.equalsIgnoreCase("CFG")) {
                byte[] cfg = Arrays.copyOfRange(cffData, cur.headerEnd, nextHeaderStart);
                Files.write(outDir.resolve(baseName + ".cfg"), cfg);
                cfgWritten = true;

            } else if (cur.type.equalsIgnoreCase("INF")) {
                byte[] inf = Arrays.copyOfRange(cffData, cur.headerEnd, nextHeaderStart);
                Files.write(outDir.resolve(baseName + ".inf"), inf);
                infWritten = true;

            } else if (cur.type.equalsIgnoreCase("HDR")) {
                byte[] hdr = Arrays.copyOfRange(cffData, cur.headerEnd, nextHeaderStart);
                Files.write(outDir.resolve(baseName + ".hdr"), hdr);
                hdrWritten = true;

            } else if (cur.type.startsWith("DAT BINARY:")) {
                // Пример: "DAT BINARY: 0001536064"
                String lenStr = cur.type.substring("DAT BINARY:".length()).trim();
                long len = Long.parseLong(lenStr);

                int binStart = cur.headerEnd;
                int binEnd = (int) (binStart + len);

                if (binEnd > cffData.length) {
                    throw new IOException("DAT BINARY length выходит за пределы файла: " + len);
                }

                byte[] dat = Arrays.copyOfRange(cffData, binStart, binEnd);
                Files.write(outDir.resolve(baseName.replaceFirst("\\.cff$", "") + ".dat"), dat);
                datWritten = true;
            }
        }

        // Мини-валидация: если в файле чего-то нет — сразу видно
        if (!cfgWritten || !infWritten || !hdrWritten || !datWritten) {
            throw new IOException("Не все секции найдены. cfg=" + cfgWritten +
                    ", inf=" + infWritten + ", hdr=" + hdrWritten + ", dat=" + datWritten);
        }

        return new File[]{
                new File(outDir.resolve(baseName + ".cfg").toUri()),
                new File(outDir.resolve(baseName + ".inf").toUri()),
                new File(outDir.resolve(baseName + ".hdr").toUri()),
                new File(outDir.resolve(baseName + ".dat").toUri())
        };
    }

    /**
     * Метод для задания параметров разбора .dat файла
     *
     * @param cfgFile конфигурационный файл в формате .cfg
     */
    private static void settingsForDatFileAnalysis(File cfgFile) {
        String cfgFileAsText;
        try {
            Charset cp1251 = Charset.forName("windows-1251");
            cfgFileAsText = new String(Files.readAllBytes(cfgFile.toPath()), cp1251);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении/преобразовании файла " + cfgFile.getName() + "!\n" + e.getMessage());
            return;
        }
        String[] lines = cfgFileAsText.split("\\R");

        //Получение информации о количестве каналов
        String[] channels = lines[1].split(",");
        totalAmountOfChannels = Integer.parseInt(channels[0]);
        analogueChannels = Integer.parseInt(channels[1].replaceAll("\\D+", ""));
        digitalChannels = Integer.parseInt(channels[2].replaceAll("\\D+", ""));

        //Получение списка с именами аналоговых сигналов и соответствующих им a и b
        for (int i = 2; i < 2 + analogueChannels; i++) {
            String[] line = lines[i].split(",");
            analogueValues.add(new AnalogueValue(line[1], Double.parseDouble(line[5]), Double.parseDouble(line[6])));
        }

        //Получение информации о дискретизации. На данный момент реализована возможность задания только одного периода
        String[] samplingInfo = lines[totalAmountOfChannels + 4].replaceAll("\\s+", "").split(",");
        samplingFrequency = Integer.parseInt(samplingInfo[0]);
        numberOfSamples = Integer.parseInt(samplingInfo[1]);

        //Получение информации о метке реального времени первой точки
        dateStamp = lines[totalAmountOfChannels + 5];

        //Получение информации о формате данных в файле .dat
        dataType = lines[totalAmountOfChannels + 7];
    }

    record Sample(double relativeTime, double[] values) {
    }

    /**
     * Читает binary (binary16) DAT и возвращает список сэмплов:
     * sampleNumber, timestamp, и массив аналоговых counts (int, полученный из signed short).
     *
     * @param datFile
     * @throws IOException
     */

    public static void readAllAnalogCounts(File datFile) throws IOException {

        List<Sample> result;

        switch (dataType.toLowerCase(Locale.ROOT)) {

            case "binary": {      // BINARY16
                byte[] data = Files.readAllBytes(datFile.toPath());
                result = readBinary(datFile, data, 2);
                break;
            }

            case "binary32": {    // BINARY32 (int32 на аналоговый канал)
                byte[] data = Files.readAllBytes(datFile.toPath());
                result = readBinary(datFile, data, 4);
                break;
            }

            case "float32": {     // FLOAT32 (float на аналоговый канал)
                byte[] data = Files.readAllBytes(datFile.toPath());
                result = readFloat32(datFile, data);
                break;
            }

            case "ascii": {       // ASCII (значения текстом через запятую)
                result = readAscii(datFile);
                break;
            }

            default:
                throw new IllegalArgumentException("Неизвестный dataType: " + dataType);
        }

        writeSamples(
                result, datFile.getParentFile().toPath(), datFile.getName()
                        .substring(0, datFile.getName().lastIndexOf('.')) + "_" + dataType.toLowerCase()
        );
    }

    /**
     * Общий ридер для BINARY16 (bytesPerAnalog=2) и BINARY32 (bytesPerAnalog=4).
     */
    private static List<Sample> readBinary(File datFile, byte[] data, int bytesPerAnalog) throws IOException {

        int statusWords = (digitalChannels + 15) / 16; // ceil(ND/16)
        int bytesPerRow = 4 + 4 + (analogueChannels * bytesPerAnalog) + (statusWords * 2);

        if (data.length % bytesPerRow != 0) {
            throw new IOException("Размер DAT не кратен размеру записи. bytesPerRow=" + bytesPerRow +
                    ", fileBytes=" + data.length + ", file=" + datFile.getName());
        }

        ByteBuffer bb = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);

        int rows = data.length / bytesPerRow;
        List<Sample> result = new ArrayList<>(rows);

        for (int r = 0; r < rows; r++) {
            long sampleNo = Integer.toUnsignedLong(bb.getInt()); // как в стандарте: unsigned 32
            long n = sampleNo - 1; // ты так делал (получаем индекс с нуля)
            double relTime = n * (1.0 / samplingFrequency);

            bb.getInt(); // timestamp пока не используем (у тебя он был ts, но дальше не применялся)

            double[] values = new double[analogueChannels];

            if (bytesPerAnalog == 2) {
                for (int i = 0; i < analogueChannels; i++) {
                    short raw = bb.getShort(); // int16
                    AnalogueValue av = analogueValues.get(i);
                    values[i] = raw * av.theValueOfA + av.theValueOfB;
                }
            } else { // bytesPerAnalog == 4
                for (int i = 0; i < analogueChannels; i++) {
                    int raw = bb.getInt(); // int32
                    AnalogueValue av = analogueValues.get(i);
                    values[i] = raw * av.theValueOfA + av.theValueOfB;
                }
            }

            // пропускаем digital status words
            for (int w = 0; w < statusWords; w++) {
                bb.getShort();
            }

            result.add(new Sample(relTime, values));
        }

        return result;
    }

    /**
     * Ридер для FLOAT32: sample(int32), timestamp(int32), затем NA * float32, затем status words.
     */
    private static List<Sample> readFloat32(File datFile, byte[] data) throws IOException {

        int statusWords = (digitalChannels + 15) / 16;
        int bytesPerRow = 4 + 4 + (analogueChannels * 4) + (statusWords * 2);

        if (data.length % bytesPerRow != 0) {
            throw new IOException("Размер DAT не кратен размеру записи. bytesPerRow=" + bytesPerRow +
                    ", fileBytes=" + data.length + ", file=" + datFile.getName());
        }

        ByteBuffer bb = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);

        int rows = data.length / bytesPerRow;
        List<Sample> result = new ArrayList<>(rows);

        for (int r = 0; r < rows; r++) {
            long sampleNo = Integer.toUnsignedLong(bb.getInt());
            long n = sampleNo - 1;
            double relTime = n * (1.0 / samplingFrequency);

            bb.getInt(); // timestamp пока не используем

            double[] values = new double[analogueChannels];
            for (int i = 0; i < analogueChannels; i++) {
                float raw = bb.getFloat(); // float32
                AnalogueValue av = analogueValues.get(i);

                // ВАЖНО: часто FLOAT32 уже в физических единицах.
                // Но чтобы сохранить твою текущую схему, оставляю умножение на A и +B:
                values[i] = raw * av.theValueOfA + av.theValueOfB;
            }

            for (int w = 0; w < statusWords; w++) {
                bb.getShort();
            }

            result.add(new Sample(relTime, values));
        }

        return result;
    }

    /**
     * Ридер ASCII: строки "n,timestamp,A1..Ak,D1..Dm" через запятую.
     */
    private static List<Sample> readAscii(File datFile) throws IOException {

        // Для РФ COMTRADE часто cp1251, но если у тебя ASCII строго — можно US_ASCII.
        // Оставлю cp1251 как более практичный вариант.
        Charset cs = Charset.forName("windows-1251");
        List<String> lines = Files.readAllLines(datFile.toPath(), cs);

        List<Sample> result = new ArrayList<>(Math.max(16, lines.size()));

        for (String line : lines) {
            if (line == null) continue;
            line = line.trim();
            if (line.isEmpty()) continue;

            // COMTRADE ASCII: разделитель обычно запятая
            String[] parts = line.split(",", -1); // -1 чтобы не терять пустые поля

            // Минимум: n, timestamp, + NA аналогов
            int min = 2 + analogueChannels;
            if (parts.length < min) {
                // можно либо пропускать, либо падать — я падаю, чтобы сразу увидеть проблему данных
                throw new IOException("Некорректная строка DAT ASCII: ожидалось >= " + min +
                        " полей, получено " + parts.length + " | line=" + line);
            }

            long sampleNo = parseUnsignedLongSafe(parts[0]);
            long n = sampleNo - 1;
            double relTime = n * (1.0 / samplingFrequency);

            // timestamp пока не используем: parts[1]

            double[] values = new double[analogueChannels];
            for (int i = 0; i < analogueChannels; i++) {
                String s = parts[2 + i].trim();
                if (s.isEmpty()) {
                    // missing value (две запятые подряд)
                    values[i] = Double.NaN;
                } else {
                    double raw = Double.parseDouble(s);
                    AnalogueValue av = analogueValues.get(i);
                    values[i] = raw * av.theValueOfA + av.theValueOfB;
                }
            }

            result.add(new Sample(relTime, values));
        }

        return result;
    }

    private static long parseUnsignedLongSafe(String s) {
        s = s.trim();
        // sampleNo в ASCII обычно без знака; если вдруг пришёл со знаком — Long.parseLong тоже ок
        long v = Long.parseLong(s);
        return v;
    }

    private static void writeSamples(
            List<Sample> samples,
            Path directory,
            String fileName
    ) throws IOException {

        // гарантируем расширение
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }

        Path filePath = directory.resolve(fileName);
        char separator = ';';

        try (BufferedWriter writer =
                     Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
            //Запись заголовка
            StringBuilder header = new StringBuilder();
            header.append("Relative time, s");
            header.append(separator);
            for (AnalogueValue value : analogueValues) {
                header.append(value.name);
                header.append(separator);
            }
            writer.write(header.toString());
            writer.newLine();
            //
            for (Sample sample : samples) {

                StringBuilder line = new StringBuilder();

                // время
                line.append(sample.relativeTime());

                // массив значений
                for (double value : sample.values()) {
                    line.append(separator);
                    line.append(String.format("%.3f", value));
                }

                writer.write(line.toString());
                writer.newLine();
            }
        }
    }

}
