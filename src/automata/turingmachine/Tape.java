package automata.turingmachine;

import java.util.ArrayList;
import java.util.List;

public class Tape {
    private List<Character> tape = new ArrayList<>();
    private final char leftEndmarker;
    private final char blankSymbol;
    private int tapeHead;

    public Tape(char leftEndmarker, char blankSymbol, String word) {
        this.leftEndmarker = leftEndmarker;
        this.blankSymbol = blankSymbol;
        tape.add(leftEndmarker);

        for (char c: word.toCharArray())
            tape.add(c);

        tapeHead = 0;
    }

    public Tape(char leftEndmarker, char blankSymbol) {
        this(leftEndmarker, blankSymbol, "");
    }

    private void increaseHead(){
        tapeHead++;
    }

    private void decreaseHead(){
        if (tapeHead > 0)
            tapeHead--;
    }

    private void moveHead(Direction direction){
        if (direction == Direction.LEFT)
            decreaseHead();
        else
            increaseHead();
    }

    private void ensureCapacity(int index){
        while (this.length() <= index)
            tape.add(blankSymbol);
    }

    private char get(int index){
        ensureCapacity(index);
        return tape.get(index);
    }

    private void set(int index, char symbol){
        ensureCapacity(index);
        tape.set(index, symbol);
    }

    public char get(){
        return get(tapeHead);
    }

    public void writeAndMove(char symbol, Direction direction){
        set(tapeHead, symbol);
        moveHead(direction);
    }

    public void resetTape(String word){
        tape = new ArrayList<>();
        tape.add(leftEndmarker);

        for (char c: word.toCharArray())
            tape.add(c);

        tapeHead = 0;
    }

    public int length(){
        return tape.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (char c: tape)
            sb.append(c);

        return sb.toString() + " " + Integer.toString(tapeHead);
    }
}
