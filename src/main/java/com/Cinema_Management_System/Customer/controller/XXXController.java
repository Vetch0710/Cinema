package com.Cinema_Management_System.Customer.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Vetch
 * @Description
 * @create 2020-10-09-10:51
 */

@Controller
public class XXXController {


    @RequestMapping(value = "login")
    @ResponseBody
    public String login(@RequestBody HashMap<String, String> map) throws Exception {
        System.out.println("*****************login******************");
        System.out.println(map);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        JSONObject jsonObject2 = new JSONObject();

        if ("customer".equals(map.get("type"))) {
            jsonObject2.put("accessToken", "754406394-customer");
            jsonObject2.put("username", "username");
        } else {
            jsonObject2.put("accessToken", "12345-manager");
            jsonObject2.put("username", "manager");
        }

        jsonObject.put("data", jsonObject2);
        System.out.println(jsonObject.toJSONString());

        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "userInfo")
    @ResponseBody
    public String userInfo(@RequestBody HashMap<String, String> map) throws Exception {
        System.out.println("**************userInfo***************");

        System.out.println(map);
        String accessToken = map.get("accessToken");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");
        JSONObject jsonObject2 = new JSONObject();

        List<String> permissions = new ArrayList<String>();
        permissions.add(accessToken.substring(accessToken.indexOf("-") + 1));
        String username = accessToken.substring(0, accessToken.indexOf("-"));
        jsonObject2.put("permissions", permissions);
        jsonObject2.put("username", username);


        jsonObject.put("data", jsonObject2);
        System.out.println(jsonObject.toJSONString());

        return jsonObject.toJSONString();
    }


    @RequestMapping(value = "getPersonalInfo", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getPersonalInfo(@RequestBody HashMap<String, String> map) throws Exception {
        System.out.println("**************getPersonalInfo***************");

        System.out.println(map);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("value", "754406394");

        List<JSONObject> datas = new ArrayList<>();


        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("type", "id");
        jsonObject1.put("value", "754406394");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("type", "头像");
        jsonObject2.put("value", "oasdsadsa.xx");
        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("type", "姓名");
        jsonObject3.put("value", "张三");
        JSONObject jsonObject4 = new JSONObject();
        jsonObject4.put("type", "性别");
        jsonObject4.put("value", "男");
        JSONObject jsonObject5 = new JSONObject();
        jsonObject5.put("type", "密码");
        jsonObject5.put("value", "123465");
        JSONObject jsonObject6 = new JSONObject();
        jsonObject6.put("type", "手机号");
        jsonObject6.put("value", "13752114812");
        datas.add(jsonObject1);
        datas.add(jsonObject2);
        datas.add(jsonObject3);
        datas.add(jsonObject4);
        datas.add(jsonObject5);
        datas.add(jsonObject6);


        jsonObject.put("data", datas);
        System.out.println(jsonObject.toJSONString());

        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "logout")
    @ResponseBody
    public String logout() throws Exception {
        System.out.println("**************logout***************");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");

        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "upload", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile picture, HttpServletRequest request) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("msg", "success");

//        System.out.println(file);
        System.out.println(picture.getContentType());

        //获取文件在服务器的储存位置
        String path = request.getSession().getServletContext().getRealPath("/img");
        File filePath = new File(path);
        System.out.println("文件的保存路径：" + path);
        if (!filePath.exists() && !filePath.isDirectory()) {
            System.out.println("目录不存在，创建目录:" + filePath);
            filePath.mkdir();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = picture.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + name + "." + type;
        System.out.println("新文件名称：" + fileName);

        //在指定路径下创建一个文件
        File targetFile = new File(path, fileName);

        //将文件保存到服务器指定位置
        try {
            picture.transferTo(targetFile);
            System.out.println("上传成功");
            //将文件在服务器的存储路径返回
            return fileName;
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return null;
        }
    }
}
