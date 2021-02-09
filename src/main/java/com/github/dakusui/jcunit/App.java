package com.github.dakusui.jcunit;


import static com.github.dakusui.pcond.Preconditions.require;
import static com.github.dakusui.pcond.functions.Predicates.isNotNull;

public class App {
  public static void main(String... args) {
    require(args, isNotNull());
    System.out.println("Hello, world");
  }
}
