import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class GridlandMetro {

    public static class Range implements Comparable<Range> {
        int startInc;
        int endInc;

        public Range(int start, int end) {
            startInc = start;
            endInc = end;
        }

        long distance() {
            return endInc - startInc + 1;
        }

        public int compareTo(Range o) {
            return Integer.valueOf(startInc).compareTo(Integer.valueOf(o.startInc));
        }

        static boolean within(int value, Range range) {
            return (value >= range.startInc && value <= range.endInc);
        }

        boolean canBeMerged(Range other) {
            return within(startInc, other) || within(endInc, other);
        }

        Range merge(Range other) {
            if(within(startInc, other)) {
                return new Range(other.startInc, Math.max(endInc, other.endInc));
            }
            if(within(endInc, other)) {
                return new Range(startInc, Math.max(endInc, other.endInc));
            }
            throw new RuntimeException(String.format("%s cannot be merged with %s", this, other));
        }
    }

    static void add(Map<Integer, List<Range>> tracks, int[][] track, int current) {
        tracks.putIfAbsent(track[current][0], new LinkedList<>());
        List<Range> ranges = tracks.get(track[current][0]);
        ranges.add(new Range(track[current][1], track[current][2]));
    }

    static List<Range> merge(List<Range> orderedRanges) {

        if(orderedRanges.isEmpty() || orderedRanges.size() == 1) {
            return orderedRanges;
        }

        final Range[] rangesArray = orderedRanges.toArray(new Range[0]);
        for(int i = 0, j = i + 1; i < rangesArray.length && j < rangesArray.length;) {
            if(rangesArray[i] != null && rangesArray[j] != null) {
                if(rangesArray[i].canBeMerged(rangesArray[j])) {
                    rangesArray[i] = rangesArray[i].merge(rangesArray[j]);
                    rangesArray[j] = null;
                } else if(rangesArray[j].canBeMerged(rangesArray[i])) {
                    rangesArray[i] = rangesArray[j].merge(rangesArray[i]);
                    rangesArray[j] = null;
                }
                j++;
            } else {
                i++;
                j = i + 1;
            }
        }
        final List<Range> mergedRanges = new LinkedList<>();
        for(int i = 0; i < rangesArray.length; i++) {
            if(rangesArray[i] != null) {
                mergedRanges.add(rangesArray[i]);
            }
        }

        return mergedRanges;
    }

    static long countOccupancy(List<Range> ranges) {
        long occupancy = 0;
        for(Range range : ranges) {
            occupancy += range.distance();
        }
        return occupancy;
    }

    static long gridlandMetro(int n, int m, int k, int[][] track) {
        Map<Integer, List<Range>> tracks = new HashMap<>();
        for(int i = 0; i < k; i++) {
            add(tracks, track, i);
        }
        for(Map.Entry<Integer, List<Range>> entryTrack : tracks.entrySet()) {
            Collections.sort(entryTrack.getValue());
        }
        long totalOccupancy = 0;
        for(Map.Entry<Integer, List<Range>> entryTrack : tracks.entrySet()) {
            totalOccupancy += countOccupancy(merge(entryTrack.getValue()));
        }

        return (long)n * (long)m - totalOccupancy;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[][] track = new int[k][3];
        for(int track_i = 0; track_i < k; track_i++){
            for(int track_j = 0; track_j < 3; track_j++){
                track[track_i][track_j] = in.nextInt();
            }
        }
        long result = gridlandMetro(n, m, k, track);
        System.out.println(result);
        in.close();
    }
}
