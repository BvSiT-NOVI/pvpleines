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

    public void printGeneralResults() {
        printResults(null);
    }

    public void printResults(Race.League league){
        List<ResultCompetitor> resultList = new ArrayList<>();
        for (Race race:raceList){
            if (race.getLeague()==league || league==null){
                //TODO move to ResultCompetitor.addToList
                for (Competitor c:race.getCompetitorList()){
                    ResultCompetitor found = ResultCompetitor.findResultForCompetitor(c,resultList);
                    if (found==null){
                        resultList.add(new ResultCompetitor(c,c.getScore()));
                    }
                    else {
                        found.setScore(found.getScore()+c.getScore());
                    }
                }
            }
        }

        resultList.sort(new ResultComparator().reversed());

        System.out.println(ResultCompetitor.header());
        for (ResultCompetitor rc: resultList){
            System.out.println(rc.line() );
        }
    }

    public void printResultsOwners(Race.League league){
        List<ResultMember> resultMembers = new ArrayList<>();
        for (Race race:raceList){
            if (race.getLeague()==league || league==null){
                for (Competitor c:race.getCompetitorList()){
                    ResultMember.addToList(c,resultMembers);//First add ResultMember to list if not exists, then add score for this competitor
                }
            }
        }

        resultMembers.sort(new ResultComparator().reversed());
        System.out.println(ResultMember.header());
        for (ResultMember rm: resultMembers){
            System.out.println(rm.line() );
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
