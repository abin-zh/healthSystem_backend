package com.cykj.util;

import com.alibaba.fastjson.JSON;
import com.cykj.mapper.UsersMapper;
import com.cykj.model.dto.ResponseDTO;
import com.cykj.model.pojo.RoleMap;
import com.cykj.model.pojo.User;
import com.cykj.model.vo.DelVO;
import com.cykj.service.RoleService;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author abin
 * @date 2024/8/8 15:29
 */
public class CommonUtils {

    private final static BigDecimal ZERO = new BigDecimal(0);

    /**
     * 登录基础值判空
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param scode    session中存放的验证码
     * @param isStaff  是否是管理员(工作人员)
     * @return
     */
    public static ResponseDTO loginCheck(String username, String password, String code, String scode,boolean isStaff) {
        //判空
        if (StrUtils.hasEmpty(username, password, code)) {
            return ResponseDTO.fail("登录失败，有信息为空");
        } else if (!isStaff && username.length() != 11 && username.length() != 18) {
            //未使用身份证或手机号(用户[体检人员])
            return ResponseDTO.fail("登录失败，账号错误");
        } else if(StrUtils.isEmpty(scode) || !scode.equalsIgnoreCase(code)){
            return ResponseDTO.fail("登录失败，验证码错误");
        }

        //检查通过
        return null;
    }

    /**
     * 密码检查
     * @param user 查询的用户(工作人员)
     * @param inputPwd 用户输入的密码
     * @param dbPwd 数据库中的密码
     * @return
     */
    public static ResponseDTO passwordCheck(Object user,String inputPwd, String dbPwd){
        //检查密码
        if(user == null){
            return ResponseDTO.fail("登录失败，用户不存在");
        } else if (!MD5Utils.encrypt(inputPwd).equals(dbPwd)){
            return ResponseDTO.fail("登录失败，密码错误");
        }

        return null;
    }


    /**
     * 用户账号查询
     *
     * @param username    用户名
     * @param usersMapper 用户接口映射
     * @return
     */
    public static User userLogin(String username, UsersMapper usersMapper) {
        //判断用户用手机号还是身份证号进行登录
        if (username.length() == 11) {
            return usersMapper.findOneByUserPhoneOrUserIdCard(username, null);
        } else if (username.length() == 18) {
            return usersMapper.findOneByUserPhoneOrUserIdCard(null, username);
        }

        //用户不存在
        return null;
    }

    /**
     * 解析token根据建名获取数据
     * @param infoName 键名
     * @param request 请求信息
     * @return
     */
    public static LinkedHashMap<String, Object> parseTokenInfo(String infoName, HttpServletRequest request){
        String token = request.getHeader("token");
        Claims claims = JWTUtils.parseToken(token);
        LinkedHashMap<String, Object> object = (LinkedHashMap<String, Object>) claims.get(infoName);
        return object;
    }

    /**
     * 检查上下限值范围值是否合法
     * @param lowerLimit 下限
     * @param upperLimit 上限
     * @return
     */
    public static ResponseDTO checkLimit(BigDecimal lowerLimit, BigDecimal upperLimit){
        if(lowerLimit.compareTo(ZERO) < 0 || upperLimit.compareTo(ZERO) < 0){
            return ResponseDTO.fail("上下限值不能是负数");
        } else if(lowerLimit.compareTo(upperLimit) > 0) {
            return ResponseDTO.fail("下限值不能大于上限值");
        }
        return null;
    }

    /**
     * 删除时基础检查
     * @param delVO 删除VO
     * @return
     */
    public static ResponseDTO checkDelVO(DelVO delVO){
        List<Integer> ids = delVO.getIds();
        if (ids == null || ids.isEmpty()) {
            return ResponseDTO.fail("删除失败，没找到删除的数据");
        }
        return null;
    }

    /**
     * 检查角色权限
     * @param roleName 角色名
     * @param roleMap 角色集合
     * @param roleService 角色业务
     * @param request 请求servlet
     * @param response 响应servlet
     * @return 是否通行
     * @throws Exception
     */
    public static boolean checkRole(String roleName, RoleMap roleMap, RoleService roleService, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if (!roleMap.getMap().containsKey(roleName)) {
            roleMap.setMap(roleService.getRoleMap());
        }

        LinkedHashMap<String, Object> staff = CommonUtils.parseTokenInfo("staff", request);
        Integer roleId = (Integer) staff.get("staffRoleId");
        Integer doctorRoleId = roleMap.getMap().get(roleName).getRoleId();


        //角色不匹配
        if (doctorRoleId != roleId) {
            response.getOutputStream().write(JSON.toJSONBytes(ResponseDTO.fail("您的角色无法使用此权限")));
            return false;
        }
        return true;
    }

}
