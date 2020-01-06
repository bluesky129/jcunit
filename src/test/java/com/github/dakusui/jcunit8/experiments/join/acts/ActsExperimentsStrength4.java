package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsExperimentsStrength4 extends ActsExperimentsBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor10() {
    executeSession(specBuilder().numFactors(10).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor20() {
    executeSession(specBuilder().numFactors(20).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor30() {
    executeSession(specBuilder().numFactors(30).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor40() {
    executeSession(specBuilder().numFactors(40).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor50() {
    executeSession(specBuilder().numFactors(50).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor60() {
    executeSession(specBuilder().numFactors(60).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor70() {
    executeSession(specBuilder().numFactors(70).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor80() {
    executeSession(specBuilder().numFactors(80).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor90() {
    executeSession(specBuilder().numFactors(90).strength(4).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength4Factor100() {
    executeSession(specBuilder().numFactors(100).strength(4).build());
  }
}
