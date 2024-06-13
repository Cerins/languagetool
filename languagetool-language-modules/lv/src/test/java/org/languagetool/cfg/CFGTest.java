package org.languagetool.cfg;


import org.junit.Test;
import org.languagetool.cfg.rules.ConcatCFGRule;
import org.languagetool.cfg.rules.TerminalCFGRule;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


public class CFGTest {
  @Test
  public void testParseTerminalShouldMatch() {
    CFG cfg = new CFG();
    Token toMatch = new Token("a", null, 0);
    cfg.addRule(
      new TerminalCFGRule(
        CFG.START_SYMBOL,
        toMatch
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
  public void testParseTerminalShouldntMatchNoSentence() {
    CFG cfg = new CFG();
    Token toMatch = new Token("a", null, 0);
    cfg.addRule(
      new TerminalCFGRule(
        CFG.START_SYMBOL,
        toMatch
      )
    );
    List<Token>[] sentence = new List[]{
    };
    ParseResult result = cfg.parse(sentence);
    assertFalse(result.getMatches());
  }

  @Test
  public void testParseTerminalShouldntMatchOtherSymbol() {
    CFG cfg = new CFG();
    Token toMatch = new Token("a", null, 0);
    cfg.addRule(
      new TerminalCFGRule(
        CFG.START_SYMBOL,
        toMatch
      )
    );
    Token other = new Token("b", null, 0);
    List<Token> firstWord = new LinkedList<>();
    firstWord.add(other);
    List<Token>[] sentence = new List[]{
      firstWord
    };
    ParseResult result = cfg.parse(sentence);
    assertFalse(result.getMatches());
  }

  @Test
  public void testParseTerminalShouldntMatchTooManySymbols() {
    CFG cfg = new CFG();
    Token toMatch = new Token("a", null, 0);
    cfg.addRule(
      new TerminalCFGRule(
        CFG.START_SYMBOL,
        toMatch
      )
    );
//    Token other = new Token("b", null);
    List<Token> firstWord = new LinkedList<>();
    firstWord.add(toMatch);
    List<Token> secondWord = new LinkedList<>();
    secondWord.add(toMatch);
    List<Token>[] sentence = new List[]{
      firstWord,
      secondWord
    };
    ParseResult result = cfg.parse(sentence);
    assertFalse(result.getMatches());
  }
  @Test
  public void testParseTerminalShouldMatchConcat() {
    CFG cfg = new CFG();
    Token toMatch = new Token("a", null, 0);
    Token toMatch2 = new Token("b", null, 1);
    cfg.addRule(
      new ConcatCFGRule(
        CFG.START_SYMBOL,
        new Token("A", null, null),
        new Token("B", null, null)
      )
    );
    cfg.addRule(
      new TerminalCFGRule(
        "A",
        toMatch
      )
    );
    cfg.addRule(
      new TerminalCFGRule(
        "B",
        toMatch2
      )
    );
//    Token other = new Token("b", null);
    List<Token> firstWord = new LinkedList<>();
    firstWord.add(toMatch);
    List<Token> secondWord = new LinkedList<>();
    secondWord.add(toMatch2);
    List<Token>[] sentence = new List[]{
      firstWord,
      secondWord
    };
    ParseResult result = cfg.parse(sentence);
    assertTrue(result.getMatches());
  }
  @Test
  public void testParseTerminalShouldntMatchConcatWrongSentence() {
    CFG cfg = new CFG();
    Token toMatch = new Token("a", null, 0);
    Token toMatch2 = new Token("b", null, 1);
    cfg.addRule(
      new ConcatCFGRule(
        CFG.START_SYMBOL,
        new Token("A", null, null),
        new Token("B", null, null)
      )
    );
    cfg.addRule(
      new TerminalCFGRule(
        "A",
        toMatch
      )
    );
    cfg.addRule(
      new TerminalCFGRule(
        "B",
        toMatch2
      )
    );
    List<Token> firstWord = new LinkedList<>();
    firstWord.add(toMatch);
    List<Token> secondWord = new LinkedList<>();
    secondWord.add(toMatch);
    List<Token>[] sentence = new List[]{
      firstWord,
      secondWord
    };
    ParseResult result = cfg.parse(sentence);
    assertFalse(result.getMatches());
  }
}