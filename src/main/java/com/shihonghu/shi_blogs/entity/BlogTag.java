package com.shihonghu.shi_blogs.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1L;
//    @TableId("blog_id")
    private Long blogId;
    @TableId("tag_id")
    private Long tagId;


}
