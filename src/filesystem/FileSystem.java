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
package filesystem;

import java.io.Serializable;
import java.util.Stack;

/**
 * The structure of the file system that JShell operates on
 */
public class FileSystem implements Serializable {

  private static final long serialVersionUID = 1L;
  // The root of the file system
  private Directory rootDirectory;
  // The current directory the user is in
  private Directory currentDirectory;
  // Used for popd and pushd
  private Stack<String> directoryStack;

  /**
   * The constructor for File System
   */
  public FileSystem() {
    this.rootDirectory = new Directory("/", null);
    this.currentDirectory = this.rootDirectory;
    this.directoryStack = new Stack<String>();
  }


  /**
   * Returns the current Directory
   * @return Directory the current Directory the user is in
   */
  public Directory getCurrentDirectory() {
    return this.currentDirectory;
  }


  /**
   * Returns the root Directory
   * @return Directory the root Directory of the File System
   */
  public Directory getRoot() {
    return this.rootDirectory;
  }


  /**
   * Sets the current directory of the filesystem
   * @param currentDirectory    the new current directory to set
   */
  public void setCurrentDirectory(Directory currentDirectory) {
    this.currentDirectory = currentDirectory;
  }


  /**
   * Adds to the directory stack
   * @param newDirectoryPath   what to add to directory stack
   */
  public void addToDirectoryStack(String newDirectoryPath) {
    this.directoryStack.add(newDirectoryPath);
  }


  /**
   * Gets the directory stack
   * @return  Stack the directory stack
   */
  public Stack<String> getDirectoryStack() {
    return this.directoryStack;
  }


  /**
   * Gets the top elements from the stack
   * @return String top elements from directory Stack
   */
  public String popFromDirectoryStack() {
    return this.directoryStack.pop();
  }
}
