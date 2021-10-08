package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import filesystem.*;
import commands.List;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class ListTest {

  ByteArrayOutputStream baos;
  PrintStream ps;
  PrintStream defaultps;

  FileSystem fileSystem;
  List list;

  String listOutput;

  @Before
  public void setUp() {

    fileSystem = new FileSystem();
    list = new List(fileSystem);

    listOutput = "";


    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    defaultps = System.out;

    System.setOut(ps);


  }

  public void testEmptyList() {
    list.list("/");
    listOutput = baos.toString();
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("", listOutput);
  }

}
