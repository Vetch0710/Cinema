package com.Cinema_Management_System.Inform.controller;


import com.Cinema_Management_System.Inform.entity.Inform;
import com.Cinema_Management_System.Inform.service.InformService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Vetch
 * @Description
 * @create 2020-10-09-10:51
 */

@Controller
public class InformController {
    @Qualifier("InformService")
    @Autowired
    private InformService infoService;

    @RequestMapping(value = "gethistoryList" ,method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> getHistoryList(int customerId) throws Exception {

        List<Inform> messages = infoService.selectAllInfo(customerId);
        int i = infoService.updateStatus(customerId);
        String s = infoService.selectCustomerAvatar(customerId);
        System.out.println("历史记录message++++++"+messages);
        Map<String,Object> result=new HashMap<>();
        result.put("history",messages);
        result.put("avatar",s);
        return result;
    }


    @RequestMapping(value = "changeStatus" ,method = {RequestMethod.GET})
    @ResponseBody
    public void changeStatus(int infoId) throws Exception {

        int i = infoService.updateStatusById(infoId);


    }
}
