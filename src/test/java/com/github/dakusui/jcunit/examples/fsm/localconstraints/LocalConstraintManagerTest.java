package com.github.dakusui.jcunit.examples.fsm.localconstraints;


import com.github.dakusui.jcunit.constraint.constraintmanagers.ConstraintManagerBase;
import com.github.dakusui.jcunit.core.FactorField;
import com.github.dakusui.jcunit.core.JCUnit;
import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit.exceptions.UndefinedSymbol;
import com.github.dakusui.jcunit.fsm.*;
import com.github.dakusui.jcunit.fsm.spec.ActionSpec;
import com.github.dakusui.jcunit.fsm.spec.FSMSpec;
import com.github.dakusui.jcunit.fsm.spec.ParametersSpec;
import com.github.dakusui.jcunit.fsm.spec.StateSpec;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JCUnit.class)
public class LocalConstraintManagerTest {
  public enum Spec implements FSMSpec<Object> {
    @StateSpec I {
    };
    @ParametersSpec
    public static final Parameters equals = new Parameters.Builder()
        .add("another", null, new Object(), "HELLO")
        .setConstraintManager(new ConstraintManagerBase() {
          @Override
          public boolean check(Tuple tuple) throws UndefinedSymbol {
            return tuple.get("another") != null;
          }
        })
        .build();

    @ActionSpec
    public Expectation equals(Expectation.Builder b, Object o) {
      return b.valid(I, CoreMatchers.is(false)).build();
    }

    @Override
    public boolean check(Object o) {
      return true;
    }
  }

  @FactorField(levelsProvider = FSMLevelsProvider.class)
  public Story<Object, Spec> primary;

  @Test
  public void test() {
    FSMUtils.performStory(this, "primary", new Object());
  }
}