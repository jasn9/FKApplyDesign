package com.assignment.TicTacToeGame;

import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardTest extends TestCase {

    public LeaderboardTest()
    {

    }

    public void testAdd()
    {
        Leaderboard leaderboard = new Leaderboard();

        leaderboard.addIn("Ken",1);

        assertTrue(leaderboard.getLeaderborad().containsKey("Ken"));
        assertTrue(leaderboard.getLeaderborad().get("Ken").containsKey(1));

    }


}