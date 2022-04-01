package com.epam.training.artsiom_shylau.inputoutput.optionaltasks.launch;

import com.epam.training.artsiom_shylau.inputoutput.optionaltasks.util.FileOperator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.io.IOException;

public class Runner {

    private static final int AMOUNT_OF_RANDOM_NUMBERS = 15;
    private static final int MIN_VALUE_OF_RANDOM_NUMBER = 0;
    private static final int MAX_VALUE_OF_RANDOM_NUMBER = 15;
    private static final String FILE_FILLED_WITH_UNSORTED_RANDOM_NUMBERS_PATH =
            "src/main/resources/optionaltasksresources/unsorted-numbers.txt";
    private static final String FILE_FILLED_WITH_SORTED_RANDOM_NUMBERS_PATH =
            "src/main/resources/optionaltasksresources/sorted-numbers.txt";
    private static final String FILE_WITH_PROGRAM_TEXT_PATH =
            "src/main/java/com/epam/training/artsiom_shylau/inputoutput/optionaltasks/util/FileOperator.java";
    private static final String FILE_WITH_REPLACED_PUBLIC_PATH =
            "src/main/resources/optionaltasksresources/replaced-public.txt";
    private static final String FILE_WITH_REVERSED_TEXT_PATH =
            "src/main/resources/optionaltasksresources/reversed-text.txt";
    private static final String FILE_WITH_REPLACED_LOWER_CASE_PATH =
            "src/main/resources/optionaltasksresources/replaced-lower-case.txt";

    private static Logger logger = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        FileOperator fileOperator = new FileOperator();
        try {
            fileOperator.createFileFilledWithRandomIntegerNumbers(
                    FILE_FILLED_WITH_UNSORTED_RANDOM_NUMBERS_PATH, AMOUNT_OF_RANDOM_NUMBERS,
                    MIN_VALUE_OF_RANDOM_NUMBER, MAX_VALUE_OF_RANDOM_NUMBER);
            logger.log(Level.INFO, "file " + FILE_FILLED_WITH_UNSORTED_RANDOM_NUMBERS_PATH + " filled with unsorted random numbers has been created");
            logger.log(Level.INFO, "amount of random numbers: " + AMOUNT_OF_RANDOM_NUMBERS
                    + ", minimum value: " + MIN_VALUE_OF_RANDOM_NUMBER
                    + ", maximum value: " + MAX_VALUE_OF_RANDOM_NUMBER);
            fileOperator.writeSortedLinesOfFileFilledWithIntegerNumbersInAscendingOrderInAnotherFile(
                    FILE_FILLED_WITH_UNSORTED_RANDOM_NUMBERS_PATH, FILE_FILLED_WITH_SORTED_RANDOM_NUMBERS_PATH);
            logger.log(Level.INFO, "file " + FILE_FILLED_WITH_SORTED_RANDOM_NUMBERS_PATH + " with sorted values from "
                    + FILE_FILLED_WITH_UNSORTED_RANDOM_NUMBERS_PATH + " has been created");
            fileOperator.writeProgramTextWithReplacedPublicMethodsAndFieldsByPrivateInAnotherFile(
                    FILE_WITH_PROGRAM_TEXT_PATH, FILE_WITH_REPLACED_PUBLIC_PATH);
            logger.log(Level.INFO, "file " + FILE_WITH_REPLACED_PUBLIC_PATH + "with replaced public methods and "
                    + "fields by private has been created");
            logger.log(Level.INFO, "initial file is " + FILE_WITH_PROGRAM_TEXT_PATH);
            fileOperator.writeProgramTextWithLinesInReverseOrderInAnotherFile(
                    FILE_WITH_PROGRAM_TEXT_PATH, FILE_WITH_REVERSED_TEXT_PATH);
            logger.log(Level.INFO, "file " + FILE_WITH_REVERSED_TEXT_PATH + "with reversed program text has been " +
                    "created");
            logger.log(Level.INFO, "initial file is " + FILE_WITH_PROGRAM_TEXT_PATH);
            fileOperator.writeProgramTextWithReplacedLowerCaseCharsByUpperCaseIfWordLengthMoreThanTwoInAnotherFile(
                    FILE_WITH_PROGRAM_TEXT_PATH, FILE_WITH_REPLACED_LOWER_CASE_PATH);
            logger.log(Level.INFO, "file " + FILE_WITH_REPLACED_LOWER_CASE_PATH + " with replaced lower case by upper" +
                    " case if word's length more than 2");
            logger.log(Level.INFO, "initial file is " + FILE_WITH_PROGRAM_TEXT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
