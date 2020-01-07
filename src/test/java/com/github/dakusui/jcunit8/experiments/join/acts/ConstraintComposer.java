package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public interface ConstraintComposer extends Function<List<String>, NormalizedConstraint> {
  static ConstraintComposer createConstraintComposer(final String name, final Function<List<String>, NormalizedConstraint> composer) {
    return new ConstraintComposer() {
      @Override
      public String name() {
        return name;
      }

      @Override
      public NormalizedConstraint apply(List<String> factorNames) {
        return composer.apply(factorNames);
      }
    };
  }

  String name();

  default Function<List<String>, NormalizedConstraint>[] toConstraintCreatorFunctions(int numFactors) {
    return Utils.createConstraintCreatorFunctions(this, numFactors);
  }

  enum Utils {
    ;

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
     * @param factorNames        A list of factor names.
     * @param constraintComposer A constraint composer, which creates a constraint
     *                           from a list of factor names
     */
    private static NormalizedConstraint createConstraint(List<String> factorNames, ConstraintComposer constraintComposer) {
      return constraintComposer.apply(factorNames);
    }

    private static Function<List<String>, NormalizedConstraint> createConstraint(int offset, ConstraintComposer constraintComposer) {
      return strings -> createConstraint(strings.subList(offset, offset + 10), constraintComposer);
    }

    static Function<List<String>, NormalizedConstraint>[] createConstraintCreatorFunctions(
        ConstraintComposer constraintComposer,
        int numFactors) {
      List<Function<List<String>, NormalizedConstraint>> constraints = new LinkedList<>();
      for (int i = 0; i < numFactors / 10; i++) {
        constraints.add(createConstraint(i * 10, constraintComposer));
      }
      //noinspection unchecked
      return constraints.toArray((Function<List<String>, NormalizedConstraint>[]) new Function[0]);
    }
  }
}
