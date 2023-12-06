package Year2023.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FertilizerPart1 {
    public static Map<SOURCE, DESTINATION> seedToSoilMap = new HashMap<>();
    public static Map<SOURCE, DESTINATION> soilToFertilizerMap = new HashMap<>();
    public static Map<SOURCE, DESTINATION> fertilizerToWaterMap = new HashMap<>();
    public static Map<SOURCE, DESTINATION> waterToLightMap = new HashMap<>();
    public static Map<SOURCE, DESTINATION> lightToTemperatureMap = new HashMap<>();
    //temperature-to-humidity
    public static Map<SOURCE, DESTINATION> temperatureToHumidityMap = new HashMap<>();
    public static Map<SOURCE, DESTINATION> humidityToLocationMap = new HashMap<>();
    //humidity-to-location map

    public static void main(String[] args) throws FileNotFoundException {
        String line = "3766866638";
        //System.out.println("NUM " + Long.parseLong(line));

        String path = "src/Year2023/Day5/input.txt";
        long[] seedsNeedToPlanted = readInInput(path);

        if (seedsNeedToPlanted != null) {
            for (long seed : seedsNeedToPlanted) {
                System.out.print(seed + " ");
            }
            System.out.println();
            // if no source, map it  number as destination.
            long lowestLocation = Long.MAX_VALUE;
            for (long seed : seedsNeedToPlanted) {

                long soil = getDestination(seed, seedToSoilMap);

                long fertilizer = getDestination(soil, soilToFertilizerMap);

                long water = getDestination(fertilizer, fertilizerToWaterMap);

                long light = getDestination(water, waterToLightMap);

                long temp = getDestination(light, lightToTemperatureMap);

                long humidity = getDestination(temp, temperatureToHumidityMap);

                long location = getDestination(humidity, humidityToLocationMap);


//                for (Data seedSource : seedToSoilMap.keySet()) {
//                    long sourceStart = seedSource.start;
//                    long sourceEnd = seedSource.end;
//                    if (sourceStart <= seed && seed <= sourceEnd) {
//                        long range = sourceEnd - sourceStart;
//                        Data soilInfo = seedToSoilMap.get(seedSource);
//                        soilQuantity = soilInfo.start + range;
//                    }
//                }
//
//                if (soilQuantity == 0) {
//                    soilQuantity = seed;
//                }

//                long fertilizerQuantity = 0;
//                for (Data soilSource : soilToFertilizerMap.keySet()) {
//                    long sourceStart = soilSource.start;
//                    long sourceEnd = soilSource.end;
//                    if (sourceStart <= soilQuantity && soilQuantity <= sourceEnd) {
//                        long range = sourceEnd - sourceStart;
//                        Data fertilizerInfo = soilToFertilizerMap.get(soilSource);
//                        fertilizerQuantity = fertilizerInfo.start + range;
//                    }
//                }
//
//                if (fertilizerQuantity == 0) {
//                    fertilizerQuantity = soilQuantity;
//                }


//                long soil = seedToSoilMap.getOrDefault(seed, seed);
//                long fertilizer = soilToFertilizerMap.getOrDefault(soil, soil);
//                long water = fertilizerToWaterMap.getOrDefault(fertilizer, fertilizer);
//                long light = waterToLightMap.getOrDefault(water, water);
//                long temp = lightToTemperatureMap.getOrDefault(light, light);
//                long humidity = temperatureToHumidityMap.getOrDefault(temp, temp);
//                long location = humidityToLocationMap.getOrDefault(humidity, humidity);
//61262491
                lowestLocation = Math.min(lowestLocation, location);

                System.out.println(
                        " Seed: " + seed +
                                " soil: " + soil +
                                " fertilizer " + fertilizer +
                                " water " + water +
                                " light " + light +
                                " temp: " + temp +
                                " humidity: " + humidity +
                                " location: " + location
                );
            }
            System.out.println("lowestLocation: " + lowestLocation);
        }

    }

    public static long[] readInInput(String path) throws FileNotFoundException {
        long[] seedsNeedToPlanted = null;
        File filePath = new File(path);
        Scanner scan = new Scanner(filePath);
        while (scan.hasNextLine()) {

            String line = scan.nextLine();
            // grabs seeds
            if (line.contains("seeds")) {
                // seeds: 79 14 55 13
                seedsNeedToPlanted = Arrays.stream(line.split(":")[1].trim().split("\\s"))
                        .filter(s -> !s.isEmpty())
                        .mapToLong(Long::parseLong)
                        .toArray();
            }

            if (line.startsWith("seed-to-soil map:")) {
                line = scan.nextLine(); // read the next line
                while (!line.isEmpty()) {
                    //  50 98 2
                    long[] destinationSourceRange = Arrays.stream(line.split("\\s")).mapToLong(Long::parseLong).toArray();
                    if (destinationSourceRange.length == 3) {
                        buildSeedToSoilMap(destinationSourceRange);
                    }
                    line = scan.nextLine();
                }
            }
            if (line.contains("water-to-light map:")) {
                line = scan.nextLine(); // read the next line
                while (!line.isEmpty()) {
                    //  50 98 2
                    long[] destinationSourceRange = Arrays.stream(line.split("\\s")).mapToLong(Long::parseLong).toArray();
                    if (destinationSourceRange.length == 3) {
                        buildWaterToLightMap(destinationSourceRange);
                    }
                    line = scan.nextLine();
                }
            }

            // TODO:
            if (line.contains("light-to-temperature map")) {
                line = scan.nextLine(); // read the next line
                while (!line.isEmpty()) {
                    //  50 98 2
                    long[] destinationSourceRange = Arrays.stream(line.split("\\s")).mapToLong(Long::parseLong).toArray();
                    if (destinationSourceRange.length == 3) {
                        buildLightToTemperatureMap(destinationSourceRange);
                    }
                    line = scan.nextLine();
                }
            }

            if (line.contains("temperature-to-humidity map:")) {
                line = scan.nextLine(); // read the next line
                while (!line.isEmpty()) {
                    //  50 98 2
                    long[] destinationSourceRange = Arrays.stream(line.split("\\s")).mapToLong(Long::parseLong).toArray();
                    if (destinationSourceRange.length == 3) {
                        buildTemperatureToHumidityMap(destinationSourceRange);
                    }
                    line = scan.nextLine();
                }
            }

            if (line.contains("soil-to-fertilizer map:")) {
                line = scan.nextLine(); // read the next line
                while (!line.isEmpty()) {
                    //  50 98 2
                    long[] destinationSourceRange = Arrays.stream(line.split("\\s")).mapToLong(Long::parseLong).toArray();
                    if (destinationSourceRange.length == 3) {
                        buildSoilToFertilizerMap(destinationSourceRange);
                    }
                    line = scan.nextLine();
                }
            }

            if (line.contains("fertilizer-to-water map:")) {
                line = scan.nextLine(); // read the next line
                while (!line.isEmpty()) {
                    long[] destinationSourceRange = Arrays.stream(line.split("\\s")).mapToLong(Long::parseLong).toArray();
                    if (destinationSourceRange.length == 3) {
                        buildFertilizerToWaterMap(destinationSourceRange);
                    }
                    line = scan.nextLine();
                }
            }

            // humidity-to-location map
            // TODO :
            if (line.contains("humidity-to-location map")) {
                line = scan.nextLine(); // read the next line
                while (!line.isEmpty()) {
                    //  50 98 2
                    long[] destinationSourceRange = Arrays.stream(line.split("\\s")).mapToLong(Long::parseLong).toArray();
                    if (destinationSourceRange.length == 3) {
                        buildHumidityToLocationMap(destinationSourceRange);
                    }
                    if (!scan.hasNext()) {
                        break;
                    } else {
                        line = scan.nextLine();
                    }

                }
            }

        }
        if (seedsNeedToPlanted != null) {
            return seedsNeedToPlanted;
        } else {
            System.out.println("NO seedsNeedToPlanted found ");
        }
        return null;
    }

    public static long getDestination(long source, Map<SOURCE, DESTINATION> mapping) {
        System.out.println("SOURCE: " + source);
        boolean hasChanged = false;
        long requiredQuantity = 0;

        // Loop through the keys in the mapping
        for (SOURCE seedSource : mapping.keySet()) {
            long sourceStart = seedSource.start;
            long sourceEnd = seedSource.end;
            // Check if the source falls within the range specified by the current seedSource
            if (sourceStart <= source && source <= sourceEnd) {
                // Calculate the complement within the range
                long compliment = source - sourceStart;
                // Retrieve the corresponding destination information from the mapping

                DESTINATION destinationInfo = mapping.get(seedSource);
                long destStart = destinationInfo.start;
                long destEnd = destinationInfo.end;
                // Calculate the required quantity based on the soil information and the complement
                requiredQuantity = destStart + compliment;
                hasChanged = true;
            }
        }


        // If the source was mapped to a destination, return the required quantity, else return the source itself
        if (hasChanged) {
            System.out.println(" DESTINATION: " + requiredQuantity);

            return requiredQuantity;
        }
        System.out.println(" DESTINATION: " + source);

        return source;
    }

    private static void buildSeedToSoilMap(long[] destinationSourceRange) {
        long source = destinationSourceRange[1]; // seed
        long destination = destinationSourceRange[0];  // soil
        long range = destinationSourceRange[2];

        SOURCE sourceData = new SOURCE(source, source + range - 1);

        DESTINATION destinationData = new DESTINATION(destination, destination + range - 1);
        seedToSoilMap.put(sourceData, destinationData); // two first things are for sources,
    }

    private static void buildFertilizerToWaterMap(long[] destinationSourceRange) {
        long source_fertilizer = destinationSourceRange[1]; // seed
        long destination_water = destinationSourceRange[0];  // soil
        long range = destinationSourceRange[2];
        SOURCE sourceData = new SOURCE(source_fertilizer, source_fertilizer + range - 1);
        DESTINATION destinationData = new DESTINATION(destination_water, destination_water + range - 1);
        fertilizerToWaterMap.put(sourceData, destinationData);
    }

    private static void buildHumidityToLocationMap(long[] destinationSourceRange) {
        long source_humidity = destinationSourceRange[1]; // seed
        long destination_location = destinationSourceRange[0];  // soil
        long range = destinationSourceRange[2];

        SOURCE sourceData = new SOURCE(destination_location, source_humidity + range - 1);
        DESTINATION destinationData = new DESTINATION(destination_location, destination_location + range - 1);

        humidityToLocationMap.put(sourceData, destinationData);

    }

    private static void buildTemperatureToHumidityMap(long[] destinationSourceRange) {
        long source_temperature = destinationSourceRange[1]; // seed
        long destination_humidity = destinationSourceRange[0];  // soil
        long range = destinationSourceRange[2];

        SOURCE sourceData = new SOURCE(source_temperature, source_temperature + range - 1);
        DESTINATION destinationData = new DESTINATION(destination_humidity, destination_humidity + range - 1);
        temperatureToHumidityMap.put(sourceData, destinationData);
    }

    private static void buildLightToTemperatureMap(long[] destinationSourceRange) {
        long source_light = destinationSourceRange[1]; // seed
        long destination_temperature = destinationSourceRange[0];  // soil
        long range = destinationSourceRange[2];

        SOURCE sourceData = new SOURCE(source_light, source_light + range - 1);
        DESTINATION destinationData = new DESTINATION(destination_temperature, destination_temperature + range - 1);
        lightToTemperatureMap.put(sourceData, destinationData);
    }

    private static void buildWaterToLightMap(long[] destinationSourceRange) {
        long source_water = destinationSourceRange[1]; // seed
        long destination_light = destinationSourceRange[0];  // soil
        long range = destinationSourceRange[2];

        SOURCE sourceData = new SOURCE(source_water, destination_light + range - 1);
        DESTINATION destinationData = new DESTINATION(destination_light, destination_light + range - 1);
        waterToLightMap.put(sourceData, destinationData);
    }

    private static void buildSoilToFertilizerMap(long[] destinationSourceRange) {
        long source_soil = destinationSourceRange[1]; // seed
        long destination_fertilizer = destinationSourceRange[0];  // soil
        long range = destinationSourceRange[2];
        SOURCE sourceData = new SOURCE(source_soil, source_soil + range - 1);
        DESTINATION destinationData = new DESTINATION(destination_fertilizer, destination_fertilizer + range - 1);
        soilToFertilizerMap.put(sourceData, destinationData);
    }


    static class SOURCE {
        long start;
        long end;

        public SOURCE(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }


    static class DESTINATION {
        long start;
        long end;

        public DESTINATION(long start, long end) {
            this.start = start;
            this.end = end;
        }

    }
}
