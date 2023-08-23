package com.iimtiaz.rawdatatojson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormData {
    private String unitNumber;
    private String unitName;
    private List<TopicData> topics;
}



