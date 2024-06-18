package org.languagetool.rules.lv;

import org.languagetool.cfg.CFG;
import org.languagetool.cfg.rules.ConcatCFGRule;
import org.languagetool.cfg.rules.ITagMatcher;
import org.languagetool.cfg.rules.TerminalCFGRule;

public class CFGKursMatch extends BaseCFGRule {

  private class PlMismatch implements ITagMatcher {

    @Override
    public boolean matches(String tag1, String tag2) {
      if(tag1 == null || tag1.length() < 4) return false;
      if(tag2 == null || tag2.length() < 5) return false;
      if(tag1.charAt(0) != 'n' &&  tag2.charAt(1) != 'p') return false;
      char ch1 = tag1.charAt(3);
      char ch2 = tag2.charAt(4);
      return ch1 != ch2;
    }

    @Override
    public String parentTag(String tag1, String tag2) {
      return null;
    }
  }

  private class InheritRightTag implements  ITagMatcher {
    @Override
    public boolean matches(String tag1, String tag2) {
      return true;
    }

    @Override
    public String parentTag(String tag1, String tag2) {
      return tag2;
    }
  }

  private class PlMmatch implements ITagMatcher {

    @Override
    public boolean matches(String tag1, String tag2) {
      if(tag1 == null || tag1.length() < 4) return false;
      if(tag2 == null || tag2.length() < 4) return false;
      char ch1 = tag1.charAt(3);
      char ch2 = tag2.charAt(3);
      if(tag1.startsWith("n") && tag2.startsWith("n")) return true;
      if(tag1.startsWith("n") && tag2.startsWith("p")) return true;
      if(tag1.startsWith("p") && tag2.startsWith("n")) return true;
      return ch1 == ch2;
    }

    @Override
    public String parentTag(String tag1, String tag2) {
      return null;
    }
  }

  @Override
  public String getId() {
    return "KURS_CFG_RULE";
  }

  @Override
  public String getDescription() {
    return "Kurš jābūt saskaņotam.";
  }

  @Override
  protected CFG getAllowed() {
    CFG allowed = new CFG();
    return allowed;
  }

  @Override
  protected CFG getError() {
    CFG error = new CFG()
      .addRule(
        new ConcatCFGRule(
          CFG.START_SYMBOL,
          "B",
          "A"
        )
      )
      .addRule(
        new ConcatCFGRule(
          CFG.START_SYMBOL,
          "A",
          "B"
        )
      )
      .addRule(
        new ConcatCFGRule(
          CFG.START_SYMBOL,
          "A",
          "C"
        )
      )
      .addRule(
        new ConcatCFGRule(
          "C",
          "B",
          "A"
        )
      )
      .addRule(
        new ConcatCFGRule(
          "A",
          "A",
          "A"
        )
      )
      .addRule(
        new TerminalCFGRule(
          "A",
          null
        )
      )
      .addRule(
        new ConcatCFGRule(
          "B",
          "V",
          "U",
          new PlMismatch()
        )
      )
      .addRule(
        new ConcatCFGRule(
          "U",
          "KOM",
          "KUR",
          new InheritRightTag()
        )
      )
      .addRule(
        new TerminalCFGRule(
          "KOM",
          ","
        )
      )
      .addRule(
        new TerminalCFGRule(
          "U",
          "kurš"
        )
      )
      .addRule(
        new TerminalCFGRule(
          "KUR",
          "kurš"
        )
      )
      .addRule(
        new TerminalCFGRule(
          "V",
          null
        )
      );

    return error;
  }

  @Override
  protected String getMessage() {
    return "Pamanīts nesaskaņots kurš.";
  }
}
