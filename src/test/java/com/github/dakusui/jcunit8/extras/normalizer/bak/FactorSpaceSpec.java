package com.github.dakusui.jcunit8.extras.normalizer.bak;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit8.extras.generators.FactorSpaceAdapter;

import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public class FactorSpaceSpec {
  public static final Object DONT_CARE = new Object();
  /**
   * Descending order by the number of levels of factors.
   */
  protected final SortedMap<Integer, Integer> factorSpecs = new TreeMap<>((o1, o2) -> o2 - o1);

  public String createSignature() {
    return this.factorSpecs.keySet().stream()
        .map(k -> format("%s^%s", k, this.factorSpecs.get(k)))
        .collect(joining(","));
  }

  public String toString() {
    return createSignature();
  }

  public int numFactors() {
    return this.factorSpecs.values().stream().reduce(Integer::sum).orElse(0);
  }
}
