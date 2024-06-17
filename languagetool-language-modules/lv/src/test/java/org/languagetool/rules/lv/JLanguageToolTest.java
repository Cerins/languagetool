package org.languagetool.rules.lv;

import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Latvian;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JLanguageToolTest {

  @Test
  public void testBecause() throws IOException {
    final JLanguageTool tool = new JLanguageTool(new Latvian());
    List<RuleMatch> mistakes = tool.check("Dēļ aukstuma auto nedarbojās.");
    System.out.println(mistakes);
    assertEquals(1, mistakes.size());
  }
  @Test
  public void testNemtVardu() throws IOException {
    final JLanguageTool tool = new JLanguageTool(new Latvian());
    List<RuleMatch> mistakes = tool.check("Viņš ņēma vārdu.");
    System.out.println(mistakes);
    assertEquals(1, mistakes.size());
  }

}
