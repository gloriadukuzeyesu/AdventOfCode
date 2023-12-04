package Year2023.Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GearRatiosPart1 {
    // Gear Ratios
    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/Year2023/Day3/input.txt";
        int sum = findSumOfAllPartNumbers(path);
        System.out.println("sum " + sum);
    }

    /**
     * finds the sum of all the part numbers in the grid.
     *
     * @param path location of the file
     * @return the sum of all part numbers in the file.
     * @throws FileNotFoundException when no file found.
     */
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
                        sum += wholeDigit;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * reads the input and make a grid out of it
     *
     * @param path location where the input file is located
     * @return a grid
     * @throws FileNotFoundException when no file found.
     */
    public static char[][] readInputAsAGrid(String path) throws FileNotFoundException {
        char[][] grid = new char[140][140];
        int index = 0;
        File filePath = new File(path);
        Scanner reader = new Scanner(filePath);
        while (reader.hasNextLine()) {
            char[] row = reader.nextLine().toCharArray();
            grid[index++] = row;

        }
        reader.close();
        return grid;
    }


    /**
     * @param r    row coordinate
     * @param c    column coordinate
     * @param grid current grid
     * @return true is the character at the current coordinate/square is a digit otherwise false.
     */
    public static boolean isSymbol(int r, int c, char[][] grid) {
        // out of bounds
        if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0) {
            return false;
        }
        // if it is neither a dot not a digit then it is a symbol.
        return grid[r][c] != '.' && !Character.isDigit(grid[r][c]);
    }

    /**
     * verify if the current number is a part number
     *
     * @param numRow the index of the row where the number is located
     * @param cStart the column on the left of the number's column
     * @param cEnd   the column after the number's end column
     * @param grid   current grid
     * @return true is the current number is a part number, return false otherwise.
     */
    public static boolean isAPartNumber(int numRow, int cStart, int cEnd, char[][] grid) {
        boolean symbolFound = false;
        // verify top and bottom
        for (int c = cStart; c <= cEnd; c++) {
            if (isSymbol(numRow - 1, c, grid) || isSymbol(numRow + 1, c, grid)) {
                symbolFound = true;
                break;
            }
        }

        // verify left and right
        int rStart = numRow - 1;
        int rEnd = numRow + 1;
        for (int r = rStart; r <= rEnd; r++) {
            if (isSymbol(r, cStart, grid) || isSymbol(r, cEnd, grid)) {
                symbolFound = true;
                break;
            }
        }
        return symbolFound;
    }
}
