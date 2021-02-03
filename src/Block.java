import java.util.Objects;

public class Block {
    private int numberOnBlock;

    public Block(int numberOnBlock) {
        this.numberOnBlock = numberOnBlock;
    }

    public int getNumberOnBlock() {
        return numberOnBlock;
    }

    public void setNumberOnBlock(int numberOnBlock) {
        this.numberOnBlock = numberOnBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return numberOnBlock == block.numberOnBlock;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOnBlock);
    }
}
