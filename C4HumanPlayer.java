package ConnectFourTwoPlayer;

public class C4HumanPlayer extends ConnectPlayer{

	int playerNumber;
	String playerName;
	C4HumanPlayer(int playerNumber, String playerName)
	{
		this.playerName = playerName;
		this.playerNumber = playerNumber;
	}
	
	@Override
	protected String getTypeOfPlayer() {
		return ""+playerNumber;
	}
	
	protected String getPlayerName() {
		return playerName;
	}
}
