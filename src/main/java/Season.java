import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Season implements Comparable<Season>{
    private List<Race> raceList;
    private Date year;

    public Season(Date year) {
        this.year = year;
        this.raceList = new ArrayList<>();
    }

    public Date getYear() {
        return year;
    }

    public Season(String year) throws ParseException {
        this(new SimpleDateFormat("yyyy").parse(year));
    }

    public boolean addRace(Race race){
        raceList.add(race);
        return true;
    }

    public void printResults(Race.League league,Result.ResultType resultType){
        switch (resultType){
            case FANCIER: {
                //Collect total of scores of the flyers for each fancier for the races in the league
                List<ResultMember> resultList = new ArrayList<>();
                for (Race race : raceList) {
                    if (race.getLeague() == league || league == null) {
                        ResultMember.addScores(race.getCompetitorList(), resultList);
                    }
                }

                resultList.sort(new ResultComparator().reversed());
                System.out.println(ResultMember.header());
                for (ResultMember rm : resultList) {
                    System.out.println(rm.row());
                }
                break;
            }
            case FLYER: {
                //Collect total of scores for each competitor for the races in the league
                List<ResultCompetitor> resultList = new ArrayList<>();
                for (Race race : raceList) {
                    if (race.getLeague() == league || league == null) {
                        ResultCompetitor.addScores(race.getCompetitorList(), resultList);
                    }
                }

                resultList.sort(new ResultComparator().reversed());
                System.out.println(ResultCompetitor.header());
                for (ResultCompetitor rc : resultList) {
                    System.out.println(rc.row());
                }
                break;
            }
        }
    }

    @Override
    public int compareTo(Season season) {
        return season.getYear().compareTo(year);
    }

    @Override
    public String toString() {
        return "Season{" +
                "raceList=" + raceList +
                ", year=" + year +
                '}';
    }
}
