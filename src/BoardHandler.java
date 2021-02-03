import java.util.HashSet;
import java.util.Set;

public class BoardHandler {
    private final Board startingState;
    private final Board finalState;
    public BoardHandler(Board startingState, Board finalState) {
        this.startingState = startingState;
        this.finalState = finalState;
    }

    public Set<BoardState> getChildStates(BoardState currentState) {
        Set<BoardState> boardStates = new HashSet<>();
        Move[] moves = new Move[4];
        moves[0] = generateMove(currentState, Direction.UP);
        moves[1] = generateMove(currentState, Direction.DOWN);
        moves[2] = generateMove(currentState, Direction.LEFT);
        moves[3] = generateMove(currentState, Direction.RIGHT);
        for (int i = 0; i < 4; i++) {
            if (moves[i] != null) {
                boardStates.add(generateState(moves[i], getCurrentBoardCopy(currentState)));
            }
        }
        boardStates.remove(null);
        return boardStates;
    }

    private BoardState getCurrentBoardCopy(BoardState currentState) {
        int heuristicValue = currentState.getHeuristicValue();
        Set<BoardState> childStates = new HashSet<>();
        if (!currentState.getChildStateSet().isEmpty()) {
            childStates = new HashSet<>(currentState.getChildStateSet());
        }
        return new BoardState(getCurrentBoardCopy(currentState.getCurrentBoard()), childStates, heuristicValue, currentState.getDirection());
    }

    private Board getCurrentBoardCopy(Board board) {
        Block[][] blocks = new Block[board.getBlocks().length][board.getBlocks()[0].length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                blocks[i][j] = new Block((i * blocks.length) + j);
                blocks[i][j].setNumberOnBlock(board.getBlocks()[i][j].getNumberOnBlock());
            }
        }
        return new Board(blocks);
    }

    private BoardState generateState(Move move, BoardState boardState) {
        int[] rowAndColumn = getRowAndColumn(move, boardState);
        int[] rowAndColumn2 = getRowAndColumn(move, boardState.getCurrentBoard().getBlocks().length);
        swap(rowAndColumn[0], rowAndColumn[1], rowAndColumn2[0], rowAndColumn2[1], boardState, move.getDirection());
        if (!boardState.getCurrentBoard().equals(startingState)) {
            return boardState;
        }
        return null;
    }

    private Move generateMove(BoardState boardState, Direction direction) {
        boolean flag = false;
        Block[][] block = boardState.getCurrentBoard().getBlocks();
        Move move = null;
        int row;
        int column;
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                if (block[i][j].getNumberOnBlock() == 0) {
                    row = i;
                    column = j;
                    if (verifyMove(row, column, direction, boardState.getCurrentBoard().getBlocks().length)) {
                        if (direction == Direction.UP) {
                            move = new Move((i - 1) * block.length + j, block[i - 1][j].getNumberOnBlock(), direction);
                        } else if (direction == Direction.DOWN) {
                            move = new Move((i + 1) * block.length + j, block[i + 1][j].getNumberOnBlock(), direction);
                        } else if (direction == Direction.LEFT) {
                            move = new Move((i * block.length) + (j - 1), block[i][j - 1].getNumberOnBlock(), direction);
                        } else {
                            move = new Move((i * block.length) + (j + 1), block[i][j + 1].getNumberOnBlock(), direction);
                        }
                    }
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return move;
    }

    private boolean verifyMove(int row, int column, Direction direction, int boardLength) {
        if (direction == Direction.LEFT) {
            return column != 0;
        } else if (direction == Direction.UP) {
            return row != 0;
        } else if (direction == Direction.DOWN) {
            return row != boardLength - 1;
        } else {
            return column != boardLength - 1;
        }
    }

    private int[] getRowAndColumn(Move move, BoardState boardState) {
        int[] rowAndColumn = new int[2];
        int boardSize = boardState.getCurrentBoard().getBlocks().length;
        if (move.getDirection() == Direction.UP) {
            rowAndColumn[0] = (move.getBlockNumberOnBoard() / boardSize) + 1;
            rowAndColumn[1] = (move.getBlockNumberOnBoard() % boardSize);
        } else if (move.getDirection() == Direction.DOWN) {
            rowAndColumn[0] = (move.getBlockNumberOnBoard() / boardSize) - 1;
            rowAndColumn[1] = move.getBlockNumberOnBoard() % boardSize;
        } else if (move.getDirection() == Direction.LEFT) {
            rowAndColumn[0] = (move.getBlockNumberOnBoard() / boardSize);
            rowAndColumn[1] = (move.getBlockNumberOnBoard() % boardSize) + 1;
        } else {
            rowAndColumn[0] = (move.getBlockNumberOnBoard() / boardSize);
            rowAndColumn[1] = (move.getBlockNumberOnBoard() % boardSize) - 1;
        }
        return rowAndColumn;
    }

    private void swap(int row1, int column1, int row2, int column2, BoardState boardState, Direction direction) {
        Block[][] blocks = boardState.getCurrentBoard().getBlocks();
        int swap = blocks[row1][column1].getNumberOnBlock();
        blocks[row1][column1].setNumberOnBlock(blocks[row2][column2].getNumberOnBlock());
        blocks[row2][column2].setNumberOnBlock(swap);
        boardState.setHeuristicValue(assignHeuristicValues(blocks));
        boardState.setDirection(direction);
    }

    private int[] getRowAndColumn(Move move, int boardSize) {
        int[] rowAndColumn = new int[2];
        if (move.getDirection() == Direction.UP) {
            rowAndColumn[0] = (move.getBlockNumberOnBoard() / boardSize);
            rowAndColumn[1] = (move.getBlockNumberOnBoard() % boardSize);
        } else if (move.getDirection() == Direction.DOWN) {
            rowAndColumn[0] = (move.getBlockNumberOnBoard() / boardSize);
            rowAndColumn[1] = move.getBlockNumberOnBoard() % boardSize;
        } else if (move.getDirection() == Direction.LEFT) {
            rowAndColumn[0] = (move.getBlockNumberOnBoard() / boardSize);
            rowAndColumn[1] = (move.getBlockNumberOnBoard() % boardSize);
        } else {
            rowAndColumn[0] = (move.getBlockNumberOnBoard() / boardSize);
            rowAndColumn[1] = (move.getBlockNumberOnBoard() % boardSize);
        }
        return rowAndColumn;
    }

    private int assignHeuristicValues(Block[][] blocks) {
        int heuristicValue = 0;
        Block[][] blocks1 = finalState.getBlocks();
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j].getNumberOnBlock() == blocks1[i][j].getNumberOnBlock()) {
                    heuristicValue++;
                }
            }
        }
        return heuristicValue;
    }


}
