package com.github.dakusui.jcunit8.experiments.join.acts;

import org.junit.Test;

public class ActsExperimentsStrength3 extends ActsExperimentsBase {
  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors10() {
    executeSession(specBuilder().numFactors(10).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors20() {
    executeSession(specBuilder().numFactors(20).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors30() {
    executeSession(specBuilder().numFactors(30).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors40() {
    executeSession(specBuilder().numFactors(40).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factors50() {
    executeSession(specBuilder().numFactors(50).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor60() {
    executeSession(specBuilder().numFactors(60).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor70() {
    executeSession(specBuilder().numFactors(70).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor80() {
    executeSession(specBuilder().numFactors(80).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor80Levels8() {
    executeSession(specBuilder().numLevels(8).numFactors(80).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor90() {
    executeSession(specBuilder().numFactors(90).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor100() {
    executeSession(specBuilder().numFactors(100).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor110() {
    executeSession(specBuilder().numFactors(110).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor120() {
    executeSession(specBuilder().numFactors(120).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor130() {
    executeSession(specBuilder().numFactors(130).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor140() {
    executeSession(specBuilder().numFactors(140).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor150() {
    executeSession(specBuilder().numFactors(150).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor160() {
    executeSession(specBuilder().numFactors(160).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor170() {
    executeSession(specBuilder().numFactors(170).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor180() {
    executeSession(specBuilder().numFactors(180).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor190() {
    executeSession(specBuilder().numFactors(190).strength(3).build());
  }

  @Test
  public void testGenerateAndReportWithConstraintsWithStrength3Factor200() {
    executeSession(createSpec(200, 3));
  }
}
