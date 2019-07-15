package com.javarenamefiles;

import java.util.Scanner;

public class RenameFiles {

  public static void main(String[] args) {
    getInput();
    // TODO: renameAllFiles();
  }

  private static void getInput() {
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

  }

  //private static void renameAllFiles() {
  //
  //}

} // End of class.
