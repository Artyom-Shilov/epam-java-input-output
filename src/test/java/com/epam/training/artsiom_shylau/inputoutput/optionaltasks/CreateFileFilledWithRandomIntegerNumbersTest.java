package com.epam.training.artsiom_shylau.inputoutput.optionaltasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFileFilledWithRandomIntegerNumbersTest extends BaseFileOperatorTest {

    @Test
    public void shouldCreateFile() throws IOException {
        String outputFilePath = "src/test/resources/optionaltasktestresources/createddirectory/created.txt";
        String createdDirectoryPath = "src/test/resources/optionaltasktestresources/createddirectory";
        int amountOfNumbers = 10;
        File createdFile = new File(outputFilePath);
        File createdDirectory = new File(createdDirectoryPath);
        fileOperator.createFileFilledWithRandomIntegerNumbers(outputFilePath, amountOfNumbers, 3, 5);
        Assertions.assertAll(
                ()->Assertions.assertTrue(createdDirectory.exists()),
                ()->Assertions.assertTrue(createdFile.exists()),
                ()-> Assertions.assertEquals(amountOfNumbers, Files.readAllLines(Path.of(outputFilePath)).size()));
        createdFile.delete();
        createdDirectory.delete();
    }


}
