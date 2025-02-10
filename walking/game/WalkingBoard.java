package walking.game;

import walking.game.util.Direction;

public class WalkingBoard {
    private int[][] tiles;
    private int x;
    private int y;
    public static final int BASE_TILE_SCORE = 3;

    public WalkingBoard(int size){
        this.tiles = new int[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                this.tiles[i][j] = BASE_TILE_SCORE;
            }
        }
        this.x=0;
        this.y=0;
    }
    public WalkingBoard(int[][] tiles) {
        int rows = tiles.length;
        this.tiles = new int[rows][];
        for (int i = 0; i < rows; i++) {
            int cols = tiles[i].length;
            this.tiles[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                this.tiles[i][j] = Math.max(tiles[i][j], BASE_TILE_SCORE);
            }
        }
        this.x = 0;
        this.y = 0;
    }
    public int[][] getTiles(){
        int[][] copy = new int[tiles.length][];
        for (int i = 0; i< tiles.length; i++){
            copy[i] = tiles[i].clone();
        }
        return copy;
    }

    public int[] getPosition(){
        return new int[]{x,y};
    }
    public boolean isValidPosition(int x ,int y){
        return x >= 0 && x < tiles.length && y >=0 && y < tiles[0].length;
    }

    public int getTile(int x, int y){
        if(isValidPosition(x,y)){
            return tiles[x][y];
        }
        throw new IllegalArgumentException("Invalid position");
    }
    public static int getYStep(walking.game.util.Direction direction){
        switch(direction){
            case UP:
                return 0;
            case DOWN:
                return 0;
            case RIGHT:
                return 1;
            case LEFT:
                return -1;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
    public static int getXStep(walking.game.util.Direction direction){
        switch(direction){
            case UP:
                return -1;
            case DOWN:
                return 1;
            case RIGHT:
                return 0;
            case LEFT:
                return 0;
            default:
                throw new IllegalArgumentException("Invalid direction");
        }
    }
    public int moveAndSet(Direction direction,int value){
        int newX = this.x + getXStep(direction);
        int newY = this.y + getYStep(direction);
        if(isValidPosition(newX, newY)){
            int oldValue=tiles[newX][newY];
            this.x=newX;
            this.y=newY;
            this.tiles[newX][newY]=value;
            return oldValue;
        }        
        return 0;
    }
}
