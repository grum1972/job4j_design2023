package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesFinder = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("data"), duplicatesFinder);
        for (Path path : duplicatesFinder.getPaths()) {
            System.out.println(path);
        }
    }
}
