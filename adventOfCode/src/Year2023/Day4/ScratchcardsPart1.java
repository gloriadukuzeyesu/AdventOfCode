package Year2023.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ScratchcardsPart1 {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/Year2023/Day4/input.txt";
        int sum = readInput(path);
        System.out.println("SUM " + sum);

    }

    public static int readInput(String filePath) throws FileNotFoundException {
        File path = new File(filePath);
        Scanner scan = new Scanner(path);
        int totalSum = 0;
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();

            String[] parts = currentLine.split(":");
            String[] winningNumbersAndMyNumber = parts[1].split("\\|");

            int[] WinningNumbers = Arrays.stream(winningNumbersAndMyNumber[0].trim().split("\\s"))
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int[] myNumbers = Arrays.stream(winningNumbersAndMyNumber[1].split("\\s"))
                    .filter(s -> !s.isEmpty())
                    .mapToInt(Integer::parseInt)
                    .toArray();

            HashMap<Integer, Integer> winningCounts = new HashMap<>();
            for (int num : WinningNumbers) {
                winningCounts.put(num, winningCounts.getOrDefault(num, 0) + 1);
            }

            int countOfMyWinningNumber = 0;
            for (int num : myNumbers) {
                if (winningCounts.containsKey(num) && winningCounts.get(num) > 0) {
                    countOfMyWinningNumber++;
                    winningCounts.put(num, winningCounts.get(num) - 1);
                }
            }

            if (countOfMyWinningNumber <= 1) {
                totalSum += countOfMyWinningNumber;
            } else {
                int points = 1;
                while (countOfMyWinningNumber > 1) {
                    points *= 2;
                    countOfMyWinningNumber--;
                }
                totalSum += points;
            }
        }
        return totalSum;
    }
}
