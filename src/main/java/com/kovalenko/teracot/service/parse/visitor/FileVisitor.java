package com.kovalenko.teracot.service.parse.visitor;

import com.kovalenko.teracot.common.AppConstants;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static com.kovalenko.teracot.common.AppConstants.SEARCH_TEST_DIRECTORY;

@Component
@Scope("prototype")
@Getter
public class FileVisitor extends SimpleFileVisitor<Path> {

    private static final String RAR_EXTENSION = "RAR";
    private static final String ZIP_EXTENSION = "ZIP";

    private Map<String, String> reports = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (isArchive(file)) {
            Path dirToCreate = null;
            Path fileToCreate;
            ZipFile zipFile = new ZipFile(file.toString());
            Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
            for (Iterator<? extends ZipEntry> iterator = zipEntries.asIterator(); iterator.hasNext(); ) {
                ZipEntry entry = iterator.next();
                String[] entryPathParts = entry.getName().split("/");
                if (entry.isDirectory() && entryPathParts[entryPathParts.length - 1].equalsIgnoreCase(SEARCH_TEST_DIRECTORY.getValue())) {
                    dirToCreate = createTempDirectory(file, entryPathParts[entryPathParts.length - 1]);
                }
                if (!entry.isDirectory() && entryPathParts[entryPathParts.length - 2].equalsIgnoreCase(SEARCH_TEST_DIRECTORY.getValue())) {
                    fileToCreate = file.resolve(Objects.requireNonNull(dirToCreate)).resolve(entryPathParts[entryPathParts.length - 1]);
                    Files.copy(zipFile.getInputStream(entry), fileToCreate);
                }
            }
            if (Objects.nonNull(dirToCreate)) {
                readReports(dirToCreate);
                deleteTempDirectory(dirToCreate);
                return FileVisitResult.TERMINATE;
            }
        }
        if (FilenameUtils.getBaseName(file.toString()).equalsIgnoreCase(SEARCH_TEST_DIRECTORY.getValue())) {
            readReports(file);
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    private Path createTempDirectory(Path file, String entryPathPart) throws IOException {
        Path dirToCreate = file.getParent().resolve(entryPathPart);
        if (Files.exists(dirToCreate)) {
            deleteTempDirectory(dirToCreate);
        }
        Files.createDirectories(dirToCreate);
        return dirToCreate;
    }

    private void deleteTempDirectory(Path dirToCreate) throws IOException {
        Files.walk(dirToCreate).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }

    private void readReports(Path dirToCreate) throws IOException {
        List<Path> reportPaths = Files
            .walk(dirToCreate)
            .filter(path -> Files.isRegularFile(path) && path.toString().endsWith(AppConstants.CSV_EXTENSION.getValue()))
            .collect(Collectors.toList());
        for (Path path : reportPaths) {
            reports.put(FilenameUtils.getBaseName(path.toString()), Files.readString(path));
        }
    }

    private boolean isArchive(Path file) {
        String fileExtension = FilenameUtils.getExtension(file.toString()).toUpperCase();
        switch (fileExtension) {
            case RAR_EXTENSION:
            case ZIP_EXTENSION:
                return true;
            default:
                return false;
        }
    }
}
