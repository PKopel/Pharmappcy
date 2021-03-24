package wt.muppety.Util;

public class StringPair {
    private String first;
    private String second;

    public StringPair(String s1, String s2) {
        first = s1;
        second = s2;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
