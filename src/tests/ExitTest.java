package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import filesystem.*;
import java.util.List;
import commands.Exit;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class ExitTest {


  ByteArrayOutputStream baos;
  PrintStream ps;
  PrintStream defaultps;


  FileSystem fileSystem;
  List<String> tokens;
  Exit exit;
  String exitOutput;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    exit = new Exit();

    exitOutput = "";

    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    defaultps = System.out;

    System.setOut(ps);
  }

  @Test
  public void testExit() {
    exit.run(tokens);
    exitOutput = baos.toString();
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("Program Terminated", exitOutput);
  }

}
