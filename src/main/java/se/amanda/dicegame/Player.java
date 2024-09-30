package se.amanda.dicegame;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;

    public Player(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }


    public static void getPlayerOneInfo(Scanner scanner, Player player) {
        System.out.println("Player one - vänligen skriv in ditt namn");
        player.setName(scanner.nextLine());
    }


    public static void getPlayerTwoInfo(Scanner scanner, Player player){
        System.out.println("Player two - vänligen skriv in ditt namn");
        player.setName(scanner.nextLine());
    }
}

