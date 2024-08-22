package com.cykj.mapper;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cykj.model.pojo.User;

/**
 * @author abin
 * @date 2024/8/8 10:47
 */
public interface UsersMapper {

    User findOneByUserPhoneOrUserIdCard(@Param("userPhone")String userPhone,@Param("userIdCard")String userIdCard);

    int addOneUser(User user);

    List<User> findByAllWithPage(User user);

    int updateByUserId(@Param("updated")User updated,@Param("userId")Integer userId);

    int deleteUser(int userId);

    User findOneByUserId(@Param("userId")Integer userId);

    int updateUserBalance(@Param("updatedUserBalance")BigDecimal updatedUserBalance);


}