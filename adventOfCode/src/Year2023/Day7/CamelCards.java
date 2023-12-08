package Year2023.Day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CamelCards {

    public static void main(String[] args) throws FileNotFoundException {
        List<Hand> handList = readInput("src/Year2023/Day7/input.txt");

//        for (Hand hand : handList) {
//            Card[] cards = hand.cards;
//            System.out.print("Label:  ");
//            for (Card card : cards) {
//                System.out.print(card.label + "");
//            }
//            System.out.println(" bid: " + hand.bid);
//        }
        System.out.println("Winning: " + calculateWinning(handList));


    }

    public static List<Hand> readInput(String file) throws FileNotFoundException {
        List<Hand> handList = new ArrayList<>();
        File filePath = new File(file);
        Scanner scan = new Scanner(filePath);
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            //32T3K 765
            String labels = line.split("\\s")[0].trim();
            int bidAmount = Integer.parseInt(line.split("\\s")[1].trim());
            Hand currentHand = new Hand(labels, bidAmount);
            handList.add(currentHand);
        }
        scan.close();
        return handList;
    }

    public static int calculateWinning(List<Hand> handList) {
        Collections.sort(handList, (h1, h2) -> h1.compareStrength(h2));
        int totalWinnings = 0;
        for (int i = 0; i < handList.size(); i++) {
            Hand currentHand = handList.get(i);
            currentHand.rank = i + 1; // Assigning rank to the hand after sorting
         //   System.out.println("Hand: " + Arrays.toString(currentHand.cards) + " rank: " +currentHand.rank);
            Card[] cards = currentHand.cards;
            System.out.print("Label:  ");
            for (Card card : cards) {
                System.out.print(card.label + "");
            }
            System.out.print(" rank: " + currentHand.rank + "\n");
            totalWinnings += currentHand.bid * currentHand.rank; // Calculating total winnings
        }



        return totalWinnings;
    }
}
