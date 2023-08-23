package com.iimtiaz.rawdatatojson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class TopicData {
    private String topicNumber;
    private String topicName;
    private List<ContentData> contents;
}