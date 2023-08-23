package com.iimtiaz.rawdatatojson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ContentData {
    private String text;
    private List<String> textAlt;
    private String question;
    private List<String> altQuestions;
    private List<String> keywords;
}
