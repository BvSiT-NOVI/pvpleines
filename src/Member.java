import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private boolean membershipFeePaid;
    private Location loftLocation;
    private List<Pigeon> pigeonList;

    public Member(String firstName, String lastName, Location loftLocation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loftLocation = loftLocation;
        pigeonList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMembershipFeePaid() {
        return membershipFeePaid;
    }

    public void setMembershipFeePaid(boolean membershipFeePaid) {
        this.membershipFeePaid = membershipFeePaid;
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

    @Override
    public String toString() {
        return "Member{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", membershipFeePaid=" + membershipFeePaid +
                ", loftLocation=" + loftLocation +
                ", pigeonList=" + pigeonList +
                '}';
    }
}
