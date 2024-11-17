package org.example.bigset.intersacation;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

// provided by https://stackoverflow.com/questions/2851938/efficiently-finding-the-intersection-of-a-variable-number-of-sets-of-strings
public class StreamSmallSetIsContainedByNewBigSetIntersection {

    public static <T> Set<T> getIntersection(Set<T> set1, Set<T> set2) {
        Set<T> smallerSet = set1.size() < set2.size() ? new HashSet<>(set1) : new HashSet<>(set2);
        Set<T> largerSet = set1.size() < set2.size() ? set2 : set1;
        var intersection = smallerSet.stream()
                .filter(largerSet::contains)
                .collect(Collectors.toSet());

        int size = intersection.size();

        return intersection;
    }

}
