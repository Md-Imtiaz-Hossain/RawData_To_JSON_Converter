package com.iimtiaz.rawdatatojson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ContentData {
    private String text;
    private List<String> textAlt;
    private String question;
    private List<String> altQuestions;
    private List<String> keywords;

    @Override
    public String toString() {
        String textAltString = textAlt.stream()
                .map(s -> "\"" + s + "\"")
                .collect(Collectors.joining(","));

        String altQuestionsString = altQuestions.stream()
                .map(s -> "\"" + s + "\"")
                .collect(Collectors.joining(","));

        String keywordsString = keywords.stream()
                .map(s -> "\"" + s + "\"")
                .collect(Collectors.joining(","));

        return "{" +
                "\"text\":\"" + text + "\"," +
                "\"text_alt\":[" + textAltString + "]," +
                "\"question\":\"" + question + "\"," +
                "\"alt_questions\":[" + altQuestionsString + "]," +
                "\"keywords\":[" + keywordsString + "]" +
                "}";
    }
}
