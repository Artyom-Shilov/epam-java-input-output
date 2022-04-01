package com.epam.training.artsiom_shylau.inputoutput.maintask.enums;

public enum DirectoryTreeProcessingEnum {

    FIRST_LINE_CONTENT("#directorytree"),
    ELEMENT_HAS_FURTHER_DIRECTORY_IN_LAYER_INDENT("|   "),
    ELEMENT_DOES_NOT_HAVE_FURTHER_DIRECTORY_IN_LAYER_INDENT("    "),
    DIRECTORY_INDENT("|---");

    private String content;

    public String getContent() {
        return this.content;
    }

    DirectoryTreeProcessingEnum(String content) {
        this.content = content;
    }
}
