public class ResultMember extends Result<Member>{
    public ResultMember(Member owner) {
        super(owner);
    }

    public ResultMember(Member owner, double score) {
        super(owner, score);
    }

    //https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java
    //https://dzone.com/articles/how-to-format-a-string-clarified
    @Override
    public String line() {
        return String.format("%-20s %10s", super.get().getFullName(), numberformat.format(getScore()));
    }

    //TODO @Override gives error. How to ensure to have this as an override from abstract superclass Result
    public static String header() {
        return String.format("%-20s %10s", Header.OWNER_NAME.label,Header.SCORE.label);
    }
}
