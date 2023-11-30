package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Game {
    private Long id;
    private Long playerId;

    private Long kill;
    private Long deaths;
    private Long assists;

    public Game(Long id, Long playerId, Long kill, Long deaths, Long assists) {
        this.id = id;
        this.playerId = playerId;
        this.kill = kill;
        this.deaths = deaths;
        this.assists = assists;
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

    public Long getKill() {
        return kill;
    }

    public void setKill(Long kill) {
        this.kill = kill;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public Long getAssists() {
        return assists;
    }

    public void setAssists(Long assists) {
        this.assists = assists;
    }
}
