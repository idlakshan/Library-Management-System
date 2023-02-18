package model;

public class Details {
    private String id;
    private String memId;
    private String date;
    private String time;
    private String bookId;
    private int qty;

    public Details() {
    }

    public Details(String id, String memId, String date, String time, String bookId, int qty) {
        this.id = id;
        this.memId = memId;
        this.date = date;
        this.time = time;
        this.bookId = bookId;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
