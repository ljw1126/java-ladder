package nextstep.laddergame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import nextstep.laddergame.utils.RandomUtils;

public class Line {

    private static final int BOOLEAN_LIMIT = 2;

    private final List<Bridge> bridges = new ArrayList<>();

    public Line(int countOfPerson) {
        bridges.add(makeRandomBridge());
        IntStream.range(1, countOfPerson - 1)
                 .forEach(index -> addBridge(index, bridges));
    }

    private void addBridge(int index, List<Bridge> bridges) {
        if (mustEmpty(index)) {
            bridges.add(Bridge.empty());
            return;
        }
        bridges.add(makeRandomBridge());
    }

    private Bridge makeRandomBridge() {
        return new Bridge(RandomUtils.generate(BOOLEAN_LIMIT));
    }

    private boolean mustEmpty(int index) {
        return bridges.get(index - 1).exist();
    }

    public List<Bridge> getBridges() {
        return Collections.unmodifiableList(bridges);
    }

    public void moveSide(Position position) {
        if (moveLeft(position)) {
            return;
        }
        moveRight(position);
    }

    private boolean moveLeft(Position position) {
        if (!position.leftMovable()) {
            return false;
        }

        Bridge leftBridge = bridges.get(position.getCursur() - 1);
        if (leftBridge.exist()) {
            position.moveLeft();
            return true;
        }
        return false;
    }

    private void moveRight(Position position) {
        if (!position.rigthMovable(bridges.size())) {
            return;
        }

        Bridge rightBridge = bridges.get(position.getCursur());
        if (rightBridge.exist()) {
            position.moveRight();
        }
    }
}