package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(
                             new FileOutputStream(target)
                     ))) {
            boolean status = true;
            String start = "";
            String end = "";
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] words = line.split(" ", 2);
                if (words.length == 2) {
                    if (status && ("400".equals(words[0]) || "500".equals(words[0]))) {
                        start = words[1] + ";";
                        status = false;
                    }
                    if (!status && !("400".equals(words[0]) || "500".equals(words[0]))) {
                        end = words[1];
                        out.println(start + end);
                        status = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
