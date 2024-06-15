package org.languagetool.rules.lv;

import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.language.CFGLatvian;
import org.languagetool.language.Latvian;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleCFGRuleTest {
  @Test
  public void testExampleCFGFinds() throws IOException {
    final JLanguageTool tool = new JLanguageTool(new CFGLatvian());
    List<RuleMatch> mistakes = tool.check("Mazā pilsēta dzīvoja runcis.");
    System.out.println(mistakes);
    assertEquals(1, mistakes.size());
  }
  @Test
  public void testExampleCFGNotFinds() throws IOException {
    final JLanguageTool tool = new JLanguageTool(new CFGLatvian());
    List<RuleMatch> mistakes = tool.check("Lielā pilsēta dzīvoja runcis.");
    System.out.println(mistakes);
    assertEquals(0, mistakes.size());
  }
}
