package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.dakusui.jcunit.core.utils.Checks.checkcond;
import static com.github.dakusui.jcunit8.extras.generators.ActsUtils.generateAndReport;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

public abstract class ActsUtilsTestBase {
  public Function<List<String>, NormalizedConstraint> createConstraint(int offset) {
    return strings -> createConstraint(strings.subList(offset, offset + 10));
  }

  /**
   * <pre>
   *     <Constraints>
   *       <Constraint text="l01 &lt;= l02 || l03 &lt;= l04 || l05 &lt;= l06 || l07&lt;= l08 || l09 &lt;= l02">
   *       <Parameters>
   *         <Parameter name="l01" />
   *         <Parameter name="l02" />
   *         <Parameter name="l03" />
   *         <Parameter name="l04" />
   *         <Parameter name="l05" />
   *         <Parameter name="l06" />
   *         <Parameter name="l07" />
   *         <Parameter name="l08" />
   *         <Parameter name="l09" />
   *         <Parameter name="l02" />
   *       </Parameters>
   *     </Constraint>
   *   </Constraints>
   * </pre>
   * <pre>
   *   p i,1 > p i,2 ∨ p i,3 > p i,4 ∨ p i,5 > p i,6 ∨ p i,7 > p i,8 ∨ p i,9 > p i,2
   * </pre>
   *
   * @param factorNames A list of factor names.
   */
  public NormalizedConstraint createConstraint(List<String> factorNames) {
    return ActsConstraints.basic(factorNames);
  }

  static NormalizedConstraint or(NormalizedConstraint... constraints) {
    return new NormalizedConstraint() {
      @Override
      public String toText(Function<String, String> factorNameToParameterName) {
        return Arrays.stream(constraints)
            .map(each -> each.toText(factorNameToParameterName))
            .collect(joining(" || "));
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean test(Tuple tuple) {
        for (NormalizedConstraint each : constraints) {
          if (each.test(tuple))
            return true;
        }
        return false;
      }

      @Override
      public List<String> involvedKeys() {
        return Arrays.stream(constraints)
            .flatMap(each -> each.involvedKeys().stream())
            .distinct()
            .collect(Collectors.toList());
      }
    };
  }

  static NormalizedConstraint and(NormalizedConstraint... constraints) {
    return new NormalizedConstraint() {
      @Override
      public String toText(Function<String, String> factorNameToParameterName) {
        return "(" + Arrays.stream(constraints)
            .map(each -> each.toText(factorNameToParameterName))
            .collect(joining(") &amp;&amp; ("))
            + ")";
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException();
      }

      @Override
      public boolean test(Tuple tuple) {
        for (NormalizedConstraint each : constraints) {
          if (!each.test(tuple))
            return false;
        }
        return true;
      }

      @Override
      public List<String> involvedKeys() {
        return Arrays.stream(constraints)
            .flatMap(each -> each.involvedKeys().stream())
            .distinct()
            .collect(Collectors.toList());
      }
    };
  }

  static NormalizedConstraint gt(String f, String g) {
    return new Comp(f, g) {

      @Override
      public String getName() {
        throw new UnsupportedOperationException();
      }
    };
  }

  static NormalizedConstraint ge(String f, String g) {
    return new NormalizedConstraint() {
      @Override
      public String toText(Function<String, String> factorNameNormalizer) {
        ////
        // Since ACTS seems not supporting > (&gt;), invert the comparator.
        return factorNameNormalizer.apply(g) + " &lt;= " + factorNameNormalizer.apply(f);
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException();
      }

      @SuppressWarnings("unchecked")
      @Override
      public boolean test(Tuple tuple) {
        checkcond(tuple.get(f) instanceof Comparable);
        checkcond(tuple.get(g) instanceof Comparable);
        return ((Comparable) f).compareTo(g) >= 0;
      }

      @Override
      public List<String> involvedKeys() {
        return asList(f, g);
      }
    };
  }

  static NormalizedConstraint eq(String f, String g) {
    return new NormalizedConstraint() {
      @Override
      public String toText(Function<String, String> factorNameNormalizer) {
        ////
        // Since ACTS seems not supporting > (&gt;), invert the comparator.
        return factorNameNormalizer.apply(g) + " &lt;= " + factorNameNormalizer.apply(f);
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException();
      }

      @SuppressWarnings("unchecked")
      @Override
      public boolean test(Tuple tuple) {
        checkcond(tuple.get(f) instanceof Comparable);
        checkcond(tuple.get(g) instanceof Comparable);
        return ((Comparable) f).compareTo(g) == 0;
      }

      @Override
      public List<String> involvedKeys() {
        return asList(f, g);
      }
    };
  }

  void generateAndReportWithConstraints(File baseDir, int numFactors, int strength) {
    Function<List<String>, NormalizedConstraint>[] constraintComposers = createConstraintComposers(numFactors);
    generateAndReport(baseDir, 4, numFactors, strength, constraintComposers);
  }

  private Function<List<String>, NormalizedConstraint>[] createConstraintComposers(int numFactors) {
    List<Function<List<String>, NormalizedConstraint>> constraints = new LinkedList<>();
    for (int i = 0; i < numFactors / 10; i++) {
      constraints.add(createConstraint(i * 10));
    }
    //noinspection unchecked
    return constraints.toArray((Function<List<String>, NormalizedConstraint>[]) new Function[0]);
  }

  abstract static class Comp implements NormalizedConstraint {
    private final String g;
    private final String f;

    public Comp(String f, String g) {
      this.g = g;
      this.f = f;
    }

    @Override
    public String toText(Function<String, String> factorNameNormalizer) {
      ////
      // Since ACTS seems not supporting > (&gt;), invert the comparator.
      return toText(factorNameNormalizer.apply(g), factorNameNormalizer.apply(f));
    }

    public String toText(String normalizedFactorNameForG, String normalizedFactorNameForF) {
      return normalizedFactorNameForG + " &lt; " + normalizedFactorNameForF;
    }

    @Override
    public boolean test(Tuple tuple) {
      checkcond(tuple.get(f) instanceof Comparable);
      checkcond(tuple.get(g) instanceof Comparable);
      return compare(f, g);
    }

    @SuppressWarnings("unchecked")
    public static boolean compare(
        @SuppressWarnings("rawtypes") Comparable f,
        @SuppressWarnings("rawtypes") Comparable g) {
      return f.compareTo(g) > 0;
    }

    @Override
    public List<String> involvedKeys() {
      return asList(f, g);
    }
  }
}
