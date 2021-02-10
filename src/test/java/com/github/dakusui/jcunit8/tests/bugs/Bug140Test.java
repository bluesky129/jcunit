package com.github.dakusui.jcunit8.tests.bugs;

import org.junit.Test;
import org.junit.runner.Result;

import static com.github.dakusui.crest.Crest.*;
import static org.junit.runner.JUnitCore.runClasses;

public class Bug140Test {
  @Test
  public void reproduceBug410() {
    Result result = runClasses(Bug140.class);
    System.out.println(result.wasSuccessful());
    System.out.println(result.getRunCount());
    assertThat(
        result,
        allOf(
            asBoolean("wasSuccessful").isTrue().$(),
            asInteger("getRunCount").equalTo(2).$()
        )
    );
  }
}
