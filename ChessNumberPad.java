// Code and Solution by Vasuman Moza
// Response to a Competitive Programming Question - ChessTest

import java.util.ArrayList;
import java.util.Scanner;

public class ChessNumberPad
{
	public static void main(String[] args) 
	{
		// Declaring new objects of type ChessPiece
		ChessPiece king = new King();
		ChessPiece queen = new Queen();
		ChessPiece rook = new Rook();
		ChessPiece bishop = new Bishop();
		ChessPiece knight = new Knight();

		// Prompts the user to enter a starting index using rows and columns
	    System.out.printf("Enter starting indices: Row, Col.: ");
		Scanner in = new Scanner(System.in);
		// Row is what row the user enters
		int row = in.nextInt();
		// Col is what column the user enters
		int col = in.nextInt();

		// Testing to see if the starting number is 1 or *, 0, # (since those are all invalid starting positions)
		if((row == 0 && col == 0) || (row == 3))
		{
			// Reply to the user if such invalid starting positions are entered, and the code ends
			System.out.println("Invalid Starting Positions: 0 1 * #");
		}
		else
		{
			// Printing out total number of PhoneNumbers possible for all pieces given the user's starting number on the numberPad
			System.out.println("King:   " + phoneNumbers(king, row, col));
			System.out.println("Queen:  " + phoneNumbers(queen, row, col));
			System.out.println("Rook:   " + phoneNumbers(rook, row, col));
			System.out.println("Bishop: " + phoneNumbers(bishop, row, col));
			System.out.println("Knight: " + phoneNumbers(knight, row, col));
		}
	}

	public static int phoneNumbers(ChessPiece piece, int row, int col)
	{
		// Using a flipping procedure - constantly updates a 'mirror' ArrayList to build off of the previous
		// Acts as recursion with more efficiency and simpler to understand
		ArrayList<Coordinate> values  = new ArrayList<Coordinate>();
		ArrayList<Coordinate> values2 = new ArrayList<Coordinate>();

		// Set the first ArrayList to primary coordinates, the starting point
		values.add(new Coordinate(row, col));

		// Now that the first number has been given, the remaining 9 numbers should be created
		for(int count = 1; count <= 9; count++)
		{
			// Nested for loop to implement the flipping procedure
			for(int count2 = 0; count2 < values.size(); count2++)
			{
				// Adding the results of possibleMoves of the first ArrayList to the second ArrayList
				values2.addAll(piece.possibleMoves(values.get(count2)));
			}
			values.clear();

			// ForEach loop to add values2 to values
			for(Coordinate c : values2) 
			{
				values.add(c);
			}

			// All entries of values2 should be cleared
			values2.clear();

			// Repeat the process until a 10 digit phoneNumber is created
		}

		// values.size() essentially means how many entries values has, which is the total number of 10 digit phone numbers
		return values.size();
	}
}

abstract class ChessPiece
{
	// Illustrating the numberPad allows us to convert from array Indices to physical numbers
	// Substitute * and # because their physical values are irrelevant, we only need to identify them to make them invalid
	int[][] numberPad = {{1, 2, 3},
						 {4, 5, 6}, 
						 {7, 8, 9}, 
						 {10,0,10}};

	// Temporary array of movement that is large enough to store even the Queen's movement matrix
	int[][] movement = new int[30][2];

	// Rather than writing this method 5 times across all pieces, make a common method to be re-used, thereby increasing functionality
	public ArrayList<Coordinate> possibleMoves(Coordinate startingCoordinate)
	{
		// Rather than creating an ArrayList of indices, return the Coordinate of the movement point
		ArrayList<Coordinate> destinations = new ArrayList<Coordinate>();
		
		// For loop to test each of the movements
		for(int c = 0; c < movement.length; c++)
		{
			// Try to test the validity of movement
			try 
			{
				// Testing each of the possible movements
				// If the piece does not land on * or # it is valid
				if(numberPad[startingCoordinate.row + movement[c][1]][startingCoordinate.col + movement[c][0]] != 10)
				{
					// The movement is valid and thus should be counted
					destinations.add(new Coordinate(startingCoordinate.row + movement[c][1], startingCoordinate.col + movement[c][0]));
				}
			}

			// Testing to see if the movement causes the chess piece to move out of bounds, off the numberpad
			catch(IndexOutOfBoundsException e)
			{
				// Ignore the movement and do not count it
				continue;
			}
		}
		// Return destinations now that it has all the possible movement points
		return destinations;
	}
}

// Extends ChessPiece to inherit numberPad, movement, and PossibleMoves
// The King is-a ChessPiece
class King extends ChessPiece
{
	public King()
	{
		// Overwrite the movement matrix for unique King movements
		this.movement = new int[][]
						{{ 1, 1},
						{ 1,-1},
						{-1, 1},
						{-1,-1},
						{ 0, 1},
						{ 0,-1},
						{ 1, 0},
						{-1, 0}};
	}
}

// Extends ChessPiece to inherit numberPad, movement, and PossibleMoves
// The Queen is-a ChessPiece
class Queen extends ChessPiece
{
	public Queen()
	{
		// Overwrite the movement matrix for unique Queen movements
		// Exceptionally long, considering the Queen has more movement options than any other chessPiece
		this.movement = new int[][]
					   {{ 0, 1},
						{ 0, 2},
						{ 0,-1},
						{ 0,-2},
						{ 1, 1},
						{ 1, 2},
						{ 1,-1},
						{ 1,-2},
						{ 2, 1},
						{ 2, 2},
						{ 2,-1},
						{ 2,-2},
						{ 3, 1},
						{ 3, 2},
						{ 3,-1},
						{ 3,-2},
						{-1, 1},
						{-1, 2},
						{-1,-1},
						{-1,-2},
						{-2, 1},
						{-2, 2},
						{-2,-1},
						{-2,-2},
						{-3, 1},
						{-3, 2},
						{-3,-1},
						{-3,-2}};
	}
}

// Extends ChessPiece to inherit numberPad, movement, and PossibleMoves
// The Rook is-a ChessPiece
class Rook extends ChessPiece
{
	public Rook()
	{
		// Overwrite the movement matrix for unique Rook movements
		this.movement = new int[][]
					   {{ 0, 1},
						{ 0, 2},
						{ 0,-1},
						{ 0,-2},
						{ 1, 0},
						{-1, 0},
						{ 2, 0},
						{-2, 0},
						{ 3, 0},
						{-3, 0}};
	}
}

// Extends ChessPiece to inherit numberPad, movement, and PossibleMoves
// The Bishop is-a ChessPiece
class Bishop extends ChessPiece
{
	public Bishop()
	{
		// Overwrite the movement matrix for unique Bishop movements
		this.movement = new int[][]
					   {{ 1, 1},
						{ 1,-1},
						{-1, 1},
						{-1,-2},
						{ 2, 2},
						{ 2,-2},
						{-2, 2},
						{-2,-2}};

	}
}

// Extends ChessPiece to inherit numberPad, movement, and PossibleMoves
// The Knight is-a ChessPiece
// The problem originally asked for Knight only
class Knight extends ChessPiece
{
	public Knight()
	{
		// Overwrite the movement matrix for unique Knight movements
		this.movement = new int[][]
					   {{ 1, 2},
						{ 1,-2},
						{-1, 2},
						{-1,-2},
						{ 2, 1},
						{ 2,-1},
						{-2, 1},
						{-2,-1}};
	}
}

// Extension to Pawn technically useless - Pawns only move forward

// Converting the indices of two dimensional arrays into singular coordinates
// Coordinates make the transfer of data from numberPad much simpler
class Coordinate
{
	public final int row, col;

	public Coordinate(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
}