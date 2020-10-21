import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Season {
    private List<Race> raceList;
    private Date year;

    public Season(Date year) {
        this.year = year;
        this.raceList = new ArrayList<>();
    }

    public Season(String year) throws ParseException {
        this(new SimpleDateFormat("yyyy").parse(year));
    }



    public boolean addRace(Race race){
        raceList.add(race);
        return true;
    }

    public void printResults(){
        List<Result<Competitor>> resultList = new ArrayList<>();
        for (Race r:raceList){
            for (Competitor c:r.getCompetitorList()){
                Result<Competitor> found = findResultForCompetitor(c,resultList);
                if (found==null){
                    resultList.add(new Result<Competitor>(c,c.getScore()));
                }
                else {
                    found.setScore(found.getScore()+c.getScore());
                }
            }
        }

        resultList.sort(new ResultComparator());

        for (Result<Competitor> r: resultList){
            System.out.println( ((Competitor) r.get()).getOwnerID()+'\t'+r.getScore() );
        }


    }

    private Result<Competitor> findResultForCompetitor(Competitor competitor,List<Result<Competitor>> resultList){
        for (Result<Competitor> r:resultList){
            Competitor c = (Competitor) r.get();
            if (competitor.getOwnerID().equalsIgnoreCase(c.getOwnerID())) return r;
        }
        return null;
    }

}
