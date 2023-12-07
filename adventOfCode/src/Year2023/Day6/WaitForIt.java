package Year2023.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WaitForIt {

    public static void main(String[] args) throws FileNotFoundException {
        int record = 1;
        File file = new File("src/Year2023/Day6/input.txt");
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String timeEntry = scan.nextLine();
            String distanceEntry = scan.nextLine();

            String[] parts = timeEntry.replace("Time:", "").trim().split("\\s+");
            String[] distanceParts = distanceEntry.replace("Distance:", "").trim().split("\\s+");

            int[] time = Arrays.stream(parts).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
            int[] distance = Arrays.stream(distanceParts).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).toArray();
            System.out.println(Arrays.toString(time));
            System.out.println(Arrays.toString(distanceParts));
            for (int i = 0; i < time.length; i++) {
                int count = getCount(time, i, distance);
                record *= count;
            }
        }
        scan.close();
        System.out.println("Record: " + record);

    }


    private static int getCount(int[] time, int i, int[] distance) {
        int currentTime = time[i];
        Map<Integer, Integer> map = new HashMap<>();
        int num = 0;
        int currentDistance = distance[i];
        while (num < currentTime) {
            int x = currentTime - num;
            if (x * num > currentDistance) {
                map.put(x * num, map.getOrDefault(x * num, 0) + 1);
            }
            num++;
        }
        int count = 0;
        for (int key : map.keySet()) {
            count += map.get(key);
        }
        return count;
    }
}
