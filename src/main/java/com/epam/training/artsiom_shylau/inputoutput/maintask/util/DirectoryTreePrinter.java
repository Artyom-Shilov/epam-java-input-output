package com.epam.training.artsiom_shylau.inputoutput.maintask.util;

import com.epam.training.artsiom_shylau.inputoutput.maintask.enums.DirectoryTreeProcessingEnum;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DirectoryTreePrinter {

    private StringBuilder builder;

    public void printDirectoryTreeInFile(String sourceDirectoryPath, String outputFilePath) throws IOException {
        File folder = new File(sourceDirectoryPath);
        if (!folder.isDirectory()) {
            throw new IOException("source is not a directory");
        }
        builder = new StringBuilder();
        formDirectoryTreeContent(folder, builder, new ArrayList<>());
        Files.write(Path.of(outputFilePath), DirectoryTreeProcessingEnum.FIRST_LINE_CONTENT.getContent().getBytes());
        Files.write(Path.of(outputFilePath), "\n".getBytes(), StandardOpenOption.APPEND);
        Files.write(Path.of(outputFilePath), builder.toString().getBytes(), StandardOpenOption.APPEND);
    }


    private boolean checkIsThereDirectoryFurtherOnTheSameLevel(File[] directoryElements, int certainElementIndex) {
        int maxIndex = directoryElements.length - 1;
        return directoryElements.length != 1
                && !directoryElements[maxIndex].isFile()
                && (!directoryElements[maxIndex].isDirectory() || certainElementIndex != maxIndex);
    }

    private void formDirectoryTreeContent(File directoryElement, StringBuilder sb, List<Boolean> currentTreeData) {
        formIndentForDirectory(sb, currentTreeData).append(directoryElement.getName()).append("\n");
        File[] directoryElements = Arrays.stream(directoryElement.listFiles())
                .sorted(Comparator.comparing(File::isDirectory).thenComparing(File::getName))
                .toArray(File[]::new);
        for (int i = 0; i < directoryElements.length; i++) {
            currentTreeData.add(checkIsThereDirectoryFurtherOnTheSameLevel(directoryElements, i));
            if (directoryElements[i].isDirectory()) {
                formDirectoryTreeContent(directoryElements[i], sb, currentTreeData);
            } else {
                formIndentForFile(sb, currentTreeData).append((directoryElements[i].getName())).append("\n");
            }
            currentTreeData.remove(currentTreeData.size() - 1);
        }
    }

    private StringBuilder formIndentForFile(StringBuilder builder, List<Boolean> currentTreeData) {
        for (Boolean indentCase : currentTreeData) {
            if (indentCase) {
                builder.append(DirectoryTreeProcessingEnum.ELEMENT_HAS_FURTHER_DIRECTORY_IN_LAYER_INDENT.getContent());
            } else {
                builder.append(
                        DirectoryTreeProcessingEnum.ELEMENT_DOES_NOT_HAVE_FURTHER_DIRECTORY_IN_LAYER_INDENT.getContent()
                );
            }
        }
        return builder;
    }

    private StringBuilder formIndentForDirectory(StringBuilder builder, List<Boolean> currentTreeData) {
        if (currentTreeData.size() != 0) {
            for (int i = 0; i < currentTreeData.size() - 1; i++) {
                if (currentTreeData.get(i)) {
                    builder.append(DirectoryTreeProcessingEnum.ELEMENT_HAS_FURTHER_DIRECTORY_IN_LAYER_INDENT
                            .getContent());
                } else {
                    builder.append(DirectoryTreeProcessingEnum.ELEMENT_DOES_NOT_HAVE_FURTHER_DIRECTORY_IN_LAYER_INDENT
                            .getContent());
                }
            }
            builder.append(DirectoryTreeProcessingEnum.DIRECTORY_INDENT.getContent());
        }
        return builder;
    }
}

