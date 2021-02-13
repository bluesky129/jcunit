package com.github.dakusui.jcunit8.tests.validation.testclassesundertest;

import com.github.dakusui.jcunitx.model.parameter.Parameter;
import com.github.dakusui.jcunitx.engine.junit4.JCUnit8;
import com.github.dakusui.jcunitx.annotations.ParameterSource;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;

@RunWith(JCUnit8.class)
public class NoTestMethod {
  @ParameterSource
  public Parameter.Simple.Factory<String> simple() {
    return Parameter.Simple.Factory.of(asList("1", "2"));
  }
}
