public class Move {
    private final int blockNumberOnBoard;
    private final int numberOnBlock;
    private final Direction direction;

    public Move(int blockNumber, int numberOnBlock, Direction direction) {
        this.blockNumberOnBoard = blockNumber;
        this.numberOnBlock = numberOnBlock;
        this.direction = direction;
    }

    public int getBlockNumberOnBoard() {
        return blockNumberOnBoard;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getNumberOnBlock() {
        return numberOnBlock;
    }
}
