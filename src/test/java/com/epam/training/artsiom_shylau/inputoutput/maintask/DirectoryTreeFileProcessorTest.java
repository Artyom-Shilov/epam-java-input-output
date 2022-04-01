package com.epam.training.artsiom_shylau.inputoutput.maintask;

import com.epam.training.artsiom_shylau.inputoutput.maintask.util.DirectoryTreeFileProcessor;
import com.epam.training.artsiom_shylau.inputoutput.maintask.util.DirectoryTreePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class DirectoryTreeFileProcessorTest {

    DirectoryTreeFileProcessor fileProcessor;
    DirectoryTreePrinter directoryTreePrinter = new DirectoryTreePrinter();

    private DirectoryTreeFileProcessor getDirectoryTreeFileProcessor(String sourceDirectoryPath, String outputFilePath)
            throws IOException {
        directoryTreePrinter.printDirectoryTreeInFile(sourceDirectoryPath, outputFilePath);
        return new DirectoryTreeFileProcessor(outputFilePath);
    }

    @Test
    public void shouldReadDirectoryTreeFileWhenOnlyFilesInRootDirectory() throws IOException {
        fileProcessor = getDirectoryTreeFileProcessor(
                "src/test/resources/maintasktestresources/testdirectoryonlyfiles/root",
                "src/test/resources/maintasktestresources/testdirectoryonlyfiles/created.txt"
        );
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, fileProcessor.getNumberOfDirectories()),
                () -> Assertions.assertEquals(3, fileProcessor.getNumberOfFiles()),
                () -> Assertions.assertEquals(9, fileProcessor.getAverageLengthOfFileName()),
                () -> Assertions.assertEquals(3, fileProcessor.getAverageNumberOfFilesInDirectory())
        );
    }

    @Test
    public void shouldReadDirectoryTreeFileWhenOnlyDirectoriesInRootDirectory() throws IOException {
        fileProcessor = getDirectoryTreeFileProcessor(
                "src/test/resources/maintasktestresources/testdirectoryonlydirectories/root",
                "src/test/resources/maintasktestresources/testdirectoryonlydirectories/created.txt"
        );
        Assertions.assertAll(
                () -> Assertions.assertEquals(4, fileProcessor.getNumberOfDirectories()),
                () -> Assertions.assertEquals(0, fileProcessor.getNumberOfFiles()),
                () -> Assertions.assertEquals(0.0, fileProcessor.getAverageLengthOfFileName()),
                () -> Assertions.assertEquals(0, fileProcessor.getAverageNumberOfFilesInDirectory())
        );
    }

    @Test
    public void shouldReadDirectoryTreeFileWhenMixedContentInRootDirectory() throws IOException {
        fileProcessor = getDirectoryTreeFileProcessor(
                "src/test/resources/maintasktestresources/testdirectorymixedcontent/root",
                "src/test/resources/maintasktestresources/testdirectorymixedcontent/created.txt"
        );
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, fileProcessor.getNumberOfDirectories()),
                () -> Assertions.assertEquals(2, fileProcessor.getNumberOfFiles()),
                () -> Assertions.assertEquals(8.0, fileProcessor.getAverageLengthOfFileName()),
                () -> Assertions.assertEquals(0.667, fileProcessor.getAverageNumberOfFilesInDirectory())
        );
    }

    @Test
    public void shouldReadDirectoryTreeFileWhenRealisticCase() throws IOException {
        fileProcessor = getDirectoryTreeFileProcessor(
                "src/test/resources/maintasktestresources/testdirectoryrealistyccase/root",
                "src/test/resources/maintasktestresources/testdirectoryrealistyccase/created.txt"
        );
        Assertions.assertAll(
                () -> Assertions.assertEquals(6, fileProcessor.getNumberOfDirectories()),
                () -> Assertions.assertEquals(9, fileProcessor.getNumberOfFiles()),
                () -> Assertions.assertEquals(10.111, fileProcessor.getAverageLengthOfFileName()),
                () -> Assertions.assertEquals(1.5, fileProcessor.getAverageNumberOfFilesInDirectory())
        );
    }

    @Test
    public void shouldThrowExceptionForReadDirectoryTreeFileWhenSourceFileWithoutProperHeader() {
        Assertions.assertThrows(IOException.class, () -> new DirectoryTreeFileProcessor(
                "src/test/resources/maintasktestresources/file-without-proper-header.txt"));
    }

    @Test
    public void shouldThrowExceptionForReadDirectoryTreeFileWhenEmptyFile() {
        Assertions.assertThrows(IOException.class,
                () -> new DirectoryTreeFileProcessor("src/test/resources/maintasktestresources/empty.txt"));
    }

    @Test
    public void shouldReadDirectoryTreeFileWhenValidHeaderButEmptyContent() throws IOException {
        fileProcessor = new DirectoryTreeFileProcessor(
                "src/test/resources/maintasktestresources/file-with-valid-header-empty-content.txt"
        );
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, fileProcessor.getNumberOfDirectories()),
                () -> Assertions.assertEquals(0, fileProcessor.getNumberOfFiles()),
                () -> Assertions.assertEquals(0, fileProcessor.getAverageLengthOfFileName()),
                () -> Assertions.assertEquals(0, fileProcessor.getAverageNumberOfFilesInDirectory())
        );
    }
}