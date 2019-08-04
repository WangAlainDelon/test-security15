package com.wx.testsecurity15.service;


import com.wx.testsecurity15.entity.Blog;

import java.util.List;


public interface IBlogService {
    List<Blog> getBlogs();

    void deleteBlog(long id);
}
