package org.example.bigset.intersacation;

import java.util.HashSet;
import java.util.Set;

// provided by chatGPT (4o)
public class SmallSetRetainAllBigSetIntersection {

    public static <T> Set<T> getIntersection(Set<T> set1, Set<T> set2) {
        // Determine the smaller set to optimize performance
        Set<T> smallerSet = set1.size() < set2.size() ? set1 : set2;
        Set<T> largerSet = set1.size() < set2.size() ? set2 : set1;

        // Create a new set to store the intersection
        Set<T> intersection = new HashSet<>(smallerSet);
        intersection.retainAll(largerSet);

        return intersection;
    }

}
