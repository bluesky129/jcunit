package com.github.dakusui.jcunit8.pipeline.stages;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit8.testsuite.SchemafulTupleSet;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Function;

import static com.github.dakusui.jcunit.core.tuples.TupleUtils.subtupleKeyListsOf;
import static com.github.dakusui.jcunit.core.tuples.TupleUtils.subtuplesOf;
import static java.util.stream.Collectors.toSet;

public enum JoinerUtils {
  ;

  private static final PrintStream DEFAULT_PRINT_STREAM = new PrintStream(new OutputStream() {
    @Override
    public void write(int b) {
    }
  });

  static Tuple connect(Tuple tuple1, Tuple tuple2) {
    return new Tuple.Builder().putAll(tuple1).putAll(tuple2).build();
  }

  static SchemafulTupleSet weakenTo(SchemafulTupleSet in, int strength, Function<SchemafulTupleSet, Function<Integer, Set<Tuple>>> coveredTupletsFinder) {
    SchemafulTupleSet.Builder b = new SchemafulTupleSet.Builder(in.getAttributeNames());
    Stopwatch stopwatch = new Stopwatch();
    Set<Tuple> tupletsToBeCovered = coveredTupletsFinder.apply(in).apply(strength);
    stopwatch.print("Identify tuplets to be covered:" + tupletsToBeCovered.size());
    List<List<String>> keyLists = subtupleKeyListsOf(strength, new TreeSet<>(in.getAttributeNames()));
    stopwatch.print("  keys");
    for (Tuple each : in) {
      int before = tupletsToBeCovered.size();
      tupletsToBeCovered.removeAll(subtuplesOf(each, keyLists));
      if (tupletsToBeCovered.size() < before)
        b.add(each);
      if (tupletsToBeCovered.isEmpty())
        break;
    }
    stopwatch.print("  tuplesetBuilding");
    return b.build();
  }

  static Set<Tuple> tupletsCoveredBy(SchemafulTupleSet in, List<List<String>> keyLists) {
    Stopwatch stopwatch = new Stopwatch();
    try {
      return in.stream()
          .distinct()
          .parallel()
          .map(tuple -> subtuplesOf(tuple, keyLists))
          .flatMap(Collection::stream)
          .collect(toSet());
    } finally {
      stopwatch.print("tupletsCoveredBy");
    }
  }

  static List<Tuple> cartesianProduct(List<Tuple> lhs, List<Tuple> rhs) {
    return new ArrayList<Tuple>(lhs.size() * rhs.size()) {
      {
        for (Tuple l : lhs)
          for (Tuple r : rhs)
            this.add(connect(l, r));
      }
    };
  }

  static class Stopwatch {
    long from = System.nanoTime();
    private final PrintStream ps = DEFAULT_PRINT_STREAM;

    public Stopwatch() {
    }

    long get() {
      long n = System.nanoTime();
      try {
        return n - from;
      } finally {
        from = n;
      }
    }

    void print(String message) {
      print(message, this.ps);
    }

    void print(String message, PrintStream ps) {
      ps.println(message + ": " + get() / 1000000 + "[msec]");
    }
  }
}
