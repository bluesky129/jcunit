package com.github.dakusui.jcunit8.extras.normalizer.compat;

import com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer;
import com.github.dakusui.jcunit8.extras.normalizer.bak.FactorSpaceSpec;
import com.github.dakusui.jcunit8.factorspace.Factor;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.dakusui.jcunit.core.utils.Checks.checkcond;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FactorSpaceSpecWithConstraints extends FactorSpaceSpec {
  ConstraintComposer constraintComposer = null;

  public String createSignature() {
    String ret = super.createSignature();
    if (constraintComposer != null)
      ret = ret + "-" + constraintSetName().orElseThrow(RuntimeException::new);
    return ret;
  }

  private Optional<String> constraintSetName() {
    return Optional.ofNullable(constraintComposer).map(ConstraintComposer::name);
  }

  public FactorSpaceSpecWithConstraints addFactors(int numLevels, int numFactors) {
    FactorSpaceSpecWithConstraints ret = this;
    for (int i = 0; i < numFactors; i++)
      ret = this.addFactor(numLevels);
    return ret;
  }

  public FactorSpaceSpecWithConstraints addFactor(int numLevels) {
    this.factorSpecs.putIfAbsent(numLevels, 0);
    this.factorSpecs.put(numLevels, factorSpecs.get(numLevels) + 1);
    return this;
  }

  public FactorSpaceSpecWithConstraints constraintComposer(ConstraintComposer constraintComposer) {
    this.constraintComposer = constraintComposer;
    return this;
  }

  public int firstFactorIndexOf(int numLevel) {
    checkcond(factorSpecs.containsKey(numLevel));
    AtomicInteger c = new AtomicInteger(0);
    factorSpecs.keySet().stream()
        .filter(i -> i > numLevel)
        .forEach(i -> c.accumulateAndGet(factorSpecs.get(i), Integer::sum));
    return c.get();
  }

  public FactorSpace build() {
    AtomicInteger index = new AtomicInteger(0);
    LinkedList<Factor> factors = new LinkedList<Factor>() {{
      factorSpecs.keySet().stream()
          .flatMap((Integer numLevels) -> IntStream.range(0, factorSpecs.get(numLevels))
              .mapToObj(i -> Factor.create(
                  composeFactorName(index::getAndIncrement),
                  IntStream.range(0, numLevels)
                      .boxed().collect(toList())
                      .toArray())))
          .forEach(this::add);
    }};
    return FactorSpace.create(
        factors,
        this.constraintComposer != null
            ? Stream.of(this.constraintComposer.toConstraintCreatorFunctions(this.numFactors()))
            .map(each -> each.apply(factors.stream().map(Factor::getName).collect(toList())))
            .collect(toList())
            : Collections.emptyList()
    );
  }

  public String prefix() {
    return "p";
  }

  @Override
  public String toString() {
    return this.factorSpecs.keySet().stream()
        .map(k -> format("%s^%s", k, this.factorSpecs.get(k)))
        .collect(joining(" ", format("%s[", this.prefix()), "]"));
  }

  private String composeFactorName(IntSupplier factorId) {
    return format("%s-%02d", prefix(), factorId.getAsInt());
  }

  public Stream<Map.Entry<Integer, Integer>> factorSpecs() {
    return this.factorSpecs.entrySet().stream();
  }
}
