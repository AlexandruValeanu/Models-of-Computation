package automata.turingmachine;

import utility.Alphabet;
import utility.State;
import utility.StatesSet;

import java.io.*;

public class TuringMachine {
    private final Tape tape;
    private final StatesSet states;
    private final Alphabet inputAlphabet;
    private final Alphabet tapeAlphabet;
    private final char leftEndmarker;
    private final char rightEndmarker;
    private final char blankSymbol;
    private final TransitionFunction table;
    private final State initialState;
    private final State acceptingState;
    private final State rejectingState;

    public TuringMachine(StatesSet states, Alphabet inputAlphabet, Alphabet tapeAlphabet,
                         char leftEndmarker, char rightEndmarker, char blankSymbol, TransitionFunction table, State initialState,
                         State acceptingState, State rejectingState, String word) {
        this.tape = new Tape(leftEndmarker, blankSymbol, word);
        this.states = states;
        this.inputAlphabet = inputAlphabet;
        this.tapeAlphabet = tapeAlphabet;
        this.leftEndmarker = leftEndmarker;
        this.rightEndmarker = rightEndmarker;
        this.blankSymbol = blankSymbol;
        this.table = table;
        this.initialState = initialState;
        this.acceptingState = acceptingState;
        this.rejectingState = rejectingState;
    }

    public TuringMachine(StatesSet states, Alphabet inputAlphabet, Alphabet tapeAlphabet,
                         char leftEndmarker, char rightEndmarker, char blankSymbol, TransitionFunction table,
                         State initialState, State acceptingState, State rejectingState) {
        this(states, inputAlphabet, tapeAlphabet, leftEndmarker, rightEndmarker, blankSymbol, table, initialState,
                acceptingState, rejectingState, "");
    }

    public boolean run() {
        State currentState = null;
        try (PrintWriter writer = new PrintWriter("exec.out")) {
            currentState = initialState;

            while (true) {
                char currentChar = tape.get();
                Move move = table.get(currentState, currentChar);

                writer.printf(String.format("%s '%c' => %s\n%s\n", currentState, currentChar, move, tape));

                if (move == null)
                    break;

                currentState = move.getState();
                tape.writeAndMove(move.getCharToWrite(), move.getDirection());
            }

            writer.print(currentState == acceptingState);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return currentState == acceptingState;
    }

    public boolean run(String word){
        tape.resetTape(word);
        return run();
    }


}
