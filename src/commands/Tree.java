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
import java.util.HashMap;
import java.util.Stack;
import java.util.List;

/**
 * This class is responsible for showing a visual of the file system.
 */
public class Tree implements Command {

  // The filesystem that we are using
  private FileSystem fileSystem;

  /**
   * Constructor for the Tree class
   * 
   * @param fileSystem the file system we want to use
   */
  public Tree(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  @Override
  public void run(List<String> tokens) {
    tree();
  }


  /**
   * The function that prints the tree of the fileSystem
   */
  public void tree() {
    System.out.println("\\");
    treeRecursive(this.fileSystem.getRoot(), " ");
  }


  /**
   * The recursive helper function that works to help tree.
   * 
   * @param currentDirectory the directory you are currently searching
   * @param shift the amount of spaces to add to the printing
   */
  public void treeRecursive(Directory currentDirectory, String shift) {
    Stack<Directory> stack = new Stack<Directory>();
    HashMap<String, Directory> subdirectories;
    HashMap<String, File> files;

    subdirectories = currentDirectory.getSubdirectories();

    subdirectories.forEach((str, dir) -> {
      stack.add(dir);
    });

    while (!stack.isEmpty()) {
      Directory temp = stack.pop();
      System.out.println(shift + temp.getDirectoryName());
      files = temp.getFiles();
      files.forEach((str, file) -> {
        System.out.println(shift + "  " + file.getFileName());
      });
      treeRecursive(temp, shift + "  ");
    }
  }


  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the pushd command
   */
  public String toString() {
    return "tree - prints the filesystem in the form of a tree";
  }
}
