package com.ess.filter.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BaseModel {

    private Long id ;
    private LocalDateTime createDate;

}
