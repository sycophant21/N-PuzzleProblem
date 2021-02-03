import java.util.HashSet;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Block[][] blocks1 = new Block[3][3];
        int max = Math.max(blocks1.length, blocks1[0].length);
        for (int i = 0 ; i < blocks1.length ; i++) {
            for (int j = 0 ; j < blocks1[0].length ; j++) {
                blocks1[i][j] = new Block(( i * max ) + j + 1);
            }
        }
        blocks1[blocks1.length - 1][blocks1[0].length - 1] = new Block(0);
        Board board1 = new Board(blocks1);
        Block[][] blocks = new Block[3][3];
/*        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                blocks[i][j] = new Block(( i * 3 ) + j );
            }
        }*/
        blocks[0][0] = new Block(0);
        blocks[0][1] = new Block(2);
        blocks[0][2] = new Block(3);
        //blocks[0][3] = new Block(4);
        blocks[1][0] = new Block(1);
        blocks[1][1] = new Block(5);
        blocks[1][2] = new Block(6);
        //blocks[1][3] = new Block(15);
        blocks[2][0] = new Block(4);
        blocks[2][1] = new Block(7);
        blocks[2][2] = new Block(8);
/*        blocks[2][3] = new Block(10);
        blocks[3][0] = new Block(9);
        blocks[3][1] = new Block(13);
        blocks[3][2] = new Block(12);
        blocks[3][3] = new Block(2);*/
        Board board = new Board(blocks);
        BoardHandler boardHandler = new BoardHandler(board, board1);
        BoardState boardState = new BoardState(board, new HashSet<>(), 0, Direction.NULL);
        MoveTree moveTree  = new MoveTree(board1, new Stack<>(), boardHandler);
        moveTree.getPath(boardState);

        Stack<BoardState> boardStates = moveTree.getMoveStack();
        System.out.println(boardState);
        while (!boardStates.empty()) {
            System.out.println(boardStates.pop());
        }
    }

}
