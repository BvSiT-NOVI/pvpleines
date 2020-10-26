import java.util.Iterator;
import java.util.List;

public class ResultMember extends Result<Owner>{

    private int memberRegistratonId;
    public final static String printFormat="%-20s %12s";

    public ResultMember(Owner owner,int memberRegistratonId)
    {
        this(owner,memberRegistratonId,0);
    }

    public ResultMember(Owner owner, int memberRegistratonId, double score)
    {
        super(owner, score);
        this.memberRegistratonId = memberRegistratonId;
    }

    public ResultMember(Competitor c){
        this(c.getCurrentOwner(),c.getMemberRegistrationId(), c.getScore());
    }

    public int getMemberRegistratonId() {
        return memberRegistratonId;
    }

    //https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java
    //https://dzone.com/articles/how-to-format-a-string-clarified
    @Override
    public String line() {
        return String.format(printFormat, super.get().getFullName(), numberformat.format(getScore()));
    }

    //TODO @Override gives error. How to ensure to have this as an override from abstract superclass Result
    public static String header() {
        return String.format(printFormat, Header.OWNER_NAME.label,Header.SCORE.label);
    }

    //TODO move??
    public static ResultMember findByMemberRegistrationId(int memberRegistrationId, List<ResultMember> resultMembers){
        Iterator<ResultMember> resultMemberIterator = resultMembers.iterator();
        while (resultMemberIterator.hasNext()) {
            ResultMember resultMember = resultMemberIterator.next();
            if (resultMember.getMemberRegistratonId()==memberRegistrationId) {
                return resultMember;
            }
        }
        return null;
    }
}
