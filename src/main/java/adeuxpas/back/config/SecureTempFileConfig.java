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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import adeuxpas.back.datainit.seeder.PreferredMeetingPlaceSeeder;

public class SecureTempFileConfig {
    private static final Path SECURE_TEMP_DIR;
    private static final boolean IS_UNIX_LIKE;
    private static final Logger logger = LoggerFactory.getLogger(PreferredMeetingPlaceSeeder.class);

    // Private constructor to prevent instantiation
    private SecureTempFileConfig() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

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
            // If the operating system is Unix-like (Linux and MacOs), set POSIX
            // permissions, otherwise, we skip this part
            //
            // PosixFilePermissions is a class in the java.nio.file.attribute package of the
            // Java Standard Library. It provides methods for working with POSIX (Portable
            // Operating System Interface) file permissions. These permissions are used to
            // control access to files and directories in Unix-like operating systems, such
            // as Linux and macOS.
            // The permission is represented by a string, here 'rwx' means Read Write and
            // eXecute
            if (IS_UNIX_LIKE) {
                Files.setPosixFilePermissions(SECURE_TEMP_DIR, PosixFilePermissions.fromString("rwx------"));
            } else {
                logger.info("Non-Unix-like OS detected, skipping POSIX permissions.");
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Failed to create secure temp directory: " + e.getMessage());
        }
    }

    public static Path createSecureTempFile(String prefix, String suffix) throws IOException {
        // Makes sure that the file name is unique
        String fileName = prefix + UUID.randomUUID() + suffix;
        // Generates the path from the file name and the secure temporary directory's
        // path
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
            logger.atError().log("Failed to delete file: " + file + ". Error: " + e.getMessage());
        }
    }
}
