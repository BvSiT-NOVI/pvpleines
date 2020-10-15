package nl.novi;

import java.util.Date;

public class Competitor2 extends Pigeon {
    /*  BvS Version with private constructor to restrict
     *  and static create() method to restrict creation only if Race object exists
     *  New Competitor object is added directly to the Race object
     */

    private Date finishTime;
    private String chipNumber;
    private Member currentOwner;

/*    public Competitor(Pigeon pigeon, String chipNumber, Member currentOwner) {
        super(pigeon.getYearBirth(), pigeon.getRingNumber());
        this.chipNumber = chipNumber;
        this.currentOwner=currentOwner;
    }*/

    private Competitor2(Pigeon pigeon, String chipNumber, Member currentOwner) {
        super(pigeon.getYearBirth(), pigeon.getRingNumber());
        this.chipNumber = chipNumber;
        this.currentOwner = currentOwner;
    }

    public static Competitor2 create(Pigeon pigeon, String chipNumber, Member currentOwner, Race race){
        if (race!=null && currentOwner!=null){
            Competitor2 competitor2 = new this(pigeon, chipNumber, currentOwner);
            race.addCompetitor();
            return new Competitor2(pigeon, chipNumber, currentOwner);
        }
        return null;
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

    @Override
    public String toString() {
        return "Competitor{" +
                "finishTime=" + finishTime +
                ", chipNumber='" + chipNumber + '\'' +
                ", member="+currentOwner.toString()+
                '}';
    }
}
