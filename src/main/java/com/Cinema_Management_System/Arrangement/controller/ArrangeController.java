package com.Cinema_Management_System.Arrangement.controller;

import com.Cinema_Management_System.Arrangement.entity.Arrangement;
import com.Cinema_Management_System.Arrangement.service.ArrangementService;
import com.Cinema_Management_System.utils.Exception.DeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/arrangement")
@RestController
public class ArrangeController {

    @Qualifier(value = "ArrangementService")
    @Autowired
    private ArrangementService ArrangementService;
    private List<Arrangement> arrangementList;
    private Map<String, Object> returnData;

    @RequestMapping(value = "/{arrangementId}", method = RequestMethod.GET)
    public Arrangement getArrange(@PathVariable int arrangementId) {
        return ArrangementService.getArrange(arrangementId);
    }

    @RequestMapping(value = "/{arrangementPlace}/{arrangementDate}/{movieId}", method = RequestMethod.GET)
    public List<String> getDisableTime(@PathVariable int arrangementPlace, @PathVariable String arrangementDate, @PathVariable int movieId) {

        return ArrangementService.getDisableTime(arrangementPlace, arrangementDate, movieId);

    }
    //获取全部排场信息及查询排场信息
    @RequestMapping(value = "/arrangeList", method = RequestMethod.GET)
    public Map<String, Object> getArrangeList(String selectType, String permission, Integer pageNo, Integer pageSize) {
        try {
            permission = new String(permission.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {

        }
        Integer pageStart = (pageNo - 1) * pageSize;
        arrangementList = ArrangementService.getArranges(selectType, permission, pageStart, pageSize);
        int count = ArrangementService.getCount(selectType, permission);
        returnData = new HashMap<>();
        returnData.put("data", arrangementList);
        returnData.put("total", count);
        return returnData;
    }

    @RequestMapping(value = "/selectSession/{movieId}", method = RequestMethod.GET)
    public Map<String, Object> selectSession(@PathVariable int movieId) {
        return ArrangementService.getArrangeMovie(movieId);
    }

    @RequestMapping(value = "/arrangementInfo/{arrangementId}", method = RequestMethod.GET)
    public Map<String,Object> arrangementInfo(@PathVariable int arrangementId){
        return ArrangementService.getSeatInfo(arrangementId);
    }
//添加排场信息
    @RequestMapping(value = "/addArrangement", method = RequestMethod.POST)
    public String addArrangement(@RequestBody Map<String, Arrangement> arrangement) {
        try {
            ArrangementService.addArrangement(arrangement.get("arrangement"));
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }
//删除某个排场信息
    @RequestMapping(value = "/{arrangementId}", method = RequestMethod.DELETE)
    public String deleteArrangement(@PathVariable int arrangementId) {
        try {
            ArrangementService.deleteArrangement(arrangementId);
        } catch (DeleteException e) {
            return "error";
        }
        return "success";
    }
//批量删除排场信息
    @RequestMapping(value = "/batchDelete/{arrangementIds}", method = RequestMethod.DELETE)
    public String deleteMovies(@PathVariable String arrangementIds) {
        try {
            ArrangementService.deleteArrangements(arrangementIds);
        } catch (DeleteException e) {
            return "error";
        }
        return "success";
    }
//修改排场信息
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String editMovies(@RequestBody Arrangement arrangement) {
        System.out.println(arrangement);
        try {
            ArrangementService.updateArrangement(arrangement);
        }catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
