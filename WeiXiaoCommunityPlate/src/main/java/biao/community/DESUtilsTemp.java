package biao.community;

import com.alibaba.fastjson.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class DESUtilsTemp {

    //sKey有效时间
    private static final float validTime = 10000;


    public static boolean checkSKey(String md5, String sKey, JSONObject result){

        if(sKey.equals(Conf.sKey)){
            return true;
        }

        if(md5.length() != 32) return false;

        md5 = str32to8(md5);
        String time;

        byte [] byteArray = Base64.getDecoder().decode(sKey);

        try{
            byte[] decryResult = DESUtilsTemp.decrypt(byteArray, md5);
            time = new String(decryResult);
            //测试语句
            System.out.println("发送消息时间" + time);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        if(time != null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            String formatStr = formatter.format(new Date());
            //测试语句
            System.out.println("发送消息时间" + formatStr);
            result.put("发送时间",formatStr);
            try{
                Date date1 = formatter.parse(formatStr);
                Date date2 = formatter.parse(time);
                float ff =  (float) (date1.getTime() - date2.getTime());

                //测试语句
                System.out.println("时间差" + ff);
                result.put("时间差(毫秒)",ff);
                result.put("有效时间(毫秒)", DESUtilsTemp.validTime);

                if(ff <= DESUtilsTemp.validTime){
                    return true;
                }else {
                    return false;
                }
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }


        }else {
            return false;
        }

    }



    private static String str32to8(String a){
        //System.out.println(a);
        //String a = "AB15F55C75690C139D5CE0D05CAC3E98";
        String result = "";
        if (a.length() == 32){
            for (int i = 0;i < a.length();i+=4){

                char temp = (char)((a.charAt(i)+a.charAt(i+1)+a.charAt(i+2)+a.charAt(i+3))/4);
                if((temp>57&&temp<65)||(temp>90&&temp<97)) {
                    temp = 105;
                }else if(temp > 57){
                    temp += 32;
                }
                //System.out.println(temp);
                result += temp;
            }
        }

        //System.out.println(result);
        return result;
    }

    private static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
}
