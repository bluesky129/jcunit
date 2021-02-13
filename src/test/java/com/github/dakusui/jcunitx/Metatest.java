package com.github.dakusui.jcunitx;

import org.junit.Test;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;


public class Metatest {
  @Test
  public void metatest() {
    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
        .selectors(
            selectPackage("com.github.dakusui"),
            selectClass(AppTest.class)
        )
        .filters(
            includeClassNamePatterns(".*Test")
        )
        .build();

    Launcher launcher = LauncherFactory.create();

    // Register a listener of your choice
    SummaryGeneratingListener listener = new SummaryGeneratingListener();
    launcher.registerTestExecutionListeners(listener);
    try {
      launcher.execute(request);
    } finally {
      TestExecutionSummary summary = listener.getSummary();
      // Do something with the TestExecutionSummary.
      System.out.println(summary);
      summary.getFailures().forEach(System.err::println);
      System.err.println(summary.getTotalFailureCount());
      System.out.println(summary.getTestsFoundCount());
    }
  }
}
