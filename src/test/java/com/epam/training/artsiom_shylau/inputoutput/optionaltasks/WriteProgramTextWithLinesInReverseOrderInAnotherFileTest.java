package com.epam.training.artsiom_shylau.inputoutput.optionaltasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteProgramTextWithLinesInReverseOrderInAnotherFileTest extends BaseFileOperatorTest {

    @Test
    public void shouldWriteProgramReversedTextInAnotherFile() throws IOException {
        fileOperator.writeProgramTextWithLinesInReverseOrderInAnotherFile(
                "src/test/resources/optionaltasktestresources/reverseprogramtexttest/original-file.txt",
                "src/test/resources/optionaltasktestresources/reverseprogramtexttest/created-file.txt");
        Assertions.assertEquals(
                Files.readString(Path.of(
                        "src/test/resources/optionaltasktestresources/reverseprogramtexttest/expected-file.txt")),
                Files.readString(Path.of(
                        "src/test/resources/optionaltasktestresources/reverseprogramtexttest/created-file.txt")));
    }
}
