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

import filesystem.Directory;
import filesystem.FileSystem;
import java.util.List;

/*
 * This is the class for the cd command used within the JShell
 */
public class ChangeDirectory implements Command {

  private FileSystem fileSystem;

  /**
   * Constructor for the ChangeDirectory class
   * 
   * @param fileSystem the fileSystem to interact with
   */
  public ChangeDirectory(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  @Override
  public void run(List<String> tokens) {
    changeDirectory(tokens.get(1));
  }


  /**
   * Changes the current directory of the file system
   * 
   * @param path String of the path desired
   */
  public void changeDirectory(String path) {

    // Checking if the path given corresponds to the root
    if (path.equals("/")) {
      fileSystem.setCurrentDirectory(this.fileSystem.getRoot());
      return;
    }

    Directory currentDirectory;
    String[] pathList;

    // Decide which directory to start from
    if (path.startsWith("/")) {
      path = path.substring(1);
      // Sets start to root directory
      currentDirectory = fileSystem.getRoot();
    } else {
      // Sets start to the current directory
      currentDirectory = fileSystem.getCurrentDirectory();
    }

    // Creating an array of all the directory headers in the path to traverse
    // through
    pathList = path.split("/");

    // Traverse the file system until you find the directory specified
    for (int i = 0; i < pathList.length; i++) {
      // Stay in same directory
      if (pathList[i].equals(".")) {
        continue;
        // Go to parent directory
      } else if (pathList[i].equals("..")) {
        if (currentDirectory.getParentDirectory() != null) {
          // If current directory isn't the root
          currentDirectory = currentDirectory.getParentDirectory();
        } else {
          // If is root just skip this step
          continue;
        }
      } else {
        currentDirectory = currentDirectory.getDirectory(pathList[i]);
        if (currentDirectory == null) {
          System.out.println("Directory \"" + pathList[i] + 
              "\" was not found.");
          return;
        } else {
        }
      }
    }

    fileSystem.setCurrentDirectory(currentDirectory);
  }

  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the cd command
   */
  public String toString() {
    return "cd DIR - Change directory to DIR, which may be relative to the "
        + "current directory or maybe a full path. As with Unix, .. means a "
        + "parent directory and a . means the current directory. The directory"
        + " must be /, the forward slash. The foot of the file system is a" + ""
            + " single slash: /.";
  }
}
