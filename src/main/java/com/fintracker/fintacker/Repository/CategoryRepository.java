package com.fintracker.fintacker.Repository;

import com.fintracker.fintacker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
