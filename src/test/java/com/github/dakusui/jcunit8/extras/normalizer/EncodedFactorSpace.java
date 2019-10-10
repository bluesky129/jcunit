package com.github.dakusui.jcunit8.extras.normalizer;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;

import java.util.List;
import java.util.stream.Collectors;

public interface EncodedFactorSpace extends FactorSpace {
  default List<Tuple> decode(List<Tuple> testsuite) {
    return testsuite.stream().map(this::decode).collect(Collectors.toList());
  }

  Tuple decode(Tuple testCase);
}
