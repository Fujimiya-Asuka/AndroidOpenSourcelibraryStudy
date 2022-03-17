/**
  * Copyright 2021 bejson.com 
  */
package com.asuka.androidopensourcelibrarystudydemo;


import java.io.Serializable;
import java.util.List;

/**
 *  <pre>
 *  desc:   登录响应消息数据
 *  Author:  XuZhenHui
 *  Time:  2021/12/30 16:12
 *  </pre>
 */
public class LoginResDto implements Serializable {
    /**
     * 用户账户
     */
    private String account;
    /**
     * 用户管辖的停车场ID
     */
    private List<String> parkingLotIds;
    /**
     * pda编码
     */
    private String pdaCode;
    /**
     * 登录返回token
     */
    private String token;
    /**
     * 用户ID
     */
    private long userId;
    public void setAccount(String account) {
        this.account = account;
    }
        public String getAccount() {
            return account;
        }

        public void setParkingLotIds(List<String> parkingLotIds) {
            this.parkingLotIds = parkingLotIds;
        }
        public List<String> getParkingLotIds() {
            return parkingLotIds;
        }

        public void setPdaCode(String pdaCode) {
            this.pdaCode = pdaCode;
        }
        public String getPdaCode() {
            return pdaCode;
        }

        public void setToken(String token) {
            this.token = token;
        }
        public String getToken() {
            return token;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }
        public long getUserId() {
            return userId;
        }

}

