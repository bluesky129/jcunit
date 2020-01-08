package com.github.dakusui.jcunit8.extras.generators;

import com.github.dakusui.jcunit.core.tuples.Tuple;
import com.github.dakusui.jcunit.core.utils.StringUtils;
import com.github.dakusui.jcunit8.extras.normalizer.compat.FactorSpaceSpecWithConstraints;
import com.github.dakusui.jcunit8.factorspace.Constraint;
import com.github.dakusui.jcunit8.factorspace.Factor;
import com.github.dakusui.jcunit8.factorspace.FactorSpace;
import com.github.dakusui.jcunit8.testutils.testsuitequality.CompatFactorSpaceSpecWithConstraints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public enum ActsUtils {
  ;
  private static Logger LOGGER = LoggerFactory.getLogger(ActsUtils.class);

  static String buildActsModel(FactorSpace factorSpace, String systemName, Consumer<StringBuilder> seedComposer) {
    StringBuilder b = new StringBuilder();
    b.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    b.append("<System name=\"").append(systemName).append("\">\n");
    FactorSpaceAdapter factorSpaceAdapter = new FactorSpaceAdapter(factorSpace);
    renderParameters(b, 1, factorSpaceAdapter);
    renderConstraints(b, 1, factorSpaceAdapter, factorSpace.getConstraints());
    b.append("\n");
    seedComposer.accept(b);
    b.append("</System>");
    return b.toString();
  }

  public static Optional<List<Tuple>> loadPregeneratedCoveringArrayFor(FactorSpaceSpecWithConstraints factorSpaceSpec, int strength, File baseDir) {
    try {
      LOGGER.debug("Loading pre-generated covering array for " + factorSpaceSpec.createSignature() + ":strength=" + strength);
      try {
        return Optional.of(
            loadFrom(fileFor(factorSpaceSpec, strength, baseDir)).stream()
                .map(each -> convert(each, factorSpaceSpec.prefix()))
                .collect(toList()));
      } finally {
        LOGGER.debug("Loaded");
      }
    } catch (IOException | ClassNotFoundException e) {
      LOGGER.debug(
          format("Pregenerated file for '%s' was not available because: %s.",
              factorSpaceSpec.toString(),
              e.getMessage()));
      return Optional.empty();
    }
  }

  public static File fileFor(FactorSpaceSpecWithConstraints factorSpaceSpec, int strength, File baseDir) {
    return new File(new File(baseDir, Objects.toString(strength)), signatureOf(factorSpaceSpec));
  }

  private static String signatureOf(FactorSpaceSpecWithConstraints factorSpaceSpec) {
    return factorSpaceSpec.createSignature();
  }

  @SuppressWarnings("unchecked")
  private static List<Tuple> loadFrom(File file) throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
      return (List<Tuple>) ois.readObject();
    }
  }

  public static Tuple convert(Tuple in, String newPrefix) {
    Tuple.Builder b = Tuple.builder();
    in.forEach((k, v) -> b.put(
        requireArgument(k, key -> key.startsWith("p")).replace("p", newPrefix),
        v
    ));
    return b.build();
  }

  private static <T> T requireArgument(T value, Predicate<T> cond) {
    if (!cond.test(value))
      throw new IllegalArgumentException(format("'%s' must satisfy %s", value, cond));
    return value;
  }

  public static List<Tuple> loadPregeneratedOrGenerateAndSaveCoveringArrayFor(
      FactorSpaceSpecWithConstraints factorSpaceSpec,
      int strength,
      BiFunction<FactorSpace, Integer, List<Tuple>> factory) {
    File baseDir = new File("src/test/resources/pregenerated-cas");
    if (new File(baseDir, Objects.toString(strength)).mkdirs())
      LOGGER.debug(String.format("Directory '%s/%s' was created.", baseDir, strength));
    return loadPregeneratedOrGenerateAndSaveCoveringArrayFor(
        factorSpaceSpec,
        strength,
        baseDir,
        factory);
  }

  private static List<Tuple> loadPregeneratedOrGenerateAndSaveCoveringArrayFor(
      FactorSpaceSpecWithConstraints factorSpaceSpec,
      int strength,
      File baseDir,
      BiFunction<FactorSpace, Integer, List<Tuple>> factory) {
    return loadPregeneratedCoveringArrayFor(factorSpaceSpec, strength, baseDir)
        .orElseGet(() -> {
          CompatFactorSpaceSpecWithConstraints abstractModel = convertToAbstractModel(factorSpaceSpec);
          LOGGER.debug(String.format("Generating a covering array for %s(strength=%s) ...", factorSpaceSpec, strength));
          List<Tuple> ret = factory.apply(abstractModel.build(), strength);
          LOGGER.debug("Generated.");
          LOGGER.debug("Saving...");
          saveTo(fileFor(abstractModel, strength, baseDir), ret);
          LOGGER.debug("Saved.");
          return ret.stream()
              .map(t -> convert(t, factorSpaceSpec.prefix()))
              .collect(toList());
        });
  }

  private static CompatFactorSpaceSpecWithConstraints convertToAbstractModel(FactorSpaceSpecWithConstraints in) {
    CompatFactorSpaceSpecWithConstraints ret = new CompatFactorSpaceSpecWithConstraints("PREFIX");
    in.factorSpecs().forEach(entry -> ret.addFactors(entry.getKey(), entry.getValue()));
    return ret;
  }

  private static void saveTo(File file, List<Tuple> tuples) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      oos.writeObject(tuples);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static List<Tuple> readTestSuiteFromCsv(Stream<String> data) {
    AtomicReference<List<String>> header = new AtomicReference<>();
    return data.filter(s -> !s.startsWith("#"))
        .filter(s -> {
          if (header.get() == null) {
            header.set(asList(s.split(",")));
            return false;
          }
          return true;
        })
        .map(
            s -> {
              List<String> record = asList(s.split(","));
              List<String> h = header.get();
              if (record.size() != h.size()) {
                System.out.println("header:" + h);
                System.out.println("record:" + record);
                throw new IllegalArgumentException("size(header)=" + h.size() + ", size(record)=" + record.size());
              }
              Tuple.Builder b = Tuple.builder();
              for (int i = 0; i < h.size(); i++)
                b.put(h.get(i), record.get(i));
              return b.build();
            }
        )
        .collect(toList());
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

  static String fromTuplesToXml(Tuple headerTuple, List<Tuple> in, int strength) {
    requireArgument(requireNonNull(in), v -> !v.isEmpty());
    requireNonNull(headerTuple);
    requireArgument(strength, v -> v > 0);
    return createTestSetElementFromTuples(headerTuple, in, strength)
        + createHeaderElementFromTuple(headerTuple)
        + "  <Stat-Data>\n"  // A dummy element. Maybe unnecessary.
        + "    <ExecutionTime>1.485</ExecutionTime>\n"
        + "    <MaxDomainSize>4</MaxDomainSize>\n"
        + "    <MinDomainSize>4</MinDomainSize>\n"
        + "    <TotalNoOfCombination>79200</TotalNoOfCombination>\n"
        + "    <TotalNoOfTests>57</TotalNoOfTests>\n"
        + "    <Algorithm>IPOG</Algorithm>\n"
        + "  </Stat-Data>\n";
  }

  static String createTestSetElementFromTuples(Tuple headerTuple, List<Tuple> tuples, int strength) {
    AtomicInteger c = new AtomicInteger(0);
    StringBuilder b = new StringBuilder()
        .append("  ")
        .append(format("<Testset doi=\"%s\">%n", strength));
    tuples.forEach(
        s -> {
          b.append("    ")
              .append(format("<Testcase TCNo=\"%s\">%n", c.getAndIncrement()))
              .append("      ")
              .append(format("<Value>%s</Value>%n", c.get()));
          headerTuple.keySet().stream().sorted().forEach(
              k -> b.append("      ")
                  .append(s.containsKey(k) ? format("<Value>%s</Value>", s.get(k)) : "<Value/>")
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

  static String createHeaderElementFromTuple(Tuple headerTuple) {
    StringBuilder b = new StringBuilder();
    b.append("  ")
        .append("<Header>")
        .append(format("%n"))
        .append("    ")
        .append("<Value/>")
        .append(format("%n"));
    headerTuple.keySet().stream().sorted().forEach(
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
}
