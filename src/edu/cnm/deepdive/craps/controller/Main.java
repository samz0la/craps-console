package edu.cnm.deepdive.craps.controller;

import edu.cnm.deepdive.craps.model.Game;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

public class Main {


  private Random rng;
  private Game game;

  public static void main(String[] args) {
    Main main = new Main();
    if (args.length > 0) {
      main.run(Integer.parseInt(args[0]));
    } else {
      main.run();
    }

  }
  private Main () {
    rng = new SecureRandom();
    game = new Game(rng);
  }

  private void play() {
    game.play();
    for (Game.Roll roll : game.getRolls()) {
      System.out.print(roll);
    }
  }

  public void run() {
    try(Scanner scanner = new Scanner(System.in)) {
      do {
        play();
        System.out.printf("Wins: %d; Losses: %d.%nPlay again?%n", game.getWins(), game.getLosses());
        String input = scanner.next().trim();
        if (!input.isEmpty() && input.toLowerCase().charAt(0) != 'y') {
          break;
        }
      }while (true);
    }
  }
  public void run(int trials) {
    while (trials > 0) {
      game.play();
      trials--;
    }
    int wins = game.getWins();
    int losses = game.getLosses();
    double winPercent = 100.0 *wins / (wins + losses);
    System.out.printf("Wins: %d; Losses: %d.%nWinning percentage = %f%%.%n", wins, losses, winPercent);
  }
}
