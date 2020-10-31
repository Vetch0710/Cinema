package com.Cinema_Management_System.Inform.dao;

import com.Cinema_Management_System.Inform.entity.Inform;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformDao {
    List<Inform> selectAllInfo(@Param(value = "id")int customerId);
    List<Inform> selectInfoByType();
    List<Inform> selectInfoByCId(int id);
    int updateStatus(int customerId);
    String selectCustomerName(int customerId);
    int updateStatusById(int infoId);

    int addInfo(Inform message);
}
