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
package driver;

import java.util.Scanner;
import filesystem.FileSystem;
import interpreter.Interpreter;

/**
 * This class is responsible for running the actual program
 */
public class JShell {
  
  // Main extrance to the program
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    FileSystem fileSystem = new FileSystem();
    Interpreter interpreter = new Interpreter(fileSystem);

    // Used to start the loop
    boolean isRunning = true;
    // The user input
    String input = "";

    while (isRunning) {
      // Prompt user
      if (fileSystem.getCurrentDirectory().getDirectoryName().equals("/"))
        System.out.print(fileSystem.getCurrentDirectory().getAbsolutePath() + 
            ": >> ");
      else
        System.out.print(fileSystem.getCurrentDirectory().getAbsolutePath() + 
            " >> ");

      // Gather user input
      input = scanner.nextLine();

      // Process user input
      interpreter.interpret(input);
    }

    scanner.close();
  }
}
