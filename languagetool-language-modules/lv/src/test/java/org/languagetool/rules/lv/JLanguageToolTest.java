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
//    List<RuleMatch> mistakes = tool.check("Ņēma vārdu");
    List<RuleMatch> mistakes = tool.check("Dēļ aukstuma auto nedarbojās.");
    System.out.println(mistakes);
    assertEquals(1, mistakes.size());
  }
  @Test
  public void testNemtVardu() throws IOException {
    final JLanguageTool tool = new JLanguageTool(new Latvian());
//    List<RuleMatch> mistakes = tool.check("Ņēma vārdu");
    List<RuleMatch> mistakes = tool.check("Viņš ņēma vārdu.");
    System.out.println(mistakes);
    assertEquals(1, mistakes.size());
  }
  @Test
  public void testDienet() throws  IOException {
    final JLanguageTool tool = new JLanguageTool(new Latvian());
    List<RuleMatch> mistakes = tool.check("Viņš nesa dienestu.");
    System.out.println(mistakes);
    assertEquals(1, mistakes.size());
  }

  @Test
  public void testLigums() throws  IOException {
    final JLanguageTool tool = new JLanguageTool(new Latvian());
    List<RuleMatch> mistakes = tool.check("Viņi gāja uz līgumu.");
    System.out.println(mistakes);
    assertEquals(1, mistakes.size());
  }

  @Test
  public void testCilinat() throws  IOException {
    final JLanguageTool tool = new JLanguageTool(new Latvian());
    List<RuleMatch> mistakes = tool.check("Ciltāt jautājumus.");
    System.out.println(mistakes);
    assertEquals(1, mistakes.size());
  }
}
