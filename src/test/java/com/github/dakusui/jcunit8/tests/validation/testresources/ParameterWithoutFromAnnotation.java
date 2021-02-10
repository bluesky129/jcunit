package com.github.dakusui.jcunit8.tests.validation.testresources;

import com.github.dakusui.jcunitx.model.Parameter.Simple;
import com.github.dakusui.jcunitx.engine.junit4.JCUnit8;
import com.github.dakusui.jcunitx.annotations.ParameterSource;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;

@RunWith(JCUnit8.class)
public class ParameterWithoutFromAnnotation {
  @ParameterSource
  public Simple.Factory<Integer> a() {
    return Simple.Factory.of(asList(0, 1));
  }

  @SuppressWarnings("unused")
  @Test
  public void testMethod(
      int a // Used in test
  ) {
  }
}
