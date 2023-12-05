package Year2023.Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScratchcardsPart2 {
    public static List<Card> cardMatching = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/Year2023/Day4/input.txt";
        int sum = readInput(path);
        int totalScratchPads = getTotalScratchPads();
        System.out.println("totalScratchPads: " + totalScratchPads);
    }

    private static int getTotalScratchPads() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Card card : cardMatching) {
            int cardID = card.ID;
            int matches = card.matches; //
            if (!map.containsKey(cardID)) {
                map.put(cardID, 1);
            }
            for (int c = cardID + 1; c <= cardID + matches; c++) {
                map.put(c, map.getOrDefault(c, 1) + map.get(cardID));
            }

        }
        int totalScratchPads = 0;
        for (int card : map.keySet()) {
            totalScratchPads += map.get(card);
        }
        return totalScratchPads;
    }


    public static int readInput(String filePath) throws FileNotFoundException {
        File path = new File(filePath);
        Scanner scan = new Scanner(path);
        int totalSum = 0;
        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            String[] parts = currentLine.split(":");
            int cardId = findId(parts[0]);
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
            int matchingNumbers = 0;
            for (int num : myNumbers) {
                if (winningCounts.containsKey(num) && winningCounts.get(num) > 0) {
                    matchingNumbers++;
                    winningCounts.put(num, winningCounts.get(num) - 1);
                }
            }
            Card currentCard = new Card(cardId, matchingNumbers);
            cardMatching.add(currentCard);
        }
        return totalSum;
    }

    public static int findId(String cardInfo) {
        // Card 12
        Pattern pattern = Pattern.compile("Card\\s+(\\d+)");
        Matcher matcher = pattern.matcher(cardInfo);
        int Id = 0;
        if (matcher.find()) {
            Id = Integer.parseInt(matcher.group(1));
        } else {
            System.out.println("ID not found ");
        }
        return Id;
    }

    static class Card {
        int ID;
        int matches;

        Card(int id, int matches) {
            this.ID = id;
            this.matches = matches;
        }
    }
}
