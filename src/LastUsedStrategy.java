import java.util.ArrayList;
import java.util.Random;

public class LastUsedStrategy implements Strategy
{
    @Override
    public String determineMove(ArrayList<String> playerMoves)
    {
        if (playerMoves.isEmpty())
        {
            String[] moves = {"Rock", "Paper", "Scissors"};
            return moves[new Random().nextInt(moves.length)];
        }


        String lastMove = playerMoves.get(playerMoves.size() - 1);


        switch (lastMove)
        {
            case "Rock":
                return "Paper";
            case "Paper":
                return "Scissors";
            case "Scissors":
                return "Rock";
            default:
                return "Rock";
        }
    }
}
