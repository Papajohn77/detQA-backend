package pojo;

public class Answer {
    private int id;
    private String body;
    private String date_created;

    public Answer(int id, String body, String date_created) {
        this.id = id;
        this.body = body;
        this.date_created = date_created;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date_created;
    }
}
