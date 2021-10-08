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

import filesystem.*;
import java.util.List;

/**
 * This is the class for the Echo command that is used within the JShell
 */
public class Echo implements Command {

  private FileSystem fileSystem;

  /**
   * Constructor for Echo with an already existing FileSystem
   * 
   * @param fileSystem FileSystem to be used
   */
  public Echo(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  @Override
  public void run(List<String> tokens) {
    if (tokens.size() == 2) {
      echoPrintToShell(tokens.get(1).substring(1, tokens.get(1).length() - 1));
    } else if (tokens.get(2).contains(">>")) {
      echoAppendToFile(tokens.get(1).substring(1, tokens.get(1).length() - 1), 
          tokens.get(3));
    } else if (tokens.get(2).contains(">")) {
      echoOverwriteToFile(tokens.get(1).substring(1, tokens.get(1).
          length() - 1), tokens.get(3));
    }
  }


  /**
   * Basic echo command that prints to the shell windows
   * 
   * @param argument String that is to be printed to the console window
   */
  public void echoPrintToShell(String argument) {
    System.out.println(argument);
  }

  /**
   * Echo command that overwrites to the file
   * 
   * @param argument String that is to be added to the file
   * @param path String that has the file that argument is to be overwritten to
   */
  public void echoOverwriteToFile(String argument, String path) {
    File file = getFinalFile(path);
    file.setFileContents(argument);
  }

  /**
   * Echo command that appends to a file
   * 
   * @param argument String that is to be added to the file
   * @param path String that has the file that argument is to be appended to
   */
  public void echoAppendToFile(String argument, String path) {
    File file = getFinalFile(path);
    String fileContents = file.getFileContents();
    if (fileContents.equals("")) {
      file.setFileContents(argument);
      return;
    }
    file.setFileContents(fileContents + "\n" + argument);
  }

  /**
   * Helper function that is responsible for obtaining the file that is 
   * specified by either a
   * relative or absolute path
   * 
   * @param path String that contains the path of the desired file
   * @return The file that is specified by path
   */
  public File getFinalFile(String path) {
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


    // if it gave file name
    if (!(path.contains("/"))) {

      File file = currentDirectory.getFile(path);

      if (file == null) {
        file = new File(path);
        currentDirectory.addFile(path, file);
      }

      return file;
    }


    pathList = path.split("/");

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
        if (currentDirectory.getDirectory(pathList[i]) == null) {
          if (i == pathList.length - 1) {
            break;
          } else {
            System.out.println("Directory \"" + pathList[i] + 
                "\" was not found.");
            return null;
          }
        } else {
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
        }
      }
    }

    // The desired file is at the last index of pathList so mark it and
    // return it
    File file = currentDirectory.getFile(pathList[pathList.length - 1]);

    if (file == null) {
      file = new File(pathList[pathList.length - 1]);
      currentDirectory.addFile(pathList[pathList.length - 1], file);
    }

    return file;
  }


  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the echo command
   */
  public String toString() {
    return "echo STRING [> OUTFILE] - If OUTFILE is not provided, print STRING"
        + " on the shell. Otherwise, put    STRING into :ile OUTFILE. STRING is"
        + " a string of characters surrounded by double  quotation marks. This "
        + "creates a new :ile if OUTFILE does not exists and erases the old "
        + "contents if OUTFILE already exists. In either case, the only   "
        + "thing in OUTFILE should be STRING.  \n"
        + "echo STRING>>OUTFILE - Like the previous command, but appends "
        + "instead of overwrites. ";
  }

}
