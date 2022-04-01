package com.epam.training.artsiom_shylau.inputoutput.optionaltasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteSortedLinesOfFileFilledWithNumbersInAscendingOrderInAnotherFileTest extends BaseFileOperatorTest {

    @Test
    public void shouldSortLinesOfFileFilledWithIntegerNumbersInAscendingOrder() throws IOException {
        fileOperator.writeSortedLinesOfFileFilledWithIntegerNumbersInAscendingOrderInAnotherFile(
                "src/test/resources/optionaltasktestresources/sortlinestest/unsorted-file-integer-numbers.txt",
                "src/test/resources/optionaltasktestresources/sortlinestest/created-file-integer-numbers.txt");
        Assertions.assertEquals(
                Files.readString(Path.of(
                        "src/test/resources/optionaltasktestresources/sortlinestest/sorted-file-integer-numbers")),
                Files.readString(Path.of(
                        "src/test/resources/optionaltasktestresources/sortlinestest/created-file-integer-numbers.txt"))
        );
    }


    @Test
    public void shouldTrowExceptionWhenFileFilledWithFloatingPointNumbers() {
        Assertions.assertThrows(NumberFormatException.class,
                () -> fileOperator.writeSortedLinesOfFileFilledWithIntegerNumbersInAscendingOrderInAnotherFile(
                        "src/test/resources/optionaltasktestresources/sortlinestest/file-floating-point-numbers.txt",
                        "src/test/resources/optionaltasktestresources/sortlinestest/created-file-integer-numbers.txt"));
    }

    @Test
    public void shouldTrowExceptionWhenFileFilledWithText() {
        Assertions.assertThrows(NumberFormatException.class,
                () -> fileOperator.writeSortedLinesOfFileFilledWithIntegerNumbersInAscendingOrderInAnotherFile(
                        "src/test/resources/optionaltasktestresources/sortlinestest/file-fiiled-with-text.txt",
                        "src/test/resources/optionaltasktestresources/sortlinestest/created-file-integer-numbers.txt"));
    }
}