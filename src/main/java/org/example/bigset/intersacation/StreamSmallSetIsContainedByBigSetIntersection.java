package org.example.bigset.intersacation;

import java.util.Set;
import java.util.stream.Collectors;

// provided by https://stackoverflow.com/questions/2851938/efficiently-finding-the-intersection-of-a-variable-number-of-sets-of-strings
public class StreamSmallSetIsContainedByBigSetIntersection {

    public static <T> Set<T> getIntersection(Set<T> set1, Set<T> set2) {
        Set<T> smallerSet = set1.size() < set2.size() ? set1 : set2;
        Set<T> largerSet = set1.size() < set2.size() ? set2 : set1;
        return smallerSet.stream()
                .filter(largerSet::contains)
                .collect(Collectors.toSet());
    }

}
