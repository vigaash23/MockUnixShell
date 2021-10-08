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
package interpreter;

import java.util.List;

/**
 * This class is responsible for providing a relevant message to the user 
 * depending one what type of
 * error they may have had with their command.
 *
 */
public class ErrorHandler {

  /**
   * Prints an error message to user if WrongNumParamsException is thrown
   * 
   * @param tokens the tokens that the user has inputed
   */
  public static void handleWrongNumParamsException(List<String> tokens) {
    System.out.println("You have entered the incorrect number of " + 
  "parameters for the command \""
        + tokens.get(0) + "\".");
  }


  /**
   * Prints an error message to user if InvalidParamsException is thrown
   * 
   * @param tokens the tokens that the user has inputed
   */
  public static void handleInvalidParamsException(List<String> tokens) {
    System.out.println("You have entered some invalid parameters for the " + 
  "command \""
        + tokens.get(0) + "\".\nOnly those parameters that were valid up"
        + " until an invalid input were executed.");
  }
}
