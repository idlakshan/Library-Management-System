package view.tm;

import java.time.LocalDate;
import java.util.Date;

public class CartTm {
    private String BookId;
    private String memId;
    private String bookName;
    private int qty;
    private String returnDate;

    public CartTm() {
    }

    public CartTm(String bookId, String memId, String bookName, int qty, String returnDate) {
        BookId = bookId;
        this.memId = memId;
        this.bookName = bookName;
        this.qty = qty;
        this.returnDate = returnDate;
    }

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
