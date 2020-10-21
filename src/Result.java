public class Result<T> {
    private T t;
    private Double score;

    public T get(){
        return this.t;
    }

    public void set(T t1){
        this.t=t1;
    }

    public Result(T t) {
        this.t = t;
    }

    Result(T t,double score){
        this.t = t;
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
