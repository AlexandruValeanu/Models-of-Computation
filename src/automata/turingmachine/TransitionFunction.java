package automata.turingmachine;

import utility.*;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class TransitionFunction implements utility.TransitionFunction<Map.Entry<State, Character>, Move> {
    private final Map<Map.Entry<State, Character>, Move> map = new HashMap<>();

    public void addTransition(State readState, char readSymbol, State writeState, char writeSymbol, Direction direction){
        map.put(new AbstractMap.SimpleImmutableEntry<>(readState, readSymbol),
                new Move(writeState, writeSymbol, direction));
    }

    public Move get(State readState, char readSymbol){
        return map.get(new AbstractMap.SimpleImmutableEntry<>(readState, readSymbol));
    }
}
