import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LeastUsedStrategy implements Strategy {
    @Override
    public String determineMove(ArrayList<String> playerMoves) {
        // Initialize counts for each move
        Map<String, Integer> moveCount = new HashMap<>();
        moveCount.put("Rock", 0);
        moveCount.put("Paper", 0);
        moveCount.put("Scissors", 0);

        // Count the occurrences of each move in the player's moves
        for (String move : playerMoves) {
            if (moveCount.containsKey(move)) {
                moveCount.put(move, moveCount.get(move) + 1);
            }
        }

        // Determine the least used move by the player
        String leastUsedMove = "Rock"; // Default to Rock
        int leastCount = Integer.MAX_VALUE;

        for (Map.Entry<String, Integer> entry : moveCount.entrySet()) {
            if (entry.getValue() < leastCount) {
                leastUsedMove = entry.getKey();
                leastCount = entry.getValue();
            }
        }

        // Determine the computer's move that will win against the least used move by the player
        switch (leastUsedMove) {
            case "Rock":
                return "Paper"; // Paper beats Rock
            case "Paper":
                return "Scissors"; // Scissors beats Paper
            case "Scissors":
                return "Rock"; // Rock beats Scissors
            default:
                return "Rock"; // Default to Rock
        }
    }
}
