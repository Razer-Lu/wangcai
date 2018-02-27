package algorithm.signal;

public abstract class Signal {
    protected Decision decision;
    protected Double confidence;
    protected String comments;

    public  Signal(){
        this.decision = Decision.NotCalculated;
        this.confidence = 0d;
        this.comments = "";
    }

    public Signal(final Decision decision, Double confidence, final String comments) {
        this.decision = decision;
        this.confidence = confidence;
        this.comments = comments;
    }
}
