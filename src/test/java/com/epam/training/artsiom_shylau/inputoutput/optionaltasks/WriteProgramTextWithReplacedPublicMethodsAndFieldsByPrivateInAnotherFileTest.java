package com.epam.training.artsiom_shylau.inputoutput.optionaltasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteProgramTextWithReplacedPublicMethodsAndFieldsByPrivateInAnotherFileTest extends BaseFileOperatorTest {

    @Test
    public void shouldWriteToAnotherFileReplacedPublicMethodsAndFields() throws IOException {
        fileOperator.writeProgramTextWithReplacedPublicMethodsAndFieldsByPrivateInAnotherFile(
                "src/test/resources/optionaltasktestresources/replacepublictest/original-file.txt",
                "src/test/resources/optionaltasktestresources/replacepublictest/created-file.txt");
        Assertions.assertEquals(
                Files.readString(Path.of(
                        "src/test/resources/optionaltasktestresources/replacepublictest/expected-file.txt")),
                Files.readString(Path.of(
                        "src/test/resources/optionaltasktestresources/replacepublictest/created-file.txt")));
    }
}
