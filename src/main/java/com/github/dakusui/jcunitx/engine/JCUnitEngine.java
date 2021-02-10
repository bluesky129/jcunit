package com.github.dakusui.jcunitx.engine;

import org.junit.platform.engine.*;

import java.util.Optional;
import java.util.Set;

public class JCUnitEngine implements TestEngine {
  @Override
  public String getId() {
    return "jcunit";
  }

  @Override
  public TestDescriptor discover(EngineDiscoveryRequest discoveryRequest, UniqueId uniqueId) {
    return new TestDescriptor() {
      @Override
      public UniqueId getUniqueId() {
        return null;
      }

      @Override
      public String getDisplayName() {
        return null;
      }

      @Override
      public Set<TestTag> getTags() {
        return null;
      }

      @Override
      public Optional<TestSource> getSource() {
        return Optional.empty();
      }

      @Override
      public Optional<TestDescriptor> getParent() {
        return Optional.empty();
      }

      @Override
      public void setParent(TestDescriptor parent) {

      }

      @Override
      public Set<? extends TestDescriptor> getChildren() {
        return null;
      }

      @Override
      public void addChild(TestDescriptor descriptor) {

      }

      @Override
      public void removeChild(TestDescriptor descriptor) {

      }

      @Override
      public void removeFromHierarchy() {

      }

      @Override
      public Type getType() {
        return null;
      }

      @Override
      public Optional<? extends TestDescriptor> findByUniqueId(UniqueId uniqueId) {
        return Optional.empty();
      }
    };
  }

  @Override
  public void execute(ExecutionRequest request) {
    System.out.println("hello:" + request);
  }
}
