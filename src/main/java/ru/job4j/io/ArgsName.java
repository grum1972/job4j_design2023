package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key is not found!");
        }
        return values.get(key);
    }

    private void checkArgs(String[] words) {
        if (words.length != 2) {
            throw new IllegalArgumentException("You need input pair key=value");
        }
        if (words[0].length() == 0) {
            throw new IllegalArgumentException("Is not key ");
        }
        if (!words[0].contains("-")) {
            throw new IllegalArgumentException("Key must have start with symbol -");
        }
        if (words[0].contains("-") && words[0].length() == 1) {
            throw new IllegalArgumentException("Is not key after symbol -");
        }
        if (words[1].length() == 0) {
            throw new IllegalArgumentException("Value is absent");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("You need input pair key=value");
        }
        for (String arg : args) {
            String[] words = arg.split("=", 2);
            checkArgs(words);
            values.put(words[0].substring(1), words[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"=512"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
