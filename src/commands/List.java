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
import filesystem.File;
import java.util.HashMap;
import exceptions.InvalidParamsException;
import filesystem.Directory;

public class List implements Command {

  private FileSystem fileSystem;

  @Override
  public void run(java.util.List<String> tokens) throws InvalidParamsException {
    if (tokens.size() > 1) {
      if (tokens.get(1).equals("-R") && tokens.size() == 2) {
        recursiveList(".");
      } else if (tokens.get(1).equals("-R")) {
        int j = 2;
        // Only performs on valid directories
        while (j < tokens.size() && valid(tokens.get(j))) {
          recursiveList(tokens.get(j));
          j++;
        }

        // If tokens contains an invalid directory throw exception
        if (j != tokens.size())
          throw new InvalidParamsException();
      } else {
        int i = 1;
        // Only performs on valid directories
        while (i < tokens.size() && valid(tokens.get(i))) {
          list(tokens.get(i));
          i++;
        }

        // If tokens contains an invalid directory throw exception
        if (i != tokens.size())
          throw new InvalidParamsException();
      }
    } else {
      list(".");
    }
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
   * Constructor for List with an already existing FileSystem
   * 
   * @param fileSystem The FileSystem being used
   */
  public List(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  /**
   * Responsible for listing the files for a given path
   * 
   * @param path String that is the directory path that is to be printed
   */
  public void list(String path) {
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

    for (int i = 0; i < pathList.length; i++) {
      if (pathList[i].equals("."))
        continue;
      // Go to parent directory
      if (pathList[i].equals("..")) {
        if (currentDirectory.getParentDirectory() != null) {
          // If current directory isn't the root
          currentDirectory = currentDirectory.getParentDirectory();
        }
      } else {
        currentDirectory = currentDirectory.getDirectory(pathList[i]);
        if (currentDirectory == null) {
          System.out.println("Directory \"" + pathList[i] + "\" was not " +
        "found.");
          return;
        }
      }
    }

    HashMap<String, Directory> subdirectories = currentDirectory.
        getSubdirectories();
    HashMap<String, File> files = currentDirectory.getFiles();

    System.out.print(currentDirectory.getAbsolutePath() + ": ");

    subdirectories.forEach((key, value) -> System.out.print(key + ", "));

    files.forEach((key, value) -> System.out.print(key + ", "));
    System.out.print("\n");
  }


  /**
   * Responsible for listing the files for a given path recursively give -R
   * 
   * @param path String that is the directory path that is to be printed
   */
  public void recursiveList(String path) {
    Directory currentDirectory;
    String[] pathList;
    String originalPath = path;

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

    for (int i = 0; i < pathList.length; i++) {
      if (pathList[i].equals("."))
        continue;
      // Go to parent directory
      if (pathList[i].equals("..")) {
        if (currentDirectory.getParentDirectory() != null) {
          // If current directory isn't the root
          currentDirectory = currentDirectory.getParentDirectory();
        }
      } else {
        currentDirectory = currentDirectory.getDirectory(pathList[i]);
        if (currentDirectory == null) {
          System.out.println("Directory \"" + pathList[i] + "\" was not " 
        + "found.");
          return;
        }
      }
    }

    HashMap<String, Directory> subdirectories = currentDirectory.
        getSubdirectories();
    HashMap<String, File> files = currentDirectory.getFiles();

    System.out.print(currentDirectory.getAbsolutePath() + ": ");

    subdirectories.forEach((key, value) -> System.out.print(key + ", "));

    files.forEach((key, value) -> System.out.print(key + ", "));

    System.out.print("\n");

    subdirectories.forEach((key, value) -> recursiveList(originalPath + "/" 
    + key));
  }


  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the ls command
   */
  public String toString() {
    return "ls [PATH ..] - If no paths are given, print the contents "
        + "(file or directory) of the current directory, with a new line "
        + "following each of the content (file or directory). "
        + "Otherwise, for each path p, the order listed: \n" + "- If p "
        + "speci:ies a file, print p\n"
        + "- f p speci:ies a directory, print p, a colon, then the contents "
        + "of that directory, then an extra new line.\n"
        + "- If p does not exist, print a suitable message.";
  }
}
