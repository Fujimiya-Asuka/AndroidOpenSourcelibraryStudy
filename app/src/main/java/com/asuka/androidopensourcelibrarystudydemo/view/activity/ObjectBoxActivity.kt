package com.asuka.androidopensourcelibrarystudydemo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asuka.androidopensourcelibrarystudydemo.databinding.ActivityObjectBoxBinding
import com.asuka.androidopensourcelibrarystudydemo.modle.db.MyBoxStore
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.Person
import com.asuka.androidopensourcelibrarystudydemo.modle.pojo.Person_
import io.objectbox.Box
import timber.log.Timber
import kotlin.math.absoluteValue

class ObjectBoxActivity : AppCompatActivity() {
    private lateinit var binding:ActivityObjectBoxBinding
    private lateinit var personBox:Box<Person>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityObjectBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)
        personBox=MyBoxStore.store.boxFor(Person::class.java)
        personBox.removeAll()


        binding.addBtn.setOnClickListener {
            Timber.d("添加操作")
            val l1 = personBox.put(Person(0, "小明", 19, "广州"))
            Timber.d(""+l1)
            val l2 = personBox.put(Person(0,"小红",18,"上海"))
            Timber.d(""+l2)
            val l3 = personBox.put(Person(0,"小强",20,"北京"))
            Timber.d(""+l3)
            upDataTextView()
        }

        binding.upDataBtn.setOnClickListener {
            Timber.d("更新操作")
            val person = personBox.query(Person_.name.equal("小明")).build().find()
            if (person.size>0){
                person[0].address="杭州"
                personBox.put(person)
                Timber.d("更新${person[0].name}的地址为杭州")
            }
            upDataTextView()
        }

        binding.deleteBtn.setOnClickListener {
            Timber.d("删除操作")
            val person = personBox.query(Person_.name.equal("小强")).build().find()
            if (person.size>0){
                personBox.remove(person[0].id)
                Timber.d("删除了${person[0].name}")
            }
            upDataTextView()
        }

        binding.searchBtn.setOnClickListener {
            Timber.d("查询操作")
            val query = personBox.query(Person_.name.equal("小明")).build().find()
            if (query.size>0){
                binding.textView.text = ""+query[0]
                for (person in query) {
                    Timber.d(person.toString())
                }
            }
        }

    }

    private fun upDataTextView(){
        val all = personBox.all
        var stringBuf=StringBuffer()
        for (person in all) {
            stringBuf.append(person.toString())
        }
        binding.textView.text=stringBuf.toString()
    }

}