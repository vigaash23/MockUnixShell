package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import filesystem.*;
import commands.Cat;
import exceptions.InvalidParamsException;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CatTest {

  ByteArrayOutputStream baos;
  PrintStream ps;
  PrintStream defaultps;

  FileSystem fileSystem;
  Cat cat;
  ArrayList<String> files;

  String catOutput;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    cat = new Cat(fileSystem);

    catOutput = "";
    files = new ArrayList<String>();

    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    defaultps = System.out;

    System.setOut(ps);
  }

  @Test
  public void testCat() {
    File f1 = new File("f1");
    f1.setFileContents("");
    fileSystem.getRoot().addFile("f1", f1);
    files.add("f1");
    try {
      cat.concatenate(files);
    } catch (InvalidParamsException e) {
      e.printStackTrace();
    }
    assertEquals("", catOutput);
  }

}
