package com.eyesofkhepri.springbootjooq.domain;

import lombok.Data;

@Data
public class BookModel {
    private long authorId;
    private String title;

    public BookModel(long authorId, String title) {
        this.authorId = authorId;
        this.title = title;
    }
}
