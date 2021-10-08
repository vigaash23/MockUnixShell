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
 * This class is responsible for copying files and directories to new locations.
 *
 */
public class Copy implements Command {

  // The filesystem that we are using
  private FileSystem fileSystem;

  /**
   * Constructor for Copy class
   * 
   * @param fileSystem
   */
  public Copy(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }


  @Override
  public void run(List<String> tokens) {
    Directory dir1 = getDirectory(tokens.get(1));
    Directory dir2 = getDirectory(tokens.get(2));

    if (dir1 != null && dir2 != null)
      copyDirectoryToDirectory(tokens.get(1), tokens.get(2));
    else if (dir1 == null && dir2 != null)
      copyFileToDirectory(tokens.get(1), tokens.get(2));
    else if (dir1 == null && dir2 == null)
      copyFileToFile(tokens.get(1), tokens.get(2));
  }


  /**
   * Called if the used wants to copy a directory into another directory
   * 
   * @param src the directory to be copied
   * @param dest the new location to copy to
   */
  public void copyDirectoryToDirectory(String src, String dest) {
    Directory srcDir = getDirectory(src);
    Directory destDir = getDirectory(dest);

    if (srcDir == null)
      return; // Source doesn't exist

    if (destDir != null && srcDir != null) { // Both dir exist
      Directory newDir = new Directory(srcDir.getDirectoryName(), destDir);
      newDir.setSubdirectories(srcDir.getSubdirectories());
      newDir.setFiles(srcDir.getFiles());
      destDir.addSubdirectory(newDir);
    }
  }

  /**
   * Called if the used wishes to copy a file into another directory.
   * 
   * @param src the file use wants to copy
   * @param dest the directory to copy the file into
   */
  public void copyFileToDirectory(String src, String dest) {
    if (src.contains("/")) {
      Directory srcDir = getDirectory(src.substring(0, src.lastIndexOf("/")));

      File srcFile = srcDir.getFile(src.substring(src.lastIndexOf("/") + 1));
      Directory destDir = getDirectory(dest);

      if (destDir == null || srcFile == null)
        return;

      File newFile = new File(srcFile.getFileName());
      newFile.setFileContents(srcFile.getFileContents());
      destDir.addFile(newFile.getFileName(), newFile);

    } else {
      File srcFile = this.fileSystem.getCurrentDirectory().getFile(src);
      Directory destDir = getDirectory(dest);

      if (destDir == null || srcFile == null)
        return;

      File newFile = new File(srcFile.getFileName());
      newFile.setFileContents(srcFile.getFileContents());
      destDir.addFile(newFile.getFileName(), newFile);
    }
  }


  /**
   * Called if the user wants to rename a file or overwrite another file.
   * 
   * @param src the file that the user wants to copy
   * @param dest the destination file to copy to
   */
  public void copyFileToFile(String src, String dest) {
    if (src.contains("/")) {
      Directory srcDir = getDirectory(src.substring(0, src.lastIndexOf("/")));
      File srcFile = srcDir.getFile(src.substring(src.lastIndexOf("/") + 1));

      if (dest.contains("/")) {
        Directory destDir = getDirectory(dest.
            substring(0, dest.lastIndexOf("/")));
        File destFile = destDir.getFile(dest.
            substring(dest.lastIndexOf("/") + 1));

        if (srcFile == null)
          return;
        if (srcFile != null && destFile == null) {
          destFile = new File(srcFile.getFileName());
          destFile.setFileContents(srcFile.getFileContents());
          destDir.addFile(destFile.getFileName(), destFile);
        } else if (srcFile != null && destFile != null) {
          destFile.setFileContents(srcFile.getFileContents());
          if (srcFile != null && destFile != null) {
            destFile.setFileContents(srcFile.getFileContents());
          }
        }
      } else {
        File destFile = this.fileSystem.getCurrentDirectory().getFile(dest);
        if (srcFile != null && destFile != null) {
          destFile.setFileContents(srcFile.getFileContents());
        }
      }
    } else {
      File srcFile = this.fileSystem.getCurrentDirectory().getFile(src);
      Directory destDir = this.fileSystem.getCurrentDirectory();
      File destFile = this.fileSystem.getCurrentDirectory().getFile(dest);

      if (srcFile != null && destFile != null) {
        destFile.setFileContents(srcFile.getFileContents());
      } else if (srcFile != null && destFile == null) {
        destFile = new File(dest);
        destFile.setFileContents(srcFile.getFileContents());
        destDir.addFile(dest, destFile);
      }
    }
  }


  public Directory getDirectory(String path) {
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
          return null;
        } else {
          currentDirectory = currentDirectory.getDirectory(pathList[i]);
        }
      }
    }

    return currentDirectory;
  }


  /**
   * Sets which directory to start from when traversing the file system.
   * 
   * @param path the path that we want to traverse
   * @return the directory that we are starting in
   */
  public Directory setStartingDirectory(String path) {
    return path.startsWith("/") ? fileSystem.getRoot() : 
      fileSystem.getCurrentDirectory();
  }


  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the cat command
   */
  public String toString() {
    return "cp OLDPATH NEWPATH -Cp is like mv, but don't remove OLDPATH. If "
        + "OLDPATH is a directory," + " recursively copy the contents.";
  }

}
