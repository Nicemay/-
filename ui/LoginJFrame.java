package com.itheima.ui;

import com.itheima.tool.CaptchaTool;
import com.itheima.User;
import com.itheima.tool.JDialogTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


/*登录界面
* */
public class LoginJFrame extends JFrame implements MouseListener {
    ArrayList<User>list = new ArrayList<>();
    JDialogTool jDialog = new JDialogTool();

    JLabel loginJLabel = new JLabel();
    JLabel registerJLabel = new JLabel();
    JLabel captchaText;
    JLabel eye;

    CaptchaTool captchaTool = new CaptchaTool();
    String captchaChar = captchaTool.produceCaptcha();

    //图片路径  1：按钮原图， 2：按钮变暗的图
    int loginFlag = 1,registerFlag = 1;
    //图片路径  1.关闭眼睛   2.打开眼睛
    int eyeFlag = 1;
    //眼睛计数：实现点一下开，再点一下关
    int eyeCount=0;

    //全局变量
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JTextField captchaField = new JTextField();
    String username,password,captcha;


    //构造方法
    public LoginJFrame(ArrayList<User> list){
        //初始化界面
        initJFrame();

        //初始化图片、文字
        initImage();

        //创建
        this.list=list;

        //让界面显示出来
        this.setVisible(true);
    }

    /*===初始化图片、文字===*/
    private void initImage() {
        //清空已出现的图片、文字
        this.getContentPane().removeAll();

        //创建"用户名"
        JLabel usernameJLabel = new JLabel("用户名");
        usernameJLabel.setBounds(120,100,100,100);
        usernameJLabel.setForeground(new Color(184,134,11));
        usernameJLabel.setFont(new Font("宋体", Font.BOLD, 18));
        //创建一个明文显示的输入框
        usernameField.setBounds(190,135,200,30);
        //添加
        this.getContentPane().add(usernameJLabel);
        this.getContentPane().add(usernameField);

        //创建"密码"
        JLabel passwordJLabel = new JLabel("密码");
        passwordJLabel.setBounds(130,160,100,100);
        passwordJLabel.setForeground(new Color(184,134,11));
        passwordJLabel.setFont(new Font("宋体", Font.BOLD, 18));
        //创建一个密文显示的输入框
        passwordField.setBounds(190,195,200,30);
        if(eyeFlag==1){
            passwordField.setEchoChar('*');//关闭眼睛
        }else{
            passwordField.setEchoChar((char)0);//打开眼睛
        }

        //添加到界面
        this.getContentPane().add(passwordJLabel);
        this.getContentPane().add(passwordField);

        //创建"密码眼睛"
        eye = new JLabel(new ImageIcon("puzzlegame\\images\\other\\eye"+eyeFlag+".png"));
        eye.setBounds(370,160,100,100);
        eye.setForeground(Color.black);
        eye.setFont(new Font("宋体", Font.BOLD, 20));
        //添加到界面
        this.getContentPane().add(eye);
        //给密码眼睛添加鼠标事件
        eye.addMouseListener(this);


        //创建"验证码"
        JLabel captchaJlabel = new JLabel("验证码");
        captchaJlabel.setBounds(120,220,100,100);
        captchaJlabel.setForeground(new Color(184,134,11));
        captchaJlabel.setFont(new Font("宋体", Font.BOLD, 18));
        //创建一个明文显示的输入框
        captchaField.setBounds(190,255,100,30);
        //添加到界面
        this.getContentPane().add(captchaJlabel);
        this.getContentPane().add(captchaField);

        //创建"验证码"文本
        captchaText = new JLabel(captchaChar);
        captchaText.setBounds(300,220,100,100);
        captchaText.setForeground(Color.black);
        captchaText.setFont(new Font("宋体", Font.BOLD, 20));
        //添加到界面
        this.getContentPane().add(captchaText);
        //给验证码文本添加鼠标事件
        captchaText.addMouseListener(this);

        //只切换登录按钮图片，不创建新对象
        loginJLabel.setIcon(new ImageIcon("puzzlegame\\images\\other\\login"+loginFlag+".png"));
        //指定图片位置
        loginJLabel.setBounds(110,310,128,47);
        //把登录按钮图片添加到界面中
        this.getContentPane().add(loginJLabel);

        //只切换注册按钮图片，不创建新对象
        registerJLabel.setIcon(new ImageIcon("puzzlegame\\images\\other\\register"+registerFlag+".png"));
        //指定图片位置
        registerJLabel.setBounds(270,310,128,47);
        //把注册按钮图片添加到界面中
        this.getContentPane().add(registerJLabel);

        //加载背景图片
        JLabel backgroundJLabel = new JLabel(new ImageIcon("puzzlegame\\images\\other\\background2.png"));
        //指定图片位置
        backgroundJLabel.setBounds(0,0,488,430);
        //把背景图片添加到界面中
        this.getContentPane().add(backgroundJLabel);

        //刷新界面
        this.getContentPane().repaint();
    }


    /*===初始化界面===*/
    private void initJFrame() {
        //设置界面宽高
        this.setSize(500,464);
        //设置界面标题
        this.setTitle("拼图单机版 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认图片的居中放置
        this.setLayout(null);

        //给登录按钮图片添加鼠标事件
        loginJLabel.addMouseListener(this);
        //给注册按钮图片添加鼠标事件
        registerJLabel.addMouseListener(this);



    }

    /*===鼠标监听事件===*/
    //鼠标按下不松
    @Override
    public void mousePressed(MouseEvent e) {
        //获取当前被点击的按钮图片对象
        Object obj = e.getSource();
        if(obj==loginJLabel){
            loginFlag=2;
            registerFlag=1;
            initImage();
        }else if(obj==registerJLabel){
            loginFlag=1;
            registerFlag=2;
            initImage();
        }
    }

    //鼠标松开
    @Override
    public void mouseReleased(MouseEvent e) {
        //获取当前被点击的按钮图片对象
        Object obj = e.getSource();
        if(obj==loginJLabel){
            if(login()){
                //来到游戏界面
                new GameJFrame(list);
                this.dispose();
            }else{
                loginFlag=1;
                registerFlag=1;
                initImage();
            }

        }else if(obj==registerJLabel){
            //来到注册界面
            new RegisterJFrame(list);
            this.dispose();
        }
    }

    //单击
    @Override
    public void mouseClicked(MouseEvent e) {
        //刷新验证码
        Object obj = e.getSource();
        if(obj==captchaText){
            refreshCaptcha();
        }else if(obj==eye){
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

    //鼠标划入
    @Override
    public void mouseEntered(MouseEvent e) {}

    //鼠标划出
    @Override
    public void mouseExited(MouseEvent e) {}

//======================================================================

    //登录逻辑
    public boolean login(){
        //获取输入框文本
        username = usernameField.getText();
        password = passwordField.getText();
        captcha = captchaField.getText();

        //比较验证码
        if(captcha.equalsIgnoreCase(captchaChar)){
            for (int i = 0; i < list.size(); i++) {
                User user = list.get(i);
                String name = user.getUsername();
                String passwordList = user.getPassword();
                if(username.equals(name)){
                    if(passwordList.equals(password)){
                        return true;
                    }else{
                        jDialog.showJDialog("密码输入错误");
                        refreshCaptcha();
                        return false;
                    }
                }
            }
            jDialog.showJDialog("没有找到该用户，请先注册");
            refreshCaptcha();
            return false;
        }else{
            jDialog.showJDialog("验证码错误");
            refreshCaptcha();
            return false;
        }
    }


    //刷新验证码
    public void refreshCaptcha(){
        captchaChar = captchaTool.produceCaptcha();
        initImage();
    }
}
