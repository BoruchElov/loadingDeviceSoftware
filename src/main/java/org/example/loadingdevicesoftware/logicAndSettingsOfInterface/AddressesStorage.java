package org.example.loadingdevicesoftware.logicAndSettingsOfInterface;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class that manages the persistent storage of inverter MAC addresses.
 * <p>
 * The addresses are stored in a user specific directory so that packaged
 * distributions can read and update the file without depending on the
 * development "src" tree. When the file is accessed for the first time we copy
 * the default resource from the classpath so the application ships with sensible
 * defaults.
 */
public final class AddressesStorage {

    private static final String FILE_NAME = "addresses.txt";
    private static final Path STORAGE_DIR = resolveStorageDir();

    private AddressesStorage() {}

    private static Path resolveStorageDir() {
        String appData = System.getenv("APPDATA");
        if (appData != null && !appData.isBlank()) {
            return Paths.get(appData, "loadingDeviceSoftware");
        }
        return Paths.get(System.getProperty("user.home"), ".loadingdevicesoftware");
    }

    /**
     * Ensures that the addresses file exists and returns its path.
     */
    public static Path getAddressesFile() throws IOException {
        if (Files.notExists(STORAGE_DIR)) {
            Files.createDirectories(STORAGE_DIR);
        }
        Path file = STORAGE_DIR.resolve(FILE_NAME);
        if (Files.notExists(file)) {
            try (InputStream in = AddressesStorage.class.getResourceAsStream("/" + FILE_NAME)) {
                if (in != null) {
                    Files.copy(in, file, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.createFile(file);
                }
            }
        }
        return file;
    }

    /**
     * Reads all addresses from the persistent storage.
     */
    public static List<String> readAddresses() throws IOException {
        Path file = getAddressesFile();
        if (Files.size(file) == 0) {
            return new ArrayList<>();
        }
        String data = Files.readString(file, StandardCharsets.UTF_8);
        List<String> addresses = Arrays.stream(data.split(";"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());

        // Если после фильтрации список пуст — значит были только ;;;;
        if (addresses.isEmpty()) {
            return List.of(new String[]{"00:00:00:00","00:00:00:00","00:00:00:00","00:00:00:00","00:00:00:00",
                    "00:00:00:00"});
        }
        return addresses;
    }

    /**
     * Persists the provided addresses.
     */
    public static void writeAddresses(Collection<String> addresses) throws IOException {
        Path file = getAddressesFile();
        if (addresses == null || addresses.isEmpty()) {
            Files.writeString(file, "", StandardCharsets.UTF_8);
            return;
        }
        Files.writeString(file, String.join(";", addresses), StandardCharsets.UTF_8);
    }
}
