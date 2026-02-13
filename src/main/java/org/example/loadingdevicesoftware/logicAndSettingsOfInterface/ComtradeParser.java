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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComtradeParser {

    private ComtradeParser() {}

    private static int analogueChannels;
    private static int digitalChannels;
    private static int totalAmountOfChannels;
    private static final ArrayList<AnalogueValue> analogueValues = new ArrayList<AnalogueValue>();
    private static int samplingFrequency;
    private static int numberOfSamples;
    private static String dateStamp;
    private static String dataType;

    record AnalogueValue(String name, double theValueOfA, double theValueOfB) {}


    public static void parseCFF(File cffFile) throws IOException {

        //Разделение .cff на составные части
        File[] filesArray = separateCFF(cffFile);
        File cfgFile = filesArray[0];
        File infoFile = filesArray[1];
        File hdrFile = filesArray[2];
        File datFile = filesArray[3];

        settingsForDatFileAnalysis(cfgFile);
        readAllAnalogCounts(datFile);
    }


    /**
     * Метод для разделения файла в расширении .cff на составные части: .cfg, .inf, .hdr, .dat
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

        record SectionHeader(int headerStart, int headerEnd, String type) {}

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
        analogueChannels = Integer.parseInt(channels[1].replaceAll("\\D+",""));
        digitalChannels = Integer.parseInt(channels[2].replaceAll("\\D+",""));

        //Получение списка с именами аналоговых сигналов и соответствующих им a и b
        for (int i = 2; i < 2 + analogueChannels; i++) {
            String[] line = lines[i].split(",");
            analogueValues.add(new AnalogueValue(line[1], Double.parseDouble(line[5]), Double.parseDouble(line[6])));
        }

        //Получение информации о дискретизации. На данный момент реализована возможность задания только одного периода
        String[] samplingInfo = lines[totalAmountOfChannels + 4].replaceAll("\\s+","").split(",");
        samplingFrequency = Integer.parseInt(samplingInfo[0]);
        numberOfSamples = Integer.parseInt(samplingInfo[1]);

        //Получение информации о метке реального времени первой точки
        dateStamp = lines[totalAmountOfChannels + 5];

        //Получение информации о формате данных в файле .dat
        dataType = lines[totalAmountOfChannels + 7];
    }

    record Sample(double relativeTime, double[] values) {}

    /**
     * Читает binary (binary16) DAT и возвращает список сэмплов:
     * sampleNumber, timestamp, и массив аналоговых counts (int, полученный из signed short).
     * @param datFile
     * @throws IOException
     */
    public static void readAllAnalogCounts(File datFile) throws IOException {
        byte[] data = Files.readAllBytes(datFile.toPath());

        int statusWords = (digitalChannels + 15) / 16;     // ceil(Dm/16)
        int bytesPerRow = 4 + 4 + (analogueChannels * 2) + (statusWords * 2);

        if (data.length % bytesPerRow != 0) {
            throw new IOException("Размер DAT не кратен размеру записи. bytesPerRow=" + bytesPerRow +
                    ", fileBytes=" + data.length);
        }

        ByteBuffer bb = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN);

        int rows = data.length / bytesPerRow;
        List<Sample> result = new ArrayList<>(rows);

        for (int r = 0; r < rows; r++) {
            long n = Integer.toUnsignedLong(bb.getInt());
            double relTime = n * (1. / samplingFrequency);
            long ts = Integer.toUnsignedLong(bb.getInt());

            double[] a = new double[analogueChannels];
            for (int i = 0; i < analogueChannels; i++) {
                short raw = bb.getShort();          // binary16 => 2 bytes two’s complement
                AnalogueValue aV = analogueValues.get(i);
                a[i] = raw * aV.theValueOfA + aV.theValueOfB;                         // расширяем до int (сырые counts со знаком)
            }

            // статус-слова пока пропускаем
            for (int w = 0; w < statusWords; w++) {
                bb.getShort();
            }

            result.add(new Sample(relTime, a));
        }
        writeSamples(result, datFile.getParentFile().toPath(), datFile.getName() + "_binary");

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

        try (BufferedWriter writer =
                     Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {

            for (Sample sample : samples) {

                StringBuilder line = new StringBuilder();

                // время
                line.append(sample.relativeTime());

                // массив значений
                for (double value : sample.values()) {
                    line.append(",");
                    line.append(value);
                }

                writer.write(line.toString());
                writer.newLine();
            }
        }
    }

}
