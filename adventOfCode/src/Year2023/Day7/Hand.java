package Year2023.Day7;

import java.util.HashMap;
import java.util.Map;

class Card implements Comparable<Card> {
    char label; //A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, or 2.

    public Card(char label) {
        this.label = label;
    }

    // compare two cards based on their strength
    @Override
    public int compareTo(Card otherCard) {
        int otherCardStrength = otherCard.getStrength(otherCard.label);
        int thisValue = this.getStrength(this.label);
        return Integer.compare(thisValue, otherCardStrength);
    }

    public int getStrength(char label) {
        return switch (label) {
            case 'A' -> 12;
            case 'K' -> 11;
            case 'Q' -> 10;
            case 'J' -> 9;
            case 'T' -> 8;
            case '9' -> 7;
            case '8' -> 6;
            case '7' -> 5;
            case '6' -> 4;
            case '5' -> 3;
            case '4' -> 2;
            case '3' -> 1;
            case '2' -> 0;
            default -> {
                System.out.println("Not part of the label");
                yield Character.getNumericValue(label);
            }
        };

    }
}

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

