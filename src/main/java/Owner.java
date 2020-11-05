import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Owner{

    private Location loftLocation;
    private List<Pigeon> pigeonList;
    private int memberId; //TODO how to ensure this can only be set in Association.addMember?

    public Owner(Location loftLocation) {
        this.loftLocation = loftLocation;
        pigeonList = new ArrayList<>();
    }

    public Location getLoftLocation() {
        return loftLocation;
    }

    public void setLoftLocation(Location loftLocation) {
        this.loftLocation = loftLocation;
    }

    public List<Pigeon> getPigeonList() {
        return pigeonList;
    }

    public void setPigeonList(List<Pigeon> pigeonList) {
        this.pigeonList = pigeonList;
    }

    public boolean addPigeon(Pigeon pigeon){
        //TODO check if exists
        pigeonList.add(pigeon);
        return true;
    }

    public Pigeon findPigeon(String year,String ringNumber){
        for (Pigeon p : pigeonList){
            if (p.hasOwnerID(year,ringNumber) ){
                return p;
            }
        }
        return null;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Owner copy(){
        //Create copy with only essential data
        Owner owner = new Owner(loftLocation);
        owner.setMemberId(memberId);
        return owner;
    }
}
