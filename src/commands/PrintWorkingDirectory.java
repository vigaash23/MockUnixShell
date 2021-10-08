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

import filesystem.FileSystem;
import java.util.List;

public class PrintWorkingDirectory implements Command {

  private FileSystem fileSystem;

  /**
   * Constructor for PrintWorkingDirectory with an already existing FileSystem
   * 
   * @param fileSystem FileSystem that is being used
   */
  public PrintWorkingDirectory(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  @Override
  public void run(List<String> tokens) {
    System.out.println(printWorkingDirectory());
  }

  /**
   * Prints the entire absolute path of the current directory that the user is 
   * working in
   * 
   * @return String that is the absolute path of where the user is currently 
   * working in
   */
  public String printWorkingDirectory() {
    return this.fileSystem.getCurrentDirectory().getAbsolutePath();
  }

  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the pwd command
   */
  public String toString() {
    return "pwd - Print the current working directory (including the whole " + 
  "path).  ";
  }
}
