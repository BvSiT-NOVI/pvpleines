import java.util.ArrayList;
import java.util.List;

public class ResultCompetitor extends Result<Competitor> {

    public final static String printFormatRace="%-20s %12s %10s %10s %12s %10s %10s";
    public final static String printFormat="%-20s %12s %10s";

    public ResultCompetitor(Competitor competitor) {
        super(competitor, competitor.getScore());
    }

    public ResultCompetitor(Competitor competitor, double score) {
        super(competitor, score);
    }

    //https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java
    //https://dzone.com/articles/how-to-format-a-string-clarified
    //@Override
    public String row() {
        Competitor c = get();
        return String.format(printFormat,
                c.getCurrentMember().getFullName(),
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
                c.getCurrentMember().getFullName(),
                c.getOwnerID(),
                c.getChipNumber(),
                numberformat.format(distance),
                //TODO race time
                (c.getFinishTime()!=null)?timeformat.format(c.getFinishTime()): "N/A",
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

    public static ResultCompetitor findResultForCompetitor(Competitor competitor, List<ResultCompetitor> resultList){
        for (ResultCompetitor r:resultList){
            Competitor c =  r.get();
            if (competitor.getOwnerID().equalsIgnoreCase(c.getOwnerID())) return r;
        }
        return null;
    }

    public static void addScoreToList(Competitor competitor, List<ResultCompetitor> resultCompetitors){
        //Add ResultMember to list for this competitor if it does not exist in the list.
        //Add score of competitor to score of the ResultMember for this competitor in the list
        ResultCompetitor resultCompetitor = findResultForCompetitor(competitor,resultCompetitors);
        if (resultCompetitor == null){
            resultCompetitor = new ResultCompetitor(competitor);//also adds score for this competitor
            resultCompetitors.add(resultCompetitor);
        }
        else {
            resultCompetitor.addScore(competitor.getScore());//TODO test if changed in list??
        }
    }

    //TODO remove?
    public static List<ResultCompetitor> addScores(List<Competitor> competitorList){
        List<ResultCompetitor> resultList = new ArrayList<>();
        for (Competitor c: competitorList){
            ResultCompetitor.addScoreToList(c,resultList);
        }
        return resultList;
    }

    public static List<ResultCompetitor> addScores(List<Competitor> competitorList,List<ResultCompetitor> resultList){
        if (resultList==null) resultList = new ArrayList<>();
        for (Competitor c: competitorList){
            ResultCompetitor.addScoreToList(c,resultList);
        }
        return resultList;
    }

}
