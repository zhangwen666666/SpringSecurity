package com.zw.mapper;

import com.zw.entity.TUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser selectByLogiznAct(String username);
}