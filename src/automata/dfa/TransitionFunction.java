package automata.dfa;

import utility.State;

import java.util.*;

public class TransitionFunction implements utility.TransitionFunction<Map.Entry<State, Character>, State> {
    private final Map<Map.Entry<State, Character>, State> map;

    public TransitionFunction(){
        this.map = new HashMap<>();
    }

    public TransitionFunction(TransitionFunction transitionFunction){
        this.map = transitionFunction.getInternalMap();
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
