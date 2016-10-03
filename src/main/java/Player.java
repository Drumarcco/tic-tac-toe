import java.util.ArrayList;
import java.util.List;

public class Player {

    private boolean isHuman;
    private List<Integer> positions;

    public Player(boolean isHuman) {
        this.isHuman = isHuman;
        this.positions = new ArrayList<Integer>();
    }

    public boolean isHuman() {
        return this.isHuman;
    }

    public void setPosition(int position) {
        if (hasPosition(position)) return;
        positions.add(position);
    }

    public boolean hasPosition(int position) {
        return positions.contains(position);
    }

    public int countMovements() {
        return positions.size();
    }

}
