package com.epam.training.artsiom_shylau.inputoutput.optionaltasks.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class FileOperator {

    private File createFileAndDirectoriesByFilePath(String filePath) throws IOException {
        File outputFile = new File(filePath);
        outputFile.getParentFile().mkdirs();
        outputFile.createNewFile();
        return outputFile;
    }

    private void writeListOfStringsInFileWithoutNewLineInTheEnd(List<String> lines, String outputFilePath)
            throws IOException {
        File outputFile = createFileAndDirectoriesByFilePath(outputFilePath);
        FileWriter writer = new FileWriter(outputFile.toPath().toString());
        for (int i = 0; i < lines.size(); i++) {
            if (i != lines.size() - 1) {
                writer.append(lines.get(i)).append(System.lineSeparator());
            } else {
                writer.append(lines.get(i));
            }
        }
        writer.close();
    }

    public void createFileFilledWithRandomIntegerNumbers(String filePath, int amountOfNumbers, int minValue, int maxValue)
            throws IOException {
        File outputFile = createFileAndDirectoriesByFilePath(filePath);
        Files.writeString(outputFile.toPath(), "");
        Random random = new Random();
        for (int i = 0; i < amountOfNumbers; i++) {
            Files.writeString(outputFile.toPath(), String.valueOf(random.nextInt(maxValue + 1 - minValue) + minValue),
                    StandardOpenOption.APPEND);
            if (i < amountOfNumbers - 1) {
                Files.writeString(outputFile.toPath(), "\n", StandardOpenOption.APPEND);
            }
        }
    }

    public void writeSortedLinesOfFileFilledWithIntegerNumbersInAscendingOrderInAnotherFile(
            String inputFilePath, String outputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(inputFilePath))
                .stream()
                .map(Integer::valueOf)
                .sorted()
                .map(String::valueOf)
                .collect(Collectors.toList());
        writeListOfStringsInFileWithoutNewLineInTheEnd(lines, outputFilePath);
    }

    public void writeProgramTextWithReplacedPublicMethodsAndFieldsByPrivateInAnotherFile(
            String inputFilePath, String outputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(inputFilePath));
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.contains("public ") && !line.contains("class") && !line.contains("interface")
                    && !line.contains("enum")) {
                lines.set(i, line.replace("public ", "private "));
            }
            writeListOfStringsInFileWithoutNewLineInTheEnd(lines, outputFilePath);
        }
    }

    public void writeProgramTextWithLinesInReverseOrderInAnotherFile(
            String inputFilePath, String outputFilePath) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(inputFilePath));
        StringBuilder builderForWholeNewLine = new StringBuilder();
        StringBuilder builderForReversePartOfLine = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            builderForWholeNewLine.append(line);
            int firstNotSpaceCharInLineIndex = 0;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) != ' ') {
                    firstNotSpaceCharInLineIndex = j;
                    break;
                }
            }
            builderForReversePartOfLine.append(line.substring(firstNotSpaceCharInLineIndex));
            builderForWholeNewLine.replace(firstNotSpaceCharInLineIndex, builderForWholeNewLine.length(),
                    builderForReversePartOfLine.reverse().toString());
            builderForReversePartOfLine.setLength(0);
            lines.set(i, builderForWholeNewLine.toString());
            builderForWholeNewLine.setLength(0);
        }
        writeListOfStringsInFileWithoutNewLineInTheEnd(lines, outputFilePath);
    }

    public void writeProgramTextWithReplacedLowerCaseCharsByUpperCaseIfWordLengthMoreThanTwoInAnotherFile
            (String inputFilePath, String outputFilePath) throws IOException {
        String content = Files.readString(Path.of(inputFilePath));
        Set<String> foundWords = new TreeSet<>(((Comparator<String>) (o1, o2) -> o2.length() - o1.length())
                .thenComparing(String::compareTo));
        for (String word : content.split(("[^a-zA-Z0-9]"))) {
            if (word.length() > 2) {
                foundWords.add(word);
            }
        }
        for (String word : foundWords) {
            content = content.replaceAll(word, word.toUpperCase(Locale.ROOT));
        }
        Files.write(createFileAndDirectoriesByFilePath(outputFilePath).toPath(), content.getBytes());
    }
}


