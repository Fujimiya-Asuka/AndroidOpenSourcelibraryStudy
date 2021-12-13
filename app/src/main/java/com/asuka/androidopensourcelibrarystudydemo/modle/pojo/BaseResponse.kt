/**
 * Copyright 2021 bejson.com
 */
package com.asuka.androidopensourcelibrarystudydemo.modle.pojo

/**
 * Auto-generated: 2021-11-23 23:26:59
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
class BaseResponse {
    var data: Data? = null
    var errorCode = 0
    var errorMsg: String? = null
    override fun toString(): String {
        return "BaseResponse{" +
                "data=" + data +
                ", errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}'
    }
}