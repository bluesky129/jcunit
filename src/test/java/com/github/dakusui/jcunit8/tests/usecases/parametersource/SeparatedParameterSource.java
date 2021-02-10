package com.github.dakusui.jcunit8.tests.usecases.parametersource;

import com.github.dakusui.jcunitx.model.Parameter;
import com.github.dakusui.jcunitx.annotations.ParameterSource;

import static java.util.Arrays.asList;

public class SeparatedParameterSource {
  @ParameterSource
  public Parameter.Simple.Factory<Integer> a() {
    return Parameter.Simple.Factory.of(asList(1, 2, 3));
  }
}
