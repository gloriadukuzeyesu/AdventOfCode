package Year2023.Day3;

import java.io.FileNotFoundException;
import java.util.*;

import static Year2023.Day3.GearRatiosPart1.isAPartNumber;
import static Year2023.Day3.GearRatiosPart1.readInputAsAGrid;

public class GearRatioPart2 {
    public static Map<String, HashSet<Integer>> map = new HashMap<>(); // map will store the gear and all the part numbers adjacent to it.

    public static void main(String[] args) throws FileNotFoundException {
        String path = "src/Year2023/Day3/input.txt";
        Gear gearInfo = findSumOfAllPartNumbers(path);
        System.out.println("Sum Of Part Numbers: " + gearInfo.SumOfAllPartNumbers);
        System.out.println("Sum Of GearRatios: " + gearInfo.sumOfGearRatios);

    }

    public static Gear findSumOfAllPartNumbers(String path) throws FileNotFoundException {
        char[][] grid = readInputAsAGrid(path);
        int sumofAllPartNumbers = 0;
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
                    if (isAPartNumber(r, cStart, cEnd, grid)) {
                        findIfItIsAdjacentToGear(r, cStart, cEnd, grid, wholeDigit);
                        sumofAllPartNumbers += wholeDigit; // this is for part one
                    }
                }
            }
        }
        return new Gear(sumofAllPartNumbers, findTotalGearRatio());
    }

    public static int findTotalGearRatio() {
        int sumGearRatio = 0;
        for (String key : map.keySet()) {
            Set<Integer> set = map.get(key);
            if (set.size() == 2) {
                int gearProduct = 1;
                for (int x : set) {
                    gearProduct *= x;
                }
                sumGearRatio += gearProduct;
            }
        }
        return sumGearRatio;

    }


    private static void findIfItIsAdjacentToGear(int numRow, int cStart, int cEnd, char[][] grid, int num) {

        for (int c = cStart; c <= cEnd; c++) {
            isAGear(c, grid, num, numRow - 1); // top row of the number
            isAGear(c, grid, num, numRow + 1); // bottom row of the number
        }

        for (int r = numRow - 1; r <= numRow + 1; r++) {
            isAGear(cStart, grid, num, r); // check left
            isAGear(cEnd, grid, num, r); // check right
        }
    }

    private static void isAGear(int col, char[][] grid, int num, int row) {
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0) {
            return;
        }
        if (grid[row][col] == '*') {
            String coordinate = row + " " + col;
            if (map.containsKey(coordinate)) {
                map.get(coordinate).add(num);
            } else {
                HashSet<Integer> set = new HashSet<>();
                set.add(num);
                map.put(coordinate, set);
            }
        }
    }

    private static class Gear {
        public int SumOfAllPartNumbers;
        public int sumOfGearRatios;

        Gear(int SumOfAllPartNumbers, int sumOfGearRatios) {
            this.sumOfGearRatios = sumOfGearRatios;
            this.SumOfAllPartNumbers = SumOfAllPartNumbers;
        }
    }
}


