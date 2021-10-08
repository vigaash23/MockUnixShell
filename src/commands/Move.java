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
import filesystem.*;

/**
 * This class moves one directory to another.
 */
public class Move implements Command {

  /**
   * Current fileSystem that we are on
   */
  private FileSystem fileSystem;


  /**
   * Constructor for Move with an already existing command list
   * 
   * @param filesystem that we are using
   */
  public Move(FileSystem fileSystem) {
    this.fileSystem = fileSystem;
  }



  @Override
  public void run(List<String> tokens) {
    Directory dir1 = getDirectory(tokens.get(1));
    Directory dir2 = getDirectory(tokens.get(2));

    if (dir1 == null && dir2 == null) {
      moveFileToFile(tokens.get(1), tokens.get(2));
    } else if (dir1 == null && dir2 != null) {
      moveFileToDirectory(tokens.get(1), tokens.get(2));
    } else if (dir1 != null) {
      moveDirectoryToDirectory(tokens.get(1), tokens.get(2));
    }
  }


  /**
   * Moves directory to directory
   * 
   * @param src is the oldpath and dest is the newpath
   */
  public void moveDirectoryToDirectory(String src, String dest) {
    Directory srcDir = getDirectory(src);
    Directory destDir = getDirectory(dest);

    if (srcDir == null)
      return;

    if (destDir != null && srcDir != null) { // Both dir exist
      Directory newDir = new Directory(srcDir.getDirectoryName(), destDir);
      newDir.setSubdirectories(srcDir.getSubdirectories());
      newDir.setFiles(srcDir.getFiles());
      destDir.addSubdirectory(newDir);
      srcDir.getParentDirectory().removeSubdirectory(srcDir);
    } else if (srcDir != null && destDir == null) {
      if (!dest.contains("/")) {
        srcDir.getParentDirectory().removeSubdirectory(srcDir);
        srcDir.setParentDirectory(this.fileSystem.getCurrentDirectory());
        srcDir.setName(dest);
        this.fileSystem.getCurrentDirectory().addSubdirectory(srcDir);
      } else {
        destDir = getDirectory(dest.substring(0, dest.lastIndexOf("/")));
        if (destDir == null)
          return;
        srcDir.getParentDirectory().removeSubdirectory(srcDir);
        srcDir.setParentDirectory(destDir);
        srcDir.setName(dest.substring(dest.lastIndexOf("/") + 1));
        destDir.addSubdirectory(srcDir);
      }
    }
  }

  /**
   * Moves file to file
   * 
   * @param src is the oldpath and dest is the newpath
   */

  public void moveFileToFile(String src, String dest) {
    Directory srcDir, destDir = null;
    File srcFile, destFile = null;
    if (src.contains("/") && dest.contains("/")) {
      srcDir = getDirectory(src.substring(0, src.lastIndexOf("/")));
      destDir = getDirectory(dest.substring(0, dest.lastIndexOf("/")));
      if (srcDir == null)
        return;
      srcFile = srcDir.getFile(src.substring(src.lastIndexOf("/") + 1));
      destFile = destDir.getFile(dest.substring(dest.lastIndexOf("/") + 1));

      if (srcFile == null)
        return;

      if (destFile != null) {
        srcDir.removeFile(srcFile.getFileName());
        destFile.setFileContents(srcFile.getFileContents());
      } else if (destFile == null) {
        srcDir.removeFile(srcFile.getFileName());
        srcFile.setFileName(dest.substring(dest.lastIndexOf("/") + 1));
        destDir.addFile(dest.substring(dest.lastIndexOf("/") + 1), srcFile);
      }
    } else if (src.contains("/") && !dest.contains("/")) {
      srcDir = getDirectory(src.substring(0, src.lastIndexOf("/")));
      destDir = this.fileSystem.getCurrentDirectory();
      if (srcDir == null)
        return;
      srcFile = srcDir.getFile(src.substring(src.lastIndexOf("/") + 1));
      destFile = destDir.getFile(dest);

      if (srcFile == null)
        return;

      if (destFile != null) {
        srcDir.removeFile(srcFile.getFileName());
        destFile.setFileContents(srcFile.getFileContents());
      } else if (destFile == null) {
        srcDir.removeFile(srcFile.getFileName());
        srcFile.setFileName(dest);
        destDir.addFile(dest, srcFile);
      }
    } else if (!src.contains("/") && dest.contains("/")) {
      srcDir = this.fileSystem.getCurrentDirectory();
      destDir = getDirectory(dest.substring(0, dest.lastIndexOf("/")));
      if (srcDir == null)
        return;
      srcFile = srcDir.getFile(src);
      destFile = destDir.getFile(dest.substring(dest.lastIndexOf("/") + 1));

      if (srcFile == null)
        return;

      if (destFile != null) {
        srcDir.removeFile(srcFile.getFileName());
        destFile.setFileContents(srcFile.getFileContents());
      } else if (destFile == null) {
        srcDir.removeFile(srcFile.getFileName());
        srcFile.setFileName(dest.substring(dest.lastIndexOf("/") + 1));
        destDir.addFile(dest.substring(dest.lastIndexOf("/") + 1), srcFile);
      }
    } else if (!src.contains("/") && !dest.contains("/")) {
      srcDir = this.fileSystem.getCurrentDirectory();
      destDir = this.fileSystem.getCurrentDirectory();
      if (srcDir == null)
        return;
      srcFile = srcDir.getFile(src);
      destFile = destDir.getFile(dest);

      if (srcFile == null)
        return;

      if (destFile != null) {
        srcDir.removeFile(srcFile.getFileName());
        destFile.setFileContents(srcFile.getFileContents());
      } else if (destFile == null) {
        srcDir.removeFile(srcFile.getFileName());
        srcFile.setFileName(dest);
        destDir.addFile(dest, srcFile);
      }
    }
  }


  /**
   * Moves file to directory
   * 
   * @param src is the oldpath and dest is the newpath
   */
  public void moveFileToDirectory(String src, String dest) {
    Directory srcDir, destDir = null;
    File srcFile = null;
    if (src.contains("/") && dest.contains("/")) {
      srcDir = getDirectory(src.substring(0, src.lastIndexOf("/")));
      destDir = getDirectory(dest.substring(0, dest.lastIndexOf("/")));
      if (srcDir == null || destDir == null)
        return;
      srcFile = srcDir.getFile(src.substring(src.lastIndexOf("/") + 1));
      srcDir.removeFile(srcFile.getFileName());
      destDir.addFile(srcFile.getFileName(), srcFile);
    } else if (src.contains("/") && !dest.contains("/")) {
      srcDir = getDirectory(src.substring(0, src.lastIndexOf("/")));
      destDir = this.fileSystem.getCurrentDirectory().getDirectory(dest);
      if (srcDir == null || destDir == null)
        return;
      srcFile = srcDir.getFile(src.substring(src.lastIndexOf("/") + 1));
      srcDir.removeFile(srcFile.getFileName());
      destDir.addFile(srcFile.getFileName(), srcFile);
    } else if (!src.contains("/") && dest.contains("/")) {
      srcDir = this.fileSystem.getCurrentDirectory();
      destDir = getDirectory(dest.substring(0, dest.lastIndexOf("/")));
      if (srcDir == null || destDir == null)
        return;
      srcFile = srcDir.getFile(src);
      srcDir.removeFile(src);
      destDir.addFile(src, srcFile);
    } else if (!src.contains("/") && !dest.contains("/")) {
      srcDir = this.fileSystem.getCurrentDirectory();
      destDir = this.fileSystem.getCurrentDirectory().getDirectory(dest);
      if (srcDir == null || destDir == null)
        return;
      srcFile = srcDir.getFile(src);
      srcDir.removeFile(src);
      destDir.addFile(src, srcFile);
    }
  }

  /**
   * Gets the directory of the need path
   * 
   * @param path is the path of the directory
   */
  private Directory getDirectory(String path) {
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
   * Gets the start directory , used for absolute paths
   * 
   * @param path is the path of the directory
   */
  private Directory setStartingDirectory(String path) {
    return path.startsWith("/") ? fileSystem.getRoot() : fileSystem.
        getCurrentDirectory();
  }

  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the ls command
   */
  public String toString() {
    return "mv OLDPATH NEWPATH - "
        + " Move item OLDPATH to NEWPATH. Both OLD-PATH  and  NEWPATH  may"
        + "  be  relative  to  the  current  directory  or  may  be  full"
        + "  paths. If  NEWPATH  is  adirectory, move the item into the" + 
        " directory.";
  }

}
