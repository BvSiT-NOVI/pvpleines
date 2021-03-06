import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class Result<T> {
    private T t;
    private double score;

    public static enum ResultType{
        FLYER,
        FANCIER;
    }

    public enum Header {
        OWNER_NAME("Lid"),
        MEMBER_ID("Lidnr."),
        OWNER_RING_ID("RingID"),
        CHIP_ID("ChipId"),
        DISTANCE("AfstandKM"),
        RACE_TIME("Racetijd"),
        ARRIVAL_TIME("Aankomsttijd"),
        SPEED("Snelheid"),
        SCORE("Punten");

        public final String label;

        Header(String label) {
            this.label = label;
        }
    }

    public SimpleDateFormat timeformat = new SimpleDateFormat("h:mm:ss.sss");
    public NumberFormat numberformat = new DecimalFormat("#0.000");
    public NumberFormat memberIdFormat = new DecimalFormat("#0000");

    public T get(){
        return this.t;
    }

    public void set(T t1){
        this.t=t1;
    }

    public Result(T t) {
        this.t = t;
    }

    Result(T t,double score){
        this.t = t;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void addScore(double score){
        this.score+=score;
    }

    public abstract String row();

    //TODO abstract but static in subclasses?
    //public abstract String header();

    //TODO move?
    public static void printStrRepeat(char ch,int count){
        //https://stackoverflow.com/questions/1900477/can-one-initialise-a-java-string-with-a-single-repeated-character-to-a-specific
        System.out.println(new String(new char[count]).replace("\0", String.valueOf(ch)));
    }

}