package com.itheima.tool;

import javax.swing.*;

/*弹窗工具类
* */
public class JDialogTool {

    public void showJDialog(String content){
        //创建一个弹窗对象
        JDialog jDialog = new JDialog();
        //设置大小
        jDialog.setSize(200,150);
        //设置标题
        jDialog.setTitle("Warning");
        //置顶
        jDialog.setAlwaysOnTop(true);
        //居中
        jDialog.setLocationRelativeTo(null);
        //弹窗不关闭永远无法操作下面界面
        jDialog.setModal(true);
        //取消默认
        jDialog.setLayout(null);

        //管理文字
        JLabel warning = new JLabel(content);
        warning.setBounds(10,-5,200,100);
        jDialog.getContentPane().add(warning);

        //显示
        jDialog.setVisible(true);
    }


}
