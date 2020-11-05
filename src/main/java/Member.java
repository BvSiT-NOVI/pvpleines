import java.util.Date;

public class Member {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;

    private boolean membershipFeePaid=false;
    private int id; //member registration id
    private Date registrationDate;
    private Date unsubscriptionDate;//To be able to continue to show results even if unsubscribed

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setMembershipFeePaid(boolean membershipFeePaid) {
        this.membershipFeePaid = membershipFeePaid;
    }

    public boolean isMembershipFeePaid() {
        return membershipFeePaid;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public Date getUnsubscriptionDate() {
        return unsubscriptionDate;
    }

    public void setUnsubscriptionDate(Date unsubscriptionDate) {
        this.unsubscriptionDate = unsubscriptionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName(){
        return firstName.substring(0, 1).toUpperCase() + ". " + lastName;
    }

    public Member copy(){
        //Make a copy with only essential data
        Member member = new Member(this.firstName,this.lastName);
        member.setId(id);
        member.setEmailAddress(emailAddress);
        member.setPhoneNumber(phoneNumber);
        return member;
    }

}
