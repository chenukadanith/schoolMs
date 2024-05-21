package com.system.ms.schoolMs.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardRespone<T> {
    private int code ;
    private String message;
    private T data;
}
