package org.example.model;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
public class Player {
    private Long id;

    private String nickname;
    private Location location;
    private List<Skin> skins;
    private List<Game> gameHistory;
    private List<Agent> agents;

    public Player(Long id, String nickname, Location location, List<Skin> skins, List<Game> gameHistory, List<Agent> agents) {
        this.id = id;
        this.nickname = nickname;
        this.location = location;
        this.skins = skins;
        this.gameHistory = gameHistory;
        this.agents = agents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Skin> getSkins() {
        return skins;
    }

    public void setSkins(List<Skin> skins) {
        this.skins = skins;
    }

    public List<Game> getGameHistory() {
        return gameHistory;
    }

    public void setGameHistory(List<Game> gameHistory) {
        this.gameHistory = gameHistory;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}
