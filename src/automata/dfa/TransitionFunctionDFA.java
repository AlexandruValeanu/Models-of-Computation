package automata.dfa;

import utility.State;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TransitionFunctionDFA {
    private final Map<Map.Entry<State, Character>, State> map; //

    public TransitionFunctionDFA(){
        this.map = new HashMap<>();
    }

    public TransitionFunctionDFA(TransitionFunctionDFA transitionFunctionDFA){
        this.map = transitionFunctionDFA.getInternalMap();
    }

    public void addTransition(State readState, char readSymbol, State writeState){
        map.put(new AbstractMap.SimpleImmutableEntry<>(readState, readSymbol), writeState);
    }

    public void removeTransition(State readState, char readSymbol){
        Map.Entry<State, Character> entry = new AbstractMap.SimpleImmutableEntry<>(readState, readSymbol);
        map.put(entry, null);
    }

    public State get(State readState, char readSymbol){
        return map.get(new AbstractMap.SimpleImmutableEntry<>(readState, readSymbol));
    }

    public  Map<Map.Entry<State, Character>, State> getInternalMap(){
        return Collections.unmodifiableMap(map);
    }
}
