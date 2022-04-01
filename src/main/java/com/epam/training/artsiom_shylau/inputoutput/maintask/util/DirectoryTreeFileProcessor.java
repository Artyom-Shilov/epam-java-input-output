package com.epam.training.artsiom_shylau.inputoutput.maintask.util;

import com.epam.training.artsiom_shylau.inputoutput.maintask.enums.DirectoryTreeProcessingEnum;
import com.epam.training.artsiom_shylau.inputoutput.maintask.entity.Directory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DirectoryTreeFileProcessor {

    private List<Directory> directories;

    public DirectoryTreeFileProcessor(String directoryTreeFilePath) throws IOException {
        readFileWithDirectoryTree(directoryTreeFilePath);
    }

    private void readFileWithDirectoryTree(String sourceFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(sourceFilePath));

        if (lines.size() < 1 || !lines.get(0).equals(DirectoryTreeProcessingEnum.FIRST_LINE_CONTENT.getContent())) {
            throw new IOException("wrong source file format");
        }
        directories = new ArrayList<>();
        Directory currentDirectory = null;
        StringBuilder builder = null;
        if (lines.size() > 1) {
            currentDirectory = new Directory(lines.get(1));
            directories.add(currentDirectory);
            builder = new StringBuilder("\\");
        }
        for (int i = 2; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains(DirectoryTreeProcessingEnum.DIRECTORY_INDENT.getContent())) {
                String[] splitLine =
                        line.split(builder.append(DirectoryTreeProcessingEnum.DIRECTORY_INDENT.getContent()).toString());
                builder.setLength(1);
                currentDirectory = new Directory(splitLine[splitLine.length - 1]);
                directories.add(currentDirectory);
            }
            if (line.length() > 1 && !line.contains(DirectoryTreeProcessingEnum.DIRECTORY_INDENT.getContent())) {
                currentDirectory.addFile(line.substring(line.lastIndexOf(" ") + 1));
            }
        }
    }

    public int getNumberOfDirectories() {
        return directories.size();
    }

    public int getNumberOfFiles() {
        return directories.stream().mapToInt((d) -> d.getFilesInDirectory().size()).sum();
    }

    public double getAverageNumberOfFilesInDirectory() {
        int numberOfDirectories = getNumberOfDirectories();
        if (numberOfDirectories == 0) {
            return 0;
        }
        return BigDecimal
                .valueOf((double) getNumberOfFiles() / numberOfDirectories)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double getAverageLengthOfFileName() {
        int numberOfFiles = getNumberOfFiles();
        if (numberOfFiles == 0) {
            return 0;
        }
        int totalFileNameLength = 0;
        for (Directory directory : directories) {
            for (String fileName : directory.getFilesInDirectory()) {
                totalFileNameLength += fileName.length();
            }
        }
        return BigDecimal
                .valueOf((double) totalFileNameLength / numberOfFiles)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }
}