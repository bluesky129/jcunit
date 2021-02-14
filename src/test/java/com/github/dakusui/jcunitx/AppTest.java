package com.github.dakusui.jcunitx;

import com.github.dakusui.jcunitx.annotations.CombinatorialTest;
import org.junit.Test;

public class AppTest {
  @Test
  public void test1() {
    System.out.println("hello!");
  }
  @CombinatorialTest
  public void test2() {
    App.main();
  }
}
