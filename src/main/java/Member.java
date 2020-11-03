import java.util.Date;

public class Member extends Owner {
    static int instanceCounter = 1000;
    private boolean membershipFeePaid=false;
    private int registrationId;
    private Date registrationDate;
    private Date unsubscriptionDate;//To be able to continue to show results even if unsubscribed

    public Member(String firstName, String lastName, Location loftLocation) {
        super(firstName, lastName, loftLocation);
        registrationId= uniqueRegistrationId();
    }

    public Member(Owner owner){
        super(owner.getFirstName(), owner.getLastName(), owner.getLoftLocation());
        super.setEmailAddress(owner.getEmailAddress());
        super.setPhoneNumber(owner.getPhoneNumber());
        super.setPigeonList(owner.getPigeonList());//TODO ?? This is only a reference, which would mean that if one changes the list in the org. owner it would also change in Member::owner
        registrationId= uniqueRegistrationId();
        registrationDate = new Date();
    }

    private int uniqueRegistrationId(){
        //https://stackoverflow.com/questions/13431013/increments-by-1-and-add-it-with-every-created-object
        instanceCounter++;
        return instanceCounter;
    }

    public void setMembershipFeePaid(boolean membershipFeePaid) {
        this.membershipFeePaid = membershipFeePaid;
    }

    public boolean isMembershipFeePaid() {
        return membershipFeePaid;
    }

    public int getRegistrationId() {
        return registrationId;
    }
}
