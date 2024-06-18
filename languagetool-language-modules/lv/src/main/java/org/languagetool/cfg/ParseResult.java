package org.languagetool.cfg;

public class ParseResult {
  public boolean getMatches() {
    return matches;
  }

  public void setMatches(boolean matches) {
    this.matches = matches;
  }

  private boolean matches;

  public Symbol getRoot() {
    return root;
  }

  public void setRoot(Symbol root) {
    this.root = root;
  }

  private Symbol root;

}
