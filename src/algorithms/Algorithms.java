package algorithms;

import automata.dfa.DeterministicFiniteAutomaton;
import automata.nfa.NondeterministicFiniteAutomaton;
import automata.nfa.TransitionFunctionNFA;
import utility.Alphabet;
import utility.State;
import utility.StatesSet;
import utility.Constants;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Algorithms {
    private Algorithms(){
    }

    public static NondeterministicFiniteAutomaton dfaToNfa(DeterministicFiniteAutomaton dfa){
        return new NondeterministicFiniteAutomaton(dfa.getStates(), dfa.getAlphabet(),
                new TransitionFunctionNFA(dfa.getTransitionFunction().getInternalMap()), dfa.getInitialState(),
                dfa.getAcceptingStates());
    }

    private Set<State> reachableByEpsilon(State state, TransitionFunctionNFA table){
        Set<State> allStates = new HashSet<>();
        Queue<State> queue = new ArrayDeque<>();
        queue.add(state);

        while (!queue.isEmpty()){
            state = queue.poll();
            allStates.add(state);

            for (State nextState: table.get(state, Constants.EPSILON)){
                if (!allStates.contains(nextState))
                    queue.add(nextState);
            }
        }

        return allStates;
    }

    public static DeterministicFiniteAutomaton nfaToDfa(NondeterministicFiniteAutomaton nfa){
        //todo: implement
        return null;
    }

    public static NondeterministicFiniteAutomaton nfaUnion(NondeterministicFiniteAutomaton nfa1,
                                                           NondeterministicFiniteAutomaton nfa2){
        StatesSet statesSet1 = nfa1.getStates();
        StatesSet statesSet2 = nfa2.getStates();
        Alphabet alphabet = Alphabet.alphabetUnion(nfa1.getAlphabet(), nfa2.getAlphabet());
        StatesSet acceptingStates = StatesSet.buildStatesSet(nfa1.getAcceptingStates(), nfa2.getAcceptingStates());

        State stateUnion;

        for (int i = 1; ; i++){
            stateUnion = new State("q" + Integer.toString(i));

            if (!statesSet1.contains(stateUnion) && !statesSet2.contains(stateUnion))
                break;
        }

        TransitionFunctionNFA tf = TransitionFunctionNFA.transitionFunctionUnion(nfa1.getTransitionFunction(),
                                                                           nfa2.getTransitionFunction());

        return new NondeterministicFiniteAutomaton(StatesSet.buildStatesSet(statesSet1, statesSet2, stateUnion),
                alphabet, tf, stateUnion, acceptingStates);
    }

    public static NondeterministicFiniteAutomaton nfaConcatenation(NondeterministicFiniteAutomaton nfa1,
                                                                   NondeterministicFiniteAutomaton nfa2){
        StatesSet allStates = StatesSet.buildStatesSet(nfa1.getStates(), nfa2.getStates());
        Alphabet alphabet = Alphabet.alphabetUnion(nfa1.getAlphabet(), nfa2.getAlphabet());
        StatesSet acceptingStates = nfa2.getAcceptingStates();

        TransitionFunctionNFA tf = TransitionFunctionNFA.transitionFunctionUnion(nfa1.getTransitionFunction(),
                                                                           nfa2.getTransitionFunction());

        for (State state: nfa1.getAcceptingStates()){
            tf.addTransition(state, Constants.EPSILON, nfa2.getInitialState());
        }

        return new NondeterministicFiniteAutomaton(allStates, alphabet, tf, nfa1.getInitialState(), acceptingStates);
    }

    public static NondeterministicFiniteAutomaton nfaStar(NondeterministicFiniteAutomaton nfa){
        State newState;

        for (int i = 1; ; i++){
            newState = new State("q" + Integer.toString(i));

            if (!nfa.getStates().contains(newState))
                break;
        }

        Alphabet alphabet = nfa.getAlphabet();
        StatesSet allStates = StatesSet.buildStatesSet(nfa.getStates(), newState);
        TransitionFunctionNFA tf = new TransitionFunctionNFA(nfa.getTransitionFunction());
        StatesSet acceptingStates = StatesSet.buildStatesSet(newState);

        for (State state: nfa.getAcceptingStates()){
            tf.addTransition(state, Constants.EPSILON, newState);
        }

        return new NondeterministicFiniteAutomaton(allStates, alphabet, tf, newState, acceptingStates);
    }
}
