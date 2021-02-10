package com.github.dakusui.jcunitx.engine;

import org.junit.platform.engine.*;

public class JCUnitEngine implements TestEngine {
  @Override
  public String getId() {
    return "jcunit";
  }

  @Override
  public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
    return (new JCUnitDiscoverer()).discover(discoveryRequest, uniqueId);
  }

  @Override
  public void execute(ExecutionRequest request) {
    System.out.println("hello:" + request);
  }
}
