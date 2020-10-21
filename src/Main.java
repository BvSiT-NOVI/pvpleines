import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        Association association= new Association("PV De Snelvlieger",
                "Badmintonpad 1",
                "Haarlem",
                "2023 BT",
                "NL",
                "0231234567");

        final Date YEAR_2018= new SimpleDateFormat("yyyy").parse("2018");
        final Date YEAR_2019= new SimpleDateFormat("yyyy").parse("2019");
        final Date YEAR_2020= new SimpleDateFormat("yyyy").parse("2020");

        //TODO precision location liberation place?
        final LiberationPlace BERGERAC = new LiberationPlace("Bergerac",new Location(0.30240,44.50490));

        Member member1 = new Member("Gerrit","van Straten",new Location(4.619346,52.389359 ));
        member1.addPigeon(new Pigeon(YEAR_2018,"123441"));
        member1.addPigeon(new Pigeon(YEAR_2020,"123442"));

        Member member2 = new Member("Piet","van der Wel",new Location(4.654193, 52.419828));
        member2.addPigeon(new Pigeon(YEAR_2019,"123443"));
        member2.addPigeon(new Pigeon(YEAR_2020,"123444"));

        association.addMember(member1);
        association.addMember(member2);

        Season season=new Season("2020");

        Race race1 = new Race("Bergerac 2020","MARATHON",new SimpleDateFormat("dd-MM-yyyy").parse("14-08-2020"));
        race1.setLiberationPlace(BERGERAC);

        season.addRace(race1);


        //Enter pigeons in race (marking procedure)

        for (Member m:association.getMemberList()){
            int chipNumber= 12300;
            for (Pigeon p :m.getPigeonList()){
                race1.addCompetitor(new Competitor(p,Integer.toString(chipNumber),m));
                chipNumber++;
            }
        }

        member1.setFirstName("XX");

        Competitor c_inside= race1.findCompetitor("18","123441");
        System.out.println(c_inside.getCurrentOwner().toString());

        //Race starts, pigeons are released

        race1.setLiberationTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("14-08-2020 13:20:12.456"));

        //enter finish time

        //Pigeon(YEAR_2018,"123441"))

        Competitor c1= race1.findCompetitor("18","123441");
        if (c1!=null) {
            c1.setFinishTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("15-08-2020 14:23:12.456"));
        }

        Competitor c2= race1.findCompetitor("20","123442");
        if (c2!=null) {
            c2.setFinishTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("15-08-2020 15:10:12.123"));
        }

        Competitor c3= race1.findCompetitor("19","123443");
        if (c3!=null) {
            c3.setFinishTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("15-08-2020 16:50:40.789"));
        }

        Competitor c4= race1.findCompetitor("20","123444");
        if (c4!=null) {
            c4.setFinishTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("15-08-2020 13:20:12.456"));
        }

        /*
        for (Competitor c : race1.getCompetitorList()){
            System.out.println(c.getShortYear()+"-"+c.getRingNumber()+'\t'+c.getChipNumber()+
                    '\t'+c.getFinishTime()+'\t'+ race1.getSpeedMps(association,c));
        }
        */
        race1.printScore();

        season.printResults();










        //TODO Transfer ownership of pigeon




/*
        Pigeon p = member1.findPigeon("18","123441");
        if (p!=null) System.out.println(p.toString());
*/

/*        for (Member m:association.getMemberList()){
            System.out.println(m.toString());
            for (Pigeon p1:m.getPigeonList()){
                System.out.println(p1.toString());
            }
        }*/




    }
}
