package Year2023.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "src/Year2023/Day1/advent_day1.txt";
        int sum = findSum(filePath);
        System.out.println(sum);
        int sum2 = findSumPartTwo(filePath);
        System.out.println(sum2);
    }

    public static int findSum(String fileLocation) throws FileNotFoundException {
        int sum = 0;
        File file = new File(fileLocation);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            char[] chars = line.toCharArray();
            List<Character> listOfAllDigits = new ArrayList<>();
            for (char aChar : chars) {
                if (Character.isDigit(aChar)) {
                    listOfAllDigits.add(aChar);
                }
            }
            // add the first and the last digits in the list.
            int two_digit_number = Character.getNumericValue(listOfAllDigits.get(0)) * 10 + Character.getNumericValue(listOfAllDigits.get(listOfAllDigits.size() - 1));
            sum += two_digit_number;
        }

        return sum;
    }

    public static int findSumPartTwo(String path) throws FileNotFoundException {
        int sum = 0;
        File file = new File(path);
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            sum += sumOnCurrentLine(line);
        }
        return sum;
    }

    public static int sumOnCurrentLine(String line) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                sb.append(line.charAt(i));
            }
            if (line.indexOf("zero") == i || line.lastIndexOf("zero") == i) {
                sb.append("0");
            }
            if (line.indexOf("one") == i || line.lastIndexOf("one") == i) {
                sb.append("1");
            }
            if (line.indexOf("two") == i || line.lastIndexOf("two") == i) {
                sb.append("2");
            }
            if (line.indexOf("three") == i || line.lastIndexOf("three") == i) {
                sb.append("3");
            }
            if (line.indexOf("four") == i || line.lastIndexOf("four") == i) {
                sb.append("4");
            }
            if (line.indexOf("five") == i || line.lastIndexOf("five") == i) {
                sb.append("5");
            }
            if (line.indexOf("six") == i || line.lastIndexOf("six") == i) {
                sb.append("6");
            }
            if (line.indexOf("seven") == i || line.lastIndexOf("seven") == i) {
                sb.append("7");
            }
            if (line.indexOf("eight") == i || line.lastIndexOf("eight") == i) {
                sb.append("8");
            }
            if (line.indexOf("nine") == i || line.lastIndexOf("nine") == i) {
                sb.append("9");
            }
        }
        String digitsOnThisLine = sb.toString();
        return Character.getNumericValue(digitsOnThisLine.charAt(0)) * 10 + Character.getNumericValue(digitsOnThisLine.charAt(digitsOnThisLine.length() - 1));
    }
}
