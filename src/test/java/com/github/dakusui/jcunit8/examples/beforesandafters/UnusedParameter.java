package com.github.dakusui.jcunit8.examples.beforesandafters;

import com.github.dakusui.jcunitx.model.parameter.Parameter;
import com.github.dakusui.jcunitx.engine.helpers.ParameterUtils;
import com.github.dakusui.jcunitx.engine.junit4.JCUnit8;
import com.github.dakusui.jcunitx.annotations.From;
import com.github.dakusui.jcunitx.annotations.ParameterSource;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JCUnit8.class)
public class UnusedParameter {
  @ParameterSource
  public Parameter.Factory used() {
    return ParameterUtils.simple(true, false);
  }

  @ParameterSource
  public Parameter.Factory notReferednced() {
    return ParameterUtils.simple("Z1", "Z2");
  }

  @Test
  public void test(@From("used") boolean parameter) {
    System.out.println(parameter);
  }
}
