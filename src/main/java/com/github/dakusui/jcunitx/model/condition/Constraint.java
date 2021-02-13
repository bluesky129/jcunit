package com.github.dakusui.jcunitx.model.condition;

import com.github.dakusui.jcunitx.core.tuples.Tuple;
import com.github.dakusui.jcunitx.core.Utils;
import com.github.dakusui.jcunitx.model.TestPredicate;

import java.util.List;
import java.util.function.Predicate;

import static com.github.dakusui.jcunitx.core.Utils.project;
import static java.util.Arrays.asList;

public interface Constraint extends TestPredicate {
  static Constraint create(String name, Predicate<Tuple> predicate, List<String> args) {
    return new Constraint() {
      @Override
      public String getName() {
        return name;
      }

      @Override
      public boolean test(Tuple tuple) {
        return predicate.test(project(args, tuple));
      }

      @Override
      public List<String> involvedKeys() {
        return args;
      }

      @Override
      public String toString() {
        return String.format("%s:%s", Utils.className(predicate.getClass()), args);
      }
    };
  }

  static Constraint create(String name, Predicate<Tuple> predicate, String... args) {
    return create(name, predicate, asList(args));
  }
}
