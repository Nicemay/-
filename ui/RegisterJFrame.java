package com.itheima.ui;

import com.itheima.User;
import com.itheima.tool.JDialogTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/*注册界面
* */
public class RegisterJFrame extends JFrame implements MouseListener {
    ArrayList<User> list = new ArrayList<>();
    JDialogTool jDialog = new JDialogTool();

    JLabel resetJLabel = new JLabel();
    JLabel registerJLabel = new JLabel();
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField password2Field = new JPasswordField();
    JLabel eye;

    String username="",password="",password2="";

    //图片路径  1：原图， 2：变暗的图
    int resetFlag = 1,registerFlag = 1;
    //图片路径  1.关闭眼睛   2.打开眼睛
    int eyeFlag = 1;
    //眼睛计数：实现点一下开，再点一下关
    int eyeCount=0;

    //构造方法
    public RegisterJFrame(ArrayList<User> list){
        //初始化界面
        initJFrame();

        //初始化图片、文字
        initImage();

        //接收list
        this.list = list;

        //让界面显示出来
        this.setVisible(true);
    }

    /*===初始化图片、文字===*/
    private void initImage() {
        //清空已出现的图片、文字
        this.getContentPane().removeAll();

        //创建"注册用户名"
        JLabel usernameJLabel = new JLabel("注册用户名");
        usernameJLabel.setBounds(100,100,100,100);
        usernameJLabel.setForeground(new Color(184,134,11));
        usernameJLabel.setFont(new Font("宋体", Font.BOLD, 18));
        //创建一个明文显示的输入框
        usernameField.setBounds(210,135,180,30);

        //创建"注册密码"
        JLabel passwordJLabel = new JLabel("注册密码");
        passwordJLabel.setBounds(110,160,100,100);
        passwordJLabel.setForeground(new Color(184,134,11));
        passwordJLabel.setFont(new Font("宋体", Font.BOLD, 18));
        //创建一个密文显示的输入框
        passwordField.setBounds(210,195,180,30);
        if(eyeFlag==1){
            passwordField.setEchoChar('*');//关闭眼睛
        }else{
            passwordField.setEchoChar((char)0);//打开眼睛
        }

        //创建"密码眼睛"
        eye = new JLabel(new ImageIcon("puzzlegame\\images\\other\\eye"+eyeFlag+".png"));
        eye.setBounds(370,160,100,100);
        eye.setForeground(Color.black);
        eye.setFont(new Font("宋体", Font.BOLD, 20));
        //添加到界面
        this.getContentPane().add(eye);
        //给密码眼睛添加鼠标事件
        eye.addMouseListener(this);

        //创建"再次输入密码"
        JLabel password2JLabel = new JLabel("再次输入密码");
        password2JLabel.setBounds(80,220,200,100);
        password2JLabel.setForeground(new Color(184,134,11));
        password2JLabel.setFont(new Font("宋体", Font.BOLD, 18));
        //创建一个密文显示的输入框
        password2Field.setBounds(210,255,180,30);

        //只切换注册按钮图片，不创建新对象
        registerJLabel.setIcon(new ImageIcon("puzzlegame\\images\\other\\register"+registerFlag+".png"));
        //指定图片位置
        registerJLabel.setBounds(110,310,128,47);
        //把注册按钮图片添加到界面中
        this.getContentPane().add(registerJLabel);

        //只切换重置按钮图片，不创建新对象
        resetJLabel.setIcon(new ImageIcon("puzzlegame\\images\\other\\reset"+resetFlag+".png"));
        //指定图片位置
        resetJLabel.setBounds(270,310,128,47);
        //把重置按钮图片添加到界面中
        this.getContentPane().add(resetJLabel);

        //加载背景图片
        JLabel backgroundJLabel = new JLabel(new ImageIcon("puzzlegame\\images\\other\\background2.png"));
        //指定图片位置
        backgroundJLabel.setBounds(0,0,488,430);

        //把图片、文字添加到界面中
        this.getContentPane().add(usernameJLabel);
        this.getContentPane().add(usernameField);
        this.getContentPane().add(passwordJLabel);
        this.getContentPane().add(passwordField);
        this.getContentPane().add(password2JLabel);
        this.getContentPane().add(password2Field);
        this.getContentPane().add(backgroundJLabel);

        //刷新界面
        this.getContentPane().repaint();
    }

    /*===初始化界面===*/
    private void initJFrame() {
        //设置界面宽高
        this.setSize(500,464);
        //设置界面标题
        this.setTitle("拼图单机版 注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //给登录按钮图片添加鼠标事件
        resetJLabel.addMouseListener(this);
        //给注册按钮图片添加鼠标事件
        registerJLabel.addMouseListener(this);

    }

    /*===鼠标监听事件===*/
    //鼠标按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        //获取当前被点击的按钮图片对象
        Object obj = e.getSource();
        if(obj==resetJLabel){
            resetFlag=2;
            registerFlag=1;
            initImage();
        }else if(obj==registerJLabel){
            resetFlag=1;
            registerFlag=2;
            initImage();
        }
    }

    //鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        //获取当前被点击的按钮图片对象
        Object obj = e.getSource();
        if(obj==resetJLabel){
            /*执行重置功能*/
            cleanAll();
            //重置按钮变回原图
            resetFlag=1;
            registerFlag=1;
            initImage();
        }else if(obj==registerJLabel){
            /*执行注册功能*/
            if(register(list)){
                //清空输入框内容
                cleanAll();
                //注册成功提示
                jDialog.showJDialog("注册成功");

//                //查看list
//                for (int i = 0; i < list.size(); i++) {
//                    User user = list.get(i);
//                    String name = user.getUsername();
//                    String password = user.getPassword();
//                    System.out.println(name+"  "+password);
//                }

                //返回登录界面
                new LoginJFrame(list);
                this.dispose();

            }else{
                //重置按钮变回原图
                resetFlag=1;
                registerFlag=1;
                initImage();
            }



        }
    }

    //鼠标划入
    @Override
    public void mouseEntered(MouseEvent e) {}

    //鼠标划出
    @Override
    public void mouseExited(MouseEvent e) {}

    //单击
    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if(obj==eye){
            if(eyeCount%2==0){
                eyeFlag=2;//eyeCount偶数：打开眼睛
                initImage();
            }else{
                eyeFlag=1;//eyeCount奇数：关闭眼睛
                initImage();
            }
            eyeCount++;
        }
    }

//==============================================================

    //执行注册逻辑
    //获取输入框的内容
    //1.用户名重复，则重新输入，直到用户唯一
    //2.两次密码输入不一致要提示
    public boolean register(ArrayList<User> list){
        //创建
        User user = new User();
        //获取输入框的内容
        username = usernameField.getText();
        password = passwordField.getText();
        password2 = password2Field.getText();

        if(username.equals("")){
            jDialog.showJDialog("用户名不能为空");
            return false;
        }else{
            if(isOnlyUsername(list)){
                user.setUsername(username);
            }else{
                jDialog.showJDialog("用户名重复");
                return false;
            }
        }

        if(password.equals("")){
            jDialog.showJDialog("密码不能为空");
            return false;
        }else{
            if(password.equals(password2)){
                user.setPassword(password);
            }else{
                jDialog.showJDialog("密码两次输入不一致");
                return false;
            }
        }
        list.add(user);
        return true;
    }

    //判断用户名是否唯一
    public boolean isOnlyUsername(ArrayList<User> list){
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String name = user.getUsername();
            if(name.equals(username)){
                return false;//不合法
            }
        }
        return true;
    }

    //清空输入框的内容
    public void cleanAll(){
        usernameField.setText("");
        passwordField.setText("");
        password2Field.setText("");
    }


}
