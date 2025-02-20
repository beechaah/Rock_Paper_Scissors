import java.util.ArrayList;
import java.util.Random;

public class CheatStrategy implements Strategy
{
    private static final double CHEAT_PROBABILITY = 0.1;

    @Override
    public String determineMove(ArrayList<String> playerMoves)
    {
        Random random = new Random();

        if (random.nextDouble() < CHEAT_PROBABILITY && !playerMoves.isEmpty())
        {

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


        String[] moves = {"Rock", "Paper", "Scissors"};
        return moves[random.nextInt(moves.length)];
    }
}
