package com.github.dakusui.jcunitx.testsuite;

import com.github.dakusui.jcunitx.core.tuples.Tuple;
import com.github.dakusui.jcunitx.model.condition.Constraint;

import java.util.List;

public interface TestCase {
  enum Category {
    SEED,
    REGULAR,
    NEGATIVE;

    TestCase createTestCase(Tuple testInput, List<Constraint> violatedConstraints) {
      return new TestCase() {
        @Override
        public Tuple getTestInput() {
          return testInput;
        }

        @Override
        public Category getCategory() {
          return Category.this;
        }

        @Override
        public List<Constraint> violatedConstraints() {
          return violatedConstraints;
        }

        @Override
        public String toString() {
          return String.format("%s:%s:%s", this.getCategory(), this.getTestInput(), violatedConstraints);
        }
      };
    }

  }

  Tuple getTestInput();

  Category getCategory();

  List<Constraint> violatedConstraints();
}
