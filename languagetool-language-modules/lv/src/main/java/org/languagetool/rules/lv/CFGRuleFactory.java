package org.languagetool.rules.lv;

import org.languagetool.rules.Rule;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CFGRuleFactory {
  private CFGRuleFactory() {}

  public static List<Rule> allCFGRules() {
    return Arrays.asList(
      new CFGConcatMatchPlural()
    );
  }
}
