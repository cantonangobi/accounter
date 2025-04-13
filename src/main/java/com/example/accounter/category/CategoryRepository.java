package com.example.accounter.category;

import org.springframework.data.repository.Repository;

import jakarta.transaction.Transactional;

@org.springframework.stereotype.Repository
@Transactional
public interface CategoryRepository extends Repository<Category, Long> {

    
} 
