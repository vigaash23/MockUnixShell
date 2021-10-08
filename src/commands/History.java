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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the class for the history command used within the JShell
 */
public class History implements Command, Serializable {

  private static final long serialVersionUID = 1L;

  private List<String> input;


  public History() {
    this.input = new ArrayList<String>();
  }


  @Override
  public void run(List<String> tokens) {
    if (tokens.size() > 1) {
      boolean num = checkNum(tokens.get(1));
      if (num)
        printHistory(Integer.parseInt(tokens.get(1)));
    } else {
      printHistory();
    }
  }

  /**
   * Responsible for adding input to the archive of commands
   * 
   * @param input this is the command that gets added to the list of previous
   *  commands
   */
  public void addInput(String input) {
    this.input.add(input);
  }

  public int sizeOfHistory() {
    return this.input.size();
  }

  /**
   * Overloaded method responsible for printing the desired command history
   * 
   * @param num the number of previous commands that should be printed
   */
  private void printHistory(int num) {
    int size = this.input.size() - num;
    if (size < 0) {
      size = 0;
    }
    int index = this.input.size() - num + 1;
    if (index <= 0) {
      index = 1;
    }

    while (size < this.input.size()) {
      System.out.println(index + ". " + this.input.get(size));
      index++;
      size++;
    }
  }

  /**
   * Responsible for printing the desired command history
   */
  private void printHistory() {
    int index = 0;
    int outputNum = 1;

    while (index < this.input.size()) {
      System.out.println(outputNum + ". " + this.input.get(index));
      outputNum++;
      index++;
    }
  }

  /**
   * Responsible for checking if the parameter is just a number or not
   */
  private boolean checkNum(String parameter) {
    try {
      int val = 0;
      val = Integer.parseInt(parameter);
    } catch (NumberFormatException e) {
      System.out.println("You did not enter a valid integer as a parameter.");
      return false;
    }
    return true;
  }

  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the history 
   * command
   */
  public String toString() {
    return "history[number] - This command will print out recent commands, "
        + "one command per line. i.e.  \n" + "1.cd ..\n" + "2.mkdir "
            + "textFolder\n"
        + "3.echo â€œHello Worldâ€�\n" + "4.fsjhdfks\n" + "5.history";
  }
}
