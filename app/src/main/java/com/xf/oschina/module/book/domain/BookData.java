package com.xf.oschina.module.book.domain;

import java.util.List;

public class BookData {
    private int count;//": 20,
    private int start;//": 0,
    private int total;//": 57,
    private List<Book> books;//

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
