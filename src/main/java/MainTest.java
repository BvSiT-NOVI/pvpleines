import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainTest {
    public static void main(String[] args) throws ParseException {
        Date start = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("14-08-2020 13:20:12.456");
        Date end = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("15-08-2020 14:23:12.456");
        NumberFormat nf = new DecimalFormat("#0.000");
        double speed = Race.calcSpeedMps(600.555,start,end);

        System.out.println(speed);
        System.out.println(nf.format(speed));

        final Place BERGERAC = new Place("Bergerac",new Location(0.30240,44.50490));

        Owner owner1 = new Owner(new Location(4.619346,52.389359 ));

        System.out.println(Geo.distance(BERGERAC.getLocation(), owner1.getLoftLocation()));

        Seasons seasons = new Seasons();
        seasons.add(new Season("2019"));
        seasons.add(new Season("2020"));
        seasons.add(new Season("2021"));

        for (Season s : seasons.getSeasonList()){
            System.out.println(s.toString());
        }

    }
}
