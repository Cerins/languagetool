package org.languagetool.cfg;

import org.languagetool.cfg.rules.CFGRuleType;
import org.languagetool.cfg.rules.ICFGRule;

import java.util.LinkedList;
import java.util.List;

public class CFG {

  public static String START_SYMBOL = "S";

  protected List<ICFGRule> rules = new LinkedList<>();

  void addRule(ICFGRule rule) {
    rules.add(rule);
  }

  ParseResult parse(List<Token>[] tokens) {
    // Parse using cyk algorithm https://www.geeksforgeeks.org/cyk-algorithm-for-context-free-grammar/
    ParseResult result = new ParseResult();
    int n = tokens.length;
    // Create a nxn dp table
    List<Symbol>[][] dp = new List[n][n];
    for(int i=0;i<n;i++) {
      for(int j=0;j<n;j++) {
        dp[i][j] = new LinkedList<>();
      }
    }
    // Place symbols which matches one terminal rule
    for(int i=0;i<n;i++) {
      for(ICFGRule rule: rules) {
        if(rule.getRuleType() == CFGRuleType.ONE_TERMINAL) {
          // Check if the rule matches one for token
          for(Token t: tokens[i]) {
            Symbol in = new Symbol(true, t.getValue(), t.getTag());
            Symbol out = rule.matchesRight(in);
            if(out != null) {
              dp[i][i].add(out);
            }
          }
        }
      }
    }
    boolean matches = false;
    // Then we iterate over concat rule and check if it exists
    for (int l = 2; l <= n; l++) {
      for(int i=0;i<n-l+1;i++) {
        int j = i + l - 1;
        for (int k = i; k < j; k++) {
          // Go through concat rule
          for(ICFGRule rule: rules) {
            if(rule.getRuleType() == CFGRuleType.TWO_NON_TERMINAL) {
              for(Symbol potentialB: dp[i][k]) {
                for(Symbol potentialC: dp[k+1][j]) {
                  Symbol out = rule.matchesRight(
                    new Symbol[]{potentialB, potentialC}
                  );
                  if(out != null) {
                    dp[i][j].add(out);
                  }
                }
              }
            }
          }
        }
      }
    }
    if(n > 0) {
      for(Symbol potentialStart: dp[0][n-1]) {
        if(potentialStart.getValue().equals(START_SYMBOL)) {
          matches = true;
        }
      }
    }
    result.setMatches(matches);
    return result;
  }

}
