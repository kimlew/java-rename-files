package com.javarenamefiles;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RenameFiles {

  public static void main(String[] args) {
    String[] userInputs = getInput();

    String oldText = userInputs[0];
    String newText = userInputs[1];
    String startingPath = userInputs[2];

    // Note: This checks for the existing directory, not file.
    // For testing, use:  /Users/kimlew/temp
    File file = new File(startingPath);

    // Check File object to see if it is a directory.
    try {
      boolean isDirectory = file.isDirectory();
      if (!isDirectory) {
        System.out.println("This is NOT directory: " + file);
        return;
      }
    }
    catch (Exception e) {
      System.out.println("This is NOT directory: " + file);
    }

    // Check File object to see if directory exists.
    try {
      boolean fileExists = file.exists();
      if (!fileExists) {
        System.out.println("This directory path does NOT exist: " + file);
        return;
      }
    }
    catch (Exception e) {
      System.out.println("This directory path does NOT exist: " + file);
    }

    // Get & display all files in directory using listFiles() & for loop.
    File[] files = file.listFiles();
    try {
      // Get all existing filenames in given directory.

      System.out.println("Files are:");

      // Display the names of the files.
      try {
        for (int i = 0; i < files.length; i++) {
          System.out.println(files[i].getName());
        }
      }
      catch (ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
        System.out.println("There is an ArrayIndexOutOfBoundsException.");
      }
      catch (NullPointerException e) {
        e.printStackTrace();
        System.out.println("There is a NullPointerException.");
      }
      catch (Exception e) {
        e.printStackTrace();
        System.out.println("There is a general Exception.");
      }
    }
    catch (NullPointerException e) {
      e.printStackTrace();
      System.out.println("There are no files that contain the text.");
    }
    System.out.println();
    
    // Check if any filenames in Files array contain the stringToReplace.
    // If no - return from program with user message,
    // "There are no files with this text. No files were renamed."
    // If yes - Call renameAllFiles().
    // Iterate through the files array & call getName() for each file.

    String[] fileNames = new String[files.length];
    int countFound = 0;

    fileNames = new String[files.length];
    for (int i = 0; i < files.length; i++) {
      fileNames[i] = files[i].getName();
    }

    for (int j = 0; j < fileNames.length; j++) {
      boolean hasOldText = fileNames[j].contains(oldText);
      if (hasOldText) {
        ArrayList<String> filesToChange = new ArrayList<>();
        filesToChange.add(fileNames[j]);
        System.out.println("ArrayList with files to change has: " + filesToChange);
        // Add path to oldText = for full path & filename.
        countFound++;
      }
    }

    System.out.println("Number of files found with text: " + countFound);

    // TODO: renameAllFiles();

  } // End of main().

  private static String[] getInput() {
    Scanner input = new Scanner(System.in);

    // Use read(), next(), or readLine() to read input.
    System.out.println("Enter the string you want to replace: ");
    String string_to_replace = input.next();

    System.out.println("Enter the replacement string: ");
    String replacement_string = input.next();

    System.out.println("Enter the starting path of the files: ");
    String starting_path = input.next();

    System.out.println();
    System.out.println("String to Replace:\t" + string_to_replace);
    System.out.println("Replacement String:\t" + replacement_string);
    System.out.println("Starting Path of Files:\t " + starting_path);
    System.out.println();

    String[] userInputs = new String[3];
    userInputs[0] = string_to_replace;
    userInputs[1] = replacement_string;
    userInputs[2] = starting_path;

    return userInputs;
  }

  //private static void renameAllFiles() {
  //
  //}

} // End of class.
