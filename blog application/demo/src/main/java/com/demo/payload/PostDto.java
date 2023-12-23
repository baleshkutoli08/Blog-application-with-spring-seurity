package com.demo.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private long id;
    @NotNull
    @Size( min =2, message = "not vallid")
    private String title;
    private String content;
    @Size(min = 4, message = "not valid 33")
    private String description;

}
