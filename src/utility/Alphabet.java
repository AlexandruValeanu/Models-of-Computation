package utility;

import java.util.HashSet;
import java.util.Set;

public class Alphabet {
    private final Set<Character> set;

    private Alphabet(Set<Character> set){
        this.set = set;
    }

    public Alphabet(String alphabet){
        set = new HashSet<>();

        for (char c: alphabet.toCharArray())
            set.add(c);
    }

    public static Alphabet alphabetUnion(Alphabet alphabet1, Alphabet alphabet2){
        Set<Character> set = new HashSet<>();
        set.addAll(alphabet1.set);
        set.addAll(alphabet2.set);

        return new Alphabet(set);
    }
}
