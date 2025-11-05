package com.itheima.tool;

import java.util.Random;

/*验证码工具类
* */
public class CaptchaTool {

    //生成验证码
    public String produceCaptcha(){
        //生成数字
        Random r1 = new Random();
        int num = r1.nextInt(10);

        //生成字母
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++){//4位字母
            Random r2 = new Random();
            int n;
            do{
                n = r2.nextInt(57);//生成0-57的数字
            }while(n>=91-65&&n<=96-65);//如果生成了大小写字母中间的字符，那就重新生成
            char c = (char)(n+'A');//把数字变成ASCII码表对应的字母（大小写字母）
            sb.append(c);
        }

        //字母与数字拼接
        sb.append(num);
        String captcha = messString(sb.toString());//打乱验证码顺序
        return captcha;
    }

    //打乱字符串
    private String messString(String str){
        char[] arr = str.toCharArray();
        Random r = new Random();
        int rNum = r.nextInt(str.length());
        //随机与一个字符交换位置
        for (int i = 0; i < str.length(); i++) {
            char c = arr[i];
            arr[i] = arr[rNum];
            arr[rNum] = c;
        }
        String mess = new String(arr);//把字符数组变为字符串
        return mess;
    }

}
