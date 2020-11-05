import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultMember extends Result<Member>{

    public final static String printFormat="%-20s %6s %12s";

    private Owner owner;

    public ResultMember(Competitor c)
    {
        super(c.getCurrentMember(),c.getScore());
        owner=c.getCurrentOwner();//enables printing Owner loftLocation, not used for now
    }

    //https://c4code.wordpress.com/2018/03/17/how-to-print-the-results-to-console-in-a-tabular-format-using-java
    //https://dzone.com/articles/how-to-format-a-string-clarified
    @Override
    public String row() {
        return String.format(printFormat, super.get().getFullName(),
                memberIdFormat.format(super.get().getId()),
                numberformat.format(getScore()));
    }

    //TODO @Override gives error. How to ensure to have this as an override from abstract superclass Result
    public static String header() {
        return String.format(printFormat, Header.OWNER_NAME.label,Header.MEMBER_ID.label,Header.SCORE.label);
    }

    //TODO move??
    public static ResultMember findByMemberId(int memberId, List<ResultMember> resultMembers){
        Iterator<ResultMember> resultMemberIterator = resultMembers.iterator();
        while (resultMemberIterator.hasNext()) {
            ResultMember resultMember = resultMemberIterator.next();
            if (resultMember.get().getId() == memberId) {
                return resultMember;
            }
        }
        return null;
    }

    public static void addScoreToList(Competitor competitor, List<ResultMember> resultMembers){
        //Add ResultMember to list for this competitor if it does not exist in the list.
        //Add score of competitor to score of the ResultMember for this competitor in the list
        ResultMember resultMember = findByMemberId(competitor.getCurrentMember().getId(),resultMembers);
        if (resultMember == null){
            resultMember = new ResultMember(competitor);//also adds first score
            resultMembers.add(resultMember);
        }
        else {
            resultMember.addScore(competitor.getScore());
        }
    }

    public static List<ResultMember> addScores(List<Competitor> competitorList,List<ResultMember> resultList){
        if (resultList==null) resultList = new ArrayList<>();
        for (Competitor c: competitorList){
            ResultMember.addScoreToList(c,resultList);
        }
        return resultList;
    }
}
