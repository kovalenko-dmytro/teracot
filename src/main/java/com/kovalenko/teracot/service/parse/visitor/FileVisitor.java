package com.kovalenko.teracot.service.parse.visitor;

import com.kovalenko.teracot.common.AppConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import static com.kovalenko.teracot.common.AppConstants.SEARCH_TEST_DIRECTORY;

@Component
@Getter
public class FileVisitor extends SimpleFileVisitor<Path> {

    private static final String RAR_EXTENSION = "RAR";
    private static final String ZIP_EXTENSION = "ZIP";

    private Map<String, String> reports = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (isNeedToSkip(file)) {
            return FileVisitResult.CONTINUE;
        }
        if (isArchive(file)) {
            handleArchive(file);
        }
        if (FilenameUtils.getBaseName(file.toString()).equalsIgnoreCase(SEARCH_TEST_DIRECTORY.getValue())) {
            readReports(file);
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    private void handleArchive(Path file) throws IOException {
        String reportName;
        String reportContent;
        ZipFile zipFile = new ZipFile(file.toString());
        Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
        for (Iterator<? extends ZipEntry> iterator = zipEntries.asIterator(); iterator.hasNext();) {
            ZipEntry entry = iterator.next();
            String entryName = entry.getName();
            if (isSearchedReport(entry, entryName)) {
                reportName = FilenameUtils.getBaseName(entryName);
                reportContent = extractFileContent(zipFile.getInputStream(entry));
                reports.put(reportName, reportContent);
            }
        }
    }

    private boolean isSearchedReport(ZipEntry entry, String entryName) {
        return !entry.isDirectory() &&
            entryName.contains(SEARCH_TEST_DIRECTORY.getValue()) &&
            entryName.endsWith(AppConstants.CSV_EXTENSION.getValue());
    }

    private boolean isNeedToSkip(Path file) {
        return file.toString().contains(AppConstants.SKIP_JAR.getValue()) ||
            file.toString().contains(AppConstants.SKIP_LOG_ZIP.getValue());
    }

    private String extractFileContent(InputStream inputStream) throws IOException {
        StringBuilder out = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        return out.toString();
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
