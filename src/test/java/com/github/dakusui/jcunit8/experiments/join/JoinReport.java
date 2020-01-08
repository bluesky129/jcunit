package com.github.dakusui.jcunit8.experiments.join;

public class JoinReport {
  private final String lhsDesc;
  private final String rhsDesc;
  private final long   time;
  private final int    size;
  private final int    strength;

  public JoinReport(int strength, String lhsDesc, String rhdDesc, int size, long time) {
    this.strength = strength;
    this.lhsDesc = lhsDesc;
    this.rhsDesc = rhdDesc;
    this.size = size;
    this.time = time;
  }

  public static String header() {
    return "t,lhs,rhs,time,size";
  }

  public String toString() {
    return String.format("%s,%s,%s,%s,%s", strength, lhsDesc, rhsDesc, time, size);
  }
}
