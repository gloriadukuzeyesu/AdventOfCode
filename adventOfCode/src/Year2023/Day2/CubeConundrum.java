package Year2023.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CubeConundrum {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "src/Year2023/Day2/input.txt";
        int sum = sumOfGameIDs(filePath);
        System.out.println("Sum of IDs: " + sum);
        int sumOfPower = sumOfPower(filePath);
        System.out.println("Sum of Power: " + sumOfPower);
    }

    // Part 1
    public static int sumOfGameIDs(String pathName) throws FileNotFoundException {
        File filePath = new File(pathName);
        Scanner myReader = new Scanner(filePath);
        int sumOfIdPossibleGames = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] sol = line.split(":");
            int gameID = Integer.parseInt(sol[0].split(" ")[1]);
            // find all sets of this gameID and verify if each color in each set is valid.
            String[] gameSets = sol[1].split(";");
            boolean possible = true;
            for (String set : gameSets) {
                int blueTotal = 0, redTotal = 0, greenTotal = 0;
                Map<String, Integer> mapColorCountOfCubes = new HashMap<>();
                String[] sets = set.split(",\\s*");
                for (String colorAndCube : sets) {
                    String[] splitPart = colorAndCube.trim().split("\\s");
                    int numCubes = Integer.parseInt(splitPart[0].trim());
                    String color = splitPart[1].trim();
                    mapColorCountOfCubes.put(color, mapColorCountOfCubes.getOrDefault(color, 0) + numCubes);
                }
                for (String color : mapColorCountOfCubes.keySet()) {
                    switch (color) {
                        case "red" -> redTotal += mapColorCountOfCubes.get(color);
                        case "blue" -> blueTotal += mapColorCountOfCubes.get(color);
                        case "green" -> greenTotal += mapColorCountOfCubes.get(color);
                    }
                }
                // if this set has colors that are out of bounds then this whole gameID is not possible.
                if (blueTotal > 14 || redTotal > 12 || greenTotal > 13) possible = false;
            }
            if (possible) sumOfIdPossibleGames += gameID;
        }
        myReader.close();
        //System.out.println("sumOfIdPossibleGames: " + sumOfIdPossibleGames);
        return sumOfIdPossibleGames;

    }

    // Part 2
    public static int sumOfPower(String pathName) throws FileNotFoundException {
        File filePath = new File(pathName);
        Scanner myReader = new Scanner(filePath);
        int sumOfPower = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] parts = line.split(":");

            // Extract game sets from the game
            String[] gameSets = parts[1].split(";");
            int maxBlueCubes = 1, maxRedCubes = 1, maxGreenCubes = 1;

            // for each set, find the max cube for each color.
            for (String set : gameSets) {
                String[] sets = set.split(",\\s*");

                for (String colorAndCube : sets) {
                    String[] splitPart = colorAndCube.trim().split("\\s");
                    int numOfCubes = Integer.parseInt(splitPart[0].trim());
                    String color = splitPart[1].trim();
                    switch (color) {
                        case "red" -> maxRedCubes = Math.max(maxRedCubes, numOfCubes);
                        case "blue" -> maxBlueCubes = Math.max(maxBlueCubes, numOfCubes);
                        case "green" -> maxGreenCubes = Math.max(maxGreenCubes, numOfCubes);
                    }
                }
            }
            int power = maxBlueCubes * maxRedCubes * maxGreenCubes;
            sumOfPower += power;
        }
        myReader.close();
        return sumOfPower;
    }
}
