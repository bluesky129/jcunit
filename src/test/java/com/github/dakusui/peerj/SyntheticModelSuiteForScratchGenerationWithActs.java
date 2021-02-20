package com.github.dakusui.peerj;

import com.github.dakusui.peerj.model.ConstraintSet;
import com.github.dakusui.peerj.testbases.PeerJBase;
import com.github.dakusui.peerj.testbases.PeerJScratchWithActs;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.List;

import static com.github.dakusui.peerj.testbases.ExperimentBase.ConstraintHandlingMethod.FORBIDDEN_TUPLES;
import static com.github.dakusui.peerj.testbases.ExperimentBase.ConstraintHandlingMethod.SOLVER;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MINUTES;

@RunWith(Enclosed.class)
public class SyntheticModelSuiteForScratchGenerationWithActs {
  public static class Strength2 extends PeerJScratchWithActs {
    public Strength2(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, SOLVER, 20, 1000);
    }
  }

  /**
   * Constraint Handling by forbidden-tuple mode is not practical for the
   * "Industrial-scale" models because of its poor performance.
   */
  @Ignore
  public static class Strength2ForbiddenTuples extends PeerJScratchWithActs {
    public Strength2ForbiddenTuples(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, FORBIDDEN_TUPLES, 20, 400);
    }
  }

  public static class Strength3 extends PeerJScratchWithActs {
    public static final int T = 3;

    public Strength3(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(T, SOLVER, 20, 400);
    }
  }

  public static class Strength4 extends PeerJScratchWithActs {
    private static final int T = 4;

    public Strength4(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return Arrays.asList(
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(60).rank(4).constraintSet(ConstraintSet.NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(60).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(60).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build()
      );
    }
  }

  public static class Strength4Degree80 extends PeerJScratchWithActs {
    private static final int T = 4;

    @Rule
    public Timeout timeout = new Timeout(60, MINUTES);

    public Strength4Degree80(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return Arrays.asList(
          new Spec.Builder().strength(T).degree(80).rank(4).constraintSet(ConstraintSet.NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(80).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(80).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build()
      );
    }
  }

  public static class Strength5 extends PeerJScratchWithActs {
    private static final int T = 5;

    @Rule
    public Timeout timeout = new Timeout(40, MINUTES);

    public Strength5(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return Arrays.asList(
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.NONE).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.BASIC).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(20).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build(),
          new Spec.Builder().strength(T).degree(40).rank(4).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build()
      );
    }
  }

  public static class VSCA_2_3 extends PeerJScratchWithActs {
    public VSCA_2_3(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, 3, SOLVER, 20, 400);
    }
  }

  public static class VSCA_2_4 extends PeerJScratchWithActs {
    public VSCA_2_4(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, 4, SOLVER, 20, 100);
    }
  }

  public static class VSCA_2_4Cont extends PeerJScratchWithActs {
    public VSCA_2_4Cont(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return PeerJBase.parametersWith(2, 4, SOLVER, 100, 180);
    }
  }

  public static class Debug extends PeerJScratchWithActs {
    private static final int T = 3;

    @Rule
    public Timeout timeout = new Timeout(60, MINUTES);

    public Debug(Spec spec) {
      super(spec);
    }

    @Parameters
    public static List<Spec> parameters() {
      return singletonList(
          new Spec.Builder().strength(T).degree(20).rank(3).constraintSet(ConstraintSet.BASIC_PLUS).constraintHandlingMethod(SOLVER).build()
      );
    }
  }
}