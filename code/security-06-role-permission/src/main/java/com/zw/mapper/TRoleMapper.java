package com.zw.mapper;

import com.zw.entity.TRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

    List<TRole> selectByUserId(Integer userId);
}