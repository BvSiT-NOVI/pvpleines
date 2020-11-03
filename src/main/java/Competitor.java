import java.util.Date;

public class Competitor extends Pigeon {
    private Date finishTime;
    private String chipNumber;
    private Owner currentOwner;
    private double speedMps;
    private double score;
    private int memberRegistrationId;

    public Competitor(Pigeon pigeon, String chipNumber, Member currentMember) {
        super(pigeon.getYearBirth(), pigeon.getRingNumber());
        this.chipNumber = chipNumber;
        //currentOwner::pigeonList does not need to be set since Competitor class concerns only this Pigeon
        //In contrast, we save Member::registrationId to be able to identify the current owner
        this.currentOwner= new Owner(currentMember.getFirstName()  //create a deep copy
                ,currentMember.getLastName(),currentMember.getLoftLocation());
        memberRegistrationId = currentMember.getRegistrationId();
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

    public Owner getCurrentOwner() {
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

    public int getMemberRegistrationId() {
        return memberRegistrationId;
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
