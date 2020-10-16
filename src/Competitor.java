import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Competitor extends Pigeon {
    private Date finishTime;
    private String chipNumber;
    private Member currentOwner;
    private double speedMps;
    private double score;

    public Competitor(Pigeon pigeon, String chipNumber, Member currentOwner) {
        super(pigeon.getYearBirth(), pigeon.getRingNumber());
        this.chipNumber = chipNumber;
        this.currentOwner= new Member(currentOwner.getFirstName()  //create a deep copy
                ,currentOwner.getLastName(),currentOwner.getLoftLocation());
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public Member getCurrentOwner() {
        return currentOwner;
    }

    public double getSpeedMps() {
        return speedMps;
    }

    public void setSpeedMps(Race race) {
        if (currentOwner==null) return;
        double distance = race.getDistance(currentOwner.getLoftLocation());
        if (distance > 0){
            if (finishTime!=null) {
                //TODO non static? Simplify?
                speedMps = race.calcSpeedMps(distance,finishTime);
            }
        }
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Competitor{" +
                "finishTime=" + finishTime +
                ", chipNumber='" + chipNumber + '\'' +
                ", member="+currentOwner.toString()+
                '}';
    }
}
