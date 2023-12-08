package Year2023.Day7;

import java.util.HashMap;
import java.util.Map;


public class Hand {
    Card[] cards = new Card[5];
    int bid;

    int rank;

    Hand(String hand, int bid) {
        for (int i = 0; i < hand.length(); i++) {
            cards[i] = new Card(hand.charAt(i));
        }
        this.bid = bid;
    }

    // to put the hands in order of strength
    private int determineHandType() {
        boolean isFiveOfKind = true;
        char firstLabel = cards[0].label;
        for (Card card : cards) {
            if (card.label != firstLabel) {
                isFiveOfKind = false;
                break;
            }
        }
        if (isFiveOfKind) {
            return 7;
        }

        // deterFour of a kind, where four cards have the same
        // label and one card has a different label: AA8AA
        Map<Character, Integer> map = new HashMap<>();
        for (Card card : cards) {
            map.put(card.label, map.getOrDefault(card.label, 0) + 1);
        }
        for (char label : map.keySet()) {
            if (map.get(label) == 4) {
                return 6;
            }
        }

        // full house
        boolean isFullHouse1 = false;
        boolean isPair = false;

        boolean isThreeOfKind = false;

        for (char label : map.keySet()) {
            if (map.get(label) == 3) {
                isFullHouse1 = true;
            }
            if (map.get(label) == 2) {
                isPair = true;
            }
        }

        if (isFullHouse1 && isPair) {
            return 5; // full house
        }

        if (isFullHouse1) {
            // a three of kind
            return 4;
        }
        // check two pair
        int pairCount = 0;
        for (char label : map.keySet()) {
            if (map.get(label) == 2) {
                pairCount++;
            }
        }
        if (pairCount == 2) {
            return 3; // Two pair
        }
        if (pairCount == 1) {
            return 2; // one pair
        }
        // high card
        return 1;
    }

    public int compareStrength(Hand other) {
        int thisType = determineHandType();
        int otherType = other.determineHandType();

        if (thisType !=  otherType) {
            return Integer.compare(thisType, otherType);
        } else {
            // second ordering rule
            Card[] otherCards = other.cards;
            for (int i = 0; i < cards.length; i++) {
                int comparison = cards[i].compareTo(otherCards[i]);
                if (comparison != 0) {
                    return comparison;
                }
            }
        }
        System.out.println("Equal");
        return 0;
    }
}

