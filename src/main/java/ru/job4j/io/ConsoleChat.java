package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private String randomPhrase(List<String> list) {
        int i = (int) (Math.random() * list.size());
        return list.get(i);
    }

    public void run() {
        List<String> answers = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String phrase = scanner.nextLine();
        log.add(phrase);
        boolean silence = false;
        while (!phrase.equals(OUT)) {
            String answer = randomPhrase(answers);
            if (phrase.equals(STOP)) {
                silence = true;
            }
            if (phrase.equals(CONTINUE)) {
                silence = false;
            }
            if (!silence) {
                System.out.println(answer);
                log.add(answer);
            }
            phrase = scanner.nextLine();
            log.add(phrase);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                rsl.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)
                ))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/log16.txt", "data/answer.txt");
        cc.run();
    }
}
