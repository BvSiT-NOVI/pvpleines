import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO where to save countryCode, used in Pigeon ring ID?
//TODO Young pigeons only add to race if same year
//TODO Speed unit: KM/H? KM/minute?
//TODO PrintTable as interface?
//TODO packages
//TODO Replace Date by Calendar
//TODO add memberRegistrationID as column in printed results

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
        final Place BERGERAC = new Place("Bergerac",new Location(0.30240,44.50490));

        Owner owner1 = new Owner("Gerrit","van Straten",new Location(4.619346,52.389359 ));
        owner1.addPigeon(new Pigeon(YEAR_2018,"123441"));
        owner1.addPigeon(new Pigeon(YEAR_2020,"123442"));

        Owner owner2 = new Owner("Piet","van der Wel",new Location(4.654193, 52.419828));
        owner2.addPigeon(new Pigeon(YEAR_2019,"123443"));
        owner2.addPigeon(new Pigeon(YEAR_2020,"123444"));

        association.addMember(owner1);
        association.addMember(owner2);

        Season season=new Season("2020");

        Race race1 = new Race("Bergerac 2020",Race.League.MARATHON,new SimpleDateFormat("dd-MM-yyyy").parse("14-08-2020"));
        race1.setLiberationPlace(BERGERAC);

        season.addRace(race1);


        //Enter pigeons in race (marking procedure)
        int chipNumber= 12300;
        for (Member m:association.getMemberList()){
            for (Pigeon p :m.getPigeonList()){
                race1.addCompetitor(new Competitor(p,Integer.toString(chipNumber),m));
                chipNumber++;
            }
        }

        //Race starts, pigeons are released

        race1.setLiberationTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("14-08-2020 13:20:12.456"));

        //enter finish times
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

        //Results after race is finished

        System.out.println("Uitslagen " + race1.getName()+ " Competitie: " + race1.getLeague().label);
        System.out.println("Vliegers:");
        //TODO simplify
        try{
            race1.printResults(Result.ResultType.FLYER);
        }
        catch (Exception e){
           e.printStackTrace();
        }

        System.out.println("Liefhebbers:");
        //TODO simplify
        try{
            race1.printResults(Result.ResultType.FANCIER);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Result.printStrRepeat('-',30);

        System.out.println("Algemeen klassement vliegers");
        //season.printGeneralResults();

        season.printResults(null, Result.ResultType.FLYER);

        Result.printStrRepeat('-',30);
        System.out.println("Algemeen klassement liefhebbers");
        season.printResults(null, Result.ResultType.FANCIER);

        Result.printStrRepeat('-',30);
        Race.League league = Race.League.MARATHON;
        System.out.println("Klassement vliegers "+ league.label);
        season.printResults(Race.League.MARATHON, Result.ResultType.FLYER);

        Result.printStrRepeat('-',30);
        System.out.println("Klassement liefhebbers "+ league.label);
        season.printResults(Race.League.MARATHON, Result.ResultType.FANCIER);

        //TODO Transfer ownership of pigeon
    }



}
