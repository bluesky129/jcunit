package com.github.dakusui.jcunit8.testutils;

import com.github.dakusui.jcunitx.testsuite.RowSet;
import org.hamcrest.Matcher;

import static org.junit.Assert.assertThat;

public enum SchemafulTupleSetUtils {
  ;
  public static void validateSchemafulTupleSet(RowSet tupleSet, Matcher<RowSet> matcher) {
    System.out.println("tupleSet:" + tupleSet.size());
    tupleSet.forEach(System.out::println);
    assertThat(tupleSet, matcher);
  }
}
