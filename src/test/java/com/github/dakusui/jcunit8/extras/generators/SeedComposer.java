package com.github.dakusui.jcunit8.extras.generators;

import java.util.function.Consumer;

public interface SeedComposer extends Consumer<StringBuilder> {
  String mode();

  static SeedComposer empty() {
    return new SeedComposer() {
      @Override
      public String mode() {
        return "scratch";
      }

      @Override
      public void accept(StringBuilder stringBuilder) {
      }
    };
  }

  static SeedComposer create(Consumer<StringBuilder> seedComposerBody) {
    return new SeedComposer() {
      @Override
      public String mode() {
        return "extend";
      }

      @Override
      public void accept(StringBuilder stringBuilder) {
        seedComposerBody.accept(stringBuilder);
      }
    };
  }
}
