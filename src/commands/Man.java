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

import java.util.HashMap;
import java.util.List;
import filesystem.*;

/**
 * This is the class for the man command used within the JShell
 */
public class Man {

  /**
   * List of commands that the user wants help with
   */
  private String command;
  private FileSystem fileSystem;

  /**
   * Constructor for Man with an already existing command list
   * 
   * @param command List of commands that the user needs help with
   */
  public Man(String command, FileSystem filesystem) {
    this.command = command;
    this.fileSystem = filesystem;
  }

  /**
   * Constructor for Man with no command list
   */
  public Man() {
    this.command = null;
  }

  public void run(List<String> tokens) {
    print(tokens.get(1));

  }

  /**
   * Responsible for printing the man page for each command in the shell
   */
  public void print(String param) {
    HashMap<String, Object> commands = new HashMap<String, Object>();
    History history = new History();
    commands.put("exit", new Exit());
    commands.put("mkdir", new MakeDirectory(this.fileSystem));
    commands.put("ls", new commands.List(this.fileSystem));
    commands.put("cd", new ChangeDirectory(this.fileSystem));
    commands.put("pwd", new PrintWorkingDirectory(this.fileSystem));
    commands.put("history", history);
    commands.put("tree", new Tree(this.fileSystem));
    commands.put("rm", new Remove(this.fileSystem));
    commands.put("pushd", new Pushd(this.fileSystem));
    commands.put("popd", new Popd(this.fileSystem));
    commands.put("echo", new Echo(this.fileSystem));
    commands.put("cat", new Cat(this.fileSystem));
    commands.put("man", new Man());
    commands.put("curl", new Curl(new Echo(this.fileSystem)));
    commands.put("saveJShell", new SaveJShell(this.fileSystem, history));
    commands.put("loadJShell", new LoadJShell());
    commands.put("mv", new Move(this.fileSystem));
    commands.put("cp", this.fileSystem);
    if (commands.containsKey(param)) {
      System.out.println(commands.get(param).toString());
    } else {
      System.out.println("The command \"" + param + "\" does not exist.");
    }
  }

  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the man command
   */
  public String toString() {
    return "man CMD [CMD2...] - Print documentation for CMD(s)";
  }
}
