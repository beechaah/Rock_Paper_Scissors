import java.util.ArrayList;
import java.util.Random;

public class LastUsedStrategy implements Strategy {
    @Override
    public String determineMove(ArrayList<String> playerMoves) {
        if (playerMoves.isEmpty()) {
            // If this is the first move, fall back to random
            String[] moves = {"Rock", "Paper", "Scissors"};
            return moves[new Random().nextInt(moves.length)];
        }

        // Get the last move used by the player
        String lastMove = playerMoves.get(playerMoves.size() - 1);

        // Determine the computer's move that will win against the last move by the player
        switch (lastMove) {
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
