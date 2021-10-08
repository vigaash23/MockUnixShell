package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import filesystem.*;
import commands.PrintWorkingDirectory;

public class PrintWorkingDirectoryTest {

  FileSystem fileSystem;
  PrintWorkingDirectory pwd;

  String pwdOutput;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    pwd = new PrintWorkingDirectory(fileSystem);
    pwdOutput = "";

  }

  @Test
  public void testEmpty() {
    assertEquals("/", pwd.printWorkingDirectory());
  }

}
