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

public class Popd implements Command {

  private FileSystem fileSystem;
  private ChangeDirectory changeDirectory;

  /**
   * Constructor for Popd with an already existing Filesystem
   * 
   * @param fileSystem FileSystem that is being used
   */
  public Popd(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
    this.changeDirectory = new ChangeDirectory(fileSystem);
  }


  @Override
  public void run(List<String> tokens) {
    pop();
  }

  /**
   * Responsible for performing the Popd operation
   */
  public void pop() {
    if (this.fileSystem.getDirectoryStack().size() == 0)
      return;
    String popdDirectory = this.fileSystem.popFromDirectoryStack();
    this.changeDirectory.changeDirectory(popdDirectory);
  }


  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the popd command
   */
  public String toString() {
    return "popd - Remove the top entry from the directory stack, and cd into "
        + "it. The removal must be consistent as per the LIFO behavior of  a "
        + "stack.  The popd command removes the top most directory from the "
        + "directory stack and makes it the current working directory.  If "
        + "there is no directory onto the stack, then give appropriate error " 
        + "message. ";
  }
}
