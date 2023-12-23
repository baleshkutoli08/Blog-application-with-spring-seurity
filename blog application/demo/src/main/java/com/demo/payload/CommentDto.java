package com.demo.payload;


import com.demo.Entity.post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
     private long id;
     private String name;
     private String body;
     private String email;
     private com.demo.Entity.post post;
}
