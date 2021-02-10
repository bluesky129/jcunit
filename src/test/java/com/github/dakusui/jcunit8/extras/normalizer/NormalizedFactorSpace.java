package com.github.dakusui.jcunit8.extras.normalizer;

import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecForExperiments;
import com.github.dakusui.jcunitx.model.Constraint;
import com.github.dakusui.jcunitx.model.Factor;
import com.github.dakusui.jcunitx.model.FactorSpace;

import java.util.List;

public interface NormalizedFactorSpace extends FactorSpace {
  String signature();

  FactorSpace rawFactorSpace();

  static NormalizedFactorSpace normalize(FactorSpace factorSpace) {
    FactorSpaceSpecForExperiments spec = new FactorSpaceSpecForExperiments();
    for (Factor each : factorSpace.getFactors()) {
      spec.addFactor(each.getLevels().size());
    }
    return new NormalizedFactorSpace() {
      @Override
      public List<Constraint> getConstraints() {
        return null;
      }

      @Override
      public List<Factor> getFactors() {
        return null;
      }

      @Override
      public Factor getFactor(String name) {
        return null;
      }


      public String signature() {
        return null;
      }

      @Override
      public FactorSpace rawFactorSpace() {
        return null;
      }

      @Override
      public String toString() {
        return String.format("%s:%s", NormalizedFactorSpace.class.getSimpleName(), this.signature());
      }

    };
  }
}
