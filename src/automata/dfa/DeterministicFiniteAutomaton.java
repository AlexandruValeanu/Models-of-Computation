package automata.dfa;

import utility.Alphabet;
import utility.NullStateException;
import utility.State;
import utility.StatesSet;

public class DeterministicFiniteAutomaton {
    private final StatesSet states;
    private final Alphabet alphabet;
    private final TransitionFunctionDFA table;
    private final State initialState;
    private final StatesSet acceptingStates;

    public DeterministicFiniteAutomaton(StatesSet states, Alphabet alphabet, TransitionFunctionDFA table,
                                        State initialState, StatesSet acceptingStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.table = table;
        this.initialState = initialState;
        this.acceptingStates = acceptingStates;
    }

    public boolean run(String word) throws NullStateException {
        State currentState = initialState;

        if (currentState == null){
            throw new NullStateException("dfa::initialState");
        }

        for (char readSymbol: word.toCharArray()){
            currentState = table.get(currentState, readSymbol);

            if (currentState == null){
                throw new NullStateException("dfa::currentState");
            }
        }

        return acceptingStates.contains(currentState);
    }

    public StatesSet getStates() {
        return states;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public State getInitialState() {
        return initialState;
    }

    public StatesSet getAcceptingStates() {
        return acceptingStates;
    }

    public TransitionFunctionDFA getTransitionFunction() {
        return new TransitionFunctionDFA(table);
    }
}
