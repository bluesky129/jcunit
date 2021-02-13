package com.github.dakusui.jcunit8.tests.bugs;

import com.github.dakusui.jcunitx.model.parameter.Parameter;
import com.github.dakusui.jcunitx.pipeline.Requirement;
import com.github.dakusui.jcunitx.engine.helpers.ParameterUtils;
import com.github.dakusui.jcunitx.engine.junit4.JCUnit8;
import com.github.dakusui.jcunitx.annotations.Condition;
import com.github.dakusui.jcunitx.annotations.compat.ConfigureWith;
import com.github.dakusui.jcunitx.annotations.From;
import com.github.dakusui.jcunitx.annotations.ParameterSource;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JCUnit8.class)
@ConfigureWith(Bug140.ConfigFactory.class)
public class Bug140 {
  public static class ConfigFactory extends com.github.dakusui.jcunitx.pipeline.stages.ConfigFactory.Base {
    public ConfigFactory() {
    }

    protected Requirement defineRequirement(Requirement.Builder defaultValues) {
      return defaultValues.withStrength(3).build();
    }
  }

  @ParameterSource
  public Parameter.Factory a() {
    return ParameterUtils.simple(1, 2);
  }

  @ParameterSource
  public Parameter.Factory b() {
    return ParameterUtils.simple(1, 2);
  }

  @ParameterSource
  public Parameter.Factory c() {
    return ParameterUtils.simple(1, 2);
  }

  @Condition(constraint = true)
  public boolean aIsGreaterThanB(@From("a") int a, @From("b") int b) {
    return a > b;
  }

  @Test
  public void test(@From("a") int a, @From("b") int b, @From("c") int c) {
    System.out.printf("a=%s,b=%s,c=%s%n", a, b, c);
  }
}