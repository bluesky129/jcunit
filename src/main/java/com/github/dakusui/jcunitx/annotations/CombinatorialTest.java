package com.github.dakusui.jcunitx.annotations;

import com.github.dakusui.jcunitx.engine.junit5.JCUnitExtension;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@TestTemplate
@ExtendWith(JCUnitExtension.class)
public @interface CombinatorialTest {
}
