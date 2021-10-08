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
package interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import commands.ChangeDirectory;
import commands.Copy;
import commands.Curl;
import commands.Cat;
import commands.Echo;
import commands.Exit;
import commands.History;
import commands.LoadJShell;
import commands.MakeDirectory;
import commands.Move;
import commands.Popd;
import commands.PrintWorkingDirectory;
import commands.Pushd;
import commands.Remove;
import commands.SaveJShell;
import commands.Tree;
import commands.Search;
import commands.Man;
import exceptions.InvalidParamsException;
import exceptions.WrongNumParamsException;
import filesystem.FileSystem;

public class Interpreter {

  private List<String> tokens;
  private static HashMap<String, Object> commands;
  private FileSystem fileSystem;
  private History history; // Need history here in order to keep adding to it


  public Interpreter(FileSystem fileSystem) {
    tokens = new ArrayList<String>();
    this.fileSystem = fileSystem;
    this.history = new History();
    generateCommands();
  }


  /**
   * Determines which command the user wanted to run and calls the corresponding
   *  method. Also let's
   * used know if their input was invalid.
   * 
   * @param userInput String of input that the user has entered @throws
   */
  public void interpret(String userInput) {

    tokenizeUserInput(userInput);
    Object command = commands.get(tokens.get(0));
    history.addInput(userInput);

    try {
      if (command instanceof Exit && ErrorChecker.checkForErrors(command, 
          tokens)) {
        Exit.class.cast(command).run(tokens);
      } else if (command instanceof MakeDirectory && ErrorChecker.
          checkForErrors(command, tokens)) {
        MakeDirectory.class.cast(command).run(tokens);
      } else if (command instanceof commands.List && ErrorChecker.
          checkForErrors(command, tokens)) {
        commands.List.class.cast(command).run(tokens);
      } else if (command instanceof ChangeDirectory
          && ErrorChecker.checkForErrors(command, tokens)) {
        ChangeDirectory.class.cast(command).run(tokens);
      } else if (command instanceof PrintWorkingDirectory
          && ErrorChecker.checkForErrors(command, tokens)) {
        PrintWorkingDirectory.class.cast(command).run(tokens);
      } else if (command instanceof History && ErrorChecker.
          checkForErrors(command, tokens)) {
        History.class.cast(command).run(tokens);
      } else if (command instanceof Tree && ErrorChecker.
          checkForErrors(command, tokens)) {
        Tree.class.cast(command).run(tokens);
      } else if (command instanceof Remove && ErrorChecker.
          checkForErrors(command, tokens)) {
        Remove.class.cast(command).run(tokens);
      } else if (command instanceof Pushd && ErrorChecker.
          checkForErrors(command, tokens)) {
        Pushd.class.cast(command).run(tokens);
      } else if (command instanceof Popd && ErrorChecker.
          checkForErrors(command, tokens)) {
        Popd.class.cast(command).run(tokens);
      } else if (command instanceof SaveJShell && ErrorChecker.
          checkForErrors(command, tokens)) {
        SaveJShell.class.cast(command).run(tokens);
      } else if (command instanceof LoadJShell && ErrorChecker.
          checkForErrors(command, tokens)) {
        ArrayList<Object> loadedObjects = LoadJShell.class.cast(command).
            load(tokens.get(1));
        this.fileSystem = (FileSystem) loadedObjects.get(0);
        this.history = (History) loadedObjects.get(1);
        generateCommands();
      } else if (command instanceof Echo && ErrorChecker.
          checkForErrors(command, tokens)) {
        Echo.class.cast(command).run(tokens);
      } else if (command instanceof Cat && ErrorChecker.
          checkForErrors(command, tokens)) {
        Cat.class.cast(command).run(tokens);
      } else if (command instanceof Man && ErrorChecker.
          checkForErrors(command, tokens)) {
        Man.class.cast(command).run(tokens);
      } else if (command instanceof Curl && ErrorChecker.
          checkForErrors(command, tokens)) {
        Curl.class.cast(command).run(tokens);
      } else if (command instanceof Move && ErrorChecker.
          checkForErrors(command, tokens)) {
        Move.class.cast(command).run(tokens);
      } else if (command instanceof Search && ErrorChecker.
          checkForErrors(command, tokens)) {
        Search.class.cast(command).run(tokens);
      } else if (command instanceof Copy && ErrorChecker.
          checkForErrors(command, tokens)) {
        Copy.class.cast(command).run(tokens);
      } else {
        if (!tokens.get(0).equals(""))
          System.out.println("You have entered an invalid command");
      }
    } catch (WrongNumParamsException e) {
      ErrorHandler.handleWrongNumParamsException(tokens);
    } catch (InvalidParamsException e) {
      ErrorHandler.handleInvalidParamsException(tokens);
    } catch (NullPointerException e) {
    }
  }


  /**
   * Splits the users input into interpreter readable tokens that can 
   * determine what command to run.
   * 
   * @param userInput String of input that the user has entered
   */
  private void tokenizeUserInput(String userInput) {

    tokens = Arrays.asList(userInput.trim().split("\\s+"));
    String hold1 = "";
    String hold2 = "";

    
    if (tokens.get(0).equals("echo")) {
      if (userInput.indexOf("\"") == -1) {
        System.out.println("Invalid string please try again");
      }
      else {
        String string =
            "" + userInput.substring(userInput.indexOf("\""), userInput.
                lastIndexOf("\"") + 1);
        List<String> tokens1 = new ArrayList<String>();
        if (tokens.size() > 3) {
          if ((tokens.get(tokens.size() - 2).equals(">"))
              || (tokens.get(tokens.size() - 2).equals(">>"))) {
            hold1 = tokens.get(tokens.size() - 2);
            hold2 = tokens.get(tokens.size() - 1);
          }
        }

        tokens1.add("echo");
        tokens1.add(string);
        if (!(hold1.equals(""))) {
          tokens1.add(hold1);
          tokens1.add(hold2);
        }

        tokens = tokens1;
      }
      
    }
  }


  /**
   * Populates the commands hash map with all the possible commands that 
   * the user might want to use.
   */
  private void generateCommands() {
    commands = new HashMap<String, Object>();
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
    commands.put("saveJShell", new SaveJShell(this.fileSystem, history));
    commands.put("loadJShell", new LoadJShell());
    commands.put("echo", new Echo(this.fileSystem));
    commands.put("cat", new Cat(this.fileSystem));
    commands.put("man", new Man());
    commands.put("curl", new Curl(new Echo(this.fileSystem)));
    commands.put("cat", new Cat(this.fileSystem));
    commands.put("curl", new Curl(new Echo(this.fileSystem)));
    commands.put("mv", new Move(this.fileSystem));
    commands.put("search", new Search(this.fileSystem));
    commands.put("cp", new Copy(this.fileSystem));
  }
}
