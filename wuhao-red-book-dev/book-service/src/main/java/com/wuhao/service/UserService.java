package com.wuhao.service;

import com.wuhao.bo.UpdatedUserBO;
import com.wuhao.pojo.Users;

public interface UserService {
  /**
   * 判断用户是否存在，如果存在则返回用户信息
   */
  public Users queryMobileIsExist(String mobile);

  /**
   * 创建用户信息，并且返回用户对象
   */
  public Users createUser(String mobile);

  public Users getUser(String userId);

  public Users updateUserInfo(UpdatedUserBO updatedUserBO);
  public Users updateUserInfo(UpdatedUserBO updatedUserBO, Integer type);

}
