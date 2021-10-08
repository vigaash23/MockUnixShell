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
package exceptions;

/**
 * Exception thrown if Invalid params are entered as tokens.
 *
 */
public class InvalidParamsException extends Exception {

  private static final long serialVersionUID = 1L;


  public InvalidParamsException() {}


  public InvalidParamsException(String errorMessage) {
    super(errorMessage);
  }


  public InvalidParamsException(Throwable cause) {
    super(cause);
  }
}
