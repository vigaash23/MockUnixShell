package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import commands.Curl;
import commands.Echo;
import filesystem.*;

public class CurlTest {

  ByteArrayOutputStream baos;
  PrintStream ps;
  PrintStream defaultps;


  FileSystem fileSystem;
  Echo echo;
  Curl curl;

  String curlOutput;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    echo = new Echo(fileSystem);
    curl = new Curl(echo);

    curlOutput = "";

    baos = new ByteArrayOutputStream();
    ps = new PrintStream(baos);
    defaultps = System.out;

    System.setOut(ps);

  }


  @Test
  public void testInvalidUrlContents() {
    curl.getContents("randomURL");
    curlOutput = baos.toString().strip().replaceAll("\n", "");
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("This URL did not exist, please try again.", curlOutput);
  }

  @Test
  public void testInvalidUrlName() {
    curl.getName("randomURL");
    curlOutput = baos.toString().strip().replaceAll("\n", "");
    System.out.flush();
    System.setOut(defaultps);
    assertEquals("This URL did not exist, please try again.", curlOutput);
  }



}
