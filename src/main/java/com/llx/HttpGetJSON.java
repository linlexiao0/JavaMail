package com.llx;

import com.alibaba.fastjson2.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HttpGetJSON {
    public static JSONObject getJSON(HttpServletRequest request){
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(
                    request.getInputStream(), StandardCharsets.UTF_8
            ));
            String line;
            while ((line=in.readLine())!=null){
                result.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                assert in != null;
                in.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
//        System.out.println("result: "+result);
        return JSONObject.parse(result.toString());
    }
}
