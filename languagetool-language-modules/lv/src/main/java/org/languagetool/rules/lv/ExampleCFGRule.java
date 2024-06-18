package org.languagetool.rules.lv;

import org.languagetool.cfg.CFG;
import org.languagetool.cfg.rules.ConcatCFGRule;
import org.languagetool.cfg.rules.TerminalCFGRule;


public class ExampleCFGRule extends BaseCFGRule {
  @Override
  public String getId() {
    return "EXAMPLE_CFG_RULE";
  }

  @Override
  public String getDescription() {
    return "Šis ir piemērs, ka stradā CFG likmui";
  }

  @Override
  protected CFG getAllowed() {
    CFG allowed = new CFG();
    return allowed;
  }

  @Override
  protected CFG getError() {
    CFG error = new CFG();
    error.addRule(
      new ConcatCFGRule(
        CFG.START_SYMBOL,
        "B",
        "A"
      )
    );
    error.addRule(
      new ConcatCFGRule(
        CFG.START_SYMBOL,
        "A",
        "B"
      )
    );
    error.addRule(
      new ConcatCFGRule(
        CFG.START_SYMBOL,
        "A",
        "C"
      )
    );
    error.addRule(
      new ConcatCFGRule(
        "C",
        "B",
        "A"
      )
    );
    error.addRule(
      new ConcatCFGRule(
        "A",
        "A",
        "A"
      )
    );
    error.addRule(
      new TerminalCFGRule(
        CFG.START_SYMBOL,
        null
      )
    );
    error.addRule(
      new TerminalCFGRule(
        "B",
        "mazs"
      )
    );
    error.addRule(
      new TerminalCFGRule(
        "A",
        null
      )
    );
    return error;
  }

  @Override
  protected String getMessage() {
    return "Neliec vārdu mazs teikumuā";
  }
}
