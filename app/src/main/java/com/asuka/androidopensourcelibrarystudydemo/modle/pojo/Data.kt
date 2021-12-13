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
class Data {
    var admin = false
    var chapterTops: List<String>? = null
    var coinCount = 0
    var collectIds: List<String>? = null
    var email: String? = null
    var icon: String? = null
    var id: Long = 0
    var nickname: String? = null
    var password: String? = null
    var publicName: String? = null
    var token: String? = null
    var type = 0
    var username: String? = null
    override fun toString(): String {
        return "Data{" +
                "admin=" + admin +
                ", chapterTops=" + chapterTops +
                ", coinCount=" + coinCount +
                ", collectIds=" + collectIds +
                ", email='" + email + '\'' +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", publicName='" + publicName + '\'' +
                ", token='" + token + '\'' +
                ", type=" + type +
                ", username='" + username + '\'' +
                '}'
    }
}