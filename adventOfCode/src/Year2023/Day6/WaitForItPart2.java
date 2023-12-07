package Year2023.Day6;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class WaitForItPart2 {
    public static void main(String[] args) {
        System.out.println("hello");
        long time = 55826490L;
        long dis = 246144110121111L;
        long record = getCount(55826490L, 246144110121111L);
        System.out.println(record);
    }

    private static long getCount(long currentTime, long currentDistance) {

        Map<Long, Long> map = new HashMap<>();
        long num = 0;
        while (num < currentTime) {
            long x = currentTime - num;
            if (x * num > currentDistance) {
                map.put(x * num, map.getOrDefault(x * num, 0L) + 1);
            }
            num++;
        }
        long count = 0;
        for (Long key : map.keySet()) {
            count += map.get(key);
        }
        return count;
    }
}
