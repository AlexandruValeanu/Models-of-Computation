package automata.dfa;

import utility.Alphabet;
import utility.State;
import utility.StatesSet;

public class DeterministicFiniteAutomaton {
    private final StatesSet states;
    private final Alphabet alphabet;
    private final TransitionFunction table;
    private final State initialState;
    private final StatesSet acceptingStates;

    public DeterministicFiniteAutomaton(StatesSet states, Alphabet alphabet, TransitionFunction table,
                                        State initialState, StatesSet acceptingStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.table = table;
        this.initialState = initialState;
        this.acceptingStates = acceptingStates;
    }

    public boolean run(String word){
        State currentState = initialState;

        for (char readSymbol: word.toCharArray()){
            currentState = table.get(currentState, readSymbol);
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

    public TransitionFunction getTransitionFunction() {
        return new TransitionFunction(table);
    }

    public static DeterministicFiniteAutomaton endInZero(){
        State q1 = new State("q1");
        State q2 = new State("q2");

        StatesSet allStates = StatesSet.buildStatesSet(q1, q2);
        StatesSet acceptingStates = StatesSet.buildStatesSet(q1);
        Alphabet alphabet = new Alphabet("01");

        TransitionFunction table = new TransitionFunction();
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

        TransitionFunction table = new TransitionFunction();
        table.addTransition(q1, '0', q3);
        table.addTransition(q1, '1', q2);
        table.addTransition(q2, '0', q1);
        table.addTransition(q2, '1', q1);
        table.addTransition(q3, '0', q3);
        table.addTransition(q3, '1', q3);

        return new DeterministicFiniteAutomaton(allStates, alphabet, table, q1, acceptingStates);
    }
}
