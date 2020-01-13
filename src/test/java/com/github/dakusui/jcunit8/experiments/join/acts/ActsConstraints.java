package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.dakusui.jcunit.core.utils.Checks.checkcond;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;

public enum ActsConstraints {
  ;

  static NormalizedConstraint doubleBasic(List<String> factorNames) {
    String[] p = factorNames.toArray(new String[0]);
    //noinspection PointlessArithmeticExpression
    return and(
        or(
            ge(p[0], p[1]),
            gt(p[2], p[3]),
            eq(p[4], p[5]),
            gt(p[6], p[7]),
            gt(p[8], p[1])
        ),
        or(
            ge(p[9 - 0], p[9 - 1]),
            gt(p[9 - 2], p[9 - 3]),
            eq(p[9 - 4], p[9 - 5]),
            gt(p[9 - 6], p[9 - 7]),
            gt(p[9 - 8], p[9 - 1])
        )
    );
  }

  static NormalizedConstraint basicPlus(List<String> factorNames) {
    String[] p = factorNames.toArray(new String[0]);
    return and(
        or(
            ge(p[0], p[1]),
            gt(p[2], p[3]),
            eq(p[4], p[5]),
            gt(p[6], p[7]),
            gt(p[8], p[1])
        ),
        gt(p[9], p[0]),
        gt(p[8], p[1]),
        gt(p[7], p[2]),
        gt(p[6], p[3]),
        gt(p[5], p[4])
    );
  }

  static NormalizedConstraint basicPlusPlus(List<String> factorNames) {
    String[] p = factorNames.toArray(new String[0]);
    return and(
        or(
            ge(p[0], p[1]),
            gt(p[2], p[3]),
            eq(p[4], p[5]),
            gt(p[6], p[7]),
            gt(p[8], p[1])
        ),
        or(
            gt(p[9], p[0]),
            gt(p[0], p[1])
        ),
        or(
            gt(p[8], p[1]),
            gt(p[1], p[0])
        ),
        or(
            gt(p[7], p[2]),
            gt(p[2], p[1])
        ),
        or(
            gt(p[6], p[3]),
            gt(p[3], p[2])
        ),
        or(
            gt(p[5], p[4]),
            gt(p[4], p[3])
        )
    );
  }

  static NormalizedConstraint basic(List<String> factorNames) {
    String[] p = factorNames.toArray(new String[0]);
    return or(
        ge(p[0], p[1]),
        gt(p[2], p[3]),
        eq(p[4], p[5]),
        gt(p[6], p[7]),
        gt(p[8], p[1]));
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

  static NormalizedConstraint lt(String f, String g) {
    return new Comp(f, g) {
      public String toText(String normalizedFactorNameForG, String normalizedFactorNameForF) {
        return normalizedFactorNameForG + " &lt; " + normalizedFactorNameForF;
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException();
      }
    };
  }

  static NormalizedConstraint gt(String f, String g) {
    return new Comp(f, g) {
      public String toText(String normalizedFactorNameForG, String normalizedFactorNameForF) {
        return "!(" + normalizedFactorNameForG + " &lt; " + normalizedFactorNameForF + ")";
      }

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
        return "!(" + factorNameNormalizer.apply(g) + " &lt;" + factorNameNormalizer.apply(f) + ")";
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
        return factorNameNormalizer.apply(g) + " == " + factorNameNormalizer.apply(f);
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

  abstract static class Comp implements NormalizedConstraint {
    private final String g;
    private final String f;

    public Comp(String f, String g) {
      this.g = g;
      this.f = f;
    }

    @Override
    public String toText(Function<String, String> factorNameNormalizer) {
      return toText(factorNameNormalizer.apply(g), factorNameNormalizer.apply(f));
    }

    public abstract String toText(String normalizedFactorNameForG, String normalizedFactorNameForF);

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
