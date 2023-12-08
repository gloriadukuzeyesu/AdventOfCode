package Year2023.Day7;

public class Card implements Comparable<Card> {
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
            case 'J' -> -1;
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
