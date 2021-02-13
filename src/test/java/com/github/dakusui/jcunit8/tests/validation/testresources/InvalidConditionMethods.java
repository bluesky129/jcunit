package com.github.dakusui.jcunit8.tests.validation.testresources;

import com.github.dakusui.jcunitx.engine.junit4.JCUnit8;
import com.github.dakusui.jcunitx.annotations.Condition;
import com.github.dakusui.jcunitx.annotations.From;
import com.github.dakusui.jcunitx.annotations.ParameterSource;
import com.github.dakusui.jcunitx.model.parameter.Parameter;
import org.junit.Test;
import org.junit.runner.RunWith;

import static java.util.Arrays.asList;

@RunWith(JCUnit8.class)
public class InvalidConditionMethods {
  @ParameterSource
  public Parameter.Simple.Factory<Integer> a() {
    return Parameter.Simple.Factory.of(asList(1, 2, 3));
  }

  @Condition
  boolean nonPublic() {
    return false;
  }

  @Condition
  public static boolean staticMethod() {
    return false;
  }

  @Condition
  public int wrongType() {
    return 0;
  }

  @Test
  public void test(@From("a") int a) {
  }
}
