package com.asuka.androidopensourcelibrarystudydemo;

import com.asuka.androidopensourcelibrarystudydemo.modle.proto.MyProto;
import com.google.protobuf.InvalidProtocolBufferException;

import org.junit.Test;

import java.util.UUID;

public class Test2 {

    @Test
    public void Test2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(UUID.randomUUID());
        }

    }

}
