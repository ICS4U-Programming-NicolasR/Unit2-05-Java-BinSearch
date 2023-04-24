package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Binary search function.
 *
 * @author Mr. Riscalas
 * @version 1.0
 * @since 2023-04-24
 */
public final class RecBinSearch {
    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private RecBinSearch() {
        throw new IllegalStateException("Utility class");
    }

    /**
    * This is the recBinSearch method.
    *
    * @param randomNumbers // randomNumbers
    * @param requestedNum // requested Number
    * @param low // low
    * @param high // high
    * @return index
    */
    public static int recBinSearch(final int[] randomNumbers,
                                    final int requestedNum,
                                    final int low, final int high) {
        // If the low is bigger than the high then the number won't exist
        if (low > high) {
            return -1;
        }
        // calculate the middle
        final int amount = 2;
        final int mid = (low + high) / amount;
        // If the number is the same then return the mid
        if (randomNumbers[mid] == requestedNum) {
            return mid;
        // If the number is less then change the low to low + 1
        } else if (randomNumbers[mid] < requestedNum) {
            return recBinSearch(randomNumbers, requestedNum, mid + 1, high);
        // If the number is bigger then change the high to mid - 1
        } else {
            return recBinSearch(randomNumbers, requestedNum, low, mid - 1);
        }
    }

    /**
    * This is the recBinSearch method.
    *
    * @param randomNumbers //base
    * @param requestedNum // exp
    * @return index
    */
    public static int recBinSearch(final int[] randomNumbers,
                                     final int requestedNum) {
        // call the same function but this time with more arguments
        return recBinSearch(randomNumbers, requestedNum, 0,
                            randomNumbers.length - 1);
    }

    /**
    * This is the main method.
    *
    * @param args //unused
    */
    public static void main(final String[] args) {
        // Set the input and output file paths
        final String inputFilePath = "input.txt";
        final String outputFilePath = "output.txt";
        int lineNumber = 1;
        // Read input from file using Scanner
        try (Scanner inputFile = new Scanner(new File(inputFilePath));
            FileWriter writer = new FileWriter(outputFilePath)) {
            /* Read each line from the input file and find using a binary search
            * the index of the random numbers using recursions
            */
            while (inputFile.hasNextLine()) {
                if ((lineNumber % 2) != 0) {
                    lineNumber += 2;
                    // Get the random numbers and sort
                    final String line = inputFile.nextLine();
                    final int[] numbers = Arrays.stream(line.split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                    Arrays.sort(numbers);
                    // get the targeted number
                    final int targetNum = Integer.parseInt(
                                                    inputFile.nextLine());
                    // find the index by calling the recBinSearch
                    final int index = recBinSearch(numbers, targetNum);
                    // Write the answer to the output file
                    if (index != -1) {
                        writer.write(targetNum + " is the " + (index + 1)
                                    + " number in ascending order\n");
                    } else {
                        writer.write(targetNum + " was not found in file\n");
                    }
                }
            }
        } catch (FileNotFoundException error) {
            System.err.println("File not found: " + error.getMessage());
        } catch (IOException error) {
            System.err.println("Error writing to file: " + error.getMessage());
        }
    }
}
