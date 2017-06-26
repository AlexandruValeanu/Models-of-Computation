import automata.dfa.DeterministicFiniteAutomaton;

public class Main {

    public static void main(String[] args) {
        DeterministicFiniteAutomaton dfa = DeterministicFiniteAutomaton.endInZero();
        System.out.println(dfa.run("00000001110"));
    }
}
