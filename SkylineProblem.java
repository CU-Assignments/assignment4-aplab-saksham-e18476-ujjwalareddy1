import java.util.*;

public class SkylineProblem {
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        List<int[]> events = new ArrayList<>();
        
        // Convert buildings into events
        for (int[] building : buildings) {
            events.add(new int[]{building[0], -building[2]}); // Start event
            events.add(new int[]{building[1], building[2]}); // End event
        }

        // Sort events: If x is the same, sort by height (negative heights first)
        events.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0]; // Sort by x-coordinate
            return a[1] - b[1]; // If same x, process starts (-height) before ends (height)
        });

        // TreeMap to store heights with frequency count
        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder());
        heightMap.put(0, 1); // Initial ground level
        
        List<List<Integer>> result = new ArrayList<>();
        int prevMaxHeight = 0;

        // Process events
        for (int[] event : events) {
            int x = event[0], height = event[1];

            if (height < 0) {
                // Start event: Add height to map
                heightMap.put(-height, heightMap.getOrDefault(-height, 0) + 1);
            } else {
                // End event: Remove height from map
                if (heightMap.get(height) == 1) {
                    heightMap.remove(height);
                } else {
                    heightMap.put(height, heightMap.get(height) - 1);
                }
            }

            // Get the current max height
            int currentMaxHeight = heightMap.firstKey();

            if (currentMaxHeight != prevMaxHeight) {
                // Only add to result if height changes
                result.add(Arrays.asList(x, currentMaxHeight));
                prevMaxHeight = currentMaxHeight;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] buildings = {
            {2, 9, 10},
            {3, 7, 15},
            {5, 12, 12},
            {15, 20, 10},
            {19, 24, 8}
        };

        List<List<Integer>> skyline = getSkyline(buildings);
        
        for (List<Integer> point : skyline) {
            System.out.println(point);
        }
    }
}
