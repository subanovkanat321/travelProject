package com.example.travelProject.utils;

import com.example.travelProject.enums.Mark;

public class PutMark {
    Long tourId;
    Long commentId;
    Mark mark;

    public PutMark() {
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
