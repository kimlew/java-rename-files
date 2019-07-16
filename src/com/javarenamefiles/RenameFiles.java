package com.javarenamefiles;

import java.io.File;
import java.util.Scanner;

public class RenameFiles {

  public static void main(String[] args) {
    String[] userInputs = getInput();

    String oldFile = userInputs[0];
    String newFile = userInputs[1];
    String startingPath = userInputs[2];

    System.out.println("oldFile is: " + oldFile);
    System.out.println("newFile is: " + newFile);
    System.out.println("startingPath is: " + startingPath);
    System.out.println();

    // Note: This checks for the existing directory, not file.
    // For testing, use:  /Users/kimlew/temp
    File file = new File(startingPath);

    // Check File object to see if it is a directory.
    try {
      boolean isDirectory = file.isDirectory();
      if (isDirectory == false) {
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
      if (fileExists == false) {
        System.out.println("This directory path does NOT exist: " + file);
        return;
      }
    }
    catch (Exception e) {
      System.out.println("This directory path does NOT exist: " + file);
    }

    // Display all files in directory using listFiles() & a for loop.
    try {
      // Get all existing filenames in given directory.
      File[] files = file.listFiles();
      System.out.println("Files are:");

      // Display the names of the files.
      for (int i = 0; i < files.length; i++) {
        System.out.println(files[i].getName());
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
    }

    // TODO: renameAllFiles();

  }

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
