package com.blog.app.services;

import com.blog.app.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {

    //Crete
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    //Update
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

    //Delete
    void deleteCategory(Integer categoryId);

    //Get
    CategoryDTO getCategoryById(Integer categoryId);

    //Get All
    List<CategoryDTO> categories();
}
