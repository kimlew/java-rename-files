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
    System.out.println();
    System.out.println("Number of files found with text: " + countFound);

    if (countFound == 0) {
      System.out.println("No files were renamed.");
    }
    else {
      System.out.println("The files to change are: " + filesToChange);
    }
    System.out.println();

    // Do I need an object to pass the String array & the strings?
    renameAllFiles(filesOfFileTypeArray, filesToChange, startingPath,
        oldText, newText);

    // For testing, use:  /Users/kimlew/temp
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

    System.out.print("The files in this directory are: ");
    for (File aFileObjectFromArray : filesOfFileTypeArray) {
      System.out.print(aFileObjectFromArray.getName() + " | ");
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

    for (File aFile : filesToChange) {
      //  TODO: Create replacement path+filename using getParent​() & newText.
      //  Use: public String getParent​() OR public File getParentFile​().
      //  Rename these IN the file system.

      System.out.println("aFile is: " + aFile);

      String thePathStr = aFile.getParent();
      System.out.println("Path using getParent() is: \t\t" + thePathStr);
      File thePathF = aFile.getParentFile();
      System.out.println("Path using getParentFile() is: " + thePathF);
      System.out.println();

      // Have path. TODO: Change respective files with new text, then rename.

      String oldFilename = aFile.getName();
      String newFilename = oldFilename.replace(oldText, newText);
      // TODO: Create just the new filename with extension to use in replace()

      System.out.println("OLD Filename: \t" + oldFilename);
      System.out.println("NEW Filename: \t" + newFilename);

      String newPathAndText = aFile.getAbsolutePath().replace(oldFilename,
          newFilename);
      System.out.println("newPathAndText is: " + newPathAndText);
      System.out.println();

      //System.out.println("aFile.getCanonicalPath(): " + aFile
      // .getCanonicalPath());
      //System.out.println("aFile.getAbsolutePath(): " + aFile.getAbsolutePath());
      //System.out.println("aFile.getPath(): \t\t" + aFile.getPath());

      boolean isRenamed = aFile.renameTo(new File(newPathAndText));
      System.out.println("isRenamed is: " + isRenamed);
      System.out.println();

      // Check if the file can be renamed to the abstract path name.
      if (isRenamed) {
        System.out.println("File has been renamed.");
      }
      else {
        System.out.println("File cannot be renamed.");
      }
      System.out.println();
    } // End of foreach loop.
  } // End of renameAllFiles().

} // End of class.
