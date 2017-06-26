import automata.dfa.DeterministicFiniteAutomaton;
import automata.dfa.TransitionFunctionDFA;
import automata.turingmachine.Direction;
import automata.turingmachine.TransitionFunction;
import automata.turingmachine.TuringMachine;
import utility.Alphabet;
import utility.State;
import utility.StatesSet;

public class Examples {
    private Examples(){
    }

    public static DeterministicFiniteAutomaton endInZero(){
        State q1 = new State("q1");
        State q2 = new State("q2");

        StatesSet allStates = StatesSet.buildStatesSet(q1, q2);
        StatesSet acceptingStates = StatesSet.buildStatesSet(q1);
        Alphabet alphabet = new Alphabet("01");

        TransitionFunctionDFA table = new TransitionFunctionDFA();
        table.addTransition(q1, '0', q1);
        table.addTransition(q1, '1', q2);
        table.addTransition(q2, '0', q1);
        table.addTransition(q2, '1', q2);

        return new DeterministicFiniteAutomaton(allStates, alphabet, table, q1, acceptingStates);
    }

    public static DeterministicFiniteAutomaton everyOddPositionIsAOne(){
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3");

        StatesSet allStates = StatesSet.buildStatesSet(q1, q2, q3);
        StatesSet acceptingStates = StatesSet.buildStatesSet(q1, q2);
        Alphabet alphabet = new Alphabet("01");

        TransitionFunctionDFA table = new TransitionFunctionDFA();
        table.addTransition(q1, '0', q3);
        table.addTransition(q1, '1', q2);
        table.addTransition(q2, '0', q1);
        table.addTransition(q2, '1', q1);
        table.addTransition(q3, '0', q3);
        table.addTransition(q3, '1', q3);

        return new DeterministicFiniteAutomaton(allStates, alphabet, table, q1, acceptingStates);
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
