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
import java.util.ArrayList;
import java.util.*;
import filesystem.*;

/**
 * This class is responsible for searching the JShell file system
 */
public class Search {
  // The filesystem that we are using
  private FileSystem fileSystem;


  public void run(List<String> tokens) {
    ArrayList<String> hold = new ArrayList<String>();
    int i = 1;
    if (!(tokens.get(tokens.size() - 4)).equals("-type")) {
      System.out.println("You mistyped -type, please try again.");
      return;
    }
    while (!(tokens.get(i).equals("-type"))) {
      hold.add(tokens.get(i));
      i++;

    }
    if (!(tokens.get(tokens.size() - 2)).equals("-name")) {
      System.out.println("You mistyped -name, however we will still run search"
          + ", please fix this mistake next time");
    }
    searchFileSystem(hold, tokens.get(i + 1),
        tokens.get(i + 3).substring(1, tokens.get(i + 3).length() - 1));



  }


  public Search(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }

  /**
   * Sets the starting directory to start from.
   * 
   * @param path the path to traverse
   * @return
   */
  private Directory setStartingDirectory(String path) {
    return path.startsWith("/") ? fileSystem.getRoot() : fileSystem.
        getCurrentDirectory();
  }


  public Directory getToDirectory(String path) {
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
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
        } else {
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
        }
      }
    }
    return currentDirectory;
  }


  /**
   * Searches for the file in the fileSystem
   * 
   * @param path the path to start from
   * @param file the file you are searching for
   */
  public void fileSearch(String path, String file) {
    Directory currentDirectory = getToDirectory(path);
    if (currentDirectory.getFile(file) != null) {
      System.out.println(currentDirectory.getAbsolutePath() + file);
    }
    for (String key : currentDirectory.getSubdirectories().keySet()) {
      fileSearch(currentDirectory.getSubdirectories().get(key).
          getAbsolutePath(), file);
    }

  }

  /**
   * Searches for the directory in the fileSystem
   * 
   * @param path the path to start from
   * @param dir the directory you are searching for
   */
  public void dirSearch(String path, String dir) {
    Directory currentDirectory = getToDirectory(path);
    if (currentDirectory.getDirectory(dir) != null) {
      System.out.println(currentDirectory.getAbsolutePath() + dir);
    }
    for (String key : currentDirectory.getSubdirectories().keySet()) {
      dirSearch(currentDirectory.getSubdirectories().get(key).
          getAbsolutePath(), dir);
    }

  }


  public void searchFileSystem(ArrayList<String> path, String type, 
      String name) {

    for (int i = 0; i < path.size(); i++) {
      if (type.equals("f")) {
        fileSearch(path.get(i), name);
      } else if (type.equals("d")) {
        dirSearch(path.get(i), name);
      } else {
        System.out.println("You have an invalid parameter please try agin.");
      }
    }
  }


  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the pushd command
   */
  public String toString() {
    return "search path ...  -type [f|d] -name expression - searches for " + 
  "desired" + "file";
  }

}
