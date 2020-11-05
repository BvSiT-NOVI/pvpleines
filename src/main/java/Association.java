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
    private List<Owner> ownerList;

    private int counterMemberRegistrationId=1000;//to create unique memberRegistrationId when adding Member

    public Association(String name, String streetAddress, String city, String zipCode, String countryCode, String phoneNumber) {
        this.name = name;
        this.streetAddress = streetAddress;
        this.city = city;
        this.zipCode = zipCode;
        this.countryCode = countryCode; //TODO country code as also used in owner registration ring number always NL and depends not on association
        this.phoneNumber = phoneNumber;
        memberList = new ArrayList<>();
        ownerList = new ArrayList<>();
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

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public int addMember(Member member){
        //TODO check if exists
        member.setId(counterMemberRegistrationId++);
        memberList.add(member);
        return member.getId();
    }

    public int addOwner(Member member,Owner owner){
        //TODO check if exists
        int id = addMember(member);
        owner.setMemberId(id);
        ownerList.add(owner);
        return id;
    }

    public Owner getOwner(Pigeon pigeon){
        for (Owner owner: ownerList){
            for (Pigeon p:owner.getPigeonList()){
                if (p.hasOwnerID(pigeon.getShortYear(), pigeon.getRingNumber())) return owner;
            }
        }
        return null;
    }

    public Owner getOwner(int memberId){
        for (Owner owner: ownerList){
            if (owner.getMemberId()==memberId) return owner ;
        }
        return null;
    }

    public Member getMember(int memberId){
        for (Member m: memberList){
                if (m.getId()==memberId) return m ;
        }
        return null;
    }
}
