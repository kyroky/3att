package org.example;

import org.example.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    private Integer count;

    public Generator(Integer count) {
        this.count = count;
    }

    public List<Player> generate(){
        List<Player> players = new ArrayList<>();
        for (int i = 1; i < count+1; i++) {
            long playerId = i*1000;


            List<Skin> skins = new ArrayList<>();
            for (int j = 1; j < new Random().nextInt(1, 20); j++) {
                long skinId = (i*(count+1)*10+j);
                Skin skin = new Skin(
                        skinId,
                        playerId,
                        "Skin_"+skinId,
                        1L
                );
                System.out.println(skin);
                skins.add(
                  skin
                );
            }


            List<Game> games = new ArrayList<>();
            for (int j = 1; j < new Random().nextInt(1, 20); j++) {
                long gameId = (i*(count+1)*10+j);
                games.add(new Game(
                        gameId,
                        new Random().nextLong(0, 50),
                        new Random().nextLong(0, 50),
                        new Random().nextLong(0, 50),
                        playerId
                        )
                );
            }


            List<Agent> agents = new ArrayList<>();
            for (int j = 1; j < new Random().nextInt(1, 20); j++) {
                long agentId = (i*(count+1)*10+j);
                agents.add(new Agent(
                        agentId,
                        playerId,
                        AgentNames.values()[new Random().nextInt(0, AgentNames.values().length)].name(),
                        new Random().nextLong(0, 1000),
                        new Random().nextLong(0, 500)
                        )
                );
            }

            Location location = new Location(
                    playerId,
                    CountryNames.values()[new Random().nextInt(0, CountryNames.values().length)].name(),
                    new Random().nextLong(200, 1000)

            );
            Player player = new Player(
                    playerId,
                    "Name_"+playerId,
                    location,
                    skins,
                    games,
                    agents
            );
            players.add(player);
        }
        return players;
    }

}
