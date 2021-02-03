public class Comparator implements java.util.Comparator<BoardState> {

    @Override
    public int compare(BoardState o1, BoardState o2) {
        if (o1.getHeuristicValue() > o2.getHeuristicValue()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
