package com.itheima;

import com.itheima.ui.GameJFrame;
import com.itheima.ui.LoginJFrame;

import java.util.ArrayList;

public class APP {
    static ArrayList<User> list = new ArrayList<>();

    public static void main(String[] args) {
        //表示程序的入口
        new LoginJFrame(list);

    }
}
