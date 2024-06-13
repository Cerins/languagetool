package org.languagetool.cfg;


import org.junit.Test;
import org.languagetool.cfg.rules.ConcatCFGRule;
import org.languagetool.cfg.rules.ICFGRule;
import org.languagetool.cfg.rules.ITagMatcher;
import org.languagetool.cfg.rules.TerminalCFGRule;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;


public class CFGTagTest {
  @Test
  public void testParseTerminalShouldMatch() {
    CFG cfg = new CFG();
    Token toMatch = new Token("a", "abc", 0);
    cfg.addRule(
      new TerminalCFGRule(
        CFG.START_SYMBOL,
        toMatch,
        Pattern.compile("^a.+")
      )
    );
    List<Token> firstWord = new LinkedList<>();
    firstWord.add(toMatch);
    List<Token>[] sentence = new List[]{
      firstWord
    };
    ParseResult result = cfg.parse(sentence);
    assertTrue(result.getMatches());
  }
  @Test
  public void testParseTerminalShouldntMatch() {
    CFG cfg = new CFG();
    Token toMatch = new Token("a", "abc", 0);
    cfg.addRule(
      new TerminalCFGRule(
        CFG.START_SYMBOL,
        toMatch,
        Pattern.compile("^c.+")
      )
    );
    List<Token> firstWord = new LinkedList<>();
    firstWord.add(toMatch);
    List<Token>[] sentence = new List[]{
      firstWord
    };
    ParseResult result = cfg.parse(sentence);
    assertFalse(result.getMatches());
  }
  @Test
  public void testConcatRuleTag() {
    class TagMatcher implements  ITagMatcher {

      @Override
      public boolean matches(String tag1, String tag2) {
       return "a".equals(tag1) && "b".equals(tag2);
      }

      @Override
      public String parentTag(String tag1, String tag2) {
        return tag1;
      }
    }
    ICFGRule concat = new ConcatCFGRule(
      "S",
      new Token("A", "a", null),
      new Token("B", "b", null),
      new TagMatcher()
    );
    Symbol[] in = new Symbol[]{
      new Symbol(
        false,
        "A",
        "a"
      ),
      new Symbol(
        false,
        "B",
        "b"
      )
    };
    Symbol result = concat.matchesRight(in);
    assertTrue(result != null);
    assertEquals("a", result.getTag());
    assertEquals("S", result.getValue());
  }

  @Test
  public void testConcatRuleTagMismatch() {
    class TagMatcher implements  ITagMatcher {

      @Override
      public boolean matches(String tag1, String tag2) {
        return "a".equals(tag1) && "a".equals(tag2);
      }

      @Override
      public String parentTag(String tag1, String tag2) {
        return tag1;
      }
    }
    ICFGRule concat = new ConcatCFGRule(
      "S",
      new Token("A", "a", null),
      new Token("B", "b", null),
      new TagMatcher()
    );
    Symbol[] in = new Symbol[]{
      new Symbol(
        false,
        "A",
        "a"
      ),
      new Symbol(
        false,
        "B",
        "b"
      )
    };
    Symbol result = concat.matchesRight(in);
    assertTrue(result == null);
//    assertEquals("a", result.getTag());
//    assertEquals("S", result.getValue());
  }

}