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

/**
 * This is the class for the pushd command that is used within the JShell
 */
public class Pushd implements Command {

  private FileSystem fileSystem;
  private ChangeDirectory changeDirectory;

  /**
   * Constructor for Pushd with an already existing Filesystem
   * 
   * @param fileSystem FileSystem that is being used
   */
  public Pushd(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
    this.changeDirectory = new ChangeDirectory(fileSystem);
  }


  @Override
  public void run(List<String> tokens) {
    push(tokens.get(1));
  }


  /**
   * Responsible for performing the Pushd operation
   */
  public void push(String newDirectoryPath) {
    this.fileSystem.addToDirectoryStack(this.fileSystem.getCurrentDirectory().
        getAbsolutePath());
    this.changeDirectory.changeDirectory(newDirectoryPath);
  }


  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the pushd command
   */
  public String toString() {
    return "pushd DIR - Saves the current working directory by pushing onto "
        + "directory stack and then changes the new current working directory"
        + " to DIR. The push must be consistent as per the LIFO behavior of a"
        + " stack.   The pushd command saves the old current working directory"
        + " in directory stack so that it can be returned to at any time (via "
        + "the popd command).  The size of the directory stack is dynamic and "
        + "dependent on the pushd and the popd commands.";
  }
}
