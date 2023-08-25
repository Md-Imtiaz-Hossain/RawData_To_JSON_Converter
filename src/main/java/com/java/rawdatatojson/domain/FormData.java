package com.java.rawdatatojson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormData {
    private String unitNumber;
    private String unitName;
    private List<TopicData> topics;

    @Override
    public String toString() {
        String topicsString = topics.stream()
                .map(TopicData::toString)
                .collect(Collectors.joining(","));

        return "{" +
                "\"unit_number\":\"" + unitNumber + "\"," +
                "\"unit_name\":\"" + unitName + "\"," +
                "\"topics\":[" + topicsString + "]" +
                "}";
    }
}



