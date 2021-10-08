package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commands.Search;
import java.util.ArrayList;
import filesystem.*;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class SearchJUnitTest {

  ByteArrayOutputStream baos;
  PrintStream ps;
  PrintStream defaultps;

  FileSystem fileSystem;
  ArrayList<String> path;
  Search search;

  String searchOutput;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    path = new ArrayList<String>();
    search = new Search(fileSystem);
    searchOutput = "";


    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    defaultps = System.out;

    System.setOut(ps);
  }

  @Test
  public void testSearch() {
    search.searchFileSystem(path, "-f", "");
    searchOutput = baos.toString();
    System.out.flush();
    System.setOut(defaultps);

    assertEquals("", searchOutput);

  }

}
