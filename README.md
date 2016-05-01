Chess Number Pad 

This program was made using java, in direct response to the 'ChessTest' competitive programming problem. 

Problem: Find the number of all unique 10 digit phone numbers that can be created by tracing the
path of a chess piece on a telephone keypad, specifically the Knight.

I created a base program that was extended to all chess pieces, not just the Knight.

Essentially the problem asks to start out on a standard number pad, and move like a chess piece (aka the Knight can move two down and 1 right, etc), to create a 10 digit phone number. The code should return the total number of possible 10 digit phone numbers having traced the pad as a certain chess piece.

Number Pad:
1 2 3
4 5 6
7 8 9
* 0 #
 
Like normal phone numbers, the 10 digit phone number cannot begin with 0 or 1, and the chess piece should never land on a * or a #, as this is considered invalid.

I decided to extend the problem not only to encompass all chess pieces, but to provide user input for the starting position as well. 

Below is sample output given the starting number of '2'.
King:   1898811
Queen:  23034942
Rook:   349622
Bishop: 534
Knight: 1424


