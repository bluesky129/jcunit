package com.github.dakusui.jcunit8.testutils.testsuitequality;

import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecWithConstraints;

public class CompatFactorSpaceSpecWithConstraints extends FactorSpaceSpecWithConstraints {
  private final String                                       prefix;

  public CompatFactorSpaceSpecWithConstraints(String prefix) {
    this.prefix = prefix;
  }

  @Override
  public String prefix() {
    return prefix;
  }
}
