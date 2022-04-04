package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
public class EventDTO {

    @NotNull(message = "not title")
    private String title;

    @NotNull(message = "not content")
    private String content;
}
