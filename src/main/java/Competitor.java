import java.util.Date;

public class Competitor extends Pigeon {
    private Date finishTime;
    private String chipNumber;
    private Owner currentOwner;//save member data as exist when instantiating
    private double speedMps;
    private double score;
    private Member currentMember;//save member data as exist when instantiating

    public Competitor(Pigeon pigeon, String chipNumber, Member member,Owner owner) {
        super(pigeon.getYearBirth(), pigeon.getRingNumber());
        this.chipNumber = chipNumber;

        //Create a partial deep copy with essential data as is on moment of creation
        this.currentOwner = owner.copy();//currentOwner::pigeonList does not need to be set since Competitor class concerns only this Pigeon
        this.currentMember= member.copy();
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

    public Member getCurrentMember() {
        return currentMember;
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
