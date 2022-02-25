package pojo;

import dao.CategoryDAO;

public class Question {
    private int id;
    private String title;
    private String body;
    private String date_created;
    private Category category;

    public Question(int id, String title, String body, String date_created, int category_id) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date_created = date_created;
        this.category = new CategoryDAO().findCategory(category_id);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDate() {
        return date_created;
    }

    public Category getCategory() {
        return category;
    }
}
