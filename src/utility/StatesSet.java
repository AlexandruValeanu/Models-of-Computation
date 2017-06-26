package utility;


import java.util.*;

public class StatesSet implements Iterable<State> {
    private final Set<State> states;

    private StatesSet(Set<State> states){
        this.states = states;
    }

    public boolean contains(State state){
        return states.contains(state);
    }

    public Set<State> getStates(){
        return states;
    }

    public static StatesSet buildStatesSet(StatesSet statesSet){
        Set<State> allStates = new HashSet<>();
        allStates.addAll(statesSet.states);

        return new StatesSet(Collections.unmodifiableSet(allStates));
    }

    public static StatesSet buildStatesSet(StatesSet statesSet, State... states){
        Set<State> allStates = new HashSet<>();
        allStates.addAll(Arrays.asList(states));
        allStates.addAll(statesSet.states);

        return new StatesSet(Collections.unmodifiableSet(allStates));
    }

    public static StatesSet buildStatesSet(StatesSet statesSet1, StatesSet statesSet2){
        Set<State> allStates = new HashSet<>();
        allStates.addAll(statesSet1.states);
        allStates.addAll(statesSet2.states);

        return new StatesSet(Collections.unmodifiableSet(allStates));
    }

    public static StatesSet buildStatesSet(StatesSet statesSet1, StatesSet statesSet2, State... states){
        Set<State> allStates = new HashSet<>();
        allStates.addAll(Arrays.asList(states));
        allStates.addAll(statesSet1.states);
        allStates.addAll(statesSet2.states);

        return new StatesSet(Collections.unmodifiableSet(allStates));
    }

    public static StatesSet buildStatesSet(State... states){
        Set<State> allStates = new HashSet<>();
        allStates.addAll(Arrays.asList(states));

        return new StatesSet(Collections.unmodifiableSet(allStates));
    }

    public static StatesSet buildStatesSet(State[] arrayStates, State... states){
        Set<State> allStates = new HashSet<>();
        allStates.addAll(Arrays.asList(arrayStates));
        allStates.addAll(Arrays.asList(states));

        return new StatesSet(Collections.unmodifiableSet(allStates));
    }

    @Override
    public Iterator<State> iterator() {
        return states.iterator();
    }
}
