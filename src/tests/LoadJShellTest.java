package tests;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import commands.LoadJShell;
import filesystem.*;

public class LoadJShellTest {

  ByteArrayOutputStream baos;
  PrintStream ps;
  PrintStream defaultps;


  FileSystem fileSystem;
  LoadJShell ljs;

  String ljsOutput;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    ljs = new LoadJShell();
    ljsOutput = "";

    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    defaultps = System.out;

    System.setOut(ps);

  }

  @Test
  public void testLoadJShell() {
    ljs.load("randomFile");
    ljsOutput = baos.toString().strip();
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("Unable to load JShell successfully.", ljsOutput);
  }

}
