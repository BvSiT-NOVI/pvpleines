import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pigeon {
    private Date yearBirth;
    /*  ringNumber = portion NUMBER in full ring owner registration with format
            [Country code (2 characters)][ ][last 2 characters of year of birth][ - ][NUMBER]
            Ex. If full owner registration number is 'NL 21 – 1234567' then ringNumber = '1234567'
     */
    private String ringNumber;
    private String countryCode="NL";

    public Pigeon(Date yearBirth, String ringNumber) {
        this.yearBirth = yearBirth;
        this.ringNumber = ringNumber;
    }

    public Date getYearBirth() {
        return yearBirth;
    }

    public String getRingNumber() {
        return ringNumber;
    }

    public String getOwnerID() {
        //return full ring owner registration
        return countryCode+"-"+getShortYear()+"-"+getRingNumber();
    }

    public String getShortYear(){
        DateFormat dateFormat = new SimpleDateFormat("yy");
        return dateFormat.format(yearBirth);
    }

    public boolean hasOwnerID(String year,String ringNumber){
        if (year.length()==2) year="20"+year;
        Date date;
        try {
            date = new SimpleDateFormat("yyyy").parse(year);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        if (this.ringNumber.equalsIgnoreCase(ringNumber) ){
            if (this.yearBirth.compareTo(date)==0) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pigeon{" +
                "yearBirth=" + yearBirth +
                ", ringNumber='" + ringNumber + '\'' +
                '}';
    }
}
