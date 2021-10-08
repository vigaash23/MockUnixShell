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
import exceptions.InvalidParamsException;
import filesystem.Directory;
import filesystem.FileSystem;

public class MakeDirectory implements Command {

  private FileSystem fileSystem;

  public MakeDirectory(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  @Override
  public void run(List<String> tokens) throws InvalidParamsException {
    int i = 1;

    // Only performs on valid directories
    while (i < tokens.size() && valid(tokens.get(i))) {
      makeDirectory(tokens.get(i));
      i++;
    }

    // If tokens contains an invalid directory throw exception
    if (i != tokens.size())
      throw new InvalidParamsException();
  }


  /**
   * Checks if the current token contains invalid characters.
   * 
   * @param token the token that the user inputed
   * @return True or False on whether the token contains invalid characters.
   */
  private boolean valid(String token) {
    String[] invalidChar = {"!", "@", "#", "$", "%", "^", "&", "*", "(", ")",
        "{", "}", "~", "|",
        "<", ">", "?", " ", "\""};
    int index = 0;

    for (String s : invalidChar) {
      index = token.indexOf(s);
      if (index != -1)
        return false;
    }

    return true;
  }


  /**
   * Creates a new directory inside of the specified path
   * 
   * @param path String that is the path where the new directory is to be 
   * created
   */
  private void makeDirectory(String path) {
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

    pathList = path.split("/");

    for (int i = 0; i < pathList.length - 1; i++) {
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
        if (currentDirectory.getDirectory(pathList[i]) == null) {
          currentDirectory.addSubdirectory(new Directory(pathList[i], 
              currentDirectory));
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
        } else {
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
        }
      }
    }

    if (currentDirectory.getDirectory(pathList[pathList.length - 1]) != null) {
      System.out.println("The directory \"" + pathList[pathList.length - 1] + 
          "\" already exists.");
      return;
    }

    currentDirectory
        .addSubdirectory(new Directory(pathList[pathList.length - 1], 
            currentDirectory));
  }


  public String toString() {
    return "mkdir DIR1 DIR2 - This command takes in two arguments only. Create "
        + "directories, each of which may be relative to the current directory"
        + " or may be a full path. If creating DIR1 results in any kind of "
        + "error, do not proceed with creating DIR 2. However, if DIR1 was "
        + "created successfully, and DIR2 creation results in an error, then "
        + "give back an error specific to DIR2.  ";
  }
}
