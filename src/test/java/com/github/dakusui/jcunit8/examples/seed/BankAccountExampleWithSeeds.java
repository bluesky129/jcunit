package com.github.dakusui.jcunit8.examples.seed;

import com.github.dakusui.jcunitx.core.tuples.Tuple;
import com.github.dakusui.jcunit8.examples.bankaccount.BankAccountExample;
import com.github.dakusui.jcunitx.pipeline.Requirement;
import com.github.dakusui.jcunitx.pipeline.stages.ConfigFactory;
import com.github.dakusui.jcunitx.annotations.compat.ConfigureWith;

@ConfigureWith(BankAccountExampleWithSeeds.Config.class)
public class BankAccountExampleWithSeeds extends BankAccountExample {
  public static class Config extends ConfigFactory.Base {
    @Override
    protected Requirement defineRequirement(Requirement.Builder defaultValues) {
      return defaultValues.withNegativeTestGeneration(
          false
      ).addSeed(
         Tuple.builder().putRegex(
              "scenario", "open", "deposit", "withdraw", "getBalance"
          ).put(
              "depositAmount", 300
          ).put(
              "withdrawAmount", 200
          ).put(
              "transferAmount", -1
          ).build()
      ).build();
    }
  }
}
