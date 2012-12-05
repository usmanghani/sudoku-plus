/**
 * Copyleft usmanghani 2012
 */

package com.flurry.sudoku.tests;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.flurry.sudoku.InvalidBoardException;
import com.flurry.sudoku.SudokuPlusValidator;

public class SudokuPlusTests {

	/**
	 * Test the happy path for 4x4.
	 */
	@Test
	public void testValid4By4Board() {
		
		SudokuPlusValidator validator = new SudokuPlusValidator("sampleInput 4x4.txt");
		assertTrue("Validator with valid 4x4 board passed.", validator.isValidNoThrow());
	}

	/**
	 * Test the happy path for 9x9.
	 */
	@Test
	public void testValid9By9Board() {
		
		SudokuPlusValidator validator = new SudokuPlusValidator("sampleInput 9x9.txt");
		assertTrue("Validator with valid 9x9 board passed.", validator.isValidNoThrow());
	}
	
	/**
	 * Test the failure case where the board size is wrong.
	 */
	@Test
	public void testInvalidSize4By4Board() {
		
		SudokuPlusValidator validator = new SudokuPlusValidator("invalidSizeSampleInput 4x4.txt");
		assertFalse("Validator with invalid size 4x4 board passed.", validator.isValidNoThrow());
		try {
			validator.isValid();
		} catch (InvalidBoardException e) {
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			fail("Did not expect this exception at this time.");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Did not expect this exception at this time.");
		}
	}
	
	/**
	 * Test the failure case where the board size is wrong.
	 */
	@Test
	public void testInvalidSize9By9Board() {
		
		SudokuPlusValidator validator = new SudokuPlusValidator("invalidSizeSampleInput 9x9.txt");
		assertFalse("Validator with invalid size 9x9 board passed.", validator.isValidNoThrow());
		
		try {
			validator.isValid();
		} catch (InvalidBoardException e) {
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			fail("Did not expect this exception at this time.");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Did not expect this exception at this time.");
		}

	}

	/**
	 * Test the failure case where the input numbers have a wrong format.
	 * For instance one of the numbers have a letter in there.
	 */
	@Test
	public void testInvalidFormatBy4Board() {
		
		SudokuPlusValidator validator = new SudokuPlusValidator("invalidFormatSampleInput 4x4.txt");
		assertFalse("Validator with invalid format 4x4 board passed.", validator.isValidNoThrow());
		
		try {
			validator.isValid();
		} catch (NumberFormatException e) {
			
		} catch (InvalidBoardException e) {
			e.printStackTrace();
			fail("Did not expect this exception at this time.");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Did not expect this exception at this time.");
		}

	}

	/**
	 * Test the failure case where the input numbers have a wrong format.
	 * For instance one of the numbers have a letter in there.
	 */
	@Test
	public void testInvalidFormat9By9Board() {
		
		SudokuPlusValidator validator = new SudokuPlusValidator("invalidFormatSampleInput 9x9.txt");
		assertFalse("Validator with invalid format 9x9 board passed.", validator.isValidNoThrow());
		
		try {
			validator.isValid();
		} catch (NumberFormatException e) {
			
		} catch (InvalidBoardException e) {
			e.printStackTrace();
			fail("Did not expect this exception at this time.");
		} catch (IOException e) {
			e.printStackTrace();
			fail("Did not expect this exception at this time.");
		}

	}
}
