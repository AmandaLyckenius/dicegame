package se.amanda.dicegame;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DiceGame {
    Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DiceGame game = new DiceGame();
        Player playerOne = new Player("PlayerOne");
        Player playerTwo = new Player("PlayerTwo");


        System.out.println("VÄLKOMMEN TILL DICE-GAME!");
        //skapar spelare via metod i Player-klassen
        Player.getPlayerOneInfo(scanner, playerOne);
        Player.getPlayerTwoInfo(scanner, playerTwo);
        System.out.println("Hej " + playerOne.getName() + " och " + playerTwo.getName() + ". Nu spelar vi!");

        boolean playDiceGame = true;
        while (playDiceGame) {
            //Kallar på metod getPlayerScore och lagrar resultatet i en variabel
            int playerOneScore = game.getPlayerScore(scanner, playerOne);
            int playerTwoScore = game.getPlayerScore(scanner, playerTwo);

            //Variablerna som lagrats med poäng jämförs genom ny metod: CompareScore
            game.compareScore(playerOneScore, playerTwoScore, playerOne, playerTwo);

            boolean validInput = false;
            //Här får spelarna bestämma om dom vill fortsätta spela eller avsluta spelet
            while (!validInput) {
                System.out.println("För att köra en runda till, tryck 1. För att avsluta, tryck 2. ");

                try {           //Try catch används för att fånga upp error om spelaren slår in annat än 1 eller 2.
                    int stopOrContinuePlaying = scanner.nextInt();

                    if (stopOrContinuePlaying == 2) {
                        playDiceGame = false;
                    }
                    if (stopOrContinuePlaying == 1) {
                        playDiceGame = true;
                    }
                    validInput=true;
                } catch (Exception e) {
                    System.out.println("Felaktig inmatning");
                    scanner.nextLine();

                }

            }

        }

    }

    public int getPlayerScore(Scanner scanner, Player player) {
        int playerActivated = 0;
            
        while (playerActivated != 1) {
            //Om spelaren tryckt 1 för att kasta tärningar kallas metoden för att rulla tärningarna och skriva ut resultatet
            try {
                System.out.println(player.getName() + ", tryck 1 för att slå tärningarna");
                playerActivated = scanner.nextInt();

                if (playerActivated == 1) {
                    return rollDiceAndPrintResults(player);
                }

            } catch (InputMismatchException e) {
                System.out.println("Felaktig inmatning");
                scanner.nextLine();
            }
        }
        return playerActivated;
    }


    public int rollDiceAndPrintResults(Player player) {
        int total = 0;
        for (int i = 1; i <= 2; i++) {
            int roll = random.nextInt(6) + 1;
            System.out.println(player.getName() + " slog: " + roll + " på kast " + i);
            total += roll;

        }
        System.out.println(player.getName() + "s totala summa: " + total);
        System.out.println();
        return total;
    }
        //jämför spelarnas resultat och skriver ut vinnaren
    public void compareScore(int playerOneScore, int playerTwoScore, Player playerOne, Player playerTwo) {
        if (playerOneScore > playerTwoScore) {
            System.out.println("GRATTIS " + playerOne.getName() + " du fick högst poäng och vinner över " + playerTwo.getName());

        } else if (playerTwoScore > playerOneScore) {
            System.out.println("GRATTIS " + playerTwo.getName() + " du fick högst poäng och vinner över " + playerOne.getName());

        } else {
            System.out.println(playerOne.getName() + " och " + playerTwo.getName() + " fick samma poäng. Det blir oavgjort!");
        }
    }


}