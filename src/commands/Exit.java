// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: Bhatia85
// UT Student #: 1005946577
// Author: Damian Bhatia
//
// Student2:
// UTORID user_name: dogupar1
// UT Student #: 1006140926
// Author: Rahul Doguparty
//
// Student3:
// UTORID user_name: sivaso12
// UT Student #: 1006152618
// Author: Vigaash Sivasothy
//
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package commands;

import java.util.List;

/**
 * This class is responsible for exiting the program
 */
public class Exit implements Command {

  @Override
  public void run(List<String> tokens) {
    System.out.println("Program Terminated");
    System.exit(0);
  }


  /**
   * Returns a string representation of the command
   * 
   * @return String that describes the command
   */
  public String toString() {
    return "exit - Quit the program";
  }
}
