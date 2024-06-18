package org.languagetool.rules.lv;

import org.languagetool.cfg.CFG;
import org.languagetool.cfg.rules.ConcatCFGRule;
import org.languagetool.cfg.rules.ITagMatcher;
import org.languagetool.cfg.rules.TerminalCFGRule;

public class CFGIpasibas extends BaseCFGRule {

  private class IpasibasMistmatch implements ITagMatcher {

    @Override
    public boolean matches(String tag1, String tag2) {
      if(tag1 == null || tag1.length() < 4) return false;
      if(tag2 == null || tag2.length() < 4) return false;
      if(tag1.charAt(0) != 'a' || tag2.charAt(0) != 'n') return false;
      return tag1.charAt(3) != tag2.charAt(3) ||
        tag1.charAt(2) != tag2.charAt(2);
    }

    @Override
    public String parentTag(String tag1, String tag2) {
      return null;
    }
  }

  private class IpasibasMatch implements ITagMatcher {

    @Override
    public boolean matches(String tag1, String tag2) {
      if(tag1 == null || tag1.length() < 4) return false;
      if(tag2 == null || tag2.length() < 4) return false;
      return false;
    }

    @Override
    public String parentTag(String tag1, String tag2) {
      return null;
    }
  }

  @Override
  public String getId() {
    return "IPASIBAS_MISMATCH_CFG_RULE";
  }

  @Override
  public String getDescription() {
    return "Ipašības vārda galotne neatbilst lietvārdam.";
  }

  @Override
  protected CFG getAllowed() {
    CFG allowed = new CFG()
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
          "I",
          "V",
          new IpasibasMatch()
        )
      )
      .addRule(
        new TerminalCFGRule(
          "I",
          null
        )
      )
      .addRule(
        new TerminalCFGRule(
          "V",
          null
        )
      );
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
          "I",
          "V",
          new IpasibasMistmatch()
        )
      )
      .addRule(
        new TerminalCFGRule(
          "I",
          null
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
    return "Nesaskaņots skaitlis pamanīts.";
  }
}
