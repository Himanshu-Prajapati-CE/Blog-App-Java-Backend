package com.blog.app.services.impl;

import com.blog.app.entities.Category;
import com.blog.app.exceptions.ResourceNotFondException;
import com.blog.app.payloads.CategoryDTO;
import com.blog.app.repositories.CategoryRepository;
import com.blog.app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        CategoryDTO savedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);

        return savedCategoryDTO;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {

        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFondException("Category ", "Id", categoryId));

        category.setCategoryTile(categoryDTO.getCategoryTile());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCategory = categoryRepository.save(category);
        CategoryDTO catDTO = modelMapper.map(updatedCategory, CategoryDTO.class);

        return catDTO;
    }

    @Override
    public void deleteCategory(Integer categoryId) {

        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFondException("Category ", "Id", categoryId));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {

        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFondException("Category ", "Id", categoryId));
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        return categoryDTO;
    }

    @Override
    public List<CategoryDTO> categories() {

        List<CategoryDTO> listOfCategoriesDTOs = categoryRepository
                .findAll().stream().map(category -> modelMapper
                        .map(category, CategoryDTO.class)).collect(Collectors.toList());
        return listOfCategoriesDTOs;
    }
}
