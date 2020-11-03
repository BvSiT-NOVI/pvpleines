import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Seasons {
    private List<Season> seasonList;

    public Seasons() {
        seasonList= new ArrayList<Season>();
    }

    public boolean add(Season season){
        for (Season s:seasonList){
            if (s.compareTo(season)==0) return false;
        }
        seasonList.add(season);
        return true;
    }

    public List<Season> getSeasonList() {
        return seasonList;
    }
}
