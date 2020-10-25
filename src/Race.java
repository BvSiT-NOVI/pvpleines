import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Race {
    private String name;
    private League league;//fond name
    private Date raceDate;
    private Date liberationTime;
    private Place liberationPlace;
    private List<Competitor> competitorList;
    //TODO closeTime;
    //TODO disqualification if not reported by phone in time after arrival

    public static enum League {
        VITESSE("Vitesse"),
        MIDFOND("Midfond"),
        DAYFOND("Eendaagse fond"),
        MARATHON("Marathon"),
        YOUNG_FLYERS("Jonge duiven");

        public final String label;

        League(String label) {
            this.label = label;
        }
    }

    public Race(String name, League league, Date raceDate) {
        this.name = name;
        this.league = league;
        this.raceDate = raceDate;
        competitorList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
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

    public Place getLiberationPlace() {
        return liberationPlace;
    }

    public void setLiberationPlace(Place liberationPlace) {
        this.liberationPlace = liberationPlace;
    }

    public List<Competitor> getCompetitorList() {
        return competitorList;
    }

    public static double calcSpeedMps(double distanceKM,Date start,Date end){
        //TODO best procedure to calc duration? Use Calendar?
        long seconds = (end.getTime() - start.getTime())/1000;
        return distanceKM*1000 / seconds; // m/sec
    }

    public double calcSpeedMps(double distanceKM,Date arrivalTime){
        return calcSpeedMps(distanceKM, liberationTime,arrivalTime);
    }

    public double getDistance(Location finishLocation){
        if (finishLocation!=null){
            return Geo.distance(getLiberationPlace().getLocation(), finishLocation);
        }
        return 0.0;
    }

    public static double calcScore(double distanceKM,int ranking,int numCompetitors){
        return distanceKM + numCompetitors +1 - ranking;
    }

    private int getNumQualifiedCompetitors(){
        int numQualified = 0;
        for(Competitor c:competitorList){
            if (c.getSpeedMps()>0) numQualified++;
        }
        return numQualified;
        /*
        //TODO simplify? See https://stackoverflow.com/questions/41277862/how-to-filter-a-list-with-a-property-of-an-object-in-the-second-level-list-using
        List<Competitor> listQualified =  competitorList.stream().filter(c->c.getSpeedMps()>0)
                .collect(Collectors.toList());
        return listQualified.size();
         */
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

    public Competitor findCompetitor(String ownerID){
        for (Competitor c:competitorList){
            if (c.hasOwnerID(ownerID)) return c; //TODO assumes without country code
        }
        return null;
    }

    public void calcResult(){
        //Calculate average speed and save in Competitor
        for (Competitor c:competitorList){
            c.setSpeedMps(this);
        }

        //Order competitors on average speed, fastest first
        competitorList.sort(Comparator.comparing(Competitor::getSpeedMps).reversed());

        //calc scores and save in competitorList
        int numQualifiedCompetitors = getNumQualifiedCompetitors();
        for (int i=0;i< competitorList.size();i++){
            Competitor c = competitorList.get(i);
            //TODO distance is calculated also for calc speed
            double distance = getDistance(c.getCurrentOwner().getLoftLocation());
            c.setScore(calcScore(distance,i+1,numQualifiedCompetitors));
        }
    }

    public void printScore(){
        calcResult();
        SimpleDateFormat sd = new SimpleDateFormat("h:mm:ss.sss");
        NumberFormat nf = new DecimalFormat("#0.000");
        for (Competitor c : competitorList){
            System.out.println(
                    c.getCurrentOwner().getFullName()
                    + '\t' + c.getShortYear() +"-"+c.getRingNumber()
                    +'\t'+c.getChipNumber()
                    +'\t'+ nf.format(Geo.distance(liberationPlace.getLocation(), c.getCurrentOwner().getLoftLocation()))
                    +'\t'+ sd.format(c.getFinishTime())
                    //+'\t'+ nf.format(4.0)
                    //+'\t'+ c.getSpeedMps()
                    +'\t'+ nf.format(c.getSpeedMps())
                    + '\t'+ nf.format(c.getScore())
                );
        }
    }

    public void printResult(Result.ResultType resultType) throws Exception {
        calcResult();//TODO Only execute once

        switch (resultType){
            case FLYER:
                List<ResultCompetitor> resultCompetitors = new ArrayList<>();
                for (Competitor c: competitorList){
                    resultCompetitors.add(new ResultCompetitor(c,c.getScore())); //is already ordered on speed
                }
                System.out.println(ResultCompetitor.headerRace());
                for(ResultCompetitor r: resultCompetitors){
                    System.out.println(r.lineRace(this));
                }
                break;
            case FANCIER:
                List<ResultMember> resultMembers = new ArrayList<>();
                for (Competitor c: competitorList){
                    resultMembers.add(new ResultMember(c.getCurrentOwner(),c.getScore())); //is already ordered on speed
                }
                System.out.println(ResultMember.header());
                for(ResultMember r: resultMembers){
                    System.out.println(r.line());
                }
                break;
        }
    }

}
