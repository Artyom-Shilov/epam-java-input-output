package com.epam.training.artsiom_shylau.inputoutput.maintask;

import com.epam.training.artsiom_shylau.inputoutput.maintask.util.DirectoryTreePrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryTreePrinterTest {

    DirectoryTreePrinter directoryTreePrinter = new DirectoryTreePrinter();

    private void mutualTestProcess(String sourceDirectoryPath, String createdOutputFilePath, String expectedFilePath)
            throws IOException {
        directoryTreePrinter.printDirectoryTreeInFile(sourceDirectoryPath, createdOutputFilePath);
        Assertions.assertEquals(
                Files.readString(Path.of(expectedFilePath)), Files.readString(Path.of(createdOutputFilePath))
        );
    }

    @Test
    public void shouldPrintDirectoryTreeInFileWhenOnlyFilesInRootDirectory() throws IOException {
        mutualTestProcess(
                "src/test/resources/maintasktestresources/testdirectoryonlyfiles/root",
                "src/test/resources/maintasktestresources/testdirectoryonlyfiles/created.txt",
                "src/test/resources/maintasktestresources/testdirectoryonlyfiles/expected.txt");
    }

    @Test
    public void shouldPrintDirectoryTreeInFileWhenOnlyDirectoriesInRootDirectory() throws IOException {
        mutualTestProcess(
                "src/test/resources/maintasktestresources/testdirectoryonlydirectories/root",
                "src/test/resources/maintasktestresources/testdirectoryonlydirectories/created.txt",
                "src/test/resources/maintasktestresources/testdirectoryonlydirectories/expected.txt"
        );
    }

    @Test
    public void shouldPrintDirectoryTreeInFileWhenMixedContentInRootDirectory() throws IOException {
        mutualTestProcess(
                "src/test/resources/maintasktestresources/testdirectorymixedcontent/root",
                "src/test/resources/maintasktestresources/testdirectorymixedcontent/created.txt",
                "src/test/resources/maintasktestresources/testdirectorymixedcontent/expected.txt"
        );
    }

    @Test
    public void shouldPrintDirectoryTreeInFileWhenRealisticCase() throws IOException {
        mutualTestProcess(
                "src/test/resources/maintasktestresources/testdirectoryrealistyccase/root",
                "src/test/resources/maintasktestresources/testdirectoryrealistyccase/created.txt",
                "src/test/resources/maintasktestresources/testdirectoryrealistyccase/expected.txt"
        );
    }

    @Test
    public void shouldPrintDirectoryTreeInFileWhenOnlyRoot() throws IOException {
        mutualTestProcess(
                "src/test/resources/maintasktestresources/testdirectoryonlyroot/root",
                "src/test/resources/maintasktestresources/testdirectoryonlyroot/created.txt",
                "src/test/resources/maintasktestresources/testdirectoryonlyroot/expected.txt"
        );
    }

    @Test
    public void shouldPrintDirectoryTreeInFileWhenRootAndSingleInnerFile() throws IOException {
        mutualTestProcess(
                "src/test/resources/maintasktestresources/testdirectoryrootandinnerfile/root",
                "src/test/resources/maintasktestresources/testdirectoryrootandinnerfile/created.txt",
                "src/test/resources/maintasktestresources/testdirectoryrootandinnerfile/expected.txt"
        );
    }

    @Test
    public void shouldPrintDirectoryTreeInFileWhenRootAndSingleInnerDirectory() throws IOException {
        mutualTestProcess(
                "src/test/resources/maintasktestresources/testdirectoryrootandinnerdirectory/root",
                "src/test/resources/maintasktestresources/testdirectoryrootandinnerdirectory/created.txt",
                "src/test/resources/maintasktestresources/testdirectoryrootandinnerdirectory/expected.txt"
        );
    }

    @Test
    public void shouldThrowExceptionForPrintDirectoryTreeInFileWhenSourceIsNotDirectory(){
        Assertions.assertThrows(IOException.class, () -> directoryTreePrinter.printDirectoryTreeInFile(
                "src/test/resources/maintasktestresources/testdirectorymixedcontent/created.txt",
                "src/test/resources/maintasktestresources/testdirectorymixedcontent/created.txt"
                ));
    }
}
