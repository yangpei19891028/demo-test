package com.example.demotest.util;

import org.openjdk.jol.info.ClassLayout;
//import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestUtil {
    public static void main(String[] args) {
//        BASE64Encoder encoder = new sun.misc.BASE64Encoder();
//        File f = new File("d:"+ File.separator+"timg.jpg");
//        BufferedImage bi;
//        try {
//            bi = ImageIO.read(f);
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(bi, "jpg", baos);  //经测试转换的图片是格式这里就什么格式，否则会失真
//            byte[] bytes = baos.toByteArray();
//            System.out.println(encoder.encodeBuffer(bytes).trim());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Signer signer = new Signer("lIh0BcZraY24XG9maDY8R4nD","CqBg9ZpEkgc2HnKhAFowPEhhetDXmkm8");
//        SortedMap<String, String> params = new TreeMap<String, String>();
        //signer.Sign("POST","/openapi/v1/faces/detect",);
//        System.out.println(new BigDecimal("1000001011581307075949").longValue());
//
//        String s = ClassLayout.parseInstance("13").toPrintable();
//        System.out.println(s);
        System.out.println(0x0001 | 0x0020);
    }
}
