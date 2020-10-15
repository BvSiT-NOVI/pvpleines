import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Race {
    private String name;
    private String type;//fond name
    private Date raceDate;
    private Date liberationTime;
    private LiberationPlace liberationPlace;
    private List<Competitor> competitorList;
    private List<ScoreMember> scoreMemberList;
    private Date closeTime;
    //TODO closeTime;
    //TODO disqualification if not reported by phone in time after arrival

    public Race(String name, String type, Date raceDate) {
        this.name = name;
        this.type = type;
        this.raceDate = raceDate;
        competitorList = new ArrayList<>();
        scoreMemberList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }

    public Date getLiberationTime() {
        return liberationTime;
    }

    public void setLiberationTime(Date liberationTime) {
        this.liberationTime = liberationTime;
    }

    public LiberationPlace getLiberationPlace() {
        return liberationPlace;
    }

    public void setLiberationPlace(LiberationPlace liberationPlace) {
        this.liberationPlace = liberationPlace;
    }

    public List<Competitor> getCompetitorList() {
        return competitorList;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public double getSpeedMps(Association association, Competitor competitor){
        try{
            Member m = association.getOwner(competitor);
            if (m!=null){
                double distance = getDistance(m.getLoftLocation());
                if (distance > 0){
                    if (competitor.getFinishTime()!=null) {
                        return calcSpeedMps(distance,liberationTime,competitor.getFinishTime());
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return 0.0;
        }
        return 0.0;
    }

    public static double calcSpeedMps(double distanceKM,Date start,Date end){
        //TODO best procedure to calc duration? Use Calendar?
        long mSeconds = (end.getTime() - start.getTime());
        return distanceKM / mSeconds; // m/sec
    }

    public void calcResultMembers(){
        //TODO fill scoreMemberList
    }

    public double getDistance(Location finishLocation){
        //TODO calculate distance between liberationPlace.location and finishLocation
        if (finishLocation!=null){
            return 600;
        }
        return 0.0;
    }

    public boolean addCompetitor(Competitor competitor){
        competitorList.add(competitor);
        return true;
    }

    public Competitor findCompetitor(String year,String ringNumber){
        for (Competitor c:competitorList){
            if (c.hasOwnerID(year,ringNumber)) return c;
        }
        return null;
    }

}
