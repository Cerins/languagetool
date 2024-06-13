package org.languagetool.cfg.rules;

public interface ITagMatcher {
  boolean matches(String tag1, String tag2);
  String parentTag(String tag1, String tag2);
}
