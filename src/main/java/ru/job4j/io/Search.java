package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("You need input root folder "
                    + "and file extension for search");
        }
        Path start = Paths.get(args[0]);
        String ext = args[1];
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException("You need input root folder first argument");
        }
        if (ext.charAt(0) != '.') {
            ext = "." + args[1];
        }
        String finalExt = ext;
        search(start, p -> p.toFile()
                .getName()
                .endsWith(finalExt))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
