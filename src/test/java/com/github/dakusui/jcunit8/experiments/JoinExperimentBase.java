package com.github.dakusui.jcunit8.experiments;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CoveringArrayGenerationUtils;
import com.github.dakusui.jcunit8.testutils.testsuitequality.FactorSpaceSpec;
import org.junit.AfterClass;
import org.junit.Before;

import java.util.List;

import static com.github.dakusui.jcunit8.testutils.UTUtils.configureStdIOs;
import static com.github.dakusui.jcunit8.testutils.testsuitequality.CoveringArrayGenerationUtils.assertCoveringArray;
import static com.github.dakusui.jcunit8.testutils.testsuitequality.CoveringArrayGenerationUtils.generateWithIpoGplus;

public abstract class JoinExperimentBase {
  private static boolean     initialized    = false;
  private static List<Tuple> lhs            = null;
  private static FactorSpace lhsFactorSpace = null;

  @AfterClass
  public static void afterClass() {
    synchronized (JoinExperimentBase.class) {
      initialized = false;
    }
  }

  @Before
  public void before() {
    synchronized (JoinExperimentRhs2_n.class) {
      if (!initialized) {
        configureStdIOs();
        lhsFactorSpace = lhsFactorSpaceSpec().build();
        lhs = generateWithIpoGplus(lhsFactorSpace, strength());
        assertCoveringArray(lhs, lhsFactorSpace, strength());
        System.out.println(Report.header());
        initialized = true;
      }
    }
  }

  protected abstract int strength();

  protected abstract FactorSpaceSpec lhsFactorSpaceSpec();

  protected void exerciseJoin(FactorSpace rhsFactorSpace, int times) {
    List<Tuple> rhs = generateWithIpoGplus(
        rhsFactorSpace, strength()
    );
    // Do warm-up and validate generated covering array.
    assertCoveringArray(
        exerciseJoin(rhs),
        CoveringArrayGenerationUtils.mergeFactorSpaces(
            lhsFactorSpace,
            rhsFactorSpace
        ),
        strength()
    );
    for (int i = 0; i < times; i++) {
      CoveringArrayGenerationUtils.StopWatch stopWatch = new CoveringArrayGenerationUtils.StopWatch();
      System.out.printf(
          "%s%n",
          new Report(
              Integer.toString(lhsFactorSpace.getFactorNames().size()),
              Integer.toString(rhsFactorSpace.getFactorNames().size()),
              exerciseJoin(rhs).size(),
              stopWatch.get()
          )
      );
    }
  }

  private List<Tuple> exerciseJoin(List<Tuple> rhs) {
    return CoveringArrayGenerationUtils.join(
        lhs,
        rhs,
        strength()
    );
  }

  static class Report {
    private final String lhsDesc;
    private final String rhsDesc;
    private final long   time;
    private final int    size;

    public Report(String lhsDesc, String rhdDesc, int size, long time) {
      this.lhsDesc = lhsDesc;
      this.rhsDesc = rhdDesc;
      this.size = size;
      this.time = time;
    }

    static String header() {
      return "lhs,rhs,time,size";
    }

    public String toString() {
      return String.format("%s,%s,%s,%s", lhsDesc, rhsDesc, time, size);
    }
  }
}
