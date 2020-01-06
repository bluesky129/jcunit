package com.github.dakusui.jcunit8.extras.generators;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit.core.utils.StringUtils;
import com.github.dakusui.jcunit8.experiments.join.acts.ActsExperimentsBase;
import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecForExperiments;
import com.github.dakusui.jcunit8.extras.normalizer.compat.NormalizedConstraint;
import com.github.dakusui.jcunit8.factorspace.Constraint;
import com.github.dakusui.jcunit8.factorspace.Factor;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecForExperiments;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CoveringArrayGenerationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.github.dakusui.jcunit.core.utils.ProcessStreamerUtils.streamFile;
import static java.lang.String.format;

public enum ActsUtils {
  ;
  private static Logger LOGGER = LoggerFactory.getLogger(ActsUtils.class);

  static String buildActsModel(FactorSpace factorSpace, String systemName) {
    StringBuilder b = new StringBuilder();
    b.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    b.append("<System name=\"").append(systemName).append("\">\n");
    FactorSpaceAdapter factorSpaceAdapter = new FactorSpaceAdapter(factorSpace);
    renderParameters(b, 1, factorSpaceAdapter);
    renderConstraints(b, 1, factorSpaceAdapter, factorSpace.getConstraints());
    b.append("\n");
    b.append("</System>");

    return b.toString();
  }

  private static class FactorSpaceAdapter {
    static final Function<Integer, String>                    NAME_RESOLVER =
        (id) -> format("p%d", id);
    final        Function<Integer, String>                    name;
    final        Function<Integer, String>                    type;
    final        Function<Integer, Factor>                    factor;
    final        Function<Integer, Function<Integer, Object>> value;
    final        int                                          numParameters;
    final        Function<String, String>                     factorNameToParameterName;

    private FactorSpaceAdapter(
        Function<Integer, String> name,
        Function<Integer, String> type,
        Function<Integer, Factor> factor,
        Function<Integer, Function<Integer, Object>> value,
        Function<String, Integer> indexOfFactorName,
        int numParameters) {
      this.name = name;
      this.type = type;
      this.factor = factor;
      this.value = value;
      this.factorNameToParameterName = factorName ->
          name.apply(indexOfFactorName.apply(factorName));
      this.numParameters = numParameters;
    }

    FactorSpaceAdapter(FactorSpace factorSpace) {
      this(NAME_RESOLVER,
          (id) -> "0",
          (id) -> factorSpace.getFactors().get(id),
          (ii) -> (j) -> factorSpace.getFactors().get(ii).getLevels().get(j),
          (factorName) -> factorSpace.getFactorNames().indexOf(factorName),
          factorSpace.getFactors().size());
    }
  }

  @SuppressWarnings("SameParameterValue")
  private static void renderParameters(StringBuilder b, int indentLevel, FactorSpaceAdapter factorSpaceAdapter) {
    StringUtils.appendLine(b, indentLevel, "<Parameters>");
    indentLevel++;
    for (int i = 0; i < factorSpaceAdapter.numParameters; i++) {
      indentLevel = renderParameter(
          b,
          indentLevel,
          i,
          factorSpaceAdapter);
    }
    StringUtils.appendLine(b, indentLevel, "</Parameters>");
  }

  /**
   * <ul>
   * <li>type:0: Number and Range</li>
   * <li>type:1: Enum </li>
   * <li>type:2: bool</li>
   * </ul>
   * <pre>
   *   <Parameters>
   *     <Parameter id="2" name="enum1" type="1">
   *       <values>
   *         <value>elem1</value>
   *         <value>elem2</value>
   *       </values>
   *       <basechoices />
   *       <invalidValues />
   *     </Parameter>
   *     <Parameter id="3" name="num1" type="0">
   *       <values>
   *         <value>0</value>
   *         <value>100</value>
   *         <value>123</value>
   *         <value>1000000</value>
   *         <value>2000000000</value>
   *         <value>-2000000000</value>
   *       </values>
   *       <basechoices />
   *       <invalidValues />
   *     </Parameter>
   *     <Parameter id="4" name="bool1" type="2">
   *       <values>
   *         <value>true</value>
   *         <value>false</value>
   *       </values>
   *       <basechoices />
   *       <invalidValues />
   *     </Parameter>
   *     <Parameter id="5" name="range1" type="0">
   *       <values>
   *         <value>0</value>
   *         <value>1</value>
   *         <value>2</value>
   *         <value>3</value>
   *       </values>
   *       <basechoices />
   *       <invalidValues />
   *     </Parameter>
   *   </Parameters>
   * </pre>
   *
   * @param b           A string builder with which a given parameter is rendered.
   * @param indentLevel A current indentation level.
   * @param parameterId An identifier of a given parameter as {@code parameter}.
   * @param parameter   A parameter to be rendered.
   * @return The indentation level after the given parameter is rendered.
   */
  private static int renderParameter(StringBuilder b, int indentLevel, int parameterId, FactorSpaceAdapter parameter) {
    b.append(StringUtils.indent(indentLevel))
        .append("<Parameter id=\"").append(parameterId).append("\" name=\"")
        .append(parameter.name.apply(parameterId))
        .append("\" type=\"")
        .append(parameter.type.apply(parameterId))
        .append("\">")
        .append(StringUtils.newLine());
    indentLevel++;
    StringUtils.appendLine(b, indentLevel, "<values>");
    Factor factor = parameter.factor.apply(parameterId);
    indentLevel++;
    for (int j = 0; j < factor.getLevels().size(); j++) {
      b.append(StringUtils.indent(indentLevel)).append("<value>").append(parameter.value.apply(parameterId).apply(j)).append("</value>").append(StringUtils.newLine());
    }
    indentLevel--;
    StringUtils.appendLine(b, indentLevel, "</values>");
    StringUtils.appendLine(b, indentLevel, "<basechoices />");
    StringUtils.appendLine(b, indentLevel, "<invalidValues />");
    indentLevel--;
    StringUtils.appendLine(b, indentLevel, "</Parameter>\n");
    return indentLevel;
  }

  static String fromCsvToXml(Stream<String> in, int strength) {
    StringBuilder b = new StringBuilder();
    AtomicBoolean firstLine = new AtomicBoolean(true);
    b.append(createTestSetElementFromCsv(
        in.filter(s -> !s.startsWith("#"))
            .peek(s -> {
              if (firstLine.get()) {
                b.append(createHeaderElementFromCsv(s));
                firstLine.set(false);
              }
            })
            .filter(s -> !firstLine.get()),
        strength));
    return b.toString();
  }

  static String createTestSetElementFromCsv(Stream<String> in, int strength) {
    AtomicInteger c = new AtomicInteger(0);
    StringBuilder b = new StringBuilder()
        .append("  ")
        .append(format("<Testset doi=\"%s\">%n", strength));
    in.forEach(
        s -> {
          b.append("    ")
              .append(format("<Testcase TCNo=\"%s\">%n", c.getAndIncrement()));
          Arrays.stream(s.split(",")).forEach(
              t -> b.append("      ")
                  .append("<Value>")
                  .append(t)
                  .append("</Value>")
                  .append(format("%n"))
          );
          b.append("    ");
          b.append("</Testcase>");
          b.append(format("%n"));
        }
    );
    b.append("  ");
    b.append("</Testset>");
    b.append(format("%n"));
    return b.toString();
  }

  static String createHeaderElementFromCsv(String in) {
    StringBuilder b = new StringBuilder();
    b.append("  ")
        .append("<Header>")
        .append("<Value/>")
        .append(format("%n"));
    Arrays.stream(in.split(",")).forEach(
        s -> b.append("    ")
            .append("<Value>")
            .append(s)
            .append("</Value>")
            .append(format("%n"))
    );
    b.append("  ")
        .append("</Header>")
        .append(format("%n"));
    return b.toString();
  }

  /**
   * <pre>
   *     <Constraints>
   *     <Constraint text="(num1 &gt;= 100 &amp;&amp; bool1) &amp;&amp; (enum1 == &quot;elem2&quot;)"><Parameters>
   *       <Parameter name="bool1"/>
   *       <Parameter name="num1"/>
   *       <Parameter name="enum1"/>
   *     </Parameters></Constraint>
   *   </Constraints>
   * </pre>
   * <pre>
   *     <Constraints>
   *       <Constraint text="l01 &lt;= l02 || l03 &lt;= l04 || l05 &lt;= l06 || l07&lt;= l08 || l09 &lt;= l02">
   *       <Parameters>
   *         <Parameter name="l01" />
   *         <Parameter name="l02" />
   *         <Parameter name="l03" />
   *         <Parameter name="l04" />
   *         <Parameter name="l05" />
   *         <Parameter name="l06" />
   *         <Parameter name="l07" />
   *         <Parameter name="l08" />
   *         <Parameter name="l09" />
   *         <Parameter name="l02" />
   *       </Parameters>
   *     </Constraint>
   *   </Constraints>
   * </pre>
   */
  @SuppressWarnings("SameParameterValue")
  private static void renderConstraints(StringBuilder b, int indentLevel, FactorSpaceAdapter factorSpaceAdapter, List<Constraint> constraints) {
    if (constraints.isEmpty())
      return;
    StringUtils.appendLine(b, indentLevel, "<Constraints>");
    indentLevel++;
    for (Constraint each : constraints) {
      if (!(each instanceof ActsPredicate))
        throw new UnsupportedOperationException();
      StringUtils.appendLine(b, indentLevel,
          format("<Constraint text=\"%s\">",
              ((ActsPredicate) each).toText(factorSpaceAdapter.factorNameToParameterName)));
      StringUtils.appendLine(b, indentLevel, "<Parameters>");
      indentLevel++;
      for (String eachFactorName : each.involvedKeys())
        StringUtils.appendLine(b,
            indentLevel,
            format("<Parameter name=\"%s\"/>",
                factorSpaceAdapter.factorNameToParameterName.apply(eachFactorName)));
      indentLevel--;
      StringUtils.appendLine(b, indentLevel, "</Parameters>");
      StringUtils.appendLine(b, indentLevel, "</Constraint>");
    }
    indentLevel--;
    StringUtils.appendLine(b, indentLevel, "</Constraints>");
  }


  @SafeVarargs
  public static void generateAndReport(File baseDir, int numLevels, int numFactors, int strength, ActsExperimentsBase.TestSpec.CHandler chandler, Function<List<String>, NormalizedConstraint>... constraints) {
    CoveringArrayGenerationUtils.StopWatch stopWatch = new CoveringArrayGenerationUtils.StopWatch();
    List<Tuple> generated;
    generated = generateWithActs(baseDir, numLevels, numFactors, strength, chandler, constraints);
    System.out.println("model=" + numLevels + "^" + numFactors + " t=" + strength + " size=" + generated.size() + " time=" + stopWatch.get() + "[msec]");
  }

  @SafeVarargs
  public static List<Tuple> generateWithActs(File baseDir, int numLevels, int numFactors, int strength, ActsExperimentsBase.TestSpec.CHandler chandler, Function<List<String>, NormalizedConstraint>... constraints) {
    FactorSpaceSpecForExperiments factorSpaceSpec = new CompatFactorSpaceSpecForExperiments("L").addFactors(numLevels, numFactors);
    for (Function<List<String>, NormalizedConstraint> each : constraints)
      factorSpaceSpec = factorSpaceSpec.addConstraint(each);
    FactorSpace factorSpace = factorSpaceSpec.build();
    Acts.generateWithActs(
        baseDir,
        factorSpace,
        strength,
        chandler);
    List<Tuple> ret = new LinkedList<>();
    try (Stream<String> data = streamFile(Acts.outFile(baseDir)).peek(LOGGER::trace)) {
      ret.addAll(Acts.readTestSuiteFromCsv(data));
    }
    return ret;
  }

}
