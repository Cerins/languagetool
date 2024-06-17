package org.languagetool.cfg.results;

import org.junit.Test;
import org.languagetool.JLanguageTool;
import org.languagetool.language.CFGLatvian;
import org.languagetool.rules.RuleMatch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class DataEntry {
  private int classId;
  private String text;

  public DataEntry(int classId, String text) {
    this.classId = classId;
    this.text = text;
  }

  public int getClassId() {
    return classId;
  }

  public void setClassId(int classId) {
    this.classId = classId;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean shouldError(){
    return classId == 1;
  }

  @Override
  public String toString() {
    return "DataEntry{" +
      "classId=" + classId +
      ", text='" + text + '\'' +
      '}';
  }
}

public class CFGResultTest {

  private String testFN = "org/languagetool/cfg/test.tsv";

  private String trainFN = "org/languagetool/cfg/train.tsv";

  private static List<DataEntry> readTSVFile(String fileName) {
    List<DataEntry> data = new LinkedList<>();

    try (InputStream is = CFGResultTest.class.getClassLoader().getResourceAsStream(fileName);
         BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

      String line;
      while ((line = br.readLine()) != null) {
        String[] fields = line.split("\t");
        int classId = Integer.parseInt(fields[0]);
        String text = fields[1];
        DataEntry entry = new DataEntry(classId, text);
        data.add(entry);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }

  @Test
  public void testSample(){
    List<DataEntry> d = readTSVFile(testFN);
    System.out.println(d);
    assertTrue(true);
  }

  private class DataSetTraversal {
    int totalCorrect = 0;
    int foundCorrect = 0;
    int totalError = 0;
    int foundError = 0;

  }

  private DataSetTraversal getDataSetResults(String fn) throws IOException {
    final JLanguageTool tool = new JLanguageTool(new CFGLatvian());
    List<DataEntry> de = readTSVFile(fn);
    DataSetTraversal res = new DataSetTraversal();
    for(DataEntry d: de) {
//      System.out.println(de);
      List<RuleMatch> mistakes = tool.check(d.getText());
      boolean hasErrors = mistakes.size() > 0;
      boolean shouldHaveErrors = d.shouldError();
      if(shouldHaveErrors) {
        res.totalError++;
        if(hasErrors) {
          res.foundError++;
        } else {

        }
      } else {
        res.totalCorrect++;
        if(!hasErrors) {
          res.foundCorrect++;
        } else {
          System.out.println(d);
          System.out.println(mistakes);
        }
      }
    }
    return res;
  }


  private void printPrecisionRecallAndF1(DataSetTraversal res) {
    double precisionError = res.foundError / (double) (res.foundError + (res.totalCorrect - res.foundCorrect));
    double recallError = res.foundError / (double) res.totalError;
    double f1Error = 2 * (precisionError * recallError) / (precisionError + recallError);

    double precisionCorrect = res.foundCorrect / (double) (res.foundCorrect + (res.totalError - res.foundError));
    double recallCorrect = res.foundCorrect / (double) res.totalCorrect;
    double f1Correct = 2 * (precisionCorrect * recallCorrect) / (precisionCorrect + recallCorrect);

    System.out.println("For Errors:");
    System.out.println("Precision: " + precisionError);
    System.out.println("Recall: " + recallError);
    System.out.println("F1 Score: " + f1Error);

    System.out.println("For Correct:");
    System.out.println("Precision: " + precisionCorrect);
    System.out.println("Recall: " + recallCorrect);
    System.out.println("F1 Score: " + f1Correct);
  }

  @Test
  public void testBadMatchForCorrectClassInTrain() throws IOException {
    DataSetTraversal res = getDataSetResults(trainFN);
    assertEquals(res.foundCorrect, res.totalCorrect);
  }
  @Test
  public void testBadMatchForCorrectClassInTest() throws IOException {
    DataSetTraversal res = getDataSetResults(testFN);
    assertEquals(res.foundCorrect, res.totalCorrect);
  }
  @Test
  public void testGetPrecisionRecallAndF1ForTrain() throws IOException {
    DataSetTraversal res = getDataSetResults(trainFN);
    System.out.println("Train result");
    printPrecisionRecallAndF1(res);
    assertTrue(true);
  }

  @Test
  public void testGetPrecisionRecallAndF1ForTest() throws IOException {
    DataSetTraversal res = getDataSetResults(testFN);
    System.out.println("Test result");
    printPrecisionRecallAndF1(res);
    assertTrue(true);
  }
}
