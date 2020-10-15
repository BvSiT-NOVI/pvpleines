import java.util.ArrayList;
import java.util.List;

public class Association {
    private String name;
    private String streetAddress;
    private String city;
    private String zipCode;
    private String countryCode; // ISO 3166-1 alpha-2 – two-letter country code, mostly NL
    private String phoneNumber;
    private List<Member> memberList;

    public Association(String name, String streetAddress, String city, String zipCode, String countryCode, String phoneNumber) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.countryCode = countryCode; //TODO country code as also used in owner registration ring number always NL and depends not on association
        this.phoneNumber = phoneNumber;
        memberList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public boolean addMember(Member member){
        //TODO check if exists
        memberList.add(member);
        return true;
    }

    public Member getOwner(Pigeon pigeon){
        for (Member m: memberList){
            for (Pigeon p:m.getPigeonList()){
                if (p.hasOwnerID(pigeon.getShortYear(), pigeon.getRingNumber())) return m;
            }
        }
        return null;
    }


}
