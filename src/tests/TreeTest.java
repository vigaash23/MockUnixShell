package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import java.io.PrintStream;
import java.util.List;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import commands.Tree;
import filesystem.*;

public class TreeTest {

  ByteArrayOutputStream baos;
  PrintStream ps;
  PrintStream defaultps;


  Tree tree;
  FileSystem fileSystem;
  String treeOutput;
  String actualOutput;

  @Before
  public void setUp() {
    tree = new Tree(fileSystem);
    fileSystem = new FileSystem();
    treeOutput = "";
    actualOutput = "";

    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    defaultps = System.out;

    System.setOut(ps);

  }

  @Test
  public void testEmptyTree() {
    tree.treeRecursive(fileSystem.getRoot(), "");
    treeOutput = "\\" + baos.toString();
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("\\", treeOutput);
  }



}
