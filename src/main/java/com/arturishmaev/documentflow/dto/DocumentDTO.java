package com.arturishmaev.documentflow.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocumentDTO {
    private Long id;

    private String title;

    private String content;
}
