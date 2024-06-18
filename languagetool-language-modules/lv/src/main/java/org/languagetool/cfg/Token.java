package org.languagetool.cfg;

public class Token {

  public Integer getInitialPosition() {
    return initialPosition;
  }

  public void setInitialPosition(Integer initialPosition) {
    this.initialPosition = initialPosition;
  }

  private Integer initialPosition;

  public Token(String value, String tag, Integer initialPosition) {
    this.value = value;
    this.tag = tag;
    this.initialPosition = initialPosition;
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
