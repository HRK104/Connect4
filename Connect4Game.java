/* SELF ASSESSMENT

Connect4Game class (35/35 marks)
My class creates references to the Connect 4 Grid and two Connect 4 Players. It asks the user whether he/she would like to play/quit inside a loop. If the user decides to play then: 1. Connect4Grid2DArray is created using the Connect4Grid interface, 2. the two players are initialised - must specify the type to be ConnectPlayer, and 3. the game starts. In the game, I ask the user where he/she would like to drop the piece. I perform checks by calling methods in the Connect4Grid interface. Finally a check is performed to determine a win. 
Comment:Withim this class, references to Connect 4 Grid and two Connect 4 Players are created and the user is asked to play this game. If the user makes a choice of playing this game, this class creates Connect4Grid2DArray by the use of Connect4Grid interface, initialise  the two players (decide the type of ConnectPlayer) and starts this game. In addition, this program asks the user the position of being dropped and perform checks by the use of methods in the Connect4Grid interface. At the end, this program decide whether there is a winner or not by a check. 

Connect4Grid interface (10/10 marks)
I define all 7 methods within this interface.
Comment:All 7 methods are defined in this interface.

Connect4Grid2DArray class (25/25 marks) 
My class implements the Connect4Grid interface. It creates a grid using a 2D array Implementation of the method to check whether the column to drop the piece is valid. It provides as implementation of the method to check whether the column to drop the piece is full. It provides as implementation of the method to drop the piece.  It provides as implementation of the method to check whether there is a win.
Comment:Connect4Grid interface is implemented within this class. This class generates a 2D array grid and decide whether a drop is valid or not, whether the grid is full or not and whether there is a winner or not.

ConnectPlayer abstract class (10/10 marks)
My class provides at lest one non-abstract method and at least one abstract method. 
Comment:My class has both non-abstract method and abstract method.

C4HumanPlayer class (10/10 marks)
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides the Human player functionality.
Comment:This class extends the ConnectPlayer claas and implements  the Human player functionality.

C4RandomAIPlayer class (10/10 marks)
My class extends the ConnectPlayer claas and overrides the abstract method(s). It provides AI player functionality. 
Comment:This class extends the ConnectPlayer claas and implements AI player functionality.

Total Marks out of 100:

*/

package ConnectFourTwoPlayer;

import java.util.Scanner;
public class Connect4Game {

	C4HumanPlayer firstPlayer ;
	static ConnectPlayer connectPlayer;
	
	
	public static void main(String[]args)
	{
		
		boolean finish = false;
		while(!finish)
		{
			//Ask the user to play or not
			System.out.println("would you like to play connect4 game? (yes/no)");
			Scanner input = new Scanner(System.in);
			String answer = input.next();
			if(answer.equals("yes"))
			{
				finish = false;
			}
			else if(answer.equals("no"))
			{
				finish = true;
			}
			else throw new IllegalArgumentException("Invalid input");
			
			if(!finish) {
				//Ask the user the other player type
				Connect4Grid2DArray grid = new Connect4Grid2DArray();
				System.out.println("Enter your name:");
				String name = input.next();
				C4HumanPlayer firstPlayer = new C4HumanPlayer(1,name);
				boolean vsAi = false;
				
				System.out.println("Would yo like to play with another player? (yes/no)");
				String answer2 = null;
				answer2 = input.next();
				if(answer2.equals("yes"))
				{
					System.out.println("Enter another player name:");
					String anotherName = input.next();
					connectPlayer= new C4HumanPlayer(2,anotherName);
					vsAi = false;
				}
				else if(answer2.equals("no"))
				{
					connectPlayer= new C4RandomAIPlayer("AI");
					vsAi = true;
				}
				else throw new IllegalArgumentException("Invalid input");
				
				//play with human
				if(!vsAi) 
				{
					boolean finished = false;
					while(!finished)
					{
						boolean valid = false;
						while(!valid&&!finished)
						{
							System.out.println(grid.toString());
							System.out.println("Player1: which place would you like to drop a ball? (1-7)");
							if(input.hasNextInt())
							{
								int columnNumber = input.nextInt();
								if(0 >= columnNumber-1 && columnNumber-1 >7) throw new IllegalArgumentException("Invalid input"); 
								
							    if(grid.isValidColumn(columnNumber-1)) 
							    {
							    	grid.dropPiece(firstPlayer,columnNumber-1);
							    	valid = true;
							    }
							    else 
							    {
							    	System.out.println("Invalid input");
							    	valid = false;
							    }
							}
							else throw new IllegalArgumentException("Invalid input"); 
						}
						
						System.out.println(grid.toString());
						if(grid.isGridFull()&&grid.didLastPieceConnect4())
						{
							System.out.println(firstPlayer.getPlayerName()+" wins!");
							finished = true;
						}	
						else if(!grid.isGridFull()&&grid.didLastPieceConnect4()) 
						{
							System.out.println(firstPlayer.getPlayerName()+" wins!");
							finished = true;
						}
						else if(grid.isGridFull()&&!grid.didLastPieceConnect4())
						{
							System.out.println("Draw");
							finished = true;
						}
						
						valid = false;
						while(!valid&&!finished)
						{
							System.out.println("Player2: which place would you like to drop a ball? (1-7)");
							if(input.hasNextInt())
							{
								int columnNumber = input.nextInt();
							    if(grid.isValidColumn(columnNumber-1)) 
							    {
							    	grid.dropPiece(connectPlayer,columnNumber-1);
							    	valid = true;
							    }
							    else 
							    {
							    	System.out.println("Invalid input");
							    	valid = false;
							    }
							}
							else throw new IllegalArgumentException("Invalid input"); 
						}
						
						if(grid.isGridFull()&&grid.didLastPieceConnect4()&&!finished)
						{
							System.out.println(grid.toString());
							System.out.println(connectPlayer.getPlayerName()+" wins!");
							finished = true;
						}	
						else if(!grid.isGridFull()&&grid.didLastPieceConnect4()&&!finished) 
						{
							System.out.println(grid.toString());
							System.out.println(connectPlayer.getPlayerName()+" wins!");
							finished = true;
						}
						else if(grid.isGridFull()&&!grid.didLastPieceConnect4()&&!finished)
						{
							System.out.println(grid.toString());
							System.out.println("Draw");
							finished = true;
						}
					}
					
				}
				
				//play with AI
				else if(vsAi)
				{
					boolean finished = false;
					do
					{
						System.out.println(grid.toString());
						boolean valid = false;
						do
						{
							System.out.println("Player1: which place would you like to drop a ball? ");
							if(input.hasNextInt())
							{
								int columnNumber = input.nextInt();
							    if(grid.isValidColumn(columnNumber-1)) 
							    {
							    	grid.dropPiece(firstPlayer,columnNumber-1);
							    	valid = true;
							    }
							    else 
							    {
							    	System.out.println("Invalid input");
							    	valid = false;
							    }
							}
							else throw new IllegalArgumentException("Invalid input"); 
						}while(!valid);
						
						System.out.println(grid.toString());
						if(grid.isGridFull()&&grid.didLastPieceConnect4())
						{
							System.out.println("Player1 wins!");
							finished = true;
						}	
						else if(!grid.isGridFull()&&grid.didLastPieceConnect4()) 
						{
							System.out.println("Player1 wins!");
							finished = true;
						}
						else if(grid.isGridFull()&&!grid.didLastPieceConnect4())
						{
							System.out.println("Draw");
							finished = true;
						}
						
						valid = false;
						do
						{
							int columnNumber = ((C4RandomAIPlayer) connectPlayer).getColumnNumber();
							if(grid.isValidColumn(columnNumber-1))
							{
								grid.dropPiece(connectPlayer,columnNumber-1);
						    	valid = true;
							}
						}while(!valid); 
						
						
						if(grid.isGridFull()&&grid.didLastPieceConnect4())
						{
							System.out.println(grid.toString());
							System.out.println("AI wins!");
							finished = true;
						}	
						else if(!grid.isGridFull()&&grid.didLastPieceConnect4()) 
						{
							System.out.println(grid.toString());
							System.out.println("AI wins!");
							finished = true;
						}
						else if(grid.isGridFull()&&!grid.didLastPieceConnect4())
						{
							System.out.println(grid.toString());
							System.out.println("Draw");
							finished = true;
						}
					}while(!finished);
				}
				
				
			}
			
		}
	}
}
