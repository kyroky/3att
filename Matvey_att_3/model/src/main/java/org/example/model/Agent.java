package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Agent {
    private Long id;
    private Long playerId;

    private String name;
    private Long gameHours;
    private Long numberGames;

    public Agent(Long id, Long playerId, String name, Long gameHours, Long numberGames) {
        this.id = id;
        this.playerId = playerId;
        this.name = name;
        this.gameHours = gameHours;
        this.numberGames = numberGames;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGameHours() {
        return gameHours;
    }

    public void setGameHours(Long gameHours) {
        this.gameHours = gameHours;
    }

    public Long getNumberGames() {
        return numberGames;
    }

    public void setNumberGames(Long numberGames) {
        this.numberGames = numberGames;
    }
}
