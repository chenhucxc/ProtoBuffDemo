package com.test.protobuffdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.protobuf.InvalidProtocolBufferException;
import com.test.protobuffdemo.model.PBBean;
import com.test.protobuffdemo.model.PBHobby;
import com.test.protobuffdemo.model.PBScore;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        // 序列化
        PBBean.Person.Builder builder = PBBean.Person.newBuilder();
        builder.setName("张三");
        builder.setAge(18);
        builder.setNumber("112");
        builder.setSex(PBBean.Sex.MALE);
        builder.addIntArray(1);
        builder.addIntArray(2);
        builder.addIntArray(3);

        PBScore.Score.Builder scoreBuilder = PBScore.Score.newBuilder();
        // 成绩和科目必须要设置值，否则会报错(Message was missing required fields.)导致崩溃
        scoreBuilder.setDegree(90.5);
        scoreBuilder.setSubject("语文");
        scoreBuilder.setIsPass(true);
        scoreBuilder.setTeacher("李四");

        PBBean.Person.Relative.Builder relativeBuilder = PBBean.Person.Relative.newBuilder();
        relativeBuilder.setName("老爸");
        relativeBuilder.setRelationship("父子");

        builder.setScore(scoreBuilder);
        builder.setRelative(relativeBuilder);

        builder.addHobbies(setHobby(0, "足球"));
        builder.addHobbies(setHobby(1, "游戏"));
        builder.addHobbies(setHobby(2, "跑步"));
        builder.addHobbies(setHobby(3, "看书"));

        PBBean.Person person = builder.build();
        //System.out.print(person.toString());
        Log.e(TAG, "initData: 序列化: " + person.toString());

        // 反序列化
        byte[] dataArray = person.toByteArray();
        try {
            PBBean.Person response = PBBean.Person.parseFrom(dataArray);
            //System.out.print(response.toString());
            Log.e(TAG, "initData: 反序列化: " + response.toString());
            String logInfo = String.format("name = %s, hobbies = %s, relative name = %s, subject teacher = %s",
                    response.getName(), response.getHobbies(1).toString(),
                    response.getRelative().getName(), response.getScore().getTeacher());
            Log.e(TAG, "initData: " + logInfo);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置爱好
     *
     * @param id   hobby id
     * @param desc hobby description
     * @return hobby builder
     */
    private PBHobby.Hobby.Builder setHobby(int id, String desc) {
        PBHobby.Hobby.Builder builder = PBHobby.Hobby.newBuilder();
        builder.setId(id);
        builder.setDesc(desc);
        return builder;
    }
}