package tests;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;
import org.junit.Before;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import commands.Echo;
import filesystem.*;

public class EchoTest {

  ByteArrayOutputStream baos;
  PrintStream ps;
  PrintStream defaultps;

  FileSystem fileSystem;
  Echo echo;
  String echoOutput;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    echo = new Echo(fileSystem);
    echoOutput = "";

    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    defaultps = System.out;

    System.setOut(ps);


  }


  @Test
  public void testEmptyEcho() {
    echo.echoPrintToShell("");
    echoOutput = baos.toString().strip();
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("", echoOutput);
  }

  @Test
  public void testWordEcho() {
    echo.echoPrintToShell("hello");
    echoOutput = baos.toString().strip();
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("hello", echoOutput);
  }

  @Test
  public void testSentenceEcho() {
    echo.echoPrintToShell("Wow that is really cool.");
    echoOutput = baos.toString().strip();
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("Wow that is really cool.", echoOutput);
  }

  @Test
  public void testOverwriteEcho() {
    Directory d1 = new Directory("d1", fileSystem.getRoot());
    File f1 = new File("f1");
    d1.addFile("f1", f1);
    echo.echoOverwriteToFile("Nice", "/d1/f1");
    echoOutput = d1.getFile("f1").getFileContents();
    assertEquals("Nice", echoOutput);
  }

  @Test
  public void testAppendEcho() {
    Directory d1 = new Directory("d1", fileSystem.getRoot());
    File f1 = new File("f1");
    f1.setFileContents("woof");
    d1.addFile("f1", f1);
    echo.echoAppendToFile("meow", "/d1/f1");
    echoOutput = d1.getFile("f1").getFileContents();
    assertEquals("woof\nmeow", echoOutput);


  }

}


