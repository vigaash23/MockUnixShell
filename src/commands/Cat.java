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

import java.util.ArrayList;
import java.util.List;
import exceptions.InvalidParamsException;
import filesystem.Directory;
import filesystem.File;
import filesystem.FileSystem;

/**
 * This is the class for the cat command that is used within the JShell
 */
public class Cat implements Command {

  private FileSystem fileSystem;

  /**
   * Constructor for Cat with an already existing FileSystem
   * 
   * @param fileSystem The FileSystem being used
   */
  public Cat(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  @Override
  public void run(List<String> tokens) throws InvalidParamsException {
    int i = 1;
    List<String> toConcat = new ArrayList<String>();

    // Only performs on valid directories
    while (i < tokens.size() && valid(tokens.get(i))) {
      toConcat.add(tokens.get(i));
      i++;
    }

    concatenate(toConcat);

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
   * Prints the contents of the file to the console
   * 
   * @param files List of all files that should have their contents displayed
   */
  public void concatenate(List<String> files) throws InvalidParamsException {
    // Loops through all the files in the String array argument and prints
    // the contents of each file that was used as an argument
    boolean invalidFileName = false;
    String invalidFileOutput = "";
    for (int i = 0; i < files.size(); i++) {
      if (getFinalFile(files.get(i)) != null) {
        System.out.println(getFinalFile(files.get(i)).getFileContents() + 
            "\n\n\n");
      } else {
        throw new InvalidParamsException();
      }
    }
  }


  /**
   * Helper function that retrieves the file based off whether the argument 
   * given was the file name
   * or the absolute path of the file. Responsible for handling both cases.
   * 
   * @param path String that contains the path of the desired file
   * @return File that is specified by path
   */
  public File getFinalFile(String path) {
    Directory currentDirectory;
    String[] pathList;

    // Decides which directory to start from
    if (path.startsWith("/")) {
      path = path.substring(1);
      currentDirectory = fileSystem.getRoot();
    } else
      currentDirectory = fileSystem.getCurrentDirectory();

    // Checking if the file name was given instead of the path name
    if (path.contains("/") == false) {
      File file = currentDirectory.getFile(path);
      return file;
    }

    pathList = path.split("/");

    for (int i = 0; i < pathList.length; i++) {
      // Stay in same directory
      if (pathList[i].equals("."))
        continue;
      // Go to parent directory
      else if (pathList[i].equals("..")) {
        if (currentDirectory.getParentDirectory() != null) {
          // If current directory isn't the root
          currentDirectory = currentDirectory.getParentDirectory();
        } else
          continue;

      } else {
        if (currentDirectory.getDirectory(pathList[i]) == null) {
          if (i == pathList.length - 1)
            break;
          else {
            System.out.println("Directory \"" + pathList[i] + "\" was not " +
          "found.");
            return null;
          }
        } else
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
      }
    }

    // In the pathList the desired file is the one at the very end so
    // mark it and return it
    File file = currentDirectory.getFile(pathList[pathList.length - 1]);
    return file;
  }

  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the cat command
   */
  public String toString() {
    return "cat FILE1 [FILE2 ..] - Display the contents of FILE1 and other "
        + "files (i.e. File2 ....) concatenated in the shell. You may want to "
        + "use three line breaks to separate the contents of one file from the"
        + " other file.  ";
  }
}
