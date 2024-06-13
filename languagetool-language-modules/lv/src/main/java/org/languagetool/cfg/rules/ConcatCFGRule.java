package org.languagetool.cfg.rules;

import org.languagetool.cfg.Symbol;
import org.languagetool.cfg.Token;

public class ConcatCFGRule extends CFGRule {

  private ITagMatcher tm;

  public ConcatCFGRule(
    String nonTerminal,
    Token left,
    Token right,
    ITagMatcher tm
  ) {
    super(
      nonTerminal,
      new Symbol[]{
        new Symbol(false, left.getValue(), left.getTag()),
        new Symbol(false, right.getValue(), right.getTag())
      }
    );
    this.tm = tm;
  }
  public ConcatCFGRule(
    String nonTerminal,
    Token left,
    Token right
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
      String aTag = right[0].getTag();
      String bTag = right[1].getTag();
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
