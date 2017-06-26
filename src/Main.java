import automata.dfa.DeterministicFiniteAutomaton;
import utility.NullStateException;

public class Main {

    public static void main(String[] args) throws NullStateException {
        DeterministicFiniteAutomaton dfa = Examples.endInZero();
        System.out.println(dfa.run("00000001110"));
        System.out.println(dfa.run("00000001110"));
    }
}
