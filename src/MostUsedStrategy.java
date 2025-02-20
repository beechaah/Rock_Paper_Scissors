import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MostUsedStrategy implements Strategy
{
    @Override
    public String determineMove(ArrayList<String> playerMoves)
    {
        Map<String, Integer> moveCount = new HashMap<>();
        moveCount.put("Rock", 0);
        moveCount.put("Paper", 0);
        moveCount.put("Scissors", 0);


        for (String move : playerMoves)
        {
            if (moveCount.containsKey(move))
            {
                moveCount.put(move, moveCount.get(move) + 1);
            }
        }


        String mostUsedMove = "Rock";
        int mostCount = 0;

        for (Map.Entry<String, Integer> entry : moveCount.entrySet())
        {
            if (entry.getValue() > mostCount)
            {
                mostUsedMove = entry.getKey();
                mostCount = entry.getValue();
            }
        }


        switch (mostUsedMove)
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
