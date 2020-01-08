package com.github.dakusui.jcunit8.extras.generators;

import com.github.dakusui.jcunit8.factorspace.Factor;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;

import java.util.function.Function;

import static java.lang.String.format;

public class FactorSpaceAdapter {
  public static final Function<Integer, String> NAME_RESOLVER =
      (id) -> format("p%d", id);
  final Function<Integer, String> name;
  final Function<Integer, String> type;
  final Function<Integer, Factor> factor;
  final Function<Integer, Function<Integer, Object>> value;
  final int numParameters;
  final Function<String, String> factorNameToParameterName;

  private FactorSpaceAdapter(
      Function<Integer, String> name,
      Function<Integer, String> type,
      Function<Integer, Factor> factor,
      Function<Integer, Function<Integer, Object>> value,
      Function<String, Integer> indexOfFactorName,
      int numParameters) {
    this.name = name;
    this.type = type;
    this.factor = factor;
    this.value = value;
    this.factorNameToParameterName = factorName ->
        name.apply(indexOfFactorName.apply(factorName));
    this.numParameters = numParameters;
  }

  FactorSpaceAdapter(FactorSpace factorSpace) {
    this(NAME_RESOLVER,
        (id) -> "0",
        (id) -> factorSpace.getFactors().get(id),
        (ii) -> (j) -> factorSpace.getFactors().get(ii).getLevels().get(j),
        (factorName) -> factorSpace.getFactorNames().indexOf(factorName),
        factorSpace.getFactors().size());
  }
}
