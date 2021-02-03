import java.util.Objects;
import java.util.Set;

public class BoardState {
    private final Board currentBoard;
    private Set<BoardState> childStateSet;
    private int heuristicValue;
    private Direction direction;

    public BoardState(Board currentBoard, Set<BoardState> childStateSet, int heuristicValue, Direction direction) {
        this.currentBoard = currentBoard;
        this.childStateSet = childStateSet;
        this.heuristicValue = heuristicValue;
        this.direction = direction;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public Set<BoardState> getChildStateSet() {
        return childStateSet;
    }

    @Override
    public String toString() {
        return "BoardState{" +
                "currentBoard = \n" + currentBoard +
                '}';
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public void setChildStateSet(Set<BoardState> childStateSet) {
        this.childStateSet = childStateSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardState that = (BoardState) o;
        return currentBoard.equals(that.currentBoard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentBoard);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
