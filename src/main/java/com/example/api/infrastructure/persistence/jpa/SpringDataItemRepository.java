package com.example.api.infrastructure.persistence.jpa;

import com.example.api.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataItemRepository extends JpaRepository<Item, Long> { }
