package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


import commands.Popd;
import filesystem.*;

public class PopdTest {

  FileSystem fileSystem;
  Popd popd;

  @Before
  public void setUp() {
    fileSystem = new FileSystem();
    popd = new Popd(fileSystem);
  }

  @Test
  public void testPopd() {
    FileSystem fileSystemDupe = fileSystem;
    popd.pop();
    assertEquals(fileSystemDupe, fileSystem);
  }

}
