package automata.turingmachine;

import utility.State;

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

        return charToWrite == move.charToWrite && (state != null ? state.equals(move.state) : move.state == null) &&
                direction == move.direction;
    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (int) charToWrite;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
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
