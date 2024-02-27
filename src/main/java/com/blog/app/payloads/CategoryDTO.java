package com.blog.app.payloads;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private Integer categoryId;

    @NotBlank
    @Size(min = 4)
    private String categoryTile;

    @NotBlank
    @Size(min = 10)
    private String categoryDescription;
}
