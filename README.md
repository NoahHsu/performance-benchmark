Some performance comparison for different scenario in Java

## How to set different benchmark mode

find the ./src/jmh/java/{package-name}/XxxBenchmark.java

change the value inside @BenchmarkMode

e.g. Throughput, AverageTime, SampleTime, SingleShotTime, All

## How to run

```shell
 ./gradlew jmh
```
