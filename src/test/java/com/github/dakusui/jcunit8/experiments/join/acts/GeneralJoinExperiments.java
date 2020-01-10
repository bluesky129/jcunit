package com.github.dakusui.jcunit8.experiments.join.acts;

import com.github.dakusui.jcunit8.experiments.join.basic.JoinExperimentUtils;
import org.junit.Test;

import static com.github.dakusui.jcunit8.experiments.join.acts.ConstraintComposer.*;

public class GeneralJoinExperiments {
  @Test
  public void exercise() {
    int[] numLevelsArray = new int[] { 4 /*, 6, 8, 10 */ };
    int[] strengthArray = new int[] { 5 /*, 5, 6*/ };
    ConstraintComposer[] constraintComposerArray = { null, basic(), basicPlus(), basicPlusPlus() };
    for (int numLevels : numLevelsArray)
      for (int strength : strengthArray) {
        for (ConstraintComposer constraintModel : constraintComposerArray)
          for (int numFactors = 10; numFactors <= 10; numFactors += 10)
            if (condition(numLevels, strength, constraintModel, numFactors))
              JoinExperimentUtils.exerciseSession(numLevels, numFactors, constraintModel, strength);
      }
  }

  private static boolean condition(int numLevels, int strength, @SuppressWarnings("unused") ConstraintComposer constraintModel, int numFactors) {
    if (numLevels > 4)
      return strength == 2 && numFactors == 10;
    return true;
  }
}
