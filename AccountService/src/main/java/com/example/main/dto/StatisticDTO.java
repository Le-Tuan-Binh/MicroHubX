package com.example.main.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class StatisticDTO {
    private Long id;

    @NonNull
    private String message;
    
    @NonNull
    private Date createdDate;
}
