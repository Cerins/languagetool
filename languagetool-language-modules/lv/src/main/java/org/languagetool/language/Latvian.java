/* LanguageTool, a natural language style checker 
 * Copyright (C) 2007 Daniel Naber (http://www.danielnaber.de)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301
 * USA
 */
package org.languagetool.language;

import lv.semti.morphology.analyzer.Analyzer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.rules.*;
import org.languagetool.rules.lv.CFGRuleFactory;
import org.languagetool.rules.lv.ExampleCFGRule;
import org.languagetool.rules.lv.MorfologikLatvianSpellerRule;
import org.languagetool.rules.spelling.SpellingCheckRule;
import org.languagetool.synthesis.Synthesizer;
import org.languagetool.synthesis.lv.LatvianSynthesizer;
import org.languagetool.tagging.Tagger;
import org.languagetool.tagging.lv.LatvianTagger;
import org.languagetool.tokenizers.SRXSentenceTokenizer;
import org.languagetool.tokenizers.SentenceTokenizer;

import java.io.IOException;
import java.util.*;

public class Latvian extends Language {

  private static Analyzer analyzer = null;

  private static Analyzer getAnalyzer() {
    if(analyzer == null) {
      try {
        analyzer = new Analyzer();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return analyzer;
  }

  @Override
  public String getName() {
    return "Latvian";
  }

  @Override
  public String getShortCode() {
    return "lv";
  }
  
  @Override
  public String[] getCountries() {
    return new String[]{"LV"};
  }

  @Override
  public SentenceTokenizer createDefaultSentenceTokenizer() {
    return new SRXSentenceTokenizer(this);
  }
  
  @Override
  public Contributor[] getMaintainers() {
    return new Contributor[] {new Contributor("Roberts Ceriņš")};
  }

  @Override
  public List<Rule> getRelevantRules(ResourceBundle messages, UserConfig userConfig, Language motherTongue, List<Language> altLanguages) throws IOException {
    List<Rule> rules = Arrays.asList(
            new CommaWhitespaceRule(messages),
            new DoublePunctuationRule(messages),
            new GenericUnpairedBracketsRule(messages,
                    Arrays.asList("[", "(", "{", "„", "»", "«", "\""),
                    Arrays.asList("]", ")", "}", "”", "«", "»", "\"")),
//            new MorfologikLatvianSpellerRule(messages, this, userConfig, altLanguages),
            new UppercaseSentenceStartRule(messages, this),
            new WordRepeatRule(messages, this),
            new MultipleWhitespaceRule(messages, this)
    );
    List<Rule> result = new LinkedList(rules);
    List<Rule> cfgRules = CFGRuleFactory.allCFGRules();
    if(cfgRules == null) return result;
    for(Rule r: cfgRules) {
      System.out.println(r);
      result.add(r);
    }
    return result;
  }

  @Override
  @NotNull
  public Tagger createDefaultTagger() {
    return new LatvianTagger(getAnalyzer());
  }
  @Override
  @NotNull
  public Synthesizer createDefaultSynthesizer() {
    return new LatvianSynthesizer(getAnalyzer());
  }

  @Nullable
  @Override
  protected SpellingCheckRule createDefaultSpellingRule(ResourceBundle messages) throws IOException {
    return new MorfologikLatvianSpellerRule(messages, this, null, null);
  }
}
