package com.blog.app.services;

import com.blog.app.entities.Post;
import com.blog.app.payloads.PostDTO;
import com.blog.app.payloads.PostResponse;

import java.util.List;

public interface PostService {

    //create post
    PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

    //update post
    PostDTO updatePost(PostDTO postDTO, Integer postId);

    //delete post
    void deletePost(Integer postId);

    //get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);

    //get single post
    PostDTO getPostById(Integer postId);

    //get all post by category
    List<PostDTO> getPostByCategory(Integer categoryId);

    //get all post by user
    List<PostDTO> getPostByUser(Integer userId);

    //search post
    List<PostDTO> searchPosts(String keyword);
}
