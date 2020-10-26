import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public abstract class Result<T> {
    private T t;
    private double score;

    public static enum ResultType{
        FLYER,
        FANCIER;
    }

    public enum Header {
        OWNER_NAME("Lid"),
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


    public abstract String line();

    //TODO abstract but static in subclasses?
    //public abstract String header();

}