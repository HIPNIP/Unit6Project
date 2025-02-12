import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FileReader {

    private static Scanner createScanner(File theFile) {
        Scanner tempScanner = null;

        try {
            tempScanner = new Scanner(theFile);
        } catch(FileNotFoundException error) {
            System.out.println("File not found.");
        }

        return tempScanner;
    }

    private static int countFileLines(File theFile) {
        //After the scanner reads the file to get the number of lines, 
        // we cannot reset it to the beginning of the file for when we 
        // want to read the data. So, we must make a new scanner for 
        // each process.

        Scanner tempScanner = createScanner(theFile);
        int count = 0;
        while(tempScanner.hasNextLine()) {
            tempScanner.nextLine();
            count++;
        }
        tempScanner.close(); // close the scanner
        return count;
    }

    public static String[] toStringArray(String fileName){
        File myFile = new File(fileName);
        Scanner fileReader = createScanner(myFile);
        int numLines = countFileLines(myFile);

        String[] arr = new String[numLines];

        for (int i = 0; i < numLines; i++) {
            arr[i] = fileReader.nextLine();
        }

        fileReader.close(); // close the scanner
        return arr;
    }

    public static int[] toIntArray(String fileName)
    {
        File myFile = new File(fileName);
        Scanner fileReader = createScanner(myFile);
        int numLines = countFileLines(myFile);

        int[] arr = new int[numLines];

        for (int i = 0; i < numLines; i++) {
            arr[i] = fileReader.nextInt();

            //nextInt() doesn't take us to a new line, so we must 
            // call nextLine() to do that. We don't want to call
            // this after the last number though because there
            // are no more new lines and we'll get an error.
            if(fileReader.hasNextLine())
                fileReader.nextLine();
        }

        fileReader.close(); // close the scanner
        return arr;
    }

    public static ArrayList<String> toStringArrayList(String fileName){
        File myFile = new File(fileName);
        Scanner fileReader = createScanner(myFile);
        int numLines = countFileLines(myFile);

        ArrayList<String> arr = new ArrayList<>(numLines);

        for (int i = 0; i < numLines; i++) {
            arr.add(fileReader.nextLine());
        }

        fileReader.close(); // close the scanner
        return arr;
    }
}