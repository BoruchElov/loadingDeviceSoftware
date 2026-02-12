package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import java.io.File;
import java.io.IOException;
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

    public static void parseCFF(File cffFile) throws IOException {

        //Разделение .cff на составные части
        File[] filesArray = separateCFF(cffFile);
        File cfgFile = filesArray[0];
        File infoFile = filesArray[1];
        File hdrFile = filesArray[2];
        File datFile = filesArray[3];

        //Получение параметров для анализа .dat файла
        String[] parameters = analyzeDataConfiguration(cfgFile);

        //

        System.out.println();
        Charset cp1251 = Charset.forName("windows-1251");

        for (File file : filesArray) {
            System.out.println(file.getName() + " | " + file.getAbsolutePath());
            System.out.println(new String(Files.readAllBytes(file.toPath()), cp1251));
            System.out.println();
            System.out.println();
            System.out.println();
        }
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

    private static String[] analyzeDataConfiguration(File datFile) {
        return new String[] {};
    }

}
