import java.util.*;

public class MoveTree {
    private final Board finalBoard;
    private final Stack<BoardState> moveStack;
    private final BoardHandler boardHandler;
    private final Set<BoardState> previousStates = new HashSet<>();

    public MoveTree(Board finalBoard, Stack<BoardState> moveStack, BoardHandler boardHandler) {
        this.finalBoard = finalBoard;
        this.moveStack = moveStack;
        this.boardHandler = boardHandler;
    }

    public int getPath(BoardState currentState) {
        if (currentState.getCurrentBoard().equals(finalBoard)) {
            return 1;
        }
        List<BoardState> possibleStates = new ArrayList<>();
        previousStates.add(currentState);
        Set<BoardState> childStates = boardHandler.getChildStates(currentState);
        currentState.setChildStateSet(childStates);
        //BoardState tempState = currentState;
/*        for (BoardState b : childStates) {

            if (b.getHeuristicValue() >= tempState.getHeuristicValue()) {
                if (!containsPreviousStates(b) && !containsPossibleStates(b, possibleStates)) {
                    tempState = b;
                    possibleStates.add(b);
                }
            }
            if (b.getHeuristicValue() == currentState.getHeuristicValue()) {
                if (!containsPreviousStates(b) && !containsPossibleStates(b, possibleStates)) {
                    possibleStates.add(b);
                }
            }
        }*/
        for (BoardState b : childStates) {
            if (!containsPossibleStates(b, possibleStates) && !containsPreviousStates(b)) {
                possibleStates.add(b);
            }
        }
        possibleStates.sort(new Comparator());

        for (BoardState b : possibleStates) {
            if (b != currentState && !b.getCurrentBoard().equals(finalBoard)) {
                int value = getPath(b);
                if (value == 1) {
                    //System.out.println(b);
                    moveStack.push(b);
                    return 1;
                }
            }
            else if (b == currentState && b.getCurrentBoard() != finalBoard) {
                return -1;
            }
            else {
                //System.out.println(b);
                moveStack.push(b);
                return 1;
            }
        }
        return 0;
    }

    private boolean containsPreviousStates(BoardState boardState) {
        for (BoardState b : previousStates) {
            if (b.equals(boardState)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsPossibleStates(BoardState boardState, List<BoardState> possibleStates) {
        for (BoardState b : possibleStates) {
            if (b.equals(boardState)) {
                return true;
            }
        }
        return false;
    }



    public Stack<BoardState> getMoveStack() {
        return moveStack;
    }

}
