import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Season {
    private List<Race> raceList;
    private Date year;

    public Season(Date year) {
        this.year = year;
        this.raceList = new ArrayList<>();
    }
}
