package com.blog.app.services.impl;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFondException;
import com.blog.app.payloads.PostDTO;
import com.blog.app.payloads.PostResponse;
import com.blog.app.repositories.CategoryRepository;
import com.blog.app.repositories.PostRepository;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFondException("User", "Id", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFondException("Category", "id", categoryId));

        Post post = modelMapper.map(postDTO, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post newPost = postRepository.save(post);

        PostDTO newPostDTO = modelMapper.map(newPost, PostDTO.class);

        return newPostDTO;
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFondException("Post", "Id", postId));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImageName(postDTO.getImageName());

        Post updatedPost = postRepository.save(post);
        PostDTO updatedPostDTO = modelMapper.map(updatedPost, PostDTO.class);

        return updatedPostDTO;
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFondException("Post", "Id", postId));
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = (sortDirection.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending());

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> allPosts = pagePost.getContent();

        List<PostDTO> postDTOs = allPosts.stream()
                .map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();

        postResponse.setContent(postDTOs);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFondException("Post", "id", postId));

        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
        return postDTO;
    }

    @Override
    public List<PostDTO> getPostByCategory(Integer categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFondException("Category", "Id", categoryId));

        List<Post> posts = postRepository.findByCategory(category);
        List<PostDTO> postDTOs = posts.stream().map((post -> modelMapper.map(post, PostDTO.class))).collect(Collectors.toList());

//        List<PostDTO> postDTOs = postRepository.findByCategory(category)
//                .stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        return postDTOs;
    }

    @Override
    public List<PostDTO> getPostByUser(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFondException("User", "Id", userId));

        List<PostDTO> posts = postRepository.findByUser(user)
                .stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

        return posts;
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {

        List<PostDTO> postDTOs = postRepository.findByTitleContaining(keyword)
                .stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        return postDTOs;
    }
}
