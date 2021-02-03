import java.util.Arrays;

public class Board {
    private final Block[][] blocks;


    public Board(Block[][] blocks) {
        this.blocks = blocks;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        String s = "";
        for (Block[] block : blocks) {
            for (int j = 0; j < blocks[0].length; j++) {
                s = s.concat(block[j].getNumberOnBlock() + "\t");
            }
            s = s.concat("\n");
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        boolean equal = true;
        int counter = 0;
        for (Block[] blocks : blocks) {
            if (!Arrays.equals(blocks,board.blocks[counter])) {
                equal = false;
                break;
            }
            counter++;
        }
        return equal;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(blocks);
    }
}
