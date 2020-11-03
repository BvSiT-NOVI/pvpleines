import java.util.Comparator;

public class ResultComparator implements Comparator<Result> {
    @Override
    public int compare(Result o1, Result o2) {
        if (o1.getScore()>o2.getScore()) return 1;
        return -1;
    }
}
