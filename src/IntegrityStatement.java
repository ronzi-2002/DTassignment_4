
public final class IntegrityStatement {
    public static String signature() {
        String names = "Ilay Tzarfati and Ron Ziskind"; // <- Fill in your names here!
        if (names.length() == 0) {
            throw new UnsupportedOperationException("You should sign here");
        }
        return names;
    }
}
