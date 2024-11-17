package org.example.bigset.intersacation;

import com.google.common.collect.Sets;

import java.util.Set;

// https://www.geeksforgeeks.org/sets-intersection-function-guava-java/
public class GuavaSetsIntersection {

    public static <T> Set<T> getIntersection(Set<T> set1, Set<T> set2) {
        Set<T> answer;
        if (set1.size() <= set2.size()) {
            answer =  Sets.intersection(set1, set2);
        } else {
            answer = Sets.intersection(set2, set1);
        }

        int size = answer.size();

        return answer;
    }

}
