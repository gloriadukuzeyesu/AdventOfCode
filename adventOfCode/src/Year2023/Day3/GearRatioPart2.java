package Year2023.Day3;

import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Year2023.Day3.GearRatiosPart1.isAPartNumber;
import static Year2023.Day3.GearRatiosPart1.readInputAsAGrid;

public class GearRatioPart2 {
    public static Map<int[], List<Integer>> map = new HashMap<>();
    // grid of lists
    public static List<Integer>[][] store = new List[140][140];

    public static void initializeStore() {
        for (int i = 0; i < 140; i++) {
            for (int j = 0; j < 140; j++) {
                store[i][j] = new ArrayList<>();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        initializeStore();

        // 1. read in the grid
        // 2. traverse the grid and find the *
        // 3. verify that the * is adjacent to exactly two part numbers., in four
        // directions including diagonals then that is a gear
        // 4. grab those part numbers
        // multiply them and make a gear ration
        // add the gear ration

        String path = "src/Year2023/Day3/input.txt";
        char[][] grid = readInputAsAGrid(path);
        System.out.println("Done reading in the grid");
        int sum = findGears(grid);
        System.out.println("Sum: " + sum);


    }

    // A gear is any * symbol that is adjacent to exactly two part numbers. ( a number close to *)
    // Its gear ratio is the result of multiplying those two numbers together.
    public static int findGears(char[][] grid) {
        int sumGearRatio = 1;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char currenCurr = grid[r][c];
                if (Character.isDigit(currenCurr)) {
                    StringBuilder num = new StringBuilder();
                    int cStart = c - 1;
                    while (c < cols) {
                        if (Character.isDigit(grid[r][c])) {
                            num.append(grid[r][c]);
                            c++;
                        } else {
                            break;
                        }
                    }
                    int cEnd = c;
                    int wholeDigit = Integer.parseInt(num.toString());



                }
            }

        }
        return sumGearRatio;
    }


    public static int findSumOfAllPartNumbers(String path) throws FileNotFoundException {
        char[][] grid = readInputAsAGrid(path);
        int sum = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char currenCurr = grid[r][c];
                if (Character.isDigit(currenCurr)) {
                    StringBuilder num = new StringBuilder();
                    int cStart = c - 1;
                    while (c < cols) {
                        if (Character.isDigit(grid[r][c])) {
                            num.append(grid[r][c]);
                            c++;
                        } else {
                            break;
                        }
                    }
                    int cEnd = c;
                    int wholeDigit = Integer.parseInt(num.toString());
                    // verify if it is part number
                    if (isAPartNumber(r, cStart, cEnd, grid)) {

                        findIfItIsAdjacentToGear(r, cStart,cEnd, grid);



                        sum += wholeDigit;
                    }
                }
            }
        }
        return sum;
    }

    private static void findIfItIsAdjacentToGear(int r, int cStart, int cEnd, char[][] grid) {

    }


    private static List<Integer> visitNeighbors(int r, int c, char[][] grid) {
        List<Integer> adjacentPartNumbers = new ArrayList<>();
        // corner cases
        if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0) {
            System.out.println("No neighbor ");

            return adjacentPartNumbers;
        }

        // top
        int cStart = c - 1;
        int cEnd = c + 1;
        // Top
        if (cStart >= 0 && cEnd < grid[0].length) {
            for (int col = cStart; col <= cEnd; col++) {
                if (Character.isDigit(grid[r - 1][col])) {
                    int digit = findNumberFromThatRow(r - 1, col, grid);
                    adjacentPartNumbers.add(digit);
                    break;
                }
            }

            // bottom
            for (int col = cStart; col <= cEnd; col++) {
                if (Character.isDigit(grid[r + 1][col])) {
                    int digit = findNumberFromThatRow(r + 1, col, grid);
                    adjacentPartNumbers.add(digit);

                    break;
                }
            }


        }
        // right
        if (Character.isDigit(grid[r][cEnd])) {
            int col = cEnd;
            StringBuilder num = new StringBuilder();
            while (col < grid[0].length) {
                if (Character.isDigit(grid[r][col])) {
                    if (r == 140 || c == 140) {
                        System.out.println("r " + r);
                        System.out.println("c " + c);
                    }

                    num.append(grid[r][col]);
                    col++;
                } else {
                    break;
                }
            }
            int numFromRightOfX = Integer.parseInt(num.toString());
            adjacentPartNumbers.add(numFromRightOfX);

        }

        // left
        if (Character.isDigit(grid[r][cStart])) {
            int col = cStart;
            StringBuilder num = new StringBuilder();
            while (col >= 0) {
                if (Character.isDigit(grid[r][col])) {
                    num.append(grid[r][col]);
                    col--;
                } else {
                    break;
                }
            }
            int numberFromLeftOfX = Integer.parseInt(num.reverse().toString());
            adjacentPartNumbers.add(numberFromLeftOfX);
        }
        System.out.println("Adjacent numbers are : " + adjacentPartNumbers);
        return adjacentPartNumbers;
    }

    private static int findNumberFromThatRow(int row, int col, char[][] grid) {
        StringBuilder leftNum = extractNumber(row, col - 1, -1, grid);
        StringBuilder rightNum = extractNumber(row, col + 1, 1, grid);
        StringBuilder centralNum = new StringBuilder();

        // Extract the central number
        if (Character.isDigit(grid[row][col])) {
            centralNum.append(grid[row][col]);
        }

        // Combine the extracted numbers based on conditions
        if (!centralNum.isEmpty() && !leftNum.isEmpty() && rightNum.isEmpty()) {
            return combineNumbers(leftNum.reverse(), centralNum);
        } else if (!centralNum.isEmpty() && leftNum.isEmpty() && !rightNum.isEmpty()) {
            return combineNumbers(centralNum, rightNum);
        } else if (centralNum.isEmpty() && !leftNum.isEmpty() && !rightNum.isEmpty()) {
            return combineNumbers(leftNum.reverse(), rightNum);
        } else if (!centralNum.isEmpty()) {
            return Integer.parseInt(centralNum.toString());
        } else if (!leftNum.isEmpty()) {
            return Integer.parseInt(leftNum.reverse().toString());
        } else if (!rightNum.isEmpty()) {
            return Integer.parseInt(rightNum.toString());
        }

        // Return 1 if no number is found
        return 1;
    }

    // Extracts a number from the grid based on the specified direction
    private static StringBuilder extractNumber(int row, int startCol, int direction, char[][] grid) {
        StringBuilder extractedNumber = new StringBuilder();
        int col = startCol;

        while (col >= 0 && col < grid[0].length && Character.isDigit(grid[row][col])) {
            extractedNumber.append(grid[row][col]);
            col += direction;
        }

        return extractedNumber;
    }

    // Combines two StringBuilder objects and parses the result to an integer
    private static int combineNumbers(StringBuilder num1, StringBuilder num2) {
        return Integer.parseInt(num1.append(num2).toString());
    }

}
