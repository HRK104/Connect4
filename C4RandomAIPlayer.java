package ConnectFourTwoPlayer;

import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer{

	String AiString;
	C4RandomAIPlayer(String AiString)
	{
		this.AiString = AiString;
	}
	@Override
	protected String getTypeOfPlayer() {
		return "AI";
	}
	
	static int getColumnNumber() {
		Random randomGenerator = new Random();
		int number = randomGenerator.nextInt(6);
		return number;
	}
	
}
