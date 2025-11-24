package com.studentservice.sms.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private int maths;
    private int physics;
    private int chemistry;
}
