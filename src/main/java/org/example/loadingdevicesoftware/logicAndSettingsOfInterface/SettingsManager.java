package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import java.io.*;
import java.util.Properties;

public class SettingsManager {

    // Сохранение адресов и фаз в указанный файл
    public static void saveAddressPhaseSettings(String filePath, String[] addresses, String[] phases) {
        if (addresses.length != phases.length) {
            throw new IllegalArgumentException("Количество адресов и фаз должно совпадать.");
        }

        Properties properties = new Properties();
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            for (int i = 0; i < addresses.length; i++) {
                properties.setProperty("address" + (i + 1), addresses[i]);
                properties.setProperty("phase" + (i + 1), phases[i]);
            }
            properties.store(fos, "Address-Phase Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Загрузка адресов и фаз из указанного файла
    public static String[][] loadAddressPhaseSettings(String filePath, int pairCount) {
        Properties properties = new Properties();
        String[][] result = new String[2][pairCount]; // [0] - адреса, [1] - фазы

        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
            for (int i = 0; i < pairCount; i++) {
                result[0][i] = properties.getProperty("address" + (i + 1), "DefaultAddress" + (i + 1));
                result[1][i] = properties.getProperty("phase" + (i + 1), "DefaultPhase" + (i + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    // Сохранение пути рабочей директории
    public static void saveWorkingDirectory(String configFilePath, String directoryPath) {
        try (FileWriter writer = new FileWriter(configFilePath)) {
            writer.write(directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Загрузка пути рабочей директории
    public static String loadWorkingDirectory(String configFilePath) {
        File configFile = new File(configFilePath);

        // Если файл не существует, возвращаем значение по умолчанию
        if (!configFile.exists()) {
            return System.getProperty("user.home"); // Директория по умолчанию
        }

        // Если файл существует, читаем его
        try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return System.getProperty("user.home"); // Значение по умолчанию в случае ошибки
    }
}
