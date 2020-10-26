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
                    ResultMember rm;
                    rm = ResultMember.findByMemberRegistrationId(c.getMemberRegistrationId(),resultMembers);
                    if (rm == null){
                        rm = new ResultMember(c);
                        resultMembers.add(rm);
                    }
                    rm.addScore(c.getScore());//TODO test if changed in list??
                }
            }
        }

        resultMembers.sort(new ResultComparator().reversed());

        System.out.println(ResultMember.header());
        for (ResultMember rm: resultMembers){
            System.out.println(rm.line() );
        }
    }








/*
    public void printResultsFanciers(Race.League league){
        List<ResultMember> resultList = new ArrayList<>();
        for (Race r:raceList){
            if (r.getLeague()==league || league==null){
                for (Competitor c:r.getCompetitorList()){
                    ResultCompetitor found = findResultForCompetitor(c,resultList);
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
        for (ResultCompetitor r: resultList){
            System.out.println(r.line() );
        }
    }
*/

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
