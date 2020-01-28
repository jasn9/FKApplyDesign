package com.assignment.TicTacToeGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class Leaderboard {

    private Map<String , Map<Integer,Integer> > mp;

    public Leaderboard()
    {
        mp = new HashMap<>();
    }

    public void addIn(String name,int level)
    {
        if(mp.containsKey(name))
        {
            if((mp.get(name)).containsKey(level))
            {
                (mp.get(name)).put(level,1);
            }
            else
            {
                (mp.get(name)).put(level,mp.get(name).get(level)+1);
            }
        }
        else{
            mp.put(name,new HashMap<>());
            mp.get(name).put(level,1);
        }
    }

    public void display()
    {
        for(Map.Entry<String , Map<Integer,Integer> > ent:mp.entrySet())
        {
            System.out.print(ent.getKey()+" -");
            for(Map.Entry<Integer,Integer> obj:ent.getValue().entrySet())
            {
                System.out.print("\nLevel: "+obj.getKey()+" Points: "+obj.getValue());
            }
            System.out.println();
        }

    }
}
