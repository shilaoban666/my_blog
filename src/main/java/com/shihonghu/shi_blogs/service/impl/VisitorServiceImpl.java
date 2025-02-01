package com.shihonghu.shi_blogs.service.impl;

import com.shihonghu.shi_blogs.entity.Visitor;
import com.shihonghu.shi_blogs.mapper.VisitorMapper;
import com.shihonghu.shi_blogs.service.VisitorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shilaoban
 * @since 2022-04-13
 */
@Service
public class VisitorServiceImpl extends ServiceImpl<VisitorMapper, Visitor> implements VisitorService {

    @Autowired
    VisitorMapper visitorMapper;

    /**
     * 通过uuid查询是否存在是该uuid的访客
     *
     * @param uuid
     * @return
     */
    @Override
    public boolean hasUUID(String uuid) {
        return visitorMapper.hasUUID(uuid) == 0 ? false : true;
    }

    /**
     * 通过uuid查询访客
     *
     * @param uuid
     * @return
     */
    @Override
    public Visitor getVisitorByUuid(String uuid){
        return visitorMapper.selectByUuid(uuid);
    }

    /**
     * 获取Pv
     *
     * @return pv
     */
    @Override
    public int getPv(){
        return  visitorMapper.getPv();
    }


}
