package com.demo.repositry;

import com.demo.Entity.comment;
import com.demo.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface Commentrepositry  extends JpaRepository<comment , Long> {
    List<comment> findByPostId(long postId);


}
