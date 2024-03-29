package com.kimlew.javarenamefiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class RenameFiles {

  public static void main(String[] args) {
    String[] userInputs = getInputs();

    String oldText = userInputs[0];
    String newText = userInputs[1];
    String startingPath = userInputs[2];

    // Note: This checks for the existing directory, not a file.
    File startPathFileObject = new File(startingPath);

    if (pathIsNotADirectory(startPathFileObject)) return;
    if (directoryDoesNotExist(startPathFileObject)) return;

    File[] filesOfFileTypeArray = startPathFileObject.listFiles();
    // ArrayList<String> fileNamesArray = new ArrayList<>();
    int countFound = 0;

    // Check if any filenames in String array contain oldText, i.e., find
    // match.
    // If no: Return from program with user message, No files were renamed."
    // If yes: Call renameAllFiles().
    boolean hasOldText;
    ArrayList<File> filesToChange = new ArrayList<>();

    for (File aFileOfFileTypeArray : filesOfFileTypeArray) {
      hasOldText = aFileOfFileTypeArray.getName().contains(oldText);

      if (hasOldText) {

        filesToChange.add(aFileOfFileTypeArray);
        countFound++;
      }
    }
    if (countFound > 0) {
      getDirectoryFilesAndDisplay(filesOfFileTypeArray);
    }
    System.out.println();

    renameAllFiles(filesToChange, oldText, newText, startingPath, countFound);

    // For testing, use:  /Users/kimlew/temp
  } // End of main().

  private static void getDirectoryFilesAndDisplay(File[] filesOfFileTypeArray) {
    // Get all existing filenames in given directory & display all filenames
    // using listFiles() & for loop.

    ArrayList<String> theArray = new ArrayList<>();

    System.out.print("File(s) in this directory: ");
    for (File aFileObjectFromArray : filesOfFileTypeArray) {
      theArray.add(aFileObjectFromArray.getName());
      Collections.sort(theArray);
    }

    // Replace the '[' and ']' with empty space & display.
    String theList =
        Arrays.toString(theArray.toArray()).replace("[", "").replace("]", "");
    System.out.println(theList);
  }

  private static boolean directoryDoesNotExist(File file) {
    // Check File object to see if directory exists.
    boolean directoryExists = file.exists();

    if (directoryExists == true) {
      return false;
    }
    else {
      System.out.println();
      System.out.println("This directory path does NOT exist: " + file);
      return true;
    }
  }

  private static boolean pathIsNotADirectory(File file) {
    // Check File object to see if it is a directory.
    boolean pathIsADirectory = file.isDirectory();

    if (pathIsADirectory == true) {
      return false;
    }
    else {
      System.out.println();
      System.out.println("This is NOT a directory: " + file);
      return true;
    }
  }

  private static String[] getInputs() {
    Scanner input = new Scanner(System.in);

    // Use read(), next(), or readLine() to read input.
    System.out.print("Enter the text you want to replace: ");
    String string_to_replace = input.next();

    System.out.print("Enter the replacement text: ");
    String replacement_string = input.next();

    System.out.print("Enter the starting path of the files: ");
    String starting_path = input.next();

    System.out.println();
    System.out.println("Text to Replace:\t" + string_to_replace);
    System.out.println("Replacement Text:\t" + replacement_string);
    System.out.println("Starting Path of Files:\t " + starting_path);

    String[] userInputs = new String[3];
    userInputs[0] = string_to_replace;
    userInputs[1] = replacement_string;
    userInputs[2] = starting_path;

    return userInputs;
  }

  private static void renameAllFiles(ArrayList<File> filesToChange,
                                     String oldText,
                                     String newText,
                                     String startingPath,
                                     int countFound
                                     ) {

    System.out.println(countFound + " files were found and renamed.");

    for (File aFile : filesToChange) {
      //  Build old filename using getName(). Build new filename using
      //  replace with oldText & newText. Rename these IN the file system.

      // Note: For newFilename, just create new filename with extension
      // to use in replace().
      String oldFilename = aFile.getName();
      String newFilename = oldFilename.replace(oldText, newText);

      // Change applicable files to new filenames, then rename in filesystem.
      String newPathAndText = aFile.getAbsolutePath().replace(oldFilename,
          newFilename);

      boolean isRenamed = aFile.renameTo(new File(newPathAndText));

      // Check if the file can be renamed to the abstract path name.
      if (isRenamed) {
        System.out.println(oldFilename + " - has been renamed to - " + newFilename);
      }
      else {
        System.out.println("File cannot be renamed.");
      }
    } // End of foreach loop.

    File newStartPath = new File(startingPath);
    File[] newArray = newStartPath.listFiles();

    if (countFound > 0) {
      getDirectoryFilesAndDisplay(newArray);
    }

  } // End of renameAllFiles().

} // End of class.
