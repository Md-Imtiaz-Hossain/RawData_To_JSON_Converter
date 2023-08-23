package com.iimtiaz.rawdatatojson;

import lombok.Data;

import java.util.List;

@Data
public class FormData {
    private String unitNumber;
    private String unitName;
    private List<TopicData> topics;

    // Getters and setters
}

@Data
class TopicData {
    private String topicName;
    private List<ContentData> contents;

    // Getters and setters
}

@Data
class ContentData {
    private String text;
    private List<String> textAlt;
    private String question;
    private List<String> altQuestions;
    private List<String> keywords;

    // Getters and setters
}
