package org.languagetool.cfg;

public class Token {

  public Token(String value, String tag) {
    this.value = value;
    this.tag = tag;
  }

  private String value;
  private String tag;

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
}
