package org.languagetool.cfg.rules;

import org.languagetool.cfg.Symbol;
import org.languagetool.cfg.Token;

public class ConcatCFGRule extends CFGRule {
  public ConcatCFGRule(
    String nonTerminal,
    Token left,
    Token right
  ) {
    super(
      nonTerminal,
      new Symbol[]{
        new Symbol(false, left.getValue(), left.getTag()),
        new Symbol(false, right.getValue(), right.getTag())
      }
    );
  }

  @Override
  public Symbol matchesRight(Symbol[] s) {
    if(s.length != 2) return null;
    if(s[0].equals(right[0]) &&
      s[1].equals(right[1]
      )) {
      Symbol res = new Symbol(
        false,
        left.getValue(),
        left.getTag()
      );
      return res;
    }
    return null;
  }

  @Override
  public CFGRuleType getRuleType() {
    return CFGRuleType.TWO_NON_TERMINAL;
  }
}
