package org.example;



import org.example.model.Player;


import java.util.List;



public class ReadMain {
    public static void main(String[] args) {
        List<Player> players = new FileReader().readFile("f.json");

        for (Player p : players) {
            System.out.println(p.getId());
            System.out.println(p.getNickname());
            System.out.println(p.getAgents());
            System.out.println(p.getSkins());
            System.out.println(p.getAgents());
            System.out.println(p.getGameHistory());
            System.out.println(p.getLocation());
            System.out.println("____________________________");
        }

    }

}