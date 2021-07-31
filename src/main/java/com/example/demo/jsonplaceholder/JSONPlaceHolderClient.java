package com.example.demo.jsonplaceholder;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        value = "jsonplaceholder",
        url = "https://jsonplaceholder.typicode.com/" // API de donde se pillan los datos
)
public interface JSONPlaceHolderClient {

    @GetMapping(value = "posts")
    List<Post> getPosts();

    @GetMapping(value = "posts/{postId}")
    Post getPost(@PathVariable("postId") Integer postId);
}
