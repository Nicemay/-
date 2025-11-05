package com.itheima.ui;

import com.itheima.User;
import com.itheima.tool.RecoverTool;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;



/*游戏主界面
* */
public class GameJFrame extends JFrame implements KeyListener,ActionListener {

    //创建二位数组
    //用来管理数据，代表图片
    int[][] data = new int[4][4];

    //记录空白图片的位置
    int x=0,y=0;

    //记录步数
    int counter=0;

    //创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem anime = new JMenuItem("动漫");
    JMenuItem accountItem = new JMenuItem("打赏作者");

    //创建一个
    ArrayList<User> list = new ArrayList<>();
    RecoverTool recoverTool = new RecoverTool();

    //路径
    public static final int ANIME_NUM = 4;
    public static final int ANIMAL_NUM = 2;
    Random r = new Random();
    int index = r.nextInt(ANIME_NUM)+1;
    String type = "anime\\p"+index;



    //构造方法:初始化
    public GameJFrame(ArrayList<User> list){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱图片）
        initDate();

        //初始化图片、文字
        initImage();

        //
        this.list=list;

        //让界面显示出来
        this.setVisible(true);
    }


    /*===初始化数据（打乱图片）===*/
    private void initDate() {

        int[] temp = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random random = new Random();
        int index;

        do{
            for (int i = 0; i < temp.length; i++) {
                index = random.nextInt(temp.length);
                int p = temp[i];
                temp[i] = temp[index];
                temp[index] = p;
            }
            //把打乱的一维数组数据 赋给 二维数组
            for (int i = 0; i < temp.length; i++) {
                data[i/4][i%4] = temp[i];
                //15就是空白图片
                if(data[i/4][i%4]==15){
                    x=i/4;
                    y=i%4;
                }
            }

            for (int i = 0; i < temp.length; i++) {
                System.out.print(temp[i]+" ");
            }
            System.out.println();
        }while(!recoverTool.isRecover(temp,x));//进行判断一下，如果符合就可以，不符合就重新随机



        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(data[i][j]+" ");
            }
            System.out.println();
        }


    }

    /*===初始化图片、文字===*/
    private void initImage() {
        //清空已出现的图片、文字
        this.getContentPane().removeAll();

        //如果胜利，显示胜利图标
        if(victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("puzzlegame\\images\\other\\victory.png"));
            winJLabel.setBounds(203,283,180,78);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数："+counter);
        stepCount.setBounds(20,0,60,50);
        this.getContentPane().add(stepCount);

        for(int i=0;i<16;i++){
            /*细节：后加载的图片放后面*/

            //获取图片序号
            int num = data[i/4][i%4];
            //创建一个图片ImageIcon的对象
            ImageIcon icon = new ImageIcon("puzzlegame\\images\\"+type+"\\"+num+".jpg");
            //创建一个JLabel的对象（管理容器）
            JLabel jLabel = new JLabel(icon);
            //指定图片位置
            jLabel.setBounds(105*(i%4)+83,105*(i/4)+134,105,105);
            //给图片加边框
            //LOWERED:凹入
            //RAISED:凸起
            jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
            //把管理容器添加到界面中
            this.getContentPane().add(jLabel);

            //加载背景图片
            JLabel backgroundJLabel = new JLabel(new ImageIcon("puzzlegame\\images\\other\\background.png"));
            //指定图片位置
            backgroundJLabel.setBounds(0,17,570,590);
            //把背景图片添加到界面中
            this.getContentPane().add(backgroundJLabel);

            //刷新界面
            this.getContentPane().repaint();


        }


    }


    /*===初始化界面===*/
    private void initJFrame() {
        //设置界面宽高
        this.setSize(603,680);
        //设置界面标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认图片的居中放置
        this.setLayout(null);
        //给界面添加键盘监听事件
        this.addKeyListener(this);
    }


    /*===初始化菜单===*/
    private void initJMenuBar() {
        //创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上面的两个选项对象
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        JMenu replaceJMenu = new JMenu("更换图片");

        //将条目添加到选项中
        functionJMenu.add(replaceJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);


        replaceJMenu.add(animal);
        replaceJMenu.add(anime);

        aboutJMenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        animal.addActionListener(this);
        anime.addActionListener(this);

        //将选项添加到菜单中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }


    /*===键盘监听事件===*/
    //按下不松开时调用
    @Override
    public void keyPressed(KeyEvent e) {
        //游戏胜利，下面代码不运行
        if(victory()){
            return;
        }

        int code = e.getKeyCode();
        //A:65
        if(code==65){
            //清空所有图片
            this.getContentPane().removeAll();
            //加载完整图片
            JLabel all = new JLabel(new ImageIcon("puzzlegame\\images\\"+type+"\\all.jpg"));
            //指定图片位置
            all.setBounds(83,134,420,420);
            //把管理容器添加到界面中
            this.getContentPane().add(all);

            //加载背景图片
            JLabel backgroundJLabel = new JLabel(new ImageIcon("puzzlegame\\images\\other\\background.png"));
            //指定图片位置
            backgroundJLabel.setBounds(0,17,570,590);
            //把背景图片添加到界面中
            this.getContentPane().add(backgroundJLabel);


            //刷新界面
            this.getContentPane().repaint();


        }
    }

    //按下松开时调用
    @Override
    public void keyReleased(KeyEvent e) {
        //游戏胜利，下面代码不运行
        if(victory()){
            return;
        }

        //对键盘进行判断
        //左：37  上：38  右：39  下：40
        int code = e.getKeyCode();
        //左
        if(code==37 && y!=3){
            //空白图片位置：x y
            //空白图片右边的图片的位置：x y+1
            int tp = data[x][y];
            data[x][y]=data[x][y+1];
            data[x][y+1] = tp;
            y++;
            //步数加一
            counter++;
            //加载图片
            initImage();
        }
        //上
        else if(code==38 && x!=3){
            //空白图片位置：x y
            //空白图片下面的图片的位置：x+1 y
            int tp = data[x][y];
            data[x][y]=data[x+1][y];
            data[x+1][y] = tp;
            x++;
            //步数加一
            counter++;
            //加载图片
            initImage();
        }
        //右
        else if(code==39 && y!=0){
            //空白图片位置：x y
            //空白图片左边的图片的位置：x y-1
            int tp = data[x][y];
            data[x][y]=data[x][y-1];
            data[x][y-1] = tp;
            y--;
            //步数加一
            counter++;
            //加载图片
            initImage();
        }
        //下
        else if(code==40  && x!=0){
            //空白图片位置：x y
            //空白图片上面的图片的位置：x-1 y
            int tp = data[x][y];
            data[x][y]=data[x-1][y];
            data[x-1][y] = tp;
            x--;
            //步数加一
            counter++;
            //加载图片
            initImage();
        }
        //A:65 查看完整图片
        else if(code==65){
            initImage();
        }
        //W:87 直接胜利
        else if(code==87){
            for(int i=0;i<16;i++){
                data[i/4][i%4] = i;
            }
            initImage();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /*===动作监听事件:监听条目对象===*/
    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        if(obj == replayItem){
            System.out.println("重新游戏");
            //再次打乱二维数组数据
            initDate();
            //步数清零
            counter=0;
            //重新加载图片
            initImage();
        }else if(obj == reLoginItem){
            System.out.println("重新登陆");
            //关闭游戏界面
            this.setVisible(false);
            //打开登录界面
            new LoginJFrame(list);
        }else if(obj == closeItem){
            System.out.println("关闭程序");
            //直接关闭游戏
            System.exit(0);
        }else if(obj == accountItem){
            System.out.println("打赏作者");
            //弹窗对象
            JDialog jDialog = new JDialog();

            //添加文字
            JLabel font = new JLabel("v我五十");
            font.setBounds(20,-5,60,50);
            jDialog.getContentPane().add(font);

            //管理图片的容器
            JLabel jLabel = new JLabel(new ImageIcon("puzzlegame\\images\\other\\about.jpg"));
            jLabel.setBounds(35,35,258,246);
            //把图片添加到弹窗中
            jDialog.getContentPane().add(jLabel);
            //设置弹窗宽高
            jDialog.setSize(344,344);
            //设置弹窗标题
            jDialog.setTitle("助力主播  打赏作者");
            //设置弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //设置弹窗居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭，则无法操作下面界面
            jDialog.setModal(true);
            //取消默认图片的居中放置
            jDialog.setLayout(null);
            //弹窗显示
            jDialog.setVisible(true);
        }else if(obj == animal){
            System.out.println("从animal中选择一张图片");
            //随机选择编号
            index=r.nextInt(ANIMAL_NUM)+1;
            //选择类型
            type="animal\\p"+index;
            //再次打乱二维数组数据
            initDate();
            //步数清零
            counter=0;
            //重新加载图片
            initImage();

        }else if(obj ==anime){
            System.out.println("从anime中选择一张图片");
            //随机选择编号
            index=r.nextInt(ANIME_NUM)+1;
            //选择类型
            type="anime\\p"+index;
            //再次打乱二维数组数据
            initDate();
            //步数清零
            counter=0;
            //重新加载图片
            initImage();
        }
    }

    /*===判断游戏是否胜利===*/
    public boolean victory(){
        for(int i=0;i<16;i++){
            if(data[i/4][i%4] != i){
                return false;
            }
        }
        return true;
    }
}