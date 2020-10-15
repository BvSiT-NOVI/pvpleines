import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Competitor extends Pigeon {
    private Date finishTime;
    private String chipNumber;
    private Member currentOwner;

    public Competitor(Pigeon pigeon, String chipNumber, Member currentOwner) {
        super(pigeon.getYearBirth(), pigeon.getRingNumber());
        this.chipNumber = chipNumber;
        this.currentOwner=currentOwner;
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
