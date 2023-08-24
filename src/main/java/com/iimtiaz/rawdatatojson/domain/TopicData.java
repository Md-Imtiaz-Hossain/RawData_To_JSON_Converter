package com.iimtiaz.rawdatatojson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
class TopicData {
    private String topicNumber;
    private String topicName;
    private List<ContentData> contents;

    @Override
    public String toString() {
        String contentsString = contents.stream()
                .map(ContentData::toString)
                .collect(Collectors.joining(","));

        return "{" +
                "\"topic_number\":\"" + topicNumber + "\"," +
                "\"topic_name\":\"" + topicName + "\"," +
                "\"contents\":[" + contentsString + "]" +
                "}";
    }
}