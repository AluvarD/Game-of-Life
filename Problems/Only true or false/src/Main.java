import java.util.Objects;

class Primitive {
    public static boolean toPrimitive(Boolean b) {
        return Objects.requireNonNullElse(b, false);
    }
}