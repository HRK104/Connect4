package ConnectFourTwoPlayer;

public class Connect4Grid2DArray implements Connect4Grid{

	static char[][] grid = new char [6][7];
	public static final char BLANK = ' ';
	public static final char COMPUTER = 'C';
	Connect4Grid2DArray()
	{
		emptyGrid();
	}
	
	@Override
	public void emptyGrid() {
		for(int row =0;(row<grid.length);row++)
			for(int column=0;(column<grid[row].length);column++)
				grid[row][column] = BLANK;
		
	}

	@Override
	public boolean isValidColumn(int column) {
		boolean valid = false;
		if(0 <= column && column < 7)
		{
			for(int row=grid.length-1;row>=0&&!valid;row--)
			{
				if(grid[row][column]==BLANK) valid= true;
			}
		}
		return valid;
	}

	@Override
	public boolean isColumnFull(int column) {
		boolean valid = false;
		for(int row =grid.length-1;(row>=0)&&!valid;row--)
		{
			if(grid[row][column] == BLANK) valid = true;
		}
		return valid;
	}

	@Override
	public void dropPiece(ConnectPlayer player, int column) {
		boolean put = false;
		for(int row=grid.length-1;(row>=0)&&!put;row--)
		{
			if(grid[row][column] == BLANK)
			{
				if(player.getTypeOfPlayer().matches("AI")) grid[row][column] =COMPUTER;
				else if(player.getTypeOfPlayer().matches("1")) grid[row][column] ='1';
				else if(player.getTypeOfPlayer().matches("2")) grid[row][column] ='2';
				put = true;
			}
		}
	}

	@Override
	public boolean didLastPieceConnect4() {
		boolean win = false;
		//int row =0;
		for(int column=0;column<grid[0].length;column++)
			for(int row = grid.length-1;row>=0;row--)
			{
				if(grid[row][column] != BLANK)
				{
					//check horizontal
					if(column+3 < grid[0].length && column >= 0 && grid[row][column+0] == grid[row][column+1] && grid[row][column+1] == grid[row][column+2] && grid[row][column+2] == grid[row][column+3] 
							&& grid[row][column+0]!=BLANK && grid[row][column+1]!=BLANK && grid[row][column+2]!=BLANK && grid[row][column+3]!=BLANK ) win = true;
					else if(column+2 < grid[0].length && column-1 >= 0 && grid[row][column-1] == grid[row][column+0] && grid[row][column+0] == grid[row][column+1] && grid[row][column+1] == grid[row][column+2]
							&& grid[row][column-1]!=BLANK && grid[row][column+0]!=BLANK && grid[row][column+1]!=BLANK && grid[row][column+2]!=BLANK ) win = true;
					else if(column+1 < grid[0].length && column-2 >= 0 && grid[row][column-2] == grid[row][column-1] && grid[row][column-1] == grid[row][column+0] && grid[row][column+0] == grid[row][column+1]
							&& grid[row][column-2]!=BLANK && grid[row][column-1]!=BLANK && grid[row][column+0]!=BLANK && grid[row][column+1]!=BLANK ) win = true;
					else if(column+0 < grid[0].length && column-3 >= 0 && grid[row][column-3] == grid[row][column-2] && grid[row][column-2] == grid[row][column-1] && grid[row][column-1] == grid[row][column+0]
							&& grid[row][column-3]!=BLANK && grid[row][column-2]!=BLANK && grid[row][column-1]!=BLANK && grid[row][column+0]!=BLANK ) win = true;
					//check vertical
					else if(row+3 < grid.length && row+0 >= 0 && grid[row+0][column] == grid[row+1][column] && grid[row+1][column] == grid[row+2][column] && grid[row+2][column] == grid[row+3][column]
							&& grid[row+0][column]!=BLANK && grid[row+1][column]!=BLANK && grid[row+2][column]!=BLANK && grid[row+3][column]!=BLANK) win = true;
					else if(row+2 < grid.length && row-1 >= 0 && grid[row-1][column] == grid[row+0][column] && grid[row+0][column] == grid[row+1][column] && grid[row+1][column] == grid[row+2][column]
							&& grid[row-1][column]!=BLANK && grid[row+0][column]!=BLANK && grid[row+1][column]!=BLANK && grid[row+2][column]!=BLANK) win = true;
					else if(row+1 < grid.length && row-2 >= 0 && grid[row-2][column] == grid[row-1][column] && grid[row-1][column] == grid[row+0][column] && grid[row+0][column] == grid[row+1][column]
							&& grid[row-2][column]!=BLANK && grid[row-1][column]!=BLANK && grid[row+0][column]!=BLANK && grid[row+1][column]!=BLANK) win = true;
					else if(row+0 < grid.length && row-3 >= 0 && grid[row-3][column] == grid[row-2][column] && grid[row-2][column] == grid[row-1][column] && grid[row-1][column] == grid[row+0][column]
							&& grid[row-3][column]!=BLANK && grid[row-2][column]!=BLANK && grid[row-1][column]!=BLANK && grid[row+0][column]!=BLANK) win = true;
					//check diag0nal-toRight
					else if(row+3 < grid.length && column+3 < grid[0].length && row+0 >= 0 && column+0 >= 0 && grid[row+0][column+0]==grid[row+1][column+1]&&grid[row+1][column+1]==grid[row+2][column+2]&&grid[row+2][column+2]==grid[row+3][column+3]
							&& grid[row+0][column+0]!=BLANK && grid[row+1][column+1]!=BLANK && grid[row+2][column+2]!=BLANK && grid[row+3][column+3]!=BLANK) win = true;
					else if(row+2 < grid.length && column+2 < grid[0].length && row-1 >= 0 && column-1 >= 0 && grid[row-1][column-1]==grid[row+0][column+0]&&grid[row+0][column+0]==grid[row+1][column+1]&&grid[row+1][column+1]==grid[row+2][column+2]
							&& grid[row-1][column-1]!=BLANK && grid[row+0][column+0]!=BLANK && grid[row+1][column+1]!=BLANK && grid[row+2][column+2]!=BLANK) win = true;
					else if(row+1 < grid.length && column+1 < grid[0].length && row-2 >= 0 && column-2 >= 0 && grid[row-2][column-2]==grid[row-1][column-1]&&grid[row-1][column-1]==grid[row+0][column+0]&&grid[row+0][column+0]==grid[row+1][column+1]
							&& grid[row-2][column-2]!=BLANK && grid[row-1][column-1]!=BLANK && grid[row+0][column+0]!=BLANK && grid[row+1][column+1]!=BLANK) win = true;
					else if(row+0 < grid.length && column+0 < grid[0].length && row-3 >= 0 && column-3 >= 0 && grid[row-3][column-3]==grid[row-2][column-2]&&grid[row-2][column-2]==grid[row-1][column-1]&&grid[row-1][column-1]==grid[row+0][column+0]
							&& grid[row-3][column-3]!=BLANK && grid[row-2][column-2]!=BLANK && grid[row-1][column-1]!=BLANK && grid[row+0][column+0]!=BLANK) win = true;
					
					
					else if(row+3 < grid.length && column+0 < grid[0].length && row+0 >= 0 && column-3 >= 0 && grid[row+0][column+0]==grid[row+1][column-1]&&grid[row+1][column-1]==grid[row+2][column-2]&&grid[row+2][column-2]==grid[row+3][column-3]
							&& grid[row+0][column+0]!=BLANK && grid[row+1][column-1]!=BLANK && grid[row+2][column-2]!=BLANK && grid[row+3][column-3]!=BLANK) win = true;
					else if(row+2 < grid.length && column+1 < grid[0].length && row-1 >= 0 && column-2 >= 0 && grid[row-1][column+1]==grid[row+0][column+0]&&grid[row+0][column+0]==grid[row+1][column-1]&&grid[row+1][column-1]==grid[row+2][column-2]
							&& grid[row-1][column+1]!=BLANK && grid[row+0][column+0]!=BLANK && grid[row+1][column-1]!=BLANK && grid[row+2][column-2]!=BLANK) win = true;
					else if(row+1 < grid.length && column+2 < grid[0].length && row-2 >= 0 && column-1 >= 0 && grid[row-2][column+2]==grid[row-1][column+1]&&grid[row-1][column+1]==grid[row+0][column+0]&&grid[row+0][column+0]==grid[row+1][column-1]
							&& grid[row-2][column+2]!=BLANK && grid[row-1][column+1]!=BLANK && grid[row+0][column+0]!=BLANK && grid[row+1][column-1]!=BLANK) win = true;
					else if(row+0 < grid.length && column+3 < grid[0].length && row-3 >= 0 && column+0 >= 0 && grid[row-3][column+3]==grid[row-2][column+2]&&grid[row-2][column+2]==grid[row-1][column+1]&&grid[row-1][column+1]==grid[row+0][column+0]
							&& grid[row-3][column+3]!=BLANK && grid[row-2][column+2]!=BLANK && grid[row-1][column+1]!=BLANK && grid[row+0][column+0]!=BLANK) win = true;

				}
			}
		return win;
	}

	@Override
	public boolean isGridFull() {
		boolean full = true;
		for(int row =0;(row<grid.length)&&full;row++)
			for(int column=0;(column<grid[row].length)&&full;column++)
			{
				if(grid[row][column] == BLANK) full = false;
			}
		return full;
	}
	@Override
	public String toString() {
		String returnString=null;
		returnString = " 1234567 \n";
		//returnString += "8 9 10 11 12 13 14 15";
		
		for (int row = -1; (row <= grid.length); row++)
		{
			returnString +="|";
			for (int column = 0; (column < grid[0].length); column++)
				if ((row == -1) || (row == grid.length))
					returnString+="-";
				else returnString+=grid[row][column];
			returnString +="|\n";
		}
		
		return returnString;
	}


}
