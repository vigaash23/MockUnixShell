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

import java.util.List;
import commands.Cat;
import commands.ChangeDirectory;
import commands.Copy;
import commands.Curl;
import commands.Echo;
import commands.Exit;
import commands.History;
import commands.LoadJShell;
import commands.MakeDirectory;
import commands.Man;
import commands.Move;
import commands.Popd;
import commands.PrintWorkingDirectory;
import commands.Pushd;
import commands.Remove;
import commands.SaveJShell;
import commands.Search;
import commands.Tree;
import exceptions.WrongNumParamsException;

/**
 * Check for errors in the given command
 */
public class ErrorChecker {

  /**
   * Calls the relevant method depending on the command entered.
   * @param command     the command the user wants
   * @param tokens      the parameters the user entered
   * @return    true or false based on presence of errors
   * @throws WrongNumParamsException
   */
  public static boolean checkForErrors(Object command, List<String> tokens)
      throws WrongNumParamsException {
    if (command instanceof Exit)
      return checkExitForErrors(tokens);
    else if (command instanceof MakeDirectory)
      return checkMakeDirectoryForErrors(tokens);
    else if (command instanceof commands.List)
      return checkListForErrors(tokens);
    else if (command instanceof ChangeDirectory)
      return checkChangeDirectoryForErrors(tokens);
    else if (command instanceof PrintWorkingDirectory)
      return checkPrintWorkingDirectoryForErrors(tokens);
    else if (command instanceof History)
      return checkHistoryForErrors(tokens);
    else if (command instanceof Tree)
      return checkTreeForErrors(tokens);
    else if (command instanceof Remove)
      return checkRemoveForErrors(tokens);
    else if (command instanceof Popd)
      return checkPopdForErrors(tokens);
    else if (command instanceof Pushd)
      return checkPushdForErrors(tokens);
    else if (command instanceof SaveJShell)
      return checkSaveJShellForErrors(tokens);
    else if (command instanceof LoadJShell)
      return checkLoadJShellForErrors(tokens);
    else if (command instanceof Echo)
      return checkEchoForErrors(tokens);
    else if (command instanceof Cat)
      return checkConcatenateForErrors(tokens);
    else if (command instanceof Man)
      return checkManForErrors(tokens);
    else if (command instanceof Curl)
      return checkCurlForErrors(tokens);
    else if (command instanceof Move)
      return checkMoveForErrors(tokens);
    else if (command instanceof Search)
      return checkSearchForErrors(tokens);
    else if (command instanceof Copy)
      return checkCopyForErrors(tokens);
    return false;
  }

  /**
   * Checks errors for Exit
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkExitForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() > 1)
      throw new WrongNumParamsException();
    return true;
  }


  /**
   * Checks errors for MakeDirectory
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkMakeDirectoryForErrors(List<String> tokens)
      throws WrongNumParamsException {
    if (tokens.size() < 2)
      throw new WrongNumParamsException();
    return true;
  }

  
  /**
   * Checks errors for List
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkListForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() < 1)
      throw new WrongNumParamsException();
    return true;
  }

  
  /**
   * Checks errors for ChangeDirectory
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkChangeDirectoryForErrors(List<String> tokens) 
      throws WrongNumParamsException {
    if (tokens.size() != 2) {
      throw new WrongNumParamsException();
    }

    return true;
  }

  
  /**
   * Checks errors for PrintWorkingDirectory
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkPrintWorkingDirectoryForErrors(List<String> 
  tokens) throws WrongNumParamsException {
    if (tokens.size() > 1) {
      throw new WrongNumParamsException();
    }
    return true;
  }

  /**
   * Checks errors for History
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkHistoryForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() > 2)
      throw new WrongNumParamsException();
    return true;
  }

  /**
   * Checks errors for Tree
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkTreeForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 1) 
      throw new WrongNumParamsException();
    return true;
  }

  /**
   * Checks errors for Remove
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkRemoveForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 2) 
      throw new WrongNumParamsException();
    return true;
  }

  /**
   * Checks errors for Popd
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkPopdForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 1) 
      throw new WrongNumParamsException();
    return true;
  }

  /**
   * Checks errors for Pushd
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkPushdForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 2)
      throw new WrongNumParamsException();
    return true;
  }

  
  /**
   * Checks errors for SaveJShell
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkSaveJShellForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 2)
      throw new WrongNumParamsException();
    return true;
  }

  /**
   * Checks errors for LoadJShell
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkLoadJShellForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 2)
      throw new WrongNumParamsException();
    return true;
  }
 
  /**
   * Checks errors for Echo
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkEchoForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() < 2)
      throw new WrongNumParamsException();
    return true;
  }

  
  /**
   * Checks errors for Concatenate
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkConcatenateForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() < 2) 
      throw new WrongNumParamsException();
    return true;
  }
 
  /**
   * Checks errors for Man
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkManForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 2)
      throw new WrongNumParamsException();
    return true;

  }

  /**
   * Checks errors for Curl
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkCurlForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 2)
      throw new WrongNumParamsException();
    return true;
  }

  /**
   * Checks errors for Move
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkMoveForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 3)
      throw new WrongNumParamsException();
    return true;
  }

  /**
   * Checks errors for Search
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkSearchForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() < 6)
      throw new WrongNumParamsException();
    return true;
  }
  /**
   * Checks errors for Copy
   * @param tokens  user entered values
   * @return    true or false based on if errors present
   * @throws WrongNumParamsException
   */
  private static boolean checkCopyForErrors(List<String> tokens) throws 
  WrongNumParamsException {
    if (tokens.size() != 3)
      throw new WrongNumParamsException();
    return true;
  }


}
