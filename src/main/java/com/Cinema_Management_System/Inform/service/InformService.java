package com.Cinema_Management_System.Inform.service;

import com.Cinema_Management_System.Inform.entity.Inform;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformService {
    List<Inform> selectAllInfo(int customerId);
    List<Inform> selectInfoByType();
    int addInfo(Inform message);
    int updateStatus(int customerId);
    int updateStatusById(int infoId);
    String selectCustomerName(int customerId);
    String selectCustomerAvatar(int customerId);
}
