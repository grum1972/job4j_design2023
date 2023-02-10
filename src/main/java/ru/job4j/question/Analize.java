package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int addCount = 0;
        int changeCount = 0;
        int delCount = 0;
        if (!previous.equals(current)) {
            Map<Integer, String> mapPrevious = new HashMap<>();
            for (User user : previous) {
                mapPrevious.put(user.getId(), user.getName());
            }
            Map<Integer, String> mapCurrent = new HashMap<>();
            for (User user : current) {
                mapCurrent.put(user.getId(), user.getName());
            }
            for (Integer key : mapPrevious.keySet()) {
                if (mapCurrent.containsKey(key)) {
                    if (!mapCurrent.get(key).equals(mapPrevious.get(key))) {
                        changeCount++;
                    }
                } else {
                    delCount++;
                }
            }
            for (Integer key : mapCurrent.keySet()) {
                if (!mapPrevious.containsKey(key)) {
                    addCount++;
                }
            }
        }
        return new Info(addCount, changeCount, delCount);
    }
}