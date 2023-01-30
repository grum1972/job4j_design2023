package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private List<Path> list = new ArrayList<>();
    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
        FileProperty fp = new FileProperty(attributes.size(), file.getFileName().toString());
        List<Path> listFiles = new ArrayList<>();
        if (!files.containsKey(fp)) {
            listFiles.add(file.toAbsolutePath());
            files.put(fp, listFiles);
        } else {
            files.get(fp).add(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getPaths() {
        for (FileProperty fp : files.keySet()) {
            if (files.get(fp).size() > 1) {
                list.addAll(files.get(fp));
            }
        }
        return list;
    }
}
