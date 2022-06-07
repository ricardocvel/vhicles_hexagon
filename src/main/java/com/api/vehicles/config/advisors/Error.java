package com.api.vehicles.config.advisors;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Error {
    private String mensagem ;
    private LocalDateTime time;
    private Integer status;

}