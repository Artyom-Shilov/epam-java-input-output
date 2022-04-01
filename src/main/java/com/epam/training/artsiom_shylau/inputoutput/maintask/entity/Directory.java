package com.epam.training.artsiom_shylau.inputoutput.maintask.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Directory {

    private String name;

    private List<String> filesInDirectory;

    public Directory(String name) {
        this.name = name;
        filesInDirectory = new ArrayList<>();
    }

    public Directory() {
    }

    public void addFile(String fileName) {
        filesInDirectory.add(fileName);
    }

    public List<String> getFilesInDirectory() {
        return filesInDirectory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return Objects.equals(name, directory.name) && Objects.equals(filesInDirectory, directory.filesInDirectory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, filesInDirectory);
    }

    @Override
    public String toString() {
        return "Directory{" +
                "name='" + name + '\'' +
                ", filesInDirectory=" + filesInDirectory +
                '}';
    }
}