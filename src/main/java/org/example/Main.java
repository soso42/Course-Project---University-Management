package org.example;

import org.example.services.CommandParser;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CommandParser commandParser = new CommandParser();


    public static void main(String[] args) {

        String[] input;

        while (true) {
            System.out.println("Please enter command:");
            input = scanner.nextLine().split("\s");

            if (input[0].equals("exit")) {
                System.out.println("Exiting program...");
                return;
            } else {
                commandParser.parseCommand(input);
            }

        }

    }

}
