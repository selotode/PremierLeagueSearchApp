package PartialExam;

import java.util.List;

public class Team {
    // Class for handling the data from database
    private String ID;
    private String name;
    private String matchesPlayed;
    private String wins;
    private String draws;
    private String loses;
    private String gf;
    private String ga;
    private String gd;
    private String points;
    private String lastFive;

    private static int columns = 11;


    public Team() {
        ID = null;
        name = null;
        matchesPlayed = null;
        wins = null;
        draws = null;
        loses = null;
        gf = null;
        ga = null;
        gd = null;
        points = null;
        lastFive = null;
    }

    public Team(
            int ID, String name, int matchesPlayed, int wins, int draws, int loses, int gf, int ga,
            int gd, int points, String lastFive) {
        this.ID = Integer.toString(ID);
        this.name = name;
        this.matchesPlayed = Integer.toString(matchesPlayed);
        this.wins = Integer.toString(wins);
        this.draws = Integer.toString(draws);
        this.loses = Integer.toString(loses);
        this.gf = Integer.toString(gf);
        this.ga = Integer.toString(ga);
        this.gd = Integer.toString(gd);
        this.points = Integer.toString(points);
        this.lastFive = lastFive;

    }

    @Override
    public String toString() { // Overrided toString to return data to server
        return ID + " " + name + " " + matchesPlayed + " " + wins + " "
                + draws + " " + loses + " " + gf + " " + ga + " " + gd + " "
                + points + " " + lastFive + " ";
    }


}
