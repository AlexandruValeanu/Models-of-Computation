package automata.turingmachine;

import utility.State;

import java.util.Objects;

public class Move {
    private final State state;
    private final char charToWrite;
    private final Direction direction;

    public Move(State state, char charToWrite, Direction direction) {
        this.state = state;
        this.charToWrite = charToWrite;
        this.direction = direction;
    }

    public State getState() {
        return state;
    }

    public char getCharToWrite() {
        return charToWrite;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return charToWrite == move.charToWrite &&
                Objects.equals(state, move.state) &&
                direction == move.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, charToWrite, direction);
    }

    @Override
    public String toString() {
        return "automata.TuringMachine.automata.TuringMachine.Move{" +
                "state=" + state +
                ", charToWrite=" + charToWrite +
                ", direction=" + direction +
                '}';
    }
}
