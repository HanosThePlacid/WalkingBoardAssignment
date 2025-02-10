package walking.game;

import walking.game.util.Direction;
import walking.game.player.Player;

public class WalkingBoardWithPlayers extends WalkingBoard {
    private Player[] players;
    private int round;
    public static final int SCORE_EACH_STEP = 13;
    public WalkingBoardWithPlayers(int[][] board, int playerCount){
        super(board);
        this.round=0;
        initPlayers(playerCount);
    }
    public WalkingBoardWithPlayers(int size, int playerCount){
        super(size);
        this.round=0;
        initPlayers(playerCount);
    }
    private void initPlayers(int playrCount){
        
    }
    public int[] walk(int... stepCounts){
        int[] scoreS={1,2,3,4};
        return scoreS;
    }
}
