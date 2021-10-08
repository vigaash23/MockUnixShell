package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commands.Pushd;
import filesystem.*;

public class PushdTest {

  FileSystem fileSystem;

  Pushd pushd;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    pushd = new Pushd(fileSystem);
  }

  @Test
  public void testPushd() {
    FileSystem fileSystemDupe = fileSystem;
    pushd.push("");
    assertEquals(fileSystemDupe, fileSystem);
  }

}
