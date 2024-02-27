package com.blog.app.controllers;

import com.blog.app.payloads.APIResponse;
import com.blog.app.payloads.CategoryDTO;
import com.blog.app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Create
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO category = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @PathVariable("id") Integer catId,
                                                      @RequestBody CategoryDTO categoryDTO){

        CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO, catId);
        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<APIResponse>(new APIResponse("Category Deleted", true),
                HttpStatus.OK);
    }

    //Get
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") Integer id){
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    }

    //Get All
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> allCategories = categoryService.categories();
        return ResponseEntity.ok(allCategories);
    }
}
