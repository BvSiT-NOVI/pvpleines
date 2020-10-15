public class ScoreMember {
    private Member member;
    private int score;

    public ScoreMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
