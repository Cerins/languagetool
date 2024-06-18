package org.languagetool.cfg.rules;

import org.languagetool.cfg.Symbol;
import org.languagetool.cfg.Token;

public class ConcatCFGRule extends CFGRule {

  private ITagMatcher tm;

  public ConcatCFGRule(
    String nonTerminal,
    String left,
    String right,
    ITagMatcher tm
  ) {
    super(
      nonTerminal,
      new Symbol[]{
        new Symbol(false, left, null),
        new Symbol(false, right, null)
      }
    );
    this.tm = tm;
  }
  public ConcatCFGRule(
    String nonTerminal,
    String left,
    String right
  ) {
    this(nonTerminal, left, right, null);
  }

  @Override
  public Symbol matchesRight(Symbol[] s) {
    if(s.length != 2) return null;
    if(s[0].equals(right[0]) &&
      s[1].equals(right[1]
      )) {
      String resTag = null;
      String aTag = s[0].getTag();
      String bTag = s[1].getTag();
      if(tm != null) {
        if(!tm.matches(aTag, bTag)) {
          return null;
        }
        resTag = tm.parentTag(aTag, bTag);
      }
      Symbol res = new Symbol(
        false,
        left.getValue(),
        resTag
      );
      res.setChildren(s);
      return res;
    }
    return null;
  }

  @Override
  public CFGRuleType getRuleType() {
    return CFGRuleType.TWO_NON_TERMINAL;
  }
}
