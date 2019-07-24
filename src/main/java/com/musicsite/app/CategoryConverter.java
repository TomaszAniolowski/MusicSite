package com.musicsite.app;

import com.musicsite.entity.Category;
import com.musicsite.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class CategoryConverter implements Converter<String, Category> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category convert(String id) {
        return categoryRepository.findOne(Long.parseLong(id));
    }
}
