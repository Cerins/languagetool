package org.languagetool.cfg;

public class Symbol extends Object {
  public boolean isTerminal() {
    return terminal;
  }

  public Symbol(boolean terminal, String value, String token) {
    this.terminal = terminal;
    this.value = value;
    this.tag = token;
  }

  public void setTerminal(boolean terminal) {
    this.terminal = terminal;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  private boolean terminal;

  private String value;

  private String tag;

  @Override
  public boolean equals(Object o) {
    if(o instanceof Symbol && o != null) {
      Symbol other = (Symbol) o;
      if(other.terminal != terminal) return false;
      if(value == null) {
        return other.value == null;
      } else {
        return value.equals(other.value);
      }
    }
    return false;
  }
}
