package org.languagetool.cfg.rules;

import org.languagetool.cfg.Symbol;

public interface ICFGRule {
  Symbol matchesRight(Symbol[] s);

  Symbol matchesRight(Symbol s);

  Symbol[] matchesLeft(Symbol[] s);

  Symbol[] matchesLeft(Symbol s);

  CFGRuleType getRuleType();
}
