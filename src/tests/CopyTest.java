package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commands.Copy;
import filesystem.*;
import interpreter.Interpreter;

public class CopyTest {

  FileSystem fileSystem;
  Copy copy;
  Interpreter interpreter;
  String copyOutput;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    copy = new Copy(fileSystem);
    interpreter = new Interpreter(fileSystem);

    copyOutput = "";
  }

  @Test
  public void testCopyFiletoDirectory() {
    Directory d1 = new Directory("d1", fileSystem.getRoot());
    File f1 = new File("f1");
    fileSystem.getRoot().addFile("f1", f1);
    copy.copyFileToDirectory(f1.getFileName(), d1.getAbsolutePath());
    assertEquals(null, d1.getFile("f1"));
  }

  public void testCopyFiletoDirectoryEmpty() {
    copy.copyFileToDirectory("", "");
    assertEquals(null, fileSystem.getRoot().getFile(""));
  }

}
