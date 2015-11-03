package com.github.dakusui.jcunit.fsm;

import com.github.dakusui.jcunit.core.Utils;
import com.github.dakusui.jcunit.plugins.constraints.Constraint;

import java.util.Map;

import static com.github.dakusui.jcunit.core.Checks.checkcond;
import static com.github.dakusui.jcunit.core.Checks.checknotnull;
import static com.github.dakusui.jcunit.fsm.StateRouter.Edge;

public class FSMDescription {
  private final FSM<Object>           fsm;
  private final int                   historyLength;
  private final String                name;
  private final Map<Edge, Constraint> constraints;

  public FSM<Object> getFsm() {
    return fsm;
  }

  public int getHistoryLength() {
    return historyLength;
  }

  public String getName() {
    return name;
  }

  public Map<Edge, Constraint> getConstraints() {
    return constraints;
  }

  // FSM
  // + Local constraints
  // + History Length
  public FSMDescription(String name, FSM<Object> fsm, int historyLength, Map<Edge, Constraint> constraints) {
    checkcond(historyLength > 0);
    this.name = checknotnull(name);
    this.fsm = checknotnull(fsm);
    this.historyLength = historyLength;
    this.constraints = Utils.newMap(checknotnull(constraints));
  }
}