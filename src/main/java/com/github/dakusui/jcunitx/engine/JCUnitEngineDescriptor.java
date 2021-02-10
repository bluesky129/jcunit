package com.github.dakusui.jcunitx.engine;

import org.junit.platform.engine.UniqueId;
import org.junit.platform.engine.support.descriptor.EngineDescriptor;

public class JCUnitEngineDescriptor extends EngineDescriptor {
  public JCUnitEngineDescriptor(UniqueId uniqueId, String displayName) {
    super(uniqueId, displayName);
  }
}
