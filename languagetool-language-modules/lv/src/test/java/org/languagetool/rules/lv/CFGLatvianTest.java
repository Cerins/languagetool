package org.languagetool.rules.lv;

import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.language.CFGLatvian;
import org.languagetool.rules.RuleMatch;
import java.util.List;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class CFGLatvianTest {
  @Test
  public void testConcatPlRule() throws IOException {
    final JLanguageTool tool = new JLanguageTool(new CFGLatvian());
    List<RuleMatch> mistakes = tool.check("Mums jau ir klienti ar šādiem vai lielāku ātrumu.");
    System.out.println(mistakes);
    assertTrue(mistakes.size() > 0);
  }

  @Test
  public void testConcatPlRuleMismatch() throws IOException {
    final JLanguageTool tool = new JLanguageTool(new CFGLatvian());
    List<RuleMatch> mistakes = tool.check("- No bodes tev ko atnest ?");
    System.out.println(mistakes);
    assertTrue(mistakes.size() == 0);
  }
}
