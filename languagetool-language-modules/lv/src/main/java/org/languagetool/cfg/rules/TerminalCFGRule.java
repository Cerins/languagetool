package org.languagetool.cfg.rules;

import org.languagetool.cfg.Symbol;
import org.languagetool.cfg.Token;

public class TerminalCFGRule extends CFGRule {
  public TerminalCFGRule(String nonTerminal, Token terminal) {
    super(
      nonTerminal,
      new Symbol[]{new Symbol(true, terminal.getValue(), terminal.getTag())}
    );
  }
  @Override
  public Symbol matchesRight(Symbol[] s) {
    if(s.length != 1) return null;
    if(s[0].equals(right[0])) {
      Symbol res = new Symbol(false, left.getValue(), left.getTag());
      return res;
    }
    return null;
  }

  @Override
  public CFGRuleType getRuleType() {
    return CFGRuleType.ONE_TERMINAL;
  }
}
