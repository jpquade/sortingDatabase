package com.sorting.demo.dao;

import com.sorting.demo.entity.SortEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface repository for all database operations.
 */
public interface SortRepository extends JpaRepository<SortEntity, Integer> {
}
