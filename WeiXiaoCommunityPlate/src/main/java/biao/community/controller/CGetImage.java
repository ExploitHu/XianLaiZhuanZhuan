package biao.community.controller;

import biao.community.information.port6_1.GetImage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.*;

@CrossOrigin
@Configuration
@RestController
public class CGetImage  {

    private static final String InitAddress = "/root/images";

    /**
     * 接口6.1获取对应地址的图片
     * @param address 图片地址
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] test(@RequestBody String address/**postJson是json字符串**/) {

        //json转class
        JSONObject jsonObject = JSONObject.parseObject(address);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        GetImage getImage = JSONObject.toJavaObject(jsonObject1, GetImage.class);


        try {
            File file = new File(CGetImage.InitAddress +  getImage.getImageAddress());
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        }catch (Exception exception){
            System.out.println("地址错误！");
            byte[] bytes = new byte[]{};
            if(true){
                bytes = new byte[]{
                        -1,-40,-1,-32,0,16,74,70,73,70,0,1,1,1,0,96,0,96,0,0,-1,-31,0,34,69,120,105,102,0,0,77,
                        77,0,42,0,0,0,8,0,1,1,18,0,3,0,0,0,1,0,1,0,0,0,0,0,0,-1,-37,0,67,0,
                        2,1,1,2,1,1,2,2,2,2,2,2,2,2,3,5,3,3,3,3,3,6,4,4,3,5,7,6,7,7,
                        7,6,7,7,8,9,11,9,8,8,10,8,7,7,10,13,10,10,11,12,12,12,12,7,9,14,15,13,12,14,
                        11,12,12,12,-1,-37,0,67,1,2,2,2,3,3,3,6,3,3,6,12,8,7,8,12,12,12,12,12,12,12,
                        12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,12,
                        12,12,12,12,12,12,12,12,12,12,12,12,12,-1,-64,0,17,8,0,40,0,-112,3,1,34,0,2,17,1,3,
                        17,1,-1,-60,0,31,0,0,1,5,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,2,3,4,5,6,
                        7,8,9,10,11,-1,-60,0,-75,16,0,2,1,3,3,2,4,3,5,5,4,4,0,0,1,125,1,2,3,0,
                        4,17,5,18,33,49,65,6,19,81,97,7,34,113,20,50,-127,-111,-95,8,35,66,-79,-63,21,82,-47,-16,36,51,
                        98,114,-126,9,10,22,23,24,25,26,37,38,39,40,41,42,52,53,54,55,56,57,58,67,68,69,70,71,72,73,
                        74,83,84,85,86,87,88,89,90,99,100,101,102,103,104,105,106,115,116,117,118,119,120,121,122,-125,-124,-123,-122,-121,
                        -120,-119,-118,-110,-109,-108,-107,-106,-105,-104,-103,-102,-94,-93,-92,-91,-90,-89,-88,-87,-86,-78,-77,-76,-75,-74,-73,-72,-71,-70,
                        -62,-61,-60,-59,-58,-57,-56,-55,-54,-46,-45,-44,-43,-42,-41,-40,-39,-38,-31,-30,-29,-28,-27,-26,-25,-24,-23,-22,-15,-14,
                        -13,-12,-11,-10,-9,-8,-7,-6,-1,-60,0,31,1,0,3,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,
                        1,2,3,4,5,6,7,8,9,10,11,-1,-60,0,-75,17,0,2,1,2,4,4,3,4,7,5,4,4,0,1,
                        2,119,0,1,2,3,17,4,5,33,49,6,18,65,81,7,97,113,19,34,50,-127,8,20,66,-111,-95,-79,-63,9,
                        35,51,82,-16,21,98,114,-47,10,22,36,52,-31,37,-15,23,24,25,26,38,39,40,41,42,53,54,55,56,57,58,
                        67,68,69,70,71,72,73,74,83,84,85,86,87,88,89,90,99,100,101,102,103,104,105,106,115,116,117,118,119,120,
                        121,122,-126,-125,-124,-123,-122,-121,-120,-119,-118,-110,-109,-108,-107,-106,-105,-104,-103,-102,-94,-93,-92,-91,-90,-89,-88,-87,-86,-78,
                        -77,-76,-75,-74,-73,-72,-71,-70,-62,-61,-60,-59,-58,-57,-56,-55,-54,-46,-45,-44,-43,-42,-41,-40,-39,-38,-30,-29,-28,-27,
                        -26,-25,-24,-23,-22,-14,-13,-12,-11,-10,-9,-8,-7,-6,-1,-38,0,12,3,1,0,2,17,3,17,0,63,0,-114,-88,
                        47,-117,116,-73,-15,3,-23,43,-87,-23,-51,-86,70,55,61,-104,-71,79,-76,1,-128,-39,49,-25,119,-35,32,-12,-24,65,
                        -85,-73,23,17,-38,91,-68,-78,-70,-57,20,106,93,-35,-56,85,85,28,-110,79,96,43,-26,-17,14,107,90,124,31,-16,
                        83,127,17,-36,-3,-78,-51,32,111,2,33,105,60,-43,8,72,-70,-73,-22,-39,-57,65,-7,10,-2,-57,-51,51,63,-87,
                        -70,75,71,-49,53,31,68,-17,-81,-32,127,-97,57,14,73,-3,-96,-85,-74,-38,-10,116,-27,53,101,123,-75,109,63,19,
                        -23,58,-63,-16,103,-59,15,15,-4,66,-67,-44,-83,-12,93,82,-33,80,-101,71,-99,-83,-81,-110,32,115,107,42,-110,-69,
                        31,35,-122,5,79,7,-98,43,-51,-75,47,-120,-34,36,111,-37,-61,77,-16,-108,122,-45,67,-31,119,-16,-111,-42,-102,-62,
                        59,120,72,-102,111,61,-31,-7,-92,104,-52,-128,99,7,10,-61,-107,30,-92,31,37,-8,19,-5,88,-8,15,-10,122,-42,
                        -66,49,-1,0,-62,81,-81,67,107,115,113,-29,-19,78,107,123,72,99,123,-119,-25,77,-64,6,85,80,126,82,65,27,
                        -101,3,-125,-51,121,88,-114,40,-93,79,17,24,73,-88,-61,-102,81,-108,-101,75,88,-92,-18,-75,-39,-33,-82,-89,-67,-123,
                        -32,60,85,92,20,-22,-58,-11,42,114,83,-100,35,4,-37,-76,-28,-43,-92,-83,123,-92,-81,-90,-117,-71,-10,13,67,115,
                        -88,-37,-39,-56,-85,52,-16,-60,-49,-9,85,-36,41,111,-90,107,-126,-3,-103,127,104,-51,63,-10,-96,-16,21,-25,-119,52,
                        -99,62,-13,79,-45,98,-44,37,-79,-74,-5,91,41,-110,-31,81,99,99,33,85,36,39,46,87,110,79,-35,-50,121,-64,
                        -14,15,-37,122,-46,57,-65,107,95,-39,-41,124,106,-34,102,-77,122,24,16,62,109,-83,104,87,-14,39,35,-36,-41,118,
                        51,60,-89,79,5,28,117,15,122,50,113,75,-91,-44,-92,-107,-10,-23,123,-19,-87,-28,101,-68,49,94,-82,103,44,-81,
                        23,-5,-71,-58,51,111,68,-38,112,-125,-107,-83,125,-35,-83,-66,-105,-14,-79,-11,21,115,126,52,-8,-61,-31,-97,-121,-66,
                        35,-47,-12,-99,103,84,-122,-57,82,-15,3,-76,90,116,12,-114,-49,118,-22,84,21,77,-96,-13,-106,81,-113,-10,-123,97,
                        126,-47,-33,-76,70,-97,-5,54,-8,79,77,-43,-75,45,55,80,-44,-31,-43,53,20,-46,-29,-114,-52,-89,-104,37,117,119,
                        76,-17,42,48,118,17,-100,-16,113,94,67,-5,105,-56,-51,-5,87,126,-51,-20,-53,-27,-77,107,55,-91,-108,-100,-19,63,
                        -24,124,126,21,-98,109,-99,67,15,74,94,-63,-89,56,-54,9,-90,-98,-118,114,75,-53,-93,109,106,109,-61,-4,47,60,
                        93,120,125,105,56,-45,-87,26,-82,45,53,-85,-89,7,38,-70,-11,73,61,61,15,-89,-22,-89,-120,-68,69,97,-31,45,
                        10,-17,82,-44,-82,-96,-79,-45,-20,35,105,-82,39,-108,-19,-114,36,81,-110,-52,123,1,86,124,-8,-60,-34,94,-27,-13,
                        10,-18,11,-97,-104,-114,-103,-59,124,-73,-5,115,120,-81,92,-3,-95,-75,127,-8,82,-1,0,15,-66,-49,123,-87,-52,-126,
                        -1,0,-60,-105,6,127,42,27,11,116,42,-47,-62,-18,1,10,-50,-37,78,48,78,2,-116,16,-57,29,89,-74,104,-80,
                        120,87,94,62,-12,-74,-118,91,-54,79,100,-70,-6,-10,73,-100,92,61,-111,75,50,-57,-84,52,-33,36,55,-100,-98,-118,
                        48,91,-74,-34,-98,74,-5,-74,-105,83,-23,-35,23,91,-78,-15,38,-107,5,-10,-97,119,107,127,99,116,-95,-30,-72,-73,
                        -107,100,-114,69,-11,86,82,65,30,-32,-43,77,35,-57,90,46,-67,-82,-33,-23,54,58,-66,-101,117,-86,105,71,23,-106,
                        113,92,-93,79,107,-23,-66,48,119,38,123,18,6,107,-52,-2,25,-89,-60,-113,4,-4,48,-44,52,-72,-4,23,-31,93,
                        38,77,14,-62,40,-76,27,59,61,76,-51,13,-61,-114,10,-56,-52,19,111,0,29,-36,-110,88,-98,-67,124,67,-10,126,
                        -15,71,-59,107,127,-38,-125,-29,68,-38,127,-124,-4,43,113,-83,77,54,-110,117,91,121,-75,87,-114,43,70,22,-14,-7,
                        98,39,8,75,110,93,-59,-78,56,32,14,122,-41,-105,-120,-30,55,71,-22,-9,-90,-17,54,-45,-9,101,-91,-94,-37,-78,
                        -75,-17,117,-41,-90,-89,-71,-127,-32,-40,98,62,-74,-29,86,54,-94,-109,-113,-65,13,111,40,-91,119,123,37,-53,45,90,
                        118,-26,-46,-9,62,-69,-16,127,-114,116,95,-120,122,63,-10,-122,-125,-85,105,-38,-59,-114,-29,31,-97,101,114,-77,-96,112,
                        1,32,-78,-110,3,0,70,65,-28,100,86,-123,-11,-12,26,109,-100,-105,23,19,67,111,111,10,-18,-110,73,92,34,32,
                        -11,36,-16,63,26,-7,67,-2,9,-39,-30,15,-120,-119,-32,29,54,-47,124,59,-96,-1,0,-62,37,38,-77,127,-10,-53,
                        -31,126,126,-41,9,-13,28,-112,-111,-16,8,18,97,65,-55,36,28,-32,87,-71,126,-44,127,3,-83,-65,104,-81,-126,58,
                        -25,-123,-26,59,110,110,34,-13,-84,101,-33,-73,-55,-71,64,90,50,125,-73,112,127,-39,102,-82,-84,-65,56,-85,-119,-53,
                        -66,-71,8,123,-10,-70,77,56,-90,-19,123,38,-6,61,-81,-79,-61,-101,112,-19,12,14,117,-3,-97,82,-94,84,-71,-84,
                        -28,-102,-101,-116,111,107,-76,-102,-43,45,90,-47,-98,-126,-50,-86,87,113,81,-69,-127,-109,-9,-114,9,-29,-14,-94,-66,51,
                        -3,-67,60,51,-30,63,8,127,-63,58,52,29,39,-59,26,-123,-82,-93,-82,105,-9,-74,86,-9,55,86,-91,-118,-54,20,
                        72,23,-26,60,-77,5,-38,25,-114,55,16,91,3,56,-87,7,-20,-21,-5,35,-98,127,-74,-68,59,-1,0,-123,68,-65,
                        -4,118,-68,-38,-36,77,94,56,-113,97,26,112,-117,80,-116,-97,60,-7,95,-67,-47,90,46,-19,91,93,79,99,11,-64,
                        -8,89,-31,62,-75,58,-43,36,-100,-25,5,-20,-87,115,-81,118,-34,-13,110,113,-78,119,-47,91,-93,62,-57,50,42,-54,
                        20,-78,-18,110,-125,60,-102,90,-8,-89,-10,51,-16,-89,-126,60,37,-1,0,5,7,-15,101,-97,-61,-21,-117,107,-65,12,
                        -57,-31,61,-48,60,23,-122,-18,63,48,-51,105,-26,98,66,-52,115,-100,-9,-30,-66,-42,-81,99,33,-51,101,-113,-95,42,
                        -77,-118,-117,-116,-100,116,-107,-45,-77,-35,59,43,-89,-24,124,-1,0,21,-16,-4,114,-100,76,40,83,-101,-110,-108,35,63,
                        122,60,-78,87,-24,-29,121,89,-82,-70,-78,-113,-118,-4,53,103,-29,111,11,-22,90,62,-91,27,77,-89,-22,-42,-78,-39,
                        -36,-96,114,-123,-29,-111,74,58,-18,24,35,42,-60,100,28,-118,-8,-73,72,-3,-121,126,24,-34,-2,-34,90,-25,-125,-113,
                        -122,-39,124,63,7,-124,23,82,-122,-40,106,23,95,-69,-71,51,-60,-98,104,127,55,121,-31,-56,-38,88,-81,61,43,-19,
                        -83,83,80,-113,72,-45,46,46,-27,18,52,118,-79,52,-82,17,75,54,20,18,112,7,36,-15,-45,-67,124,47,101,-5,
                        78,-21,-97,17,-65,108,-83,115,-58,95,8,-4,23,-87,120,-30,59,-113,14,71,-95,36,-105,10,-42,118,-10,-46,-7,-47,
                        -56,100,118,108,13,-72,76,5,102,66,119,117,24,-25,-63,-29,24,-32,-71,-16,-17,17,21,41,115,-83,45,121,56,-92,
                        -18,-110,-77,109,93,-83,23,83,-21,-68,53,-87,-103,-86,120,-59,-124,-88,-31,15,102,-11,-26,-27,-126,-101,-73,43,109,-76,
                        -109,-78,118,109,-34,-41,36,-73,-3,-103,-68,77,-15,7,-10,-38,-78,-16,-9,-60,-17,24,-34,106,-109,77,-32,-10,-68,-35,
                        -95,-73,-40,21,109,-123,-45,-94,90,18,-86,11,71,-99,-52,-39,27,-119,35,-109,-116,-97,72,-1,0,-126,127,124,38,-16,
                        -81,-127,47,62,42,-101,125,35,77,-123,124,59,-29,75,-5,27,75,-87,-29,89,38,-76,-74,-117,104,84,-13,-97,47,-75,
                        70,79,45,-41,39,-87,38,-74,52,-71,-17,-91,-1,0,-126,-122,120,108,-22,-79,-92,26,-84,-97,12,-112,-35,-58,-124,109,
                        73,-115,-29,-103,20,96,-111,-128,-39,-24,79,78,-90,-95,-48,63,97,-8,-2,32,-8,103,-30,14,-117,-29,-56,35,-118,-49,
                        -60,-98,50,-72,-15,29,-116,-42,23,10,103,-119,36,108,-19,12,84,-20,37,70,27,0,-27,73,-63,-17,95,59,-127,-54,
                        -44,49,30,-37,13,79,-102,81,-99,77,100,-11,-8,87,42,-109,105,-75,-81,-30,125,-106,109,-98,123,76,23,-43,113,-75,
                        -67,-100,39,74,-117,-9,18,-73,-60,-7,-100,98,-102,79,69,-33,98,31,-8,36,-35,-121,-40,-1,0,100,43,55,44,24,
                        92,-22,-73,114,-128,63,-121,12,19,-1,0,101,-49,-29,94,71,-5,83,-2,-54,22,126,12,-3,-93,-2,12,-23,113,120,
                        -61,-57,87,41,-30,-99,94,-14,57,37,-101,83,6,77,55,13,110,115,104,118,126,-28,-97,51,-100,-18,-5,-117,-23,95,
                        70,126,-51,-33,19,126,31,120,103,-59,58,-9,-62,127,7,-38,-36,89,127,-62,4,11,73,-76,25,-96,-107,8,86,-111,
                        -52,-68,-2,-16,72,-20,-91,73,-55,42,72,-32,28,124,-5,-5,95,-2,-43,62,25,-8,-117,-5,74,124,32,-70,-16,43,
                        93,120,-22,-9,-63,-70,-123,-35,-51,-27,-98,-105,111,43,52,-101,-66,-49,-79,81,-118,-31,-77,-27,-73,43,-112,49,-51,116,
                        102,-108,-16,16,-56,-88,81,-85,37,41,-63,-62,58,55,-85,83,-118,-102,75,119,101,123,-23,123,106,112,100,53,-13,90,
                        -100,83,-118,-60,97,-23,-54,16,-85,26,-109,119,-118,-47,74,-100,-99,55,39,-86,-115,-37,86,-69,73,-67,14,-101,-10,-23,
                        -8,85,23,-63,-81,-39,-105,-63,122,37,-66,-85,-84,107,81,-81,-114,-83,38,-5,86,-85,56,-98,-23,-9,-59,114,112,-18,
                        2,-18,-57,65,-57,74,-71,-1,0,5,29,-16,6,-101,-15,75,-29,-9,-64,95,15,-21,17,-55,54,-101,-85,106,119,-10,
                        -9,9,28,-122,54,116,63,99,-56,12,57,31,81,84,127,110,111,21,120,-117,-58,-33,-78,-73,-128,-11,63,21,104,31,
                        -16,-117,107,23,62,54,-75,102,-45,76,-30,102,-127,2,92,-124,-36,-61,-85,21,10,79,3,4,-29,2,-70,127,-37,100,
                        103,-10,-75,-3,-100,121,-57,-4,78,-81,-65,-10,-50,-79,-57,-84,60,-31,-119,-124,23,-72,-2,-82,-110,119,90,115,45,26,
                        122,-19,-70,122,-9,58,114,-38,-104,-88,75,5,86,-84,-65,123,21,-116,110,81,105,-5,-36,-110,-43,56,-24,-11,-39,-83,
                        59,30,-121,-16,31,-10,40,-16,39,-20,-31,-29,59,-83,119,-62,-74,-38,-107,-83,-27,-27,-111,-79,-111,102,-68,105,-30,40,
                        93,28,-100,55,59,-73,34,115,-100,96,116,-81,33,-1,0,-126,-118,-73,-122,126,31,-8,51,-60,-98,34,-16,-49,-115,-92,
                        -16,127,-60,107,95,34,89,-20,-76,-115,92,88,-35,-21,30,116,-74,-47,-113,62,53,34,73,85,33,14,-56,23,1,119,
                        59,28,-28,-25,-21,-83,-33,53,124,-43,-1,0,5,54,-8,99,-31,-55,63,101,95,27,120,-111,-12,45,37,-68,66,-85,
                        96,-85,-87,-75,-102,53,-28,107,-10,-56,19,11,33,27,-44,108,98,-68,17,-112,72,-17,95,77,-60,89,101,42,57,53,
                        88,96,-31,24,-88,-89,43,91,107,43,-35,91,103,-40,-8,-98,11,-49,49,24,-114,37,-95,83,50,-87,57,74,78,48,
                        -67,-45,-26,77,-91,-53,46,107,-34,61,-6,-19,109,73,-2,24,89,-23,-9,-65,10,-75,93,31,-63,95,27,-81,-75,111,
                        23,-21,54,86,-9,-30,-5,82,-43,-95,-41,-82,52,-95,19,43,75,-78,49,-47,89,89,-112,-85,114,9,7,-83,121,119,
                        -123,116,63,-8,68,60,105,-30,47,17,105,127,-75,-97,-123,109,-11,-113,22,53,-69,106,-109,-1,0,-62,59,-89,-65,-38,
                        76,8,-55,31,-56,-45,21,77,-86,-60,124,-95,115,-98,114,107,-42,126,10,120,-77,-31,108,-2,41,-101,64,-16,95,-121,
                        -76,65,-81,105,-34,25,51,106,122,-82,-109,-89,71,28,48,51,44,99,-20,-17,52,106,1,118,37,-101,-109,-1,0,44,
                        -56,39,60,15,38,-3,-119,60,59,-29,61,67,-10,97,-16,-52,-38,87,-61,95,-121,94,32,-79,-109,-19,126,94,-95,-86,
                        92,-124,-69,-72,-59,-44,-32,-17,30,81,-5,-83,-107,28,-97,-107,71,78,-125,-27,107,114,-44,120,122,127,19,-76,-102,112,
                        117,90,77,89,59,40,-55,107,-17,52,-33,-56,-5,-52,60,-89,73,98,-21,-39,-59,74,80,77,84,-115,4,-28,-92,-101,
                        87,115,-125,92,-85,-39,-59,-58,58,127,53,-70,-99,31,-20,-21,-16,-109,-60,26,109,-85,120,127,-64,127,-76,-122,-111,-84,
                        89,-38,-54,-6,-123,-59,-123,-97,-122,-20,-26,111,-98,64,100,45,39,-104,-50,-127,-103,-70,-25,-116,-16,56,-30,-1,0,-4,
                        21,35,-10,126,-16,-50,-77,-16,35,-60,-65,16,46,45,103,111,19,-23,54,-10,86,-74,-73,34,-26,69,88,-112,-34,70,
                        -124,121,96,-20,57,89,92,100,-126,121,-10,-85,63,-77,38,-97,-85,105,-97,-74,-41,-116,99,-42,-68,59,-96,120,94,-9,
                        -2,17,123,102,-5,30,-113,39,-103,110,87,-49,24,124,-19,95,-104,-13,-98,59,14,-75,-43,127,-63,78,36,17,-2,-61,
                        -2,55,110,57,22,88,-25,-81,-6,125,-67,122,80,-62,-48,-97,15,-30,36,-30,-19,21,52,-109,114,105,56,-35,38,-108,
                        -37,105,-83,-20,-113,22,-74,99,-118,-91,-59,-8,40,66,107,-33,-107,54,-36,99,8,-71,41,-72,-71,41,74,-100,98,-92,
                        -98,-41,119,-70,44,120,-77,-63,62,35,-15,87,-20,-53,-32,29,55,-62,-38,7,-126,53,-45,21,-123,-119,-102,-49,-60,-10,
                        -26,107,5,-123,109,48,-92,40,-2,37,98,49,-24,51,95,62,-4,83,-1,0,-124,-29,-31,47,-60,79,6,-8,94,-1,
                        0,-31,47,-20,-25,113,-86,120,-38,-27,-19,108,5,-73,-121,-39,-110,50,-115,24,102,-108,-77,13,-86,60,-64,114,3,28,
                        43,113,-45,61,-9,-63,-33,7,-8,43,-10,-51,-8,105,101,-30,-17,17,90,-21,-102,54,-105,-32,-37,8,-12,75,61,67,
                        -5,64,-40,-63,119,111,12,49,-76,-78,-110,8,32,44,-94,81,-110,118,-19,11,-4,89,11,-31,-70,103,-20,-75,99,-5,
                        86,124,70,-15,-122,-87,-16,-23,-75,-21,127,9,-8,63,78,-110,29,34,-6,-26,-2,89,31,85,-44,64,44,60,-74,124,
                        -107,86,-24,64,35,0,33,60,-75,120,121,-41,-76,-60,66,-107,92,52,121,-100,-7,84,109,45,92,99,102,-33,43,-114,
                        -118,-55,-85,-34,-41,-43,110,125,87,11,-43,-114,10,-91,124,62,46,124,-79,-90,-28,-26,-27,7,101,57,-73,-54,-71,-43,
                        77,93,-38,109,89,59,38,-99,-83,115,-23,-33,-127,-33,11,62,38,120,15,-30,126,-101,119,121,-32,127,-126,-66,31,-46,
                        101,15,22,-95,119,-31,-83,61,-19,-81,-116,69,114,16,49,-64,42,93,80,-112,114,62,92,-11,2,-66,-119,-81,-113,62,
                        0,126,-52,95,7,-1,0,105,-17,-121,11,113,111,113,-30,-101,61,90,-37,16,-21,26,84,-102,-28,-58,123,9,-57,14,
                        -116,-110,103,-27,-36,14,9,94,71,-95,4,15,-80,-21,-17,56,78,-101,-122,25,-39,46,87,105,38,-91,-51,123,-18,-66,
                        24,-38,-42,90,106,-18,-39,-7,63,-120,56,-120,-43,-58,-82,118,-3,-84,111,25,39,14,75,91,84,-2,57,-13,94,-17,
                        91,-91,100,-83,-72,85,63,14,-8,103,77,-16,126,-111,6,-97,-92,-40,-39,-23,-74,22,-29,17,-37,-38,-60,-79,71,31,
                        -47,84,0,40,-94,-66,-93,-39,-59,-66,118,-75,62,10,53,-22,69,74,-110,126,-21,-23,-23,-73,-26,-49,51,-3,-96,63,
                        99,127,10,-2,-47,94,42,-46,117,-35,82,-17,94,-46,117,-51,30,35,5,-67,-10,-111,118,-74,-45,121,121,99,-76,-77,
                        35,-16,11,-79,24,0,-115,-57,-100,19,-98,46,127,-8,38,-113,-123,111,-99,126,-39,-29,-113,-118,90,-124,40,-37,-116,87,
                        58,-30,52,109,-19,-60,64,-2,68,26,40,-81,31,17,-61,121,117,106,-110,-83,82,-110,114,111,87,-82,-66,-89,-45,-32,
                        56,-53,57,-61,-45,-114,18,-115,118,-95,29,18,-78,118,91,-39,54,-101,91,-98,-107,-5,62,-2,-53,94,13,-3,-104,-12,
                        -69,-21,127,10,-40,-51,4,-102,-109,-85,-35,-36,-36,76,102,-98,109,-96,-123,5,-113,69,25,36,0,0,-53,19,91,-97,
                        14,126,11,-8,87,-31,26,93,47,-122,-12,29,59,73,107,-23,26,91,-119,33,-120,121,-77,51,54,-29,-67,-50,88,-116,
                        -109,-128,78,7,64,0,-30,-118,43,-81,15,-105,97,105,66,16,-91,78,49,81,-67,-84,-74,-66,-10,-11,-22,121,56,-84,
                        -21,31,94,117,42,86,-83,41,57,-37,-102,-14,126,-11,-74,-66,-70,-37,-89,99,-58,-2,33,-1,0,-63,52,-4,39,-15,
                        71,-60,-6,-74,-87,-85,120,-81,-57,-116,-70,-90,-89,46,-86,45,32,-44,98,91,91,121,-99,-104,-27,17,-94,108,21,-36,
                        85,78,114,23,-116,-43,61,87,-2,9,117,-31,29,115,80,-77,-70,-68,-15,-73,-59,11,-85,-83,53,-117,-38,75,46,-77,
                        20,-110,91,-79,-58,74,49,-125,42,78,7,35,29,7,-91,20,87,-107,46,19,-54,-89,46,105,82,87,-109,-41,87,-21,
                        -36,-6,24,120,-123,-97,-45,-126,-124,49,13,40,-85,45,35,-94,-75,-69,117,-21,-36,-20,-66,17,-2,-59,-102,79,-63,-1,
                        0,-120,118,126,34,-75,-15,119,-113,53,-119,-84,-29,-110,33,111,-84,106,73,117,1,-13,23,105,-7,66,41,4,125,107,
                        -92,-3,-89,62,0,-37,-2,-46,-1,0,10,46,60,43,117,-86,94,105,54,-9,87,16,-51,36,-74,-32,49,112,-114,27,
                        107,46,62,97,-36,116,-7,-126,-98,-40,37,21,-24,82,-55,-80,-112,-61,-53,6,-93,-18,74,-9,87,125,85,-69,-98,85,
                        78,34,-52,43,99,97,-103,84,-87,-5,-38,118,-27,118,90,91,85,-91,-83,-65,-111,-95,-16,-37,-32,103,-121,-2,11,124,
                        54,111,13,-8,87,79,-125,79,-74,-14,89,75,48,-35,36,-14,21,-37,-26,74,-3,93,-113,25,39,-73,3,0,1,94,
                        59,-16,111,-2,9,-95,-32,-33,4,-4,57,-45,-12,-49,16,75,-87,106,-6,-59,-65,-101,-10,-117,-85,77,82,-22,-42,25,
                        119,74,-20,-69,98,89,2,-82,21,-108,28,117,32,-98,-90,-118,43,26,-39,38,6,115,-126,-99,36,-44,21,-94,-83,-94,
                        78,-41,-45,110,-120,-86,60,85,-101,82,85,85,42,-14,78,-92,-108,-92,-45,-9,-101,73,-38,-17,126,-81,75,-37,-18,71,
                        93,-16,83,-10,64,-45,62,3,-4,109,-43,-68,75,-95,-34,-56,-102,78,-85,-91,45,-127,-80,-72,-110,91,-119,-46,85,-111,
                        95,-52,-13,-99,-40,-19,33,72,-37,-113,66,8,-28,30,-53,-29,-41,-64,-51,23,-10,-120,-8,117,63,-122,117,-29,116,52,
                        -5,-119,-93,-100,-75,-68,-69,24,52,110,24,116,-32,-126,50,48,114,57,-49,80,8,40,-82,-70,57,78,18,-99,7,-123,
                        -116,23,-77,-107,-37,-113,77,119,-7,121,28,-40,-82,34,-52,43,99,97,-115,-87,81,-5,88,89,41,117,92,-69,59,-11,
                        107,-69,-44,-13,-65,-117,127,-80,-3,-65,-59,-35,103,71,-45,110,124,77,-85,105,-33,14,116,-101,88,-31,95,10,-40,-86,
                        -63,3,-68,100,109,-3,-30,-128,74,-32,2,67,-122,108,-14,-84,-67,-67,-109,-63,-66,11,-46,-2,30,120,102,-45,71,-47,
                        108,45,-76,-35,50,-59,4,112,91,-37,-90,-59,-116,127,82,122,-110,114,73,-28,-110,77,20,81,-122,-55,-16,-104,105,-70,
                        -44,99,105,75,119,-66,-99,18,-66,-55,116,75,66,-77,28,-1,0,31,-117,-95,12,62,34,-93,112,-115,-38,90,37,119,
                        -69,118,-75,-28,-6,-73,118,-5,-98,67,-15,27,-10,17,-16,-17,-117,126,60,-23,63,16,116,-99,67,82,-16,-50,-83,5,
                        -46,-49,-86,-57,-89,76,-48,46,-84,-125,-109,-106,66,-83,27,-79,3,115,41,-7,-128,57,27,-114,-31,-18,20,81,90,-31,
                        114,-20,54,26,114,-107,8,-88,-71,-22,-19,-43,-1,0,95,-26,115,99,-77,-84,110,58,-99,42,120,-71,-71,42,107,-106,
                        55,-24,-97,75,-18,-4,-81,123,43,46,-121,-1,-39
                };
            }
            return bytes;
        }



    }


}