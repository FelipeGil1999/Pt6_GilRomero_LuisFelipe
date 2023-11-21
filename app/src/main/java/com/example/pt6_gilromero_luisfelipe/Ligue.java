package com.example.pt6_gilromero_luisfelipe;

public class Ligue {
    private int team_id;
    private String team_name;
    private String team_abbreviation;

    public Ligue(int team_id, String team_name, String team_abbreviation) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_abbreviation = team_abbreviation;
    }

    public int getTeam_id() {
        return team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getTeam_abbreviation() {
        return team_abbreviation;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setTeam_abbreviation(String team_abbreviation) {
        this.team_abbreviation = team_abbreviation;
    }



}
