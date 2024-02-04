package com.nhnacademy.edu.springframework.project.repository;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Score implements Comparable<Score>{
    private final int studentSeq;
    private final int score;

    public Score(int studentSeq, int score) {
        this.studentSeq = studentSeq;
        this.score = score;
    }

    public boolean isFail() {
        return (60 > this.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return getStudentSeq() == score1.getStudentSeq() && getScore() == score1.getScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudentSeq(), getScore());
    }

    @Override
    public int compareTo(Score score) {
        return Integer.compare(this.score, score.score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentSeq=" + studentSeq +
                ", score=" + score +
                '}';
    }
}
