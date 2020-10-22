public class ResultCompetitor extends Result<Competitor> {

    public final static String printFormatRace="%-20s %12s %10s %10s %12s %10s %10s";
    public final static String printFormat="%-20s %12s %10s";

    public ResultCompetitor(Competitor competitor) {
        super(competitor);
    }

    public ResultCompetitor(Competitor competitor, double score) {
        super(competitor, score);
    }

    //https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java
    //https://dzone.com/articles/how-to-format-a-string-clarified
    //@Override
    public String line() {
        Competitor c = get();
        //TODO extract format string as (static?) variable?
        return String.format(printFormat,
                c.getCurrentOwner().getFullName(),
                c.getOwnerID(),
                numberformat.format(getScore())
        );
    }

    //TODO @Override gives error. How to ensure to have this as an override from abstract superclass Result
    public static String header() {
        return String.format(printFormat,
                Header.OWNER_NAME.label,
                Header.OWNER_RING_ID.label,
                Header.SCORE.label);
    }

    public String lineRace(Race race) throws Exception {
        Competitor c = get();
        //Check this is the correct race //TODO simplify
        if (race.findCompetitor(c.getOwnerID())==null) {
            throw new Exception("Competitor does not exist in this Race");
        }

        double distance = Geo.distance(race.getLiberationPlace().getLocation(), c.getCurrentOwner().getLoftLocation());
        return String.format(printFormatRace,
                c.getCurrentOwner().getFullName(),
                c.getOwnerID(),
                c.getChipNumber(),
                numberformat.format(distance),
                //TODO race time
                timeformat.format(c.getFinishTime()),
                numberformat.format(c.getSpeedMps()),
                numberformat.format(getScore())  //NB not from Competitor::getScore
        );
    }

    public static String headerRace() {
        return String.format(printFormatRace,
                Header.OWNER_NAME.label,
                Header.OWNER_RING_ID.label,
                Header.CHIP_ID.label,
                Header.DISTANCE.label,
                Header.ARRIVAL_TIME.label,
                Header.SPEED.label,
                Header.SCORE.label);
    }


}
