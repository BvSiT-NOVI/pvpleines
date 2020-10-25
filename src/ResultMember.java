public class ResultMember extends Result<Owner>{
    public final static String printFormat="%-20s %12s %10s";

    public ResultMember(Owner owner) {
        super(owner);
    }

    public ResultMember(Owner owner, double score) {
        super(owner, score);
    }

    //https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java
    //https://dzone.com/articles/how-to-format-a-string-clarified
    @Override
    public String line() {
        return String.format(printFormat, super.get().getFullName(), numberformat.format(getScore()));
    }

    //TODO @Override gives error. How to ensure to have this as an override from abstract superclass Result
    public static String header() {
        return String.format(printFormat, Header.OWNER_NAME.label,Header.SCORE.label);
    }
}
