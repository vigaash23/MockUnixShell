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
import java.util.HashMap;

/**
 * This class is responsible for representing a folder in JShell
 */
public class Directory implements Serializable {

  private static final long serialVersionUID = 1L;

  // Hashmaps of subdirectories and files
  private HashMap<String, Directory> subdirectories;
  private HashMap<String, File> files;

  // The name of the directory
  private String directoryName;
  // The name of the parent directory
  private Directory parentDirectory;

  /**
   * Constructor for Directory class
   * 
   * @param name the name of the directory
   * @param parentDirectory the name of the parent directory
   */
  public Directory(String name, Directory parentDirectory) {
    this.subdirectories = new HashMap<String, Directory>();
    this.files = new HashMap<String, File>();
    this.directoryName = name;
    this.parentDirectory = parentDirectory;
  }


  /**
   * Get the directory name
   * 
   * @return String name of the directory
   */
  public String getDirectoryName() {
    return this.directoryName;
  }


  /**
   * Get the parent directory
   * 
   * @return Directory the parent Directory
   */
  public Directory getParentDirectory() {
    return this.parentDirectory;
  }


  /**
   * Get subdirectories
   * 
   * @return HashMap the subdirectories in this directory
   */
  public HashMap<String, Directory> getSubdirectories() {
    return this.subdirectories;
  }


  /**
   * Get files
   * 
   * @return HashMap the files in this directory
   */
  public HashMap<String, File> getFiles() {
    return this.files;
  }


  /**
   * Get a Directory from subdirectories
   * 
   * @return Directory a directory from subdirectories
   */
  public Directory getDirectory(String dirName) {
    return this.subdirectories.get(dirName);
  }


  /**
   * Get a File from files
   * 
   * @return File a file from files
   */
  public File getFile(String fileName) {
    return this.files.get(fileName);
  }


  /**
   * Remove file from files
   */
  public void removeFile(String fileName) {
    this.files.remove(fileName);
  }


  /**
   * Insert new subdirectory into hashmap
   */
  public void addSubdirectory(Directory directory) {
    if (!this.subdirectories.containsKey(directory.getDirectoryName())) {
      this.subdirectories.put(directory.getDirectoryName(), directory);
    }
  }


  /**
   * Add new file into hashmap
   */
  public void addFile(String fileName, File file) {
    if (!this.files.containsKey(fileName)) {
      this.files.put(fileName, file);
    }
  }


  /**
   * Gets the absolute path of the directory
   * 
   * @return String absolute path of the directory
   */
  public String getAbsolutePath() {
    return this.parentDirectory == null ? this.directoryName
        : this.parentDirectory.getAbsolutePath() + this.directoryName + "/";
  }


  /**
   * Get rid of all subdirectories
   */
  public void emptySubdirectories() {
    this.subdirectories.clear();
  }


  /**
   * Remove subdirectory from directory
   */
  public void removeSubdirectory(Directory directory) {
    this.subdirectories.remove(directory.getDirectoryName());
  }


  /**
   * Set the parent directory
   */
  public void setParentDirectory(Directory directory) {
    this.parentDirectory = directory;
  }


  /**
   * Sets the directory name
   */
  public void setName(String name) {
    this.directoryName = name;
  }


  /**
   * Sets the subdirectories
   */
  public void setSubdirectories(HashMap<String, Directory> sub) {
    this.subdirectories = sub;
  }


  /**
   * Set the files
   */
  public void setFiles(HashMap<String, File> files) {
    this.files = files;
  }
}
