package org.languagetool.cfg;

import org.junit.Test;
import org.languagetool.cfg.rules.ICFGRule;
import org.languagetool.cfg.rules.TerminalCFGRule;

import static org.junit.Assert.*;

public class CFGSymbolTree {
  @Test
  public void testSymbolTree() {
    ICFGRule rule = new TerminalCFGRule(
      "S",
      new Token("abc", "test1", null)
    );
    Symbol in  = new Symbol(
      true,
      "abc",
      "test",
      5
    );
    Symbol out = rule.matchesRight(in);
    assertTrue(out != null);
    assertEquals(out.getValue(), "S");
    // The parent gets childs tag if its terminal rule
    assertEquals(out.getTag(), "test");
    Symbol[] childs = out.getChildren();
    assertEquals(childs.length, 1);
    Symbol child = childs[0];
    assertEquals(child.getTag(), "test");
    assertEquals(child.getValue(), "abc");
    assertEquals(child.getInitialPosition(), new Integer(5));
    assertEquals(child.getChildren(), null);



  }

}
