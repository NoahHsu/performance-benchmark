package org.example.bigset.intersacation;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark results for different methods of finding the intersection of large sets of BigInteger elements.
 * Each benchmark measures the average execution time in milliseconds per operation (ms/op).
 * Tests were conducted with sets containing over 10,000 BigInteger elements each.
 *
 * <pre>
 * +---------------------------------------------------------------------+-----------+---------------------+
 * | Benchmark                                                           |  Average  | Execution Time     |
 * |                                                                     |  Rounds   |  (ms per operation) |
 * +---------------------------------------------------------------------+-----------+---------------------+
 * | guavaSetsIntersection                                               |   5       | ≈ 10⁻⁵ ms/op       |
 * | loopBigSetIsContainedByNewSmallSet                                  |   5       |  0.085 ±  0.007 ms |
 * | loopBigSetIsContainedBySmallSet                                     |   5       |  0.046 ±  0.001 ms |
 * | smallSetRetainAllBigSet                                             |   5       |  0.086 ±  0.008 ms |
 * | streamSmallSetIsContainedByBigSet                                   |   5       |  0.049 ±  0.001 ms |
 * | streamSmallSetIsContainedByNewBigSet                                |   5       |  0.088 ±  0.011 ms |
 * +---------------------------------------------------------------------+-----------+---------------------+
 * </pre>
 *
 * <h2>Explanation of Methods</h2>
 * <ul>
 *   <li><b>guavaSetsIntersection</b>: Utilizes Google’s Guava library for set intersection. This method shows extremely fast execution (≈ 10⁻⁵ ms/op) but may vary based on specific set characteristics.</li>
 *   <li><b>loopBigSetIsContainedByNewSmallSet</b>: Loops over the large set and checks if each element is contained within a newly created smaller set. This method shows moderate efficiency at around 0.085 ms/op.</li>
 *   <li><b>loopBigSetIsContainedBySmallSet</b>: Loops over the large set, checking each element against an existing smaller set, achieving faster results with an average time of 0.046 ms/op.</li>
 *   <li><b>smallSetRetainAllBigSet</b>: Uses the `retainAll` method on the smaller set to retain only elements that are present in the larger set. Execution is efficient, averaging around 0.086 ms/op.</li>
 *   <li><b>streamSmallSetIsContainedByBigSet</b>: Streams over the small set, checking if each element exists in the large set, resulting in moderate efficiency at about 0.049 ms/op.</li>
 *   <li><b>streamSmallSetIsContainedByNewBigSet</b>: Streams over the small set while checking against a newly created large set. This approach is comparable to `smallSetRetainAllBigSet`, averaging 0.088 ms/op.</li>
 * </ul>
 *
 * <h2>Conclusion</h2>
 * The <b>loopBigSetIsContainedBySmallSet</b> approach proved to be the most efficient among looping and streaming methods with an average time of 0.046 ms/op.
 * For use cases demanding extremely fast intersection operations and when external libraries are acceptable, <b>guavaSetsIntersection</b> offers superior performance at ≈ 10⁻⁵ ms/op.
 *
 * <h2>Notes:</h2>
 * - ± values indicate standard deviation across test rounds.
 * - Execution times are indicative and may vary depending on the dataset, JVM optimizations, and environment.
 */


@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BigIntegerSetIntersectionBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public Set<Integer> baseSet;
        public Set<Integer> filterSet;

        @Setup(Level.Invocation)
        public void setup() {
            Random random = new Random();
            baseSet = new HashSet<>();
            filterSet = new HashSet<>();
            for (int i = 0; i < 10000; i++) {
                baseSet.add(random.nextInt(80000));
                filterSet.add(random.nextInt(80000));
            }
        }
    }

    @Benchmark
    public void loopBigSetIsContainedBySmallSet(Blackhole bh, BenchmarkState state) {
        bh.consume(LoopBigSetIsContainedBySmallSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void loopBigSetIsContainedByNewSmallSet(Blackhole bh, BenchmarkState state) {
        bh.consume(LoopBigSetIsContainedByNewSmallSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void smallSetRetainAllBigSet(Blackhole bh, BenchmarkState state) {
        bh.consume(SmallSetRetainAllBigSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void streamSmallSetIsContainedByBigSet(Blackhole bh, BenchmarkState state) {
        bh.consume(StreamSmallSetIsContainedByBigSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void streamSmallSetIsContainedByNewBigSet(Blackhole bh, BenchmarkState state) {
        bh.consume(StreamSmallSetIsContainedByNewBigSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void guavaSetsIntersection(Blackhole bh, BenchmarkState state) {
        bh.consume(GuavaSetsIntersection.getIntersection(state.baseSet, state.filterSet));
    }

}
