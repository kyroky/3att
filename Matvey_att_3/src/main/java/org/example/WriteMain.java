package org.example;


import org.example.model.Player;

import java.util.List;

public class WriteMain {
    public static void main(String[] args) {
        Generator g = new Generator(10);

        List<Player> players = g.generate();


        new FileWritener().writeToJsonFile("f1.json", players);


    }

}