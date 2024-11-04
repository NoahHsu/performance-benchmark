package org.example.bigset.intersacation;

import com.google.common.collect.Sets;

import java.util.Set;

// https://www.geeksforgeeks.org/sets-intersection-function-guava-java/
public class GuavaSetsIntersection {

    public static <T> Set<T> getIntersection(Set<T> set1, Set<T> set2) {
        if (set1.size() <= set2.size()) {
            return Sets.intersection(set1, set2);
        } else {
            return Sets.intersection(set2, set1);
        }
    }

}
