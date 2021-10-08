package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commands.ChangeDirectory;
import filesystem.*;


public class ChangeDirectoryTest {

  FileSystem fileSystem;
  ChangeDirectory cd;


  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    cd = new ChangeDirectory(fileSystem);
  }

  @Test
  public void testChangeDirectory() {
    Directory d1 = new Directory("d1", fileSystem.getRoot());
    cd.changeDirectory("");
    Directory cdDirectory = fileSystem.getCurrentDirectory();

    assertEquals("/", cdDirectory.getAbsolutePath());


  }

}
