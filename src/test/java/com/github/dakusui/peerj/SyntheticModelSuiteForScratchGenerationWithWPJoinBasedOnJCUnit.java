package com.github.dakusui.peerj;

import com.github.dakusui.peerj.model.ConstraintSet;
import com.github.dakusui.peerj.testbases.PeerJBase;
import com.github.dakusui.peerj.testbases.PeerJScratchWithWPJoinBasedOnJCUnit;
import org.junit.Rule;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import java.util.List;
import java.util.stream.Stream;

import static com.github.dakusui.peerj.model.ConstraintSet.*;
import static com.github.dakusui.peerj.testbases.ExperimentBase.ConstraintHandlingMethod.SOLVER;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.Collectors.toList;

@RunWith(Enclosed.class)
public class SyntheticModelSuiteForScratchGenerationWithWPJoinBasedOnJCUnit {
  public static class Sandbox extends PeerJScratchWithWPJoinBasedOnJCUnit {
    public Sandbox(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, SOLVER, 20, 80)
          .stream()
          .filter(each -> each.constraintSet() == BASIC)
          .collect(toList());
    }
  }


  public static class Strength2 extends PeerJScratchWithWPJoinBasedOnJCUnit {
    public Strength2(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, SOLVER, 20, 1000)
          .stream()
          .filter(each -> each.constraintSet() == NONE)
          .collect(toList());
    }
  }

  public static class Strength3 extends PeerJScratchWithWPJoinBasedOnJCUnit {
    public static final int T = 3;

    public Strength3(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(T, SOLVER, 20, 400).
          stream()
          .filter(each -> each.constraintSet() == NONE)
          .collect(toList());
    }
  }

  public static class Strength4 extends PeerJScratchWithWPJoinBasedOnJCUnit {
    private static final int T = 4;

    public Strength4(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return Stream.of(
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(60).rank(4).constraintSet(NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(60).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(60).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build())
          .filter(each -> each.constraintSet() == NONE)
          .collect(toList());
    }
  }

  public static class Strength5 extends PeerJScratchWithWPJoinBasedOnJCUnit {
    private static final int T = 5;

    @Rule
    public Timeout timeout = new Timeout(40, MINUTES);

    public Strength5(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return Stream.of(
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build())
          .filter(each -> each.constraintSet() == NONE)
          .collect(toList());
    }
  }

  public static class VSCA_2_3 extends PeerJScratchWithWPJoinBasedOnJCUnit {
    public VSCA_2_3(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, 3, SOLVER, 20, 400);
    }
  }

  public static class VSCA_2_4 extends PeerJScratchWithWPJoinBasedOnJCUnit {
    public VSCA_2_4(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, 4, SOLVER, 20, 180);
    }
  }

  public static class Strength4Degree80 extends PeerJScratchWithWPJoinBasedOnJCUnit {
    private static final int T = 4;

    @Rule
    public Timeout timeout = new Timeout(60, MINUTES);

    public Strength4Degree80(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return Stream.of(
          new Spec.Builder().strength(T).degree(80).rank(4).constraintSet(NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(80).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(80).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build())
          .filter(each -> each.constraintSet() == NONE)
          .collect(toList());
    }
  }

  public static class Debug extends PeerJScratchWithWPJoinBasedOnJCUnit {
    private static final int T = 3;

    @Rule
    public Timeout timeout = new Timeout(60, MINUTES);

    public Debug(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return singletonList(
          new Spec.Builder().strength(T).degree(20).rank(3).constraintSet(NONE).constraintHandlingMethod(SOLVER).build()
      );
    }
  }
}
