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

/**
 * This class is responsible for removing directories from the file system
 */
public class Remove implements Command {

  // The filesystem that we are using
  private FileSystem fileSystem;

  /**
   * Constructor for Remove class
   * 
   * @param fileSystem the file system we want to use
   */
  public Remove(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  @Override
  public void run(List<String> tokens) {
    remove(tokens.get(1));
  }

  /**
   * Sets the starting directory for traversing the file system
   * 
   * @param path the path we want to traverse
   * @return the directory to start in
   */
  public Directory setStartingDirectory(String path) {
    return path.startsWith("/") ? fileSystem.getRoot() : fileSystem.
        getCurrentDirectory();
  }


  /**
   * Removes a directory from the file system
   * 
   * @param path the path we want to remove
   */
  public void remove(String path) {
    Directory currentDirectory = setStartingDirectory(path);

    if (path.startsWith("/"))
      path = path.substring(1);

    String[] pathList = path.split("/");

    for (int i = 0; i < pathList.length; i++) {
      if (pathList[i].equals(".")) {
        continue;
      } else if (pathList[i].equals("..")) {
        if (currentDirectory.getParentDirectory() != null) {
          // If current directory isn't the root
          currentDirectory = currentDirectory.getParentDirectory();
        } else {
          continue;
        }
      } else {
        if (currentDirectory.getDirectory(pathList[i]) == null) {
          currentDirectory.addSubdirectory(new Directory(pathList[i], 
              currentDirectory));
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
        } else {
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
        }
      }
    }

    currentDirectory.emptySubdirectories();
    currentDirectory.getParentDirectory().removeSubdirectory(currentDirectory);
  }

  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the pushd command
   */
  public String toString() {
    return "rm DIR -removes the DIR from the file system.  "
        + "This also removes all the children of DIR.";
  }
}
