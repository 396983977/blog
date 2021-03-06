package com.zhongmc.blog.dao;

import com.zhongmc.blog.domain.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by ZMC on 2017/1/16.
 */

@Mapper
public interface BlogMapper {

    @Select("select * from tbl_blog order by tbl_blog.createtime DESC")
    List<Blog> findAllBlog();

    @Select("select * from tbl_blog where id = #{id}")
    Blog findOneById(Integer id);

    //按博客类别分页查询
    @Select("SELECT b.id,b.title,b.content,b.keywords,b.viewnum,b.coverpic,b.createtime,b.createby,b.updatetime,b.updateby from " +
            " tbl_blog as b,tbl_blog_tag where b.id=tbl_blog_tag.blogid and tbl_blog_tag.tagid = #{tagid} limit #{startIndex},#{pageSize}")
    List<Blog> findBlogsByTagId(Map<String,Integer> map);

    //按年月筛选博客
    //多个参数是一定要写@param，要不然无法绑定
    @Select("SELECT * FROM tbl_blog WHERE createtime >= #{start} and createtime < #{end}")
    List<Blog> findBlogByYM(@Param("start") String start, @Param("end") String end);
    //分页查询记录
    @Select("select * from tbl_blog order by tbl_blog.createtime DESC limit #{startIndex},#{pageSize}")
    List<Blog> findBlogsByPage(Map<String,Integer> map);

    @Select("select count(*) from tbl_blog")
    int Count();

    @Select("SELECT count(*) from " +
            " tbl_blog as b,tbl_blog_tag where b.id=tbl_blog_tag.blogid and tbl_blog_tag.tagid = #{tagid}")
    int CountTagBlogs(Integer tagid);
}
