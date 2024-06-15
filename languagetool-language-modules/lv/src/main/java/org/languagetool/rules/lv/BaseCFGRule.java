package org.languagetool.rules.lv;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedToken;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.cfg.CFG;
import org.languagetool.cfg.ParseResult;
import org.languagetool.cfg.Symbol;
import org.languagetool.cfg.Token;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class BaseCFGRule extends Rule {

  abstract protected CFG getAllowed();

  abstract protected CFG getError();

  abstract protected String getMessage();
//  public

//  @Override
//  public String getId() {
//    return null;
//  }
//
//  @Override
//  public String getDescription() {
//    return null;
//  }

  @Override
  public RuleMatch[] match(AnalyzedSentence sentence) throws IOException {
//    return new RuleMatch[0];
      AnalyzedTokenReadings[] rawItems = sentence.getTokensWithoutWhitespace();
      List<Token>[] cfgSentence = new List[rawItems.length - 1];
      for(int i=0;i<rawItems.length - 1;i++) {
        AnalyzedTokenReadings rawItem = rawItems[i+1];
        cfgSentence[i] = new LinkedList<>();
        List<AnalyzedToken> tk = rawItem.getReadings();
        for(AnalyzedToken t: tk) {
          Token cfgToken = new Token(
            t.getLemma(),
            t.getPOSTag(),
            i
          );
          cfgSentence[i].add(cfgToken);
        }
      }
      // Transform into proper form
      ParseResult allowedResult = getAllowed().parse(cfgSentence);
      ParseResult errorResult = getError().parse(cfgSentence);
      if(allowedResult.getMatches()) {
        return new RuleMatch[0];
      }
      if(errorResult.getMatches() == false) {
        return new RuleMatch[0];
      }
    Symbol root = errorResult.getRoot();

    // Now traverse while and expand the interval while we have a items;
    Queue<Symbol> q = new LinkedList<>();
    q.add(root);
    Integer start = null;
    Integer end = null;
    while(q.size() > 0) {
      Symbol el = q.poll();
      Integer p = el.getInitialPosition();
      if(p != null) {
        if(start == null) start = p;
        if(p < start) start = p;
        if(end == null) end = p;
        if(p > end) end = p;
      }
      Symbol[] ch = el.getChildren();
      if(ch != null) {
        for(Symbol child: ch) {
          // Only traverse paths that matched some value and not general match
          q.add(child);
        }
      }
    }
    if(start == null) {
      start = new Integer(0);
    }
    if(end == null) {
      end = new Integer(rawItems.length - 2);
    }
    return new RuleMatch[]{
      new RuleMatch(
        this,
        start,
        end,
        getMessage()
      )
    };
  }
}
