/*
This class is used to read in text files and return them as 2D arrays
to make use of data for the google hash code competition
*/

import java.io.*;   
import java.util.*;

class Reader{
    static RandomAccessFile raf;
    static Scanner stdIn = new Scanner(System.in);

    public static String getFileName(){
        System.out.println("Please enter a file name\n> ");
        String fileName = stdIn.nextLine();
        return fileName;
    }
    public static void main(String args[]){
    //Get the file name
    String fileName = getFileName();

    //Open the file
    try {
        raf = new RandomAccessFile(fileName, "r");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    }
}