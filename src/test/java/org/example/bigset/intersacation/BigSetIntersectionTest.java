package org.example.bigset.intersacation;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BigSetIntersectionTest {

    Set<String> GIVEN_SMALL_SET = Set.of("123", "456", "789");

    Set<String> GIVEN_BIGGER_SET = Set.of("123", "124", "125", "456", "457", "458", "987", "988", "989");

    @Test
    void testLoopBigSetIsContainedBySmallSetIntersection() {
        Set<String> result = LoopBigSetIsContainedBySmallSetIntersection.getIntersection(GIVEN_SMALL_SET, GIVEN_BIGGER_SET);

        assertThat(result).contains("123", "456");
    }

    @Test
    void testSmallSetRetainAllBigSetIntersection() {
        Set<String> result = SmallSetRetainAllBigSetIntersection.getIntersection(GIVEN_SMALL_SET, GIVEN_BIGGER_SET);

        assertThat(result).contains("123", "456");
    }

    @Test
    void testStreamSmallSetIsContainedByBigSetIntersection() {
        Set<String> result = StreamSmallSetIsContainedByBigSetIntersection.getIntersection(GIVEN_SMALL_SET, GIVEN_BIGGER_SET);

        assertThat(result).contains("123", "456");
    }

}
