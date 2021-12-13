package com.asuka.androidopensourcelibrarystudydemo.modle.pojo

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
@Entity
class Person(
    @Id
    var id:Long=0,
    var name: String,
    var age:Int,
    var address:String
    ){
    override fun toString(): String {
        return "Person(id=$id, name=$name, age=$age, address=$address)"
    }

}