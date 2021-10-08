package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import commands.History;

public class HistoryTest {

  History history;

  @Before
  public void setUp() {
    history = new History();

  }

  @Test
  public void testHistory() {
    assertEquals(0, history.sizeOfHistory());
  }

}
