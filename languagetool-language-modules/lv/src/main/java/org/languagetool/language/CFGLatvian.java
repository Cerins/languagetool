package org.languagetool.language;

import lv.semti.morphology.analyzer.Analyzer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.broker.ResourceDataBroker;
import org.languagetool.rules.*;
import org.languagetool.rules.lv.CFGRuleFactory;
import org.languagetool.rules.lv.ExampleCFGRule;
import org.languagetool.rules.lv.MorfologikLatvianSpellerRule;
import org.languagetool.synthesis.Synthesizer;
import org.languagetool.synthesis.lv.LatvianSynthesizer;
import org.languagetool.tagging.Tagger;
import org.languagetool.tagging.lv.LatvianTagger;

import java.io.IOException;
import java.util.*;

public class CFGLatvian extends Language {

  private static Analyzer analyzer = null;

  private static Analyzer getAnalyzer() {
    if (analyzer == null) {
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

  @Nullable
  @Override
  public Contributor[] getMaintainers() {
    return new Contributor[0];
  }

  @Override
  public List<Rule> getRelevantRules(ResourceBundle messages, UserConfig userConfig, Language motherTongue, List<Language> altLanguages) throws IOException {
    return CFGRuleFactory.allCFGRules();
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

  @Override
  public List<String> getRuleFileNames() {
    List<String> s = new ArrayList<>();
    ResourceDataBroker dataBroker = JLanguageTool.getDataBroker();
    s.add(dataBroker.getRulesDir() +
      "/" + getShortCode() + "/"  + "empty.xml"
    );
    return s;
  }

}
