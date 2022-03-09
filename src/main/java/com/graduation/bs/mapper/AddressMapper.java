package com.graduation.bs.mapper;

import com.graduation.bs.dao.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MyBatisPlusAutoGenerator
 * @since 2022-03-09
 */
public interface AddressMapper extends BaseMapper<Address> {

    @Update("update address set is_default = '否' where user_id = #{userId} ${sub}")
    void setAddressIsDefault(String userId, String sub);

}
