package automata.nfa;

import utility.Alphabet;
import utility.State;
import utility.StatesSet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NondeterministicFiniteAutomaton {
    private final StatesSet states;
    private final Alphabet alphabet;
    private final TransitionFunction table;
    private final State initialState;
    private final StatesSet acceptingStates;

    public NondeterministicFiniteAutomaton(StatesSet states, Alphabet alphabet, TransitionFunction table, State initialState, StatesSet acceptingStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.table = table;
        this.initialState = initialState;
        this.acceptingStates = acceptingStates;
    }

    public StatesSet getStates() {
        return states;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public TransitionFunction getTransitionFunction() {
        return table;
    }

    public State getInitialState() {
        return initialState;
    }

    public StatesSet getAcceptingStates() {
        return acceptingStates;
    }

    public boolean run(String word){
        Set<State> currentStates = Collections.singleton(initialState);

        for (char readSymbol: word.toCharArray()){
            Set<State> nextStates = new HashSet<>();

            for (State readState: currentStates){
                nextStates.addAll(table.get(readState, readSymbol));
            }

            currentStates = nextStates;
        }

        currentStates.retainAll(acceptingStates.getStates());
        return !currentStates.isEmpty();
    }
}
