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

    public static TuringMachine equalBinaryWords(){
        Alphabet inputAlphabet = new Alphabet("ae");
        Alphabet tapeAlphabet = new Alphabet("aeáéàè");

        State acceptingState = new State("qacc");
        State rejectingState = new State("qrej");
        State initialState = new State("q0");

        State[] states = new State[11];

        for (int i = 1; i <= 10; i++) {
            states[i] = new State("q" + Integer.toString(i));
        }

        StatesSet allStates = StatesSet.buildStatesSet(states, acceptingState, rejectingState, initialState);

        char leftEndmarker = '⊢';
        char rightEndmarker = '⊣';
        char blankSymbol = ' ';

        TransitionFunction table = new TransitionFunction();

        table.addTransition(initialState, leftEndmarker, states[1], leftEndmarker, Direction.RIGHT);
        table.addTransition(states[2], leftEndmarker, acceptingState, leftEndmarker, Direction.RIGHT);
        table.addTransition(states[3], leftEndmarker, states[4], leftEndmarker, Direction.RIGHT);
        table.addTransition(states[6], leftEndmarker, states[7], leftEndmarker, Direction.RIGHT);
        table.addTransition(states[10], leftEndmarker, states[7], leftEndmarker, Direction.RIGHT);

        table.addTransition(initialState, 'a', states[1], 'a', Direction.RIGHT);
        table.addTransition(states[1], 'a', initialState, 'a', Direction.RIGHT);
        table.addTransition(states[2], 'a', states[3], 'á', Direction.LEFT);
        table.addTransition(states[3], 'a', states[3], 'a', Direction.LEFT);
        table.addTransition(states[4], 'a', states[5], 'à', Direction.RIGHT);
        table.addTransition(states[5], 'a', states[5], 'a', Direction.RIGHT);

        table.addTransition(initialState, 'e', states[1], 'e', Direction.RIGHT);
        table.addTransition(states[1], 'e', initialState, 'e', Direction.RIGHT);
        table.addTransition(states[2], 'e', states[3], 'é', Direction.LEFT);
        table.addTransition(states[3], 'e', states[3], 'e', Direction.LEFT);
        table.addTransition(states[4], 'e', states[5], 'è', Direction.RIGHT);
        table.addTransition(states[5], 'e', states[5], 'e', Direction.RIGHT);

        table.addTransition(states[2], 'à', states[6], 'à', Direction.LEFT);
        table.addTransition(states[3], 'à', states[4], 'à', Direction.RIGHT);
        table.addTransition(states[6], 'à', states[6], 'à', Direction.LEFT);
        table.addTransition(states[7], 'à', states[8], blankSymbol, Direction.RIGHT);
        table.addTransition(states[8], 'à', states[8], 'à', Direction.RIGHT);
        table.addTransition(states[9], 'à', states[9], 'à',  Direction.RIGHT);
        table.addTransition(states[10], 'à', states[10], 'à', Direction.LEFT);

        table.addTransition(states[2], 'è', states[6], 'è', Direction.LEFT);
        table.addTransition(states[3], 'è', states[4], 'è', Direction.RIGHT);
        table.addTransition(states[6], 'è', states[6], 'è', Direction.LEFT);
        table.addTransition(states[7], 'è', states[9], blankSymbol, Direction.RIGHT);
        table.addTransition(states[8], 'è', states[8], 'è', Direction.RIGHT);
        table.addTransition(states[9], 'è', states[9], 'è',  Direction.RIGHT);
        table.addTransition(states[10], 'è', states[10], 'è', Direction.LEFT);

        table.addTransition(states[2], 'á', states[2], 'á', Direction.LEFT);
        table.addTransition(states[5], 'á', states[2], 'á', Direction.LEFT);
        table.addTransition(states[8], 'á', states[10], blankSymbol, Direction.LEFT);
        table.addTransition(states[9], 'á', rejectingState, leftEndmarker, Direction.RIGHT);

        table.addTransition(states[2], 'é', states[2], 'é', Direction.LEFT);
        table.addTransition(states[5], 'é', states[2], 'é', Direction.LEFT);
        table.addTransition(states[8], 'é', rejectingState, leftEndmarker, Direction.RIGHT);
        table.addTransition(states[9], 'é', states[10], blankSymbol, Direction.LEFT);

        table.addTransition(initialState, blankSymbol, rejectingState, leftEndmarker, Direction.RIGHT);
        table.addTransition(states[1], blankSymbol, states[2], rightEndmarker, Direction.LEFT);
        table.addTransition(states[7], blankSymbol, states[7], blankSymbol, Direction.RIGHT);
        table.addTransition(states[8], blankSymbol, states[8], blankSymbol, Direction.RIGHT);
        table.addTransition(states[9], blankSymbol, states[9], blankSymbol, Direction.RIGHT);
        table.addTransition(states[10], blankSymbol, states[10], blankSymbol, Direction.LEFT);

        table.addTransition(states[7], rightEndmarker, acceptingState, leftEndmarker, Direction.RIGHT);

        return new TuringMachine(allStates, inputAlphabet, tapeAlphabet, leftEndmarker, rightEndmarker,
                blankSymbol, table, initialState, acceptingState, rejectingState);
    }
}
