package com.app.dao;

import com.app.entity.TPermit;
import org.springframework.stereotype.Repository;

@Repository
public interface ITPermitDao {
    int deleteByPrimaryKey(Integer pId);

    int insert(TPermit record);

    int insertSelective(TPermit record);

    TPermit selectByPrimaryKey(Integer pId);

    int updateByPrimaryKeySelective(TPermit record);

    int updateByPrimaryKey(TPermit record);
}