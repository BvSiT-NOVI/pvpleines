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

    public void printResults(){
        List<ResultCompetitor> resultList = new ArrayList<>();
        for (Race r:raceList){
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

        resultList.sort(new ResultComparator().reversed());

        System.out.println(ResultCompetitor.header());
        for (ResultCompetitor r: resultList){
            System.out.println(r.line() );
        }
    }

    private ResultCompetitor findResultForCompetitor(Competitor competitor,List<ResultCompetitor> resultList){
        for (ResultCompetitor r:resultList){
            Competitor c =  r.get();
            if (competitor.getOwnerID().equalsIgnoreCase(c.getOwnerID())) return r;
        }
        return null;
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
