/**
 * Copyleft usmanghani 2012
 */

package com.flurry.sudoku;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Determines whether a sudoku board is valid or not.
 * @author usmanghani
 * 
 */
public class SudokuPlusValidator {
	
	/**
	 * Store the filename for use with validate.
	 */
	private String fileName;
	
	/**
	 * Initializes the new instance of the {@link SudokuPlusValidator} class
	 * @param fileName
	 */
	public SudokuPlusValidator(String fileName) {
		this.fileName = fileName;		
    }
	
	/**
	 * This method detects whether a board is valid or not.
	 * This method eats the exceptions. If you want to get the actual
	 * exceptions, call {@link isValid}
	 * @return True or False depending on whether the board is valid or not.
	 */
	public boolean isValidNoThrow() {
		
		// Eat all the exceptions thrown by isValid.
        try {
        	return isValid();            
        } catch (IOException _) {
        	
        } catch (NumberFormatException _) {
        	
        } catch (InvalidBoardException _) {
        	
		}   
        
		return false;
	}

	/**
	 * This method is called to validate whether the given board is a valid
	 * Sudoku solution or not.
	 * The algorithm works by loading the board into a 2D array,
	 * then going over each box, putting that box into a 1D array, sorting it,
	 * and checking that all elements from 1 to N are present in that box.
	 * @return True if the board is valid. Throws exceptions otherwise.
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws InvalidBoardException
	 */
	public boolean isValid() throws IOException, NumberFormatException, InvalidBoardException {
		
		// Read all the lines first.
		List<String> allLines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());

		// Validate the board. This checks conditions like the size of the board,
		// etc.
        boolean isBoardValid = ValidateBoard(allLines);
        
        // If the board is not valid, bail out here.
        if (!isBoardValid) {
        	
        	throw new InvalidBoardException("Board did not meet the size criteria.");
        }

        // read the board in to a 2D array.
        int[][] sudokuBoard = readBoard(allLines);
        
        // Validate the solution.
        return isValidSolution(sudokuBoard);
	}
	
	/**
	 * Given a sudoku board, determines whether the solution is valid or not.
	 * @param sudokuBoard. The incoming sudokuBoard. It is a 2D array of NxN, 
	 * where N is the size of hte board.
	 * @return Returns true of false based on whether the board is a valid,
	 * sudoku board or not.
	 * @throws InvalidBoardException Thrown when any of the boxes is invalid.
	 */
    private static boolean isValidSolution(int[][] sudokuBoard) throws InvalidBoardException {

    	// Total number of rows also give us the board size.
        int totalSize = sudokuBoard.length;
        
        // The size of each box is the sqrt of the total size.
        int boxSize = (int)Math.sqrt(totalSize);

        // For an NxN sudoku board, we will have N boxes within that board,
        // where each box is of size sqrt(N).
        // This loop goes over all the boxes.
        for (int i = 0; i < totalSize; i++) {
        	
        	// For a given box, figure out the row and column index within
        	// the 2D array where that box starts.
            int rowNumber = (i / boxSize) * boxSize;
            int colNumber = (i * boxSize) % totalSize;

            // Load up the selected box into a 1D array.
            int[] boxLinearized = new int[totalSize];
            for (int j = 0; j < boxSize; j ++) {
                for (int k = 0; k < boxSize; k ++) {

                    boxLinearized[j * boxSize + k] = sudokuBoard[rowNumber + j][colNumber + k];
                }
            }

            // Sort the linearized box.
            Arrays.sort(boxLinearized);

            // Make sure that each box has all the elements.
            // If we sort the elements, and iterate through them,
            // each element will be equal to the loop index + 1.
            // If this is not the case, then the solution is not valid.
            for (int index = 0; index < totalSize; index++) {
                if (boxLinearized[index] != index + 1) {
                    
                    throw new InvalidBoardException(
                    		String.format("Box %d is invalid.\n", i + 1));

                }
            }
        }

        return true;
    }

    /**
     * Determines whether a given number has an integral square root.
     * @param n Input Number.
     * @return True or False depending on whether the number has an integral root or not.
     */
    private static boolean isPerfectSquare(int n) {

        if (n < 0) return false;

        int roundedUp = (int)(Math.sqrt(n) + 0.5);
        return roundedUp * roundedUp == n;
    }

    /**
     * Reads the board from a list of strings. The lines were previously loaded from a file.
     * @param allLines The list of strings read from the file and passed onto this method.
     * @return A 2D array representing the board.
     * @throws InvalidBoardException 
     */
    private static int[][] readBoard(List<String> allLines) throws InvalidBoardException {
    	
        // Model the board using a 2D array.
        int[][] sudokuBoard = new int[allLines.size()][allLines.size()];

        // Load the board with 
        for (int i = 0; i < allLines.size(); i++) {

            String line = allLines.get(i);

            line = line.trim();

            String[] tokens = line.split(",");

            if (tokens.length != allLines.size()) {

            	throw new InvalidBoardException(
	                String.format(
	                        "Line %d is invalid. " + 
	                		"It is %d elements wide, while there are %d rows.\n",
	                        i + 1,
	                        tokens.length,
	                        allLines.size()));

            }

            sudokuBoard[i] = convertTokensToInt(tokens);
        }
        
		return sudokuBoard;
    }
    
    /**
     * Validates that that board is a perfect square and its not empty.
     * @param allLines The list of strings read from the file.
     * @return True of False depending on whether validity criteria were satisfied or not.
     */
    private static boolean ValidateBoard(List<String> allLines) {

        if (allLines.size() <= 0) {
            System.out.println("File is empty.");
            return false;
        }

        if (!isPerfectSquare(allLines.size())) {
            System.out.format("The size of the board %d is not a perfect square.\n", allLines.size());
            return false;
        }

        return true;
    }

    /**
     * Converts a list of strings (numbers) to an integer array.
     * This is called for every line read from the file.
     * @param tokens This is the list of numbers on a single line.
     * @return An array of integers representing the numbers on the input array.
     * @throws NumberFormatException
     */
    private static int[] convertTokensToInt(String[] tokens) throws NumberFormatException {

        int[] intArray = new int[tokens.length];
        
        for (int i = 0; i < tokens.length; i++) {
            intArray[i] = Integer.parseInt(tokens[i], 10);
        }
        
        return intArray;
    }
}
