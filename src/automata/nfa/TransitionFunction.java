package automata.nfa;

import utility.State;

import java.util.*;

public class TransitionFunction implements utility.TransitionFunction<Map.Entry<State, Character>, Set<State>> {
    private Map<Map.Entry<State, Character>, Set<State>> map;

    public TransitionFunction(){
        this.map = new HashMap<>();
    }

    public TransitionFunction(TransitionFunction transitionFunction){
        this.map = transitionFunction.getInternalMap();
    }

    public TransitionFunction(Map<Map.Entry<State, Character>, State> map){
        this.map = new HashMap<>();
        map.forEach((k, v) -> addTransition(k.getKey(), k.getValue(), v));
    }

    public void addTransition(State readState, char readSymbol, State writeState){
        Map.Entry<State, Character> entry = new AbstractMap.SimpleImmutableEntry<>(readState, readSymbol);
        Set<State> states = map.get(entry);

        if (states == null)
            states = new HashSet<>();

        states.add(writeState);
        map.put(entry, states);
    }

    public void removeTransition(State readState, char readSymbol, State writeState){
        Map.Entry<State, Character> entry = new AbstractMap.SimpleImmutableEntry<>(readState, readSymbol);
        Set<State> states = map.get(entry);

        if (states == null)
            return;

        states.remove(writeState);
        map.put(entry, states);
    }

    public Set<State> get(State readState, char readSymbol){
        return map.get(new AbstractMap.SimpleImmutableEntry<>(readState, readSymbol));
    }

    public  Map<Map.Entry<State, Character>, Set<State>> getInternalMap(){
        return Collections.unmodifiableMap(map);
    }

    public static TransitionFunction transitionFunctionUnion(TransitionFunction tf1, TransitionFunction tf2){
        Map<Map.Entry<State, Character>, Set<State>> map = new HashMap<>();
        map.putAll(tf1.map);
        map.putAll(tf2.map);

        TransitionFunction tf = new TransitionFunction();
        tf.map = map;
        return tf;
    }
}