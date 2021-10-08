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

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import filesystem.FileSystem;
import java.util.List;

/**
 * This class saves the current state of the JShell for later use.
 */
public class SaveJShell implements Command {

  private FileSystem fileSystem;
  private History history;


  public SaveJShell(FileSystem fileSystem, History history) {
    this.fileSystem = fileSystem;
    this.history = history;
  }


  @Override
  public void run(List<String> tokens) {
    save(this.fileSystem, this.history, tokens.get(1));
  }


  /**
   * This method saves the current state of the JShell in a file fileName so 
   * that the user can
   * return back to a previous session if they desire.
   * 
   * @param fileSystem current file system with all the files and directories
   * @param history the history of all the commands that have been called
   * @param fileName the name of the file to which to save the JShell data
   */
  public void save(FileSystem fileSystem, History history, String fileName) {
    try {
      FileOutputStream fileOut = new FileOutputStream(fileName);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      ArrayList<Object> list = new ArrayList<Object>();

      // Add items you are saving to a list
      list.add(fileSystem);
      list.add(history);

      // Write serialized object to file
      out.writeObject(list);
      out.close();
      fileOut.close();
    } catch (Exception e) {
      System.out.println("Attempt to save JShell was unsuccessful.");
    }
  }


  public String toString() {
    return "saveJShell FileName - Saves the current state of your JShell"
        + " in a file that can be loaded in another session.";
  }
}
