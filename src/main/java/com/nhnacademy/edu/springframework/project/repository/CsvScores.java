package com.nhnacademy.edu.springframework.project.repository;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CsvScores implements Scores {

    public static final String FILE_NAME = "data/score.csv";
    private List<Score> scoreList;

    private CsvScores() {
    }

    private static class LazyHolder {
        public static final CsvScores INSTANCE = new CsvScores();
    }

    /**
     * 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        return LazyHolder.INSTANCE;
    }

    // 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {

            scoreList = reader.lines()
                    .map(this::parsingScore)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("파일을 읽지 못하였습니다.", e);
        }
    }

    private Score parsingScore(String line) {

        String[] split = line.split(",");
        int studentSeq = Integer.parseInt(split[0].trim());
        int score = Integer.parseInt(split[1].trim());

        return new Score(studentSeq, score);
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
