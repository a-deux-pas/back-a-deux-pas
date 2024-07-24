package adeuxpas.back.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.io.IOException;
import java.util.UUID;

public class SecureTempFileConfig {
    private static final Path SECURE_TEMP_DIR;
    private static final boolean IS_UNIX_LIKE;

    // Static block to initialize the secure temp directory
    static {
        // Check if the operating system is Unix-like
        IS_UNIX_LIKE = System.getProperty("os.name").toLowerCase().contains("nux");
        // Defines the path of the secure temporary directory
        SECURE_TEMP_DIR = Paths.get(System.getProperty("java.io.tmpdir"),
                "aDeuxPas-" + System.getProperty("user.name"));
        try {
            // Create the secure temporary directory
            Files.createDirectories(SECURE_TEMP_DIR);
            // If the operating system is Unix-like, set POSIX permissions
            if (IS_UNIX_LIKE) {
                Files.setPosixFilePermissions(SECURE_TEMP_DIR, PosixFilePermissions.fromString("rwx------"));
            } else {
                System.out.println("Non-Unix-like OS detected, skipping POSIX permissions.");
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to create secure temp directory: " + e.getMessage());
        }
    }

    public static Path createSecureTempFile(String prefix, String suffix) throws IOException {
        String fileName = prefix + UUID.randomUUID() + suffix;
        Path filePath = SECURE_TEMP_DIR.resolve(fileName);

        if (IS_UNIX_LIKE) {
            FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions
                    .asFileAttribute(PosixFilePermissions.fromString("rw-------"));
            return Files.createFile(filePath, attr);
        } else {
            return Files.createFile(filePath);
        }
    }

    public static void safelyDeleteFile(Path file) {
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            System.err.println("Failed to delete file: " + file + ". Error: " + e.getMessage());
        }
    }
}
