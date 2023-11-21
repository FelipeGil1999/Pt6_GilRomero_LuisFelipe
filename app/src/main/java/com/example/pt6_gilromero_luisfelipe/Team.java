package com.example.pt6_gilromero_luisfelipe;

public class Team {
    private int titles;
    private String team_stadium;

    public Team(int titles, String team_stadium) {
        this.titles = titles;
        this.team_stadium = team_stadium;
    }

    public int getTitles() {
        return titles;
    }

    public void setTitles(int titles) {
        this.titles = titles;
    }

    public String getTeam_stadium() {
        return team_stadium;
    }

    public void setTeam_stadium(String team_stadium) {
        this.team_stadium = team_stadium;
    }

}
