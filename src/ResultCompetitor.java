public class ResultCompetitor extends Result<Competitor> {
    public ResultCompetitor(Competitor competitor) {
        super(competitor);
    }

    public ResultCompetitor(Competitor competitor, double score) {
        super(competitor, score);
    }

    //https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java
    //https://dzone.com/articles/how-to-format-a-string-clarified
    @Override
    public String line() {
        Competitor c = get();
        //TODO extract format string as (static?) variable?
        return String.format("%-20s %12s %10s ",
                c.getCurrentOwner().getFullName(),
                c.getOwnerID(),
                numberformat.format(getScore())
                );
    }

    //TODO @Override gives error. How to ensure to have this as an override from abstract superclass Result
    public static String header() {
        return String.format("%-20s %12s %10s ",
                Header.OWNER_NAME.label,
                Header.OWNER_RING_ID.label,
                Header.SCORE.label);
    }
}
