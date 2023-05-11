package biao.community;
/*****************************************
 *  Function：实现MD5消息摘要算法
 *  Author：  Qiu Yihao
 *  Date：    2018-12-04
 *  Contact:  576261090@qq.com
 *****************************************/

import biao.community.tool.MD5;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.lang.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

class Main{
    public static void main(String[] args)throws IOException,  NoSuchAlgorithmException{


        for(int i=0;i<110;i++) {
            System.out.println(randInt(90,100));
        }
    }

    public static int randInt(int min,int max){
        Random rd = new Random();

        int temp = rd.nextInt();

        return  (temp>0?temp:temp*-1) %(max+1-min) + min;

    }
}