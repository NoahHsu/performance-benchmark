package org.example.bigset.intersacation;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark results for various methods of finding the intersection of large sets of strings.
 * The table below shows average execution time in milliseconds per operation for each method.
 * Results are derived from testing with sets containing over 10,000 elements each.
 *
 * <pre>
 * +-------------------------------------------------------------+-----------+---------------------+
 * | Benchmark                                                  |  Average  | Execution Time     |
 * |                                                           |  Rounds   |  (ms per operation) |
 * +-------------------------------------------------------------+-----------+---------------------+
 * | guavaSetsIntersection                                      |   5       | ≈ 10⁻⁵ ms/op       |
 * | loopBigSetIsContainedByNewSmallSet                         |   5       |  0.084 ±  0.001 ms |
 * | loopBigSetIsContainedBySmallSet                            |   5       |  0.046 ±  0.001 ms |
 * | smallSetRetainAllBigSet                                    |   5       |  0.085 ±  0.001 ms |
 * | streamSmallSetIsContainedByBigSet                          |   5       |  0.048 ±  0.001 ms |
 * | streamSmallSetIsContainedByNewBigSet                       |   5       |  0.086 ±  0.003 ms |
 * +-------------------------------------------------------------+-----------+---------------------+
 * </pre>
 *
 * <h2>Explanation of Methods</h2>
 * <ul>
 *   <li><b>guavaSetsIntersection</b>: Uses Google's Guava library for set intersection, achieving very fast execution times, but performance may vary depending on the specifics of the sets used.</li>
 *   <li><b>loopBigSetIsContainedByNewSmallSet</b>: Loops over the large set, checking if each element is contained within a newly created smaller set, resulting in average efficiency.</li>
 *   <li><b>loopBigSetIsContainedBySmallSet</b>: Loops over the large set, checking if each element is contained within an existing smaller set, yielding slightly faster results.</li>
 *   <li><b>smallSetRetainAllBigSet</b>: Uses the smaller set's `retainAll` method to retain only elements found in the larger set, achieving moderate performance.</li>
 *   <li><b>streamSmallSetIsContainedByBigSet</b>: Streams over the small set to check if each element is contained in the large set, resulting in moderate execution time.</li>
 *   <li><b>streamSmallSetIsContainedByNewBigSet</b>: Streams over the smaller set and checks if each element is contained within a newly created large set, achieving similar times to `smallSetRetainAllBigSet`.</li>
 * </ul>
 *
 * <h2>Conclusion</h2>
 * The <b>loopBigSetIsContainedBySmallSet</b> approach demonstrated the fastest execution time (0.046 ms per operation) among the looping and streaming approaches. However, for applications where minimal execution time is critical, <b>guavaSetsIntersection</b> might offer superior performance at ≈ 10⁻⁵ ms/op, depending on the circumstances and the availability of the Guava library.
 *
 * <h2>Note:</h2>
 * - ± values indicate the standard deviation in execution time across test rounds.
 * - Results may vary depending on the specific dataset and JVM optimizations.
 */

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BigStringSetIntersectionBenchmark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public Set<String> baseSet;
        public Set<String> filterSet;

        @Setup(Level.Invocation)
        public void setup() {
            baseSet = new HashSet<>();
            filterSet = new HashSet<>();
            for (int i = 0; i < 10000; i++) {
                String uuid = UUID.randomUUID().toString();
                baseSet.add(uuid);
                filterSet.add(UUID.randomUUID().toString());
                if (i % 8 == 0) filterSet.add(uuid);
            }
        }
    }

    @Benchmark
    public void loopBigSetIsContainedBySmallSet(Blackhole bh, BigIntegerSetIntersectionBenchmark.BenchmarkState state) {
        bh.consume(LoopBigSetIsContainedBySmallSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void loopBigSetIsContainedByNewSmallSet(Blackhole bh, BigIntegerSetIntersectionBenchmark.BenchmarkState state) {
        bh.consume(LoopBigSetIsContainedByNewSmallSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void smallSetRetainAllBigSet(Blackhole bh, BigIntegerSetIntersectionBenchmark.BenchmarkState state) {
        bh.consume(SmallSetRetainAllBigSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void streamSmallSetIsContainedByBigSet(Blackhole bh, BigIntegerSetIntersectionBenchmark.BenchmarkState state) {
        bh.consume(StreamSmallSetIsContainedByBigSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void streamSmallSetIsContainedByNewBigSet(Blackhole bh, BigIntegerSetIntersectionBenchmark.BenchmarkState state) {
        bh.consume(StreamSmallSetIsContainedByNewBigSetIntersection.getIntersection(state.baseSet, state.filterSet));
    }

    @Benchmark
    public void guavaSetsIntersection(Blackhole bh, BigIntegerSetIntersectionBenchmark.BenchmarkState state) {
        bh.consume(GuavaSetsIntersection.getIntersection(state.baseSet, state.filterSet));
    }

}
