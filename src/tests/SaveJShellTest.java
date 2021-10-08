package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commands.SaveJShell;
import filesystem.*;
import commands.History;

public class SaveJShellTest {

  FileSystem fileSystem;
  SaveJShell sjs;
  History history;
  String fileName;

  @Before
  public void setUp() {
    fileName = "";
    fileSystem = new FileSystem();
    history = new History();
    sjs = new SaveJShell(fileSystem, history);
  }

  @Test
  public void testSaveJShell() {
    sjs.save(fileSystem, history, fileName);

  }

}
