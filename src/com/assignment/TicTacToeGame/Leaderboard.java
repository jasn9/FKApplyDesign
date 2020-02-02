package com.assignment.TicTacToeGame;

import java.util.HashMap;
import java.util.Map;

public class Leaderboard {

    private Map<String, Map<Integer, Integer>> leaderboard;

    public Leaderboard() {
        leaderboard = new HashMap<>();
    }

    public void addIn(String name, int level) {
        if (leaderboard.containsKey(name)) {
            if (!(leaderboard.get(name)).containsKey(level)) {
                (leaderboard.get(name)).put(level, 1);
            } else {
                (leaderboard.get(name)).put(level, leaderboard.get(name).get(level) + 1);
            }
        } else {
            leaderboard.put(name, new HashMap<>());
            leaderboard.get(name).put(level, 1);
        }
    }

    public void display() {
        for (Map.Entry<String, Map<Integer, Integer>> ent : leaderboard.entrySet()) {
            System.out.print(ent.getKey() + " -");
            for (Map.Entry<Integer, Integer> obj : ent.getValue().entrySet()) {
                System.out.print("\nLevel: " + obj.getKey() + " Points: " + obj.getValue());
            }
            System.out.println();
        }

    }
}
