package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import commands.Remove;
import filesystem.*;


public class RemoveTest {

  FileSystem fileSystem;
  Remove remove;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    remove = new Remove(fileSystem);
  }

  @Test
  public void testRemove() {
    Directory d1 = new Directory("d1", fileSystem.getRoot());
    File f1 = new File("f1");
    fileSystem.getRoot().addFile("f1", f1);
    remove.remove("/f1");
    assertEquals(null, fileSystem.getRoot().getFile("f1"));
  }

}
