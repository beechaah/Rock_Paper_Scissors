import java.util.ArrayList;
import java.util.Random;

public class CheatStrategy implements Strategy {
    private static final double CHEAT_PROBABILITY = 0.1; // 10% chance to cheat

    @Override
    public String determineMove(ArrayList<String> playerMoves) {
        Random random = new Random();

        if (random.nextDouble() < CHEAT_PROBABILITY && !playerMoves.isEmpty()) {
            // Cheat by picking the move that wins against the player's last move
            String lastMove = playerMoves.get(playerMoves.size() - 1);

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

        // If not cheating, fall back to random
        String[] moves = {"Rock", "Paper", "Scissors"};
        return moves[random.nextInt(moves.length)];
    }
}
