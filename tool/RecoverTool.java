package com.itheima.tool;

import java.util.Random;

/*判断是否能够复原
* */
public class RecoverTool{
//    public static void main(String[] args) {
//        int[][] data = new int[4][4];
//        int x=0,y;
//        int[] temp = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
//        Random random = new Random();
//        int index;
//        do{
//            for (int i = 0; i < temp.length; i++) {
//                index = random.nextInt(temp.length);
//                int p = temp[i];
//                temp[i] = temp[index];
//                temp[index] = p;
//            }
//            //把打乱的一维数组数据 赋给 二维数组
//            for (int i = 0; i < temp.length; i++) {
//                data[i/4][i%4] = temp[i];
//                //15就是空白图片
//                if(data[i/4][i%4]==15){
//                    x=i/4;
//                    y=i%4;
//                }
//            }
//
//            for (int i = 0; i < temp.length; i++) {
//                System.out.print(temp[i]+" ");
//            }
//            System.out.println();
//        }while(!isRecover(temp,x));//进行判断一下，如果符合就可以，不符合就重新随机
//  }
    //能够复原的条件：逆序数的奇偶性 + 空格行数差的奇偶性 = 目标状态的奇偶性（偶数）
    //1.逆序数：每一位数字后面出现比它小的数字  的数量之和
    //2.行数差：空格初、末状位置的行数之差
    //3.空格不参与计算

    public boolean isRecover(int[] temp,int row){
        int[] counter = new int[16];
        for (int i = 0; i < temp.length; i++) {
            int count=0;
            if(temp[i]!=15){
                for(int j=i+1;j<temp.length;j++){
                    if(temp[i]>temp[j]){
                        count++;
                    }
                }
            }
            counter[i]=count;
        }
        int sum = 0;
        for (int i = 0; i < counter.length; i++) {
            sum += counter[i];
        }
        System.out.println(sum);
        System.out.println(3-row);
        if((sum+3-row)%2==0){
            return true;
        }
        return false;
    }
}
