package com.github.dakusui.jcunit8.tests.usecases.parametersource;

import com.github.dakusui.jcunitx.engine.junit4.JCUnit8;
import com.github.dakusui.jcunitx.annotations.ConfigureWith;
import com.github.dakusui.jcunitx.annotations.From;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JCUnit8.class)
@ConfigureWith(parameterSpace = SeparatedParameterSource.class)
public class SeparatedParameterSpaceExample {
  @Test
  public void test(
      @From("a") int a
  ) {
    System.out.println(a);
  }
}
