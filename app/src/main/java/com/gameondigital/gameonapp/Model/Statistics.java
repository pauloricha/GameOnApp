package com.gameondigital.gameonapp.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pmoreirr on 22/08/2017.
 */

public class Statistics {

    private int goals;
    private int goals_matches;
    private int matches;
    private HashMap<String, Object> defenses = new HashMap<>();
    private HashMap<String, Object> gunners = new HashMap<>();

    public Statistics() {
    }

    public int getGoals() {
        return goals;
    }

    public int getGoals_matches() {
        return goals_matches;
    }

    public int getMatches() {
        return matches;
    }

    public HashMap<String, Object> getDefenses() {
        return defenses;
    }

    public HashMap<String, Object> getGunners() {
        return gunners;
    }
}
