package com.epam.training.artsiom_shylau.inputoutput.optionaltasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteProgramTextWithReplacedLowerCaseCharsByUpperCaseIfWordLengthMoreThanTwoInAnotherFileTest extends BaseFileOperatorTest {

    @Test
    public void shouldWriteProgramReplacedTextInAnotherFile() throws IOException {
        fileOperator.writeProgramTextWithReplacedLowerCaseCharsByUpperCaseIfWordLengthMoreThanTwoInAnotherFile(
                "src/test/resources/optionaltasktestresources/replcelowercasebyuppercase/original-file.txt",
                "src/test/resources/optionaltasktestresources/replcelowercasebyuppercase/created-file.txt");
        Assertions.assertEquals(
                Files.readString(Path.of(
                        "src/test/resources/optionaltasktestresources/replcelowercasebyuppercase/expected-file.txt")),
                Files.readString(Path.of(
                        "src/test/resources/optionaltasktestresources/replcelowercasebyuppercase/created-file.txt")));
    }
}
