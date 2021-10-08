package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import filesystem.*;
import commands.Move;

public class MoveTest {

  FileSystem fileSystem;
  Move move;


  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    move = new Move(fileSystem);
  }

  @Test
  public void testMove() {
    Directory d1 = new Directory("d1", fileSystem.getRoot());
    File f1 = new File("f1");
    fileSystem.getRoot().addFile("f1", f1);
    move.moveFileToDirectory("/f1", "/d1/f1");
    assertNotEquals(null, fileSystem.getRoot().getFile("f1"));
  }

}
