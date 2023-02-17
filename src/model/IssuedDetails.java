package model;



public class IssuedDetails {
    private String bookId;
    private int QtyForIssued;

    public IssuedDetails() {
    }

    public IssuedDetails(String bookId, int qtyForIssued) {
        this.bookId = bookId;
        QtyForIssued = qtyForIssued;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQtyForIssued() {
        return QtyForIssued;
    }

    public void setQtyForIssued(int qtyForIssued) {
        QtyForIssued = qtyForIssued;
    }
}
