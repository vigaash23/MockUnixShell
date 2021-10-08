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

public class File {

  private String fileName;
  private String fileContents;

  /**
   * Constuctor for file give a file name
   * 
   * @params fileName is the name of the file
   */
  public File(String fileName) {
    this.fileName = fileName;
    this.fileContents = "";
  }


  /**
   * Retrieves the name of the file.
   * 
   * @return String that is the name of the file
   */
  public String getFileName() {
    return this.fileName;
  }


  /**
   * Assigns fileName to be the name of the file.
   * 
   * @param fileName the name of the file
   */
  public void setFileName(String fileName) {
    this.fileName = fileName;
  }


  /**
   * Retrieves the contents of the file.
   * 
   * @return String that contains contents of the file
   */
  public String getFileContents() {
    return this.fileContents;
  }


  /**
   * Assigns the fileContents to be the new contents of the file.
   * 
   * @param fileContents the new contents of the file
   */
  public void setFileContents(String fileContents) {
    this.fileContents = fileContents;
  }
}
