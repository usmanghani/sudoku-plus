/**
 * Copyleft usmanghani 2012
 */

import java.io.IOException;

import com.flurry.sudoku.InvalidBoardException;
import com.flurry.sudoku.SudokuPlusValidator;

/**
 * The driver module for the sudoku validator.
 * @author usmanghani
 *
 */
public class Main {

    public static void main(String[] args) {

        if (args.length < 1) {
            usage();
            return;
        }

        String fileName = args[0];

        System.out.format("Using filename %s\n", fileName);

        SudokuPlusValidator validator = new SudokuPlusValidator(fileName);
        
        try {        	
            if (validator.isValid()) {
                System.out.println("Valid Solution.\n");
            }
        	            
        } catch (IOException e) {
        	
        	System.out.println("Invalid Solution.\n");
        	e.printStackTrace();
        } catch (NumberFormatException e) {
        	
        	System.out.println("Invalid Solution.\n");
        	e.printStackTrace();
        } catch (InvalidBoardException e) {
        	
        	System.out.println("Invalid Solution.\n");
        	e.printStackTrace();
		}   

    }

    private static void usage() {

        System.out.println("Usage: java SudokuPlus.jar <fileName>");
    }
}
