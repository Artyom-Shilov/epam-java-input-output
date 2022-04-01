package com.epam.training.artsiom_shylau.inputoutput.maintask.launch;
import com.epam.training.artsiom_shylau.inputoutput.maintask.util.DirectoryTreeFileProcessor;
import com.epam.training.artsiom_shylau.inputoutput.maintask.util.DirectoryTreePrinter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


public class Runner {

    private static final String SOURCE_DIRECTORY_PATH = "C:\\epam-training-java-inputoutput";
    private static final String OUTPUT_FILE_PATH = "src/main/resources/maintaskresources/tree.txt";

    private static Logger logger = Logger.getLogger(Runner.class);

    public static void main(String[] args) {
        DirectoryTreePrinter fileTreePrinter = new DirectoryTreePrinter();
        try {
            new File(OUTPUT_FILE_PATH).delete();
            fileTreePrinter.printDirectoryTreeInFile(SOURCE_DIRECTORY_PATH, OUTPUT_FILE_PATH);
            logger.log(Level.INFO, "directory tree with root directory " + SOURCE_DIRECTORY_PATH
                    + " has been printed printed in file" + OUTPUT_FILE_PATH);
            DirectoryTreeFileProcessor directoryTreeReader = new DirectoryTreeFileProcessor(OUTPUT_FILE_PATH);
            logger.log(Level.INFO, "directory tree file has been read");
            logger.log(Level.INFO, "number of directories: " + directoryTreeReader.getNumberOfDirectories());
            logger.log(Level.INFO, "number of files: " + directoryTreeReader.getNumberOfFiles());
            logger.log(Level.INFO, "average number of files in a directory: "
                    + directoryTreeReader.getAverageNumberOfFilesInDirectory());
            logger.log(Level.INFO, "average length of file's name: " + directoryTreeReader.getAverageLengthOfFileName());
        } catch (IOException e) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            logger.log(Level.ERROR, stringWriter.toString());
        }
    }
}
