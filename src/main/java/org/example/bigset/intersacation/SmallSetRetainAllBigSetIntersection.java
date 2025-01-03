package org.example.bigset.intersacation;

import java.util.Set;

// provided by chatGPT (4o), modified by me
public class SmallSetRetainAllBigSetIntersection {

    public static <T> Set<T> getIntersection(Set<T> set1, Set<T> set2) {
        // Determine the smaller set to optimize performance
        Set<T> smallerSet = set1.size() < set2.size() ? set1 : set2;
        Set<T> largerSet = set1.size() < set2.size() ? set2 : set1;

        // Create a new set to store the intersection
        smallerSet.retainAll(largerSet);

        int size = smallerSet.size();

        return smallerSet;
    }

}
