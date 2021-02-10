package com.github.dakusui.jcunitx.engine.junit5;

import org.junit.platform.engine.EngineDiscoveryRequest;
import org.junit.platform.engine.UniqueId;

public class JCUnitDiscoverer  {
  JCUnitEngineDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
    return new JCUnitEngineDescriptor(uniqueId, discoveryRequest.toString());
  }
}
