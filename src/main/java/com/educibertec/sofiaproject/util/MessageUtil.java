package com.educibertec.sofiaproject.util;

import lombok.*;

import java.util.Date;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageUtil {
    private String message;
    private Date timestamp;
    private int status;
    private Object result;
}
