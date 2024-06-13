package org.languagetool.cfg;

public class Symbol extends Object {
  public boolean isTerminal() {
    return terminal;
  }

  public Symbol(boolean terminal, String value, String token, Integer initialPosition) {
    this.terminal = terminal;
    this.value = value;
    this.tag = token;
    this.initialPosition = initialPosition;
  }

  public Symbol(boolean terminal, String value, String token) {
    this(terminal, value, token, null);
  }

  public Symbol[] getChildren() {
    return children;
  }

  public void setChildren(Symbol[] children) {
    this.children = children;
  }

  protected Symbol[] children;

  public Integer getInitialPosition() {
    return initialPosition;
  }

  public void setInitialPosition(Integer initialPosition) {
    this.initialPosition = initialPosition;
  }

  protected Integer initialPosition;

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
