package com.github.dakusui.lisj;

import com.github.dakusui.jcunit.exceptions.JCUnitCheckedException;

import java.util.LinkedList;
import java.util.List;

public class FormResult {
  private int                          nextPosition;
  private Object                       value;
  private int                          numPositions;
  private List<JCUnitCheckedException> ignoredExceptions;

  public FormResult(int nextPosition, int numPositions, Object value) {
    this.nextPosition = nextPosition;
    this.numPositions = numPositions;
    this.value = value;
    this.ignoredExceptions = new LinkedList<JCUnitCheckedException>();
  }

  public int nextPosition() {
    return nextPosition;
  }

  public void nextPosition(int nextPosition) {
    this.nextPosition = nextPosition;
  }

  public void addIgnoredException(JCUnitCheckedException e) {
    this.ignoredExceptions.add(e);
  }

  public List<JCUnitCheckedException> ignoredExceptions() {
    return this.ignoredExceptions;
  }

  public void clearIgnoredExceptions() {
    this.ignoredExceptions.clear();
  }

  public Object value() {
    return value;
  }

  public void value(Object value) {
    this.value = value;
  }

  public void incrementPosition() {
    this.nextPosition++;
  }

  public int numPositions() {
    return this.numPositions;
  }
}