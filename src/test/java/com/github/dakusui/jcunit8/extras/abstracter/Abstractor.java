package com.github.dakusui.jcunit8.extras.abstracter;

import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecWithConstraints;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public enum Abstractor {
  ;

  static FactorSpace toFactorSpace(FactorSpaceSpecWithConstraints factorSpaceSpec) {
    return null;
  }

  static FactorSpace encode(FactorSpace factorSpace) {
    FactorSpaceSpecWithConstraints ret = new FactorSpaceSpecWithConstraints();
    List<Function<String, Integer>> factorNameEncoders = new ArrayList<>(factorSpace.getFactors().size());
    factorSpace.getFactors().forEach(f -> ret.addFactor(f.getLevels().size()));
    return ret.build();
  }

}
