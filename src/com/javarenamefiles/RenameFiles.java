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

    // Note: This checks for the existing directory, not a file.
    File startPathFileObject = new File(startingPath);

    if (isPathADirectory(startPathFileObject)) return;
    if (doesDirectoryExist(startPathFileObject)) return;

    File[] filesOfFileTypeArray = startPathFileObject.listFiles();
    ArrayList<String> fileNamesArray = new ArrayList<>();
    int countFound = 0;

    getDirectoryFilesAndDisplay(filesOfFileTypeArray);
    putFileObjectFilenamesInStringArray(filesOfFileTypeArray, fileNamesArray);

    // Check if any filenames in String array contain oldText, i.e., find
    // match.
    // If no: Return from program with user message, No files were renamed."
    // If yes: Call renameAllFiles().
    boolean hasOldText;
    // ArrayList<String> filesToChange = new ArrayList<>();
    ArrayList<File> filesToChange = new ArrayList<>();

    for (File aFileOfFileTypeArray : filesOfFileTypeArray) {
      hasOldText = aFileOfFileTypeArray.getName().contains(oldText);

      if (hasOldText) {
        filesToChange.add(aFileOfFileTypeArray);

        // TODO: Add path to oldText = for full path & filename.
        countFound++;
      }
    }
    System.out.println("Number of files found with text: " + countFound);

    if (countFound == 0) {
      System.out.println("No files were renamed.");
    }
    else {
      System.out.println("The files to change are: " + filesToChange);
    }
    // For testing, use:  /Users/kimlew/temp

    // Do I need an object to pass the String array & the strings?
    renameAllFiles(filesOfFileTypeArray, filesToChange, startingPath,
        oldText, newText);

  } // End of main().

  private static void putFileObjectFilenamesInStringArray(
      File[] filesOfFileTypeArray,
      ArrayList<String> fileNamesArray) {

    // Get filenames from array that is of type, File object,
    // filesOfFileTypeArray & put into String array, fileNamesArray - for
    // easier manipulation.

    for (File aFileObjectFromArray : filesOfFileTypeArray) {
      fileNamesArray.add(aFileObjectFromArray.getName());
    }
    //System.out.println("The files in the String array are:");
    //System.out.println(fileNamesArray);
  }

  private static void getDirectoryFilesAndDisplay(File[] filesOfFileTypeArray) {
    // Get all existing filenames in given directory & display all filenames
    // using listFiles() & for loop.

    System.out.println("The files in this directory are:");
    for (File aFileObjectFromArray : filesOfFileTypeArray) {
      System.out.println(aFileObjectFromArray.getName());
    }
    System.out.println();
  }

  private static boolean doesDirectoryExist(File file) {
    // Check File object to see if directory exists.

    boolean fileExists = file.exists();
    if (!fileExists) {
      System.out.println("This directory path does NOT exist: " + file);
      return true;
    }
    return false;
  }

  private static boolean isPathADirectory(File file) {
    // Check File object to see if it is a directory.
    boolean isDirectory = file.isDirectory();
    if (!isDirectory) {
      System.out.println("This is NOT directory: " + file);
      return true;
    }
    return false;
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
    System.out.println();

    String[] userInputs = new String[3];
    userInputs[0] = string_to_replace;
    userInputs[1] = replacement_string;
    userInputs[2] = starting_path;

    return userInputs;
  }

  private static void renameAllFiles(File[] filesOfFileTypeArray,
                                     ArrayList<File> filesToChange,
                                     String startingPath,
                                     String oldText,
                                     String newText) {
    System.out.println("IN renameAllFiles now...");
    // Get getAbsolutePath() from ArrayList.

    for (File aFile : filesToChange) {
      String oldPathAndText;
      String newPathAndText;

      // TODO: How do I rename these IN the file system?
      //oldPathAndText.renameTo(newPathAndText);
    }
    //for (File f: filesOfFileTypeArray) {
    //  String newName = f.getAbsolutePath().replace("Old", "New");
    //  boolean isRenamed = f.renameTo(new File(newName));
    //}

    for (File aFile : filesToChange) {
      // System.out.println("Absolute path from ArrayList is: " + aFile
      // .getPath());

      // TODO: Create replacement path + filename using getParent​() & newText.

      boolean isRenamed = aFile.renameTo(new File(pathWithNewText));
    }
  }

} // End of class.
