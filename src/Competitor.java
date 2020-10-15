import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Competitor extends Pigeon {
    private Date finishTime;
    private String chipNumber;

    public Competitor(Pigeon pigeon, String chipNumber) {
        super(pigeon.getYearBirth(), pigeon.getRingNumber());
        this.chipNumber = chipNumber;
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

    @Override
    public String toString() {
        return "Competitor{" +
                "finishTime=" + finishTime +
                ", chipNumber='" + chipNumber + '\'' +
                '}';
    }
}
