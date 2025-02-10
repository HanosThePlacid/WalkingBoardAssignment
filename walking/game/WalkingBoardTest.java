package walking.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import walking.game.util.Direction;


public class WalkingBoardTest {

    @ParameterizedTest
    @CsvSource({
        "5",
        "3",
        "10"
    })
    public void testSimpleInit(int size) {
        WalkingBoard board = new WalkingBoard(size);
        int[][] tiles = board.getTiles();
        assertEquals(size, tiles.length);
        for (int i = 0; i < size; i++) {
            assertEquals(size, tiles[i].length);
            for (int j = 0; j < size; j++) {
                assertEquals(WalkingBoard.BASE_TILE_SCORE, tiles[i][j]);
            }
        }
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, 3",
        "0, 1, 3",
        "0, 2, 3",
        "1, 0, 4",
        "1, 1, 5",
        "1, 2, 6",
        "2, 0, 7",
        "2, 1, 8",
        "2, 2, 9"
    })
    public void testCustomInit(int x, int y, int expected) {
        int[][] customTiles = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        WalkingBoard board = new WalkingBoard(customTiles);

        assertEquals(expected, board.getTile(x, y));


        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(0, 0));
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(0, 1));

        
        customTiles[0][0] = 999;
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(0, 0));

        
        int[][] tilesCopy = board.getTiles();
        tilesCopy[0][0] = 999;
        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.getTile(0, 0));
    }

    @Test
    public void testMoves() {
        WalkingBoard board = new WalkingBoard(3);

        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.moveAndSet(Direction.RIGHT, 10));
        assertEquals(10, board.getTile(0, 1));
        assertArrayEquals(new int[]{0, 1}, board.getPosition());

        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.moveAndSet(Direction.DOWN, 5));
        assertEquals(5, board.getTile(1, 1));
        assertArrayEquals(new int[]{1, 1}, board.getPosition());

        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.moveAndSet(Direction.LEFT, 2));
        assertEquals(2, board.getTile(1, 0));
        assertArrayEquals(new int[]{1, 0}, board.getPosition());
        

        assertEquals(WalkingBoard.BASE_TILE_SCORE, board.moveAndSet(Direction.UP, 666));
        assertEquals(666, board.getTile(0, 0));
        assertArrayEquals(new int[]{0, 0}, board.getPosition());

        assertEquals(0, board.moveAndSet(Direction.UP, 20));
        assertArrayEquals(new int[]{0, 0}, board.getPosition());
        assertEquals(666, board.getTile(0, 0));
         
    }
}