package pojo;

public class SubmittedAnswer {
    private String body;
    private int question_id;

    public SubmittedAnswer(String body, int question_id) {
        this.body = body;
        this.question_id = question_id;
    }

    public String getBody() {
        return body;
    }

    public int getQuestionId() {
        return question_id;
    }
}
