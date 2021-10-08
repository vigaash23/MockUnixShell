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

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the curl class used for retrieving web file info
 */
public class Curl implements Command {
  /**
   * FileSystem to be used with the MakeDirectory class
   */
  private Echo echo;
  private String text;
  private URL url;
  private String name;
  private List<String> fileInfo;

  /**
   * Constructor for MakeDirectory with an already existing FileSystem
   * 
   * @param fileSystem FileSystem that is being used
   */
  public Curl(Echo echo) {
    this.echo = echo;
  }


  @Override
  public void run(List<String> tokens) {
    this.fileInfo = new ArrayList<String>();
    this.fileInfo.add("echo");
    this.fileInfo.add(getContents(tokens.get(1)));
    this.fileInfo.add(">");
    if (this.fileInfo.get(1) != null) {
      this.fileInfo.add(getName(tokens.get(1)));
      this.echo.run(this.fileInfo);
    }
  }


  public String getContents(String param) {
    try {
      url = new URL(param);
      try {
        BufferedReader input = new BufferedReader(new InputStreamReader(url.
            openStream()));
        String temp;
        while ((temp = input.readLine()) != null) {
          this.text = this.text + temp + "\n";

        }

      } catch (IOException e) {
        System.out.println("Could not read/ write from URL, please try again");
        return null;
      }
    } catch (MalformedURLException e) {
      System.out.println("This URL did not exist, please try again.");
      return null;
    }
    return this.text;
  }


  public String getName(String param) {
    try {
      url = new URL(param);
      try {
        BufferedReader input = new BufferedReader(new InputStreamReader(url.
            openStream()));
        name = url.getFile();
        name = name.substring(name.lastIndexOf("/") + 1);
        name = name.replaceAll("\\.", "");

      } catch (IOException e) {
        System.out.println("Could not read/ write from URL, please try again");
        return null;
      }
    } catch (MalformedURLException e) {
      System.out.println("This URL did not exist, please try again.");
      return null;
    }
    return this.name;
  }

  /**
   * toString function used by the Man class
   * 
   * @return Returns a string that states the functionality of the cat command
   */
  public String toString() {
    return "curl URL - URL is a web address.  Retrieve the file at that URL and"
  + " add it to "
        + "the current working directory.";
  }


}
