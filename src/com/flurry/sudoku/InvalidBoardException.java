/**
 * Copyleft usmanghani 2012
 */

package com.flurry.sudoku;

/**
 * This exception is thrown by the validator when it encounters an error in loading the baord.
 * @author usmanghani
 *
 */
public class InvalidBoardException extends Exception {
	
	static final long serialVersionUID = 1;
	
	/**
	 * Initializes a new instance of the {@link InvalidBoardException} class.
	 * @param message
	 */
	public InvalidBoardException(String message) {
		super(message);
	}
}
