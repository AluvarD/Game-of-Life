class Predicate {

    @FunctionalInterface
    public interface TernaryIntPredicate {
        // Write a method here
        boolean test(int a, int b, int c);
    }

    public static final TernaryIntPredicate allValuesAreDifferentPredicate = (int a, int b, int c) -> a != b && a!= c && b != c;
    // Write a lambda expression here

}