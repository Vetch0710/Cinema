package com.Cinema_Management_System.User.controller;


import com.Cinema_Management_System.User.entity.Customer;
import com.Cinema_Management_System.User.entity.Manager;
import com.Cinema_Management_System.User.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/UserInfo")
public class UserManagerController {

    @Qualifier("UserService")
    @Autowired
    private UserService userService;


    //获取个人信息
    @RequestMapping(value = "/getPersonalInfo", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getPersonalInfo(@RequestHeader("accessToken") String accessToken) {
        System.out.println("**************getPersonalInfo***************");

        String identity = accessToken.substring(accessToken.indexOf("-") + 1);
        int userId = Integer.parseInt(accessToken.substring(0, accessToken.indexOf("-")));
        List<JSONObject> datas = new ArrayList<>();
        if ("customer".equals(identity)) {
            datas = userService.getPersonalInfo(userId, null);
        } else {
            datas = userService.getPersonalInfo(null, userId);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", datas);
        System.out.println(jsonObject.toJSONString());

        return jsonObject.toJSONString();
    }


    //修改信息
    @RequestMapping(value = "/doEdit", method = {RequestMethod.POST}, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String doEdit(@RequestBody Map<String, String> params, @RequestHeader("accessToken") String accessToken) {
        System.out.println("**************doEdit***************");

        String identity = accessToken.substring(accessToken.indexOf("-") + 1);
        Integer id = Integer.parseInt(params.get("id"));
        String title = params.get("title");
        String newValue = params.get("newValue");
        if ("手机号".equals(params.get("title"))) {
            if (userService.updateInfoById(id, params.get("phone"), title, identity)) {
                return "success";
            }
        } else {
            if (userService.updateInfoById(id, newValue, title, identity)) {
                return "success";
            }
        }

        return "修改失败，请重新操作";
    }

    //上传图片
    @RequestMapping(value = "upload", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile picture, HttpServletRequest request, @RequestHeader("accessToken") String accessToken) throws Exception {
        String identity = accessToken.substring(accessToken.indexOf("-") + 1);
        int userId = Integer.parseInt(accessToken.substring(0, accessToken.indexOf("-")));

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
            if (userService.updateInfoById(userId, fileName, "头像", identity)) {
                return fileName;
            }
            //将文件在服务器的存储路径返回
            return "fail";
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            return "fail";
        }
    }

    //获取管理员/用户信息
    @RequestMapping(value = "/getList", method = {RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getList(String identity, String pageNo, String pageSize) {
        System.out.println("**************getList***************");
        int n = Integer.parseInt(pageNo);
        int s = Integer.parseInt(pageSize);
        System.out.println(identity);
        Map<String, Object> allUser = userService.getAllUser(identity, s * (n - 1), s);

        System.out.println(allUser);

        return allUser;
    }

    /* //修改用户信息
     @RequestMapping(value = "/doEditAll", method = {RequestMethod.POST})
     @ResponseBody
     public String doEdit(@RequestBody Map<String, Object> params,) {
         System.out.println("**************doEdit***************");
         System.out.println(params);
         String type = (String) params.get("type");
         String title = (String) params.get("title");
         if (userService.UpdateUserInfo(type, title, params.get("info"))){
             return "success";
         }
         return "操作失败，请检查网络";
     }*/
    //修改用户信息
    @RequestMapping(value = "/doEditCus", method = {RequestMethod.POST})
    @ResponseBody
    public String doEditCus(@RequestBody Customer customer) {
        System.out.println("**************doEditCus***************");
        if (userService.UpdateUserInfo("customer", null, customer)) {
            return "success";
        }
        return "操作失败，请检查网络";
    }

    @RequestMapping(value = "/doEditMan", method = {RequestMethod.POST})
    @ResponseBody
    public String doEditMan(@RequestBody Manager manager) {
        System.out.println("**************doEditMan***************");
        if (userService.UpdateUserInfo("manager", null, manager)) {
            return "success";
        }
        return "操作失败，请检查网络";
    }

    //获取管理员最大编号
    @RequestMapping(value = "/getMaxNum", method = {RequestMethod.GET})
    @ResponseBody
    public int getMaxNum() {
        System.out.println("**************getMaxNum***************");
        int maxCount = userService.getMaxCount();

        return maxCount;
    }

    //删除用户
    @RequestMapping(value = "/doDelete", method = {RequestMethod.POST},produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String doDelete(@RequestBody Map<String, Object> params)  {
        System.out.println("**************doDelete***************");
        System.out.println(params.get("Ids"));
        System.out.println(params.get("type"));
        List<Integer> pam = new ArrayList<>();
        String ids = params.get("Ids").toString();
        if (ids.indexOf("[") == -1) {
            int i = (int) params.get("Ids");
            pam.add(i);
        } else {
            pam = (List<Integer>) params.get("Ids");
        }
        try {
            if (userService.deleteUser((String) params.get("type"), pam)) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "删除失败，请查看用户是否有未完成订单";
    }


}
