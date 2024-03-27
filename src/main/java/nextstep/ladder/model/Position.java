package nextstep.ladder.model;

import java.util.Objects;

public class Position {
    private int position;

    public Position(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("음수 값은 허용하지 않습니다");
        }

        this.position = position;
    }

    public void moveTo(Direction direction) {
        this.position = direction.next(this.position);
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Position position1 = (Position) other;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
