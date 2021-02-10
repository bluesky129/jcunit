package com.github.dakusui.jcunit8.tests.validation.testresources;

import com.github.dakusui.jcunitx.model.Parameter;
import com.github.dakusui.jcunitx.engine.junit4.JCUnit8;
import com.github.dakusui.jcunitx.annotations.Condition;
import com.github.dakusui.jcunitx.annotations.From;
import com.github.dakusui.jcunitx.annotations.ParameterSource;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;

@RunWith(JCUnit8.class)
public class ConstraintThatNeverBecomesTrue {
  @ParameterSource
  public Parameter.Simple.Factory<Integer> a() {
    return Parameter.Simple.Factory.of(asList(1, 2, 3));
  }

  @Condition(constraint = true)
  public boolean neverTrue(
      @From("a") int a
  ) {
    return false;
  }

  @Test
  public void test(@From("a") int a) {

  }
}
