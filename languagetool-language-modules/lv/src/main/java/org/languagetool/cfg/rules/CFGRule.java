package org.languagetool.cfg.rules;
import org.languagetool.cfg.Symbol;

public abstract class CFGRule implements ICFGRule {

  protected Symbol left;

  protected Symbol right[];

    public CFGRule(String nonTerminal, Symbol[] right) {
      this.left = new Symbol(
        false,
        nonTerminal,
        null
      );
      this.right = right;
    }

    public abstract Symbol matchesRight(Symbol[] s);

    public Symbol matchesRight(Symbol s) {
      Symbol[] sy = new Symbol[]{s};
      return matchesRight(sy);
    }

    public Symbol[] matchesLeft(Symbol[] s) {
      if(s.length != 1) return null;
      Symbol given = s[0];
      if(left.equals(given)) {
        return right;
      }
      return null;
    }

    public Symbol[] matchesLeft(Symbol s) {
      Symbol[] sy = new Symbol[]{s};
      return matchesLeft(sy);
    }



}
