package com.demo.repositry;

import com.demo.Entity.post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface repositry extends JpaRepository<post,Long> {
}
