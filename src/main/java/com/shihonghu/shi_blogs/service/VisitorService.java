package com.shihonghu.shi_blogs.service;

import com.shihonghu.shi_blogs.entity.Visitor;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
public interface VisitorService extends IService<Visitor> {

    /**
     * 通过uuid查询是否存在是该uuid的访客
     *
     * @param uuid
     * @return
     */
    boolean hasUUID(String uuid);

    /**
     * 通过uuid查询访客
     *
     * @param uuid
     * @return
     */
    Visitor getVisitorByUuid(String uuid);
    /**
     * 获取Pv
     *
     * @return pv
     */
    int getPv();
}
