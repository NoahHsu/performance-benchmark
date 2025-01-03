package org.example.bigset.intersacation;

import java.util.HashSet;
import java.util.Set;

// provided by Gemini (1.5 Flash) original
public class LoopBigSetIsContainedByNewSmallSetIntersection {

    public static <T> Set<T> getIntersection(Set<T> set1, Set<T> set2) {
        Set<T> smallerSet = set1.size() < set2.size() ? new HashSet<>(set1) : new HashSet<>(set2);
        Set<T> largerSet = set1.size() < set2.size() ? set2 : set1;

        Set<T> intersection = new HashSet<>();
        for (T element : largerSet) {
            if (smallerSet.contains(element)) {
                intersection.add(element);
            }
        }
        int size = intersection.size();

        return intersection;
    }

}
