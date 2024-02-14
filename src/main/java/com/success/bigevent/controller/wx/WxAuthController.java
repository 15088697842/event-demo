package com.success.bigevent.controller.wx;

import com.success.bigevent.DTO.Result;
import com.success.bigevent.DTO.UserReq;
import com.success.bigevent.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.prefs.PreferencesFactory;


@RestController
@RequestMapping("wx")
public class WxAuthController {

    @Autowired
    private UserService userService;

    @GetMapping("auth")
    public String auth(@RequestParam String signature, @RequestParam String timestamp,
                           @RequestParam String nonce, @RequestParam String echostr)  {

        String token = "434";
          String[] sorts = new String[]{token,timestamp,nonce};
           Arrays.sort(sorts, String.CASE_INSENSITIVE_ORDER);
          String aa = sorts[0] + sorts[1] + sorts[2];
          String s = sha1(aa);
          if(true) {
              return echostr;
          }else{
              return "";
          }
    }

    public static String sha1(String input) {
        byte[] hashedBytes = null;
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");

            // 将输入转换为字节数组并进行哈希计算
            hashedBytes = sha1.digest(input.getBytes());
        }catch (Exception e){

        }
        // 将字节数组转换为十六进制字符串
        return bytesToHex(hashedBytes);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            // 将每个字节转换为十六进制，并追加到字符串中
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}
