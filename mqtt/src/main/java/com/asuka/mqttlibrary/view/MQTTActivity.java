package com.asuka.mqttlibrary.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.asuka.mqttlibrary.databinding.ActivityMqttactivityBinding;
import com.asuka.mqttlibrary.modle.event.Data;
import com.asuka.mqttlibrary.modle.event.MQTTEvent;
import com.asuka.mqttlibrary.service.MQTTService;
import com.asuka.mqttlibrary.view.adapter.MyAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MQTTActivity extends AppCompatActivity {
    private ActivityMqttactivityBinding binding;
    private MyAdapter myAdapter;
    private List<Data> dataList=new ArrayList<Data>();

    //EventBus订阅接收到MQTT消息的事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(MQTTEvent mqttEvent){
        dataList.add(JSON.parseObject(mqttEvent.getMqttMessage().getPayload(),Data.class));
        //通知数据已经刷新
        myAdapter.notifyDataSetChanged();
        //recycleView定位到最底部的位置
        binding.recyclerView.scrollToPosition(myAdapter.getItemCount()-1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMqttactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MQTTService.startService(this);
        EventBus.getDefault().register(this);


        myAdapter = new MyAdapter(dataList);
        binding.recyclerView.setAdapter(myAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //底部堆叠
//        linearLayoutManager.setStackFromEnd(true);
        //布局反转显示
//        linearLayoutManager.setReverseLayout(true);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

    }


    @Override
    protected void onResume() {
        super.onResume();
        binding.tv1.setText("服务器地址："+MQTTService.HOST);
        binding.tv2.setText("发布主题："+MQTTService.PUBLISH_TOPIC);
        binding.tv3.setText("订阅主题："+MQTTService.SUBSCRIBE_TOPIC);
        binding.mqttSendBtn.setOnClickListener(I->{
            Data data = new Data("" + Build.DEVICE, binding.mqttSendEdit.getText().toString());
            MQTTService.publish(JSON.toJSONString(data));
            dataList.add(data);
            myAdapter.notifyDataSetChanged();
            binding.mqttSendEdit.getText().clear();
        });



    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}