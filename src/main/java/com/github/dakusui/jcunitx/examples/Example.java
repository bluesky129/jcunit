package com.github.dakusui.jcunitx.examples;


import com.github.dakusui.jcunitx.annotations.ParameterSource;
import com.github.dakusui.jcunitx.model.parameter.Parameter;

import static java.util.Arrays.asList;

public class Example {
  @ParameterSource
  public static Parameter.Simple.Factory<String> simple() {
    return Parameter.Simple.Factory.of(asList("hello", "world"));
  }
}
