public class TextString {
    private int ID;
    private String string, pattern;

    public TextString(int ID, String string, String pattern) {
        this.ID = ID;
        this.string = string;
        this.pattern = pattern;
    }

    public int getID() {
        return ID;
    }

    public String getString() {
        return string;
    }

    public String getPattern() {
        return pattern;
    }
}
