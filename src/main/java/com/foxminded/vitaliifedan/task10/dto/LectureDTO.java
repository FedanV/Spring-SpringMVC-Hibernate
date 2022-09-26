package com.foxminded.vitaliifedan.task10.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureDTO {
    private Integer id;
    private Integer courseId;
    private Integer teacherId;
    private Integer groupId;
    private Integer audienceId;
    private String date;
    private Integer pairNumber;
}
