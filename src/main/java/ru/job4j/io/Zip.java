package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.Search.search;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                System.out.println(source);
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void validateCountArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("You need input root folder "
                    + "and file extension for search");
        }
    }

    private void validateStartDir(Path start) {
        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException("You need input root folder first argument");
        }
    }

    private String controlExt(String ext) {
        return (ext.charAt(0) != '.') ? "." + ext : ext;
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validateCountArgs(args);
        ArgsName params = ArgsName.of(args);
        Path start = Paths.get(params.get("d"));
        zip.validateStartDir(start);
        String finalExt = zip.controlExt(params.get("e"));
        List<File> sources = new ArrayList<>();
        search(start, p -> !p.toFile()
                .getName()
                .endsWith(finalExt))
                .forEach(x -> sources.add(x.toFile()));
        zip.packFiles(sources, new File(params.get("o")));
    }
}