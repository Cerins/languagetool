package org.languagetool.cfg.rules;

import org.languagetool.cfg.Symbol;
import org.languagetool.cfg.Token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TerminalCFGRule extends CFGRule {

  private Pattern tagPattern;

  public TerminalCFGRule(String nonTerminal, String terminal, Pattern tagPattern) {
    super(
      nonTerminal,
      new Symbol[]{new Symbol(true, terminal, null, null)}
    );
    this.tagPattern = tagPattern;
  }

  public TerminalCFGRule(String nonTerminal, String terminal) {
    this(nonTerminal, terminal, null);
  }

  private static boolean symbolMatchesPattern(Symbol s, Pattern p) {
    if(s == null) return false;
    String tag = s.getTag();
    if(tag == null) return false;
    Matcher m = p.matcher(tag);
    return m.matches();
  }

  @Override
  public Symbol matchesRight(Symbol[] s) {
    if(s.length != 1) return null;
    if(s[0].equals(right[0])) {
      if(tagPattern != null && !symbolMatchesPattern(s[0], tagPattern)) {
        return null;
      }
      // Inherit tags from given symbol
      // This is needed to get the place
      // TODO change
//      if(right[0] == null) {
//        s[0].setInitialPosition(null);
//      }
      Symbol res = new Symbol(false, left.getValue(), s[0].getTag(), null);
      res.setChildren(s);
      return res;
    }
    return null;
  }

  @Override
  public CFGRuleType getRuleType() {
    return CFGRuleType.ONE_TERMINAL;
  }

}
