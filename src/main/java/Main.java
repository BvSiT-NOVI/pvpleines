import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        int id = association.addOwner(
                new Member("Gerrit","van Straten"),
                new Owner(new Location(4.619346,52.389359 ))
                );

        //TODO Builder pattern
        Member member = association.getMember(id);
        if (member!=null){
            Owner owner=association.getOwner(id);
            if (owner!=null){
                owner.addPigeon(new Pigeon(YEAR_2018,Pigeon.getUniqueId()));
                owner.addPigeon(new Pigeon(YEAR_2020,Pigeon.getUniqueId()));
            }
        }

        id = association.addOwner(
                new Member("Piet","van der Wel"),
                new Owner(new Location(4.654193, 52.419828))
            );

        member = association.getMember(id);
        if (member!=null){
            Owner owner=association.getOwner(id);
            if (owner!=null){
                owner.addPigeon(new Pigeon(YEAR_2019,Pigeon.getUniqueId()));
                owner.addPigeon(new Pigeon(YEAR_2020,Pigeon.getUniqueId()));
            }
        }

        Season season=new Season("2020");

        Race race1 = new Race("Bergerac 2020",
                Race.League.MARATHON,
                new SimpleDateFormat("dd-MM-yyyy").parse("14-08-2020"));
        race1.setLiberationPlace(BERGERAC);

        season.addRace(race1);


        //Enter pigeons in race (marking procedure)
        int chipNumber= 12300;
        for (Owner owner:association.getOwnerList()){
            Member m = association.getMember(owner.getMemberId());
            for (Pigeon p :owner.getPigeonList()){
                race1.addCompetitor(new Competitor(p,Integer.toString(chipNumber),m,owner));
                chipNumber++;
            }
        }

        //Race starts, pigeons are released

        race1.setLiberationTime(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("14-08-2020 13:20:12.456"));

        //enter finish times

        Calendar calendar = Calendar.getInstance();
        Date arrivalTime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").parse("15-08-2020 14:23:12.456");
        calendar.setTime(arrivalTime);
        for(Competitor c:race1.getCompetitorList()){
            c.setFinishTime(calendar.getTime());
            calendar.add(Calendar.MINUTE,57);//add time to arrival and introduce some irregularity.
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
