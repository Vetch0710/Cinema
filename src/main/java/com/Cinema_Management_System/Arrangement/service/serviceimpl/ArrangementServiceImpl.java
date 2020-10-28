package com.Cinema_Management_System.Arrangement.service.serviceimpl;

import com.Cinema_Management_System.Arrangement.dao.ArrangementDao;
import com.Cinema_Management_System.Arrangement.entity.Arrangement;
import com.Cinema_Management_System.Arrangement.entity.UsedTime;
import com.Cinema_Management_System.Arrangement.service.ArrangementService;
import com.Cinema_Management_System.utils.Exception.DeleteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "ArrangementService")
public class ArrangementServiceImpl implements ArrangementService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ArrangementDao arrangementDao;


    @Override
    public Arrangement getArrange(int arrangementId) {
        return arrangementDao.getArrange(arrangementId);
    }

    @Override
    public List<Arrangement> getArranges(String selectType, String permission, Integer pageNo, Integer pageSize) {
        return arrangementDao.getArranges(selectType, permission, pageNo, pageSize);
    }

    @Override
    public int getCount(String selectType, String permission) {
        return arrangementDao.getCount(selectType, permission);
    }

    @Override
    public void addArrangement(Arrangement arrangement) {
        arrangementDao.addArrangement(arrangement);
    }

    @Override
    public void deleteArrangement(int arrangementId) throws DeleteException {
        int affectRows = arrangementDao.deleteArrangement(arrangementId);
        if (affectRows == 0) {
            throw new DeleteException();
        }
    }

    @Override
    @Transactional(rollbackFor = DeleteException.class)
    public void deleteArrangements(String arrangementIds) throws DeleteException {
        String[] idArray = arrangementIds.split("&");
        List<Integer> idList = new ArrayList<>();
        for (String str : idArray) {
            idList.add(Integer.parseInt(str));
        }
        int affectRows = arrangementDao.deleteArrangements(idList);
        if (affectRows != idList.size()) {
            throw new DeleteException();
        }
    }

    @Override
    public void updateArrangement(Arrangement arrangement) throws DeleteException {
        int affectRows = arrangementDao.updateArrangement(arrangement);
        if (affectRows == 0) {
            throw new DeleteException();
        }
    }

    @Override
    public List<String> getDisableTime(int arrangementPlace, String arrangementDate, int movieId) {
        String dayStart = arrangementDate + " 00:00:00";
        String dayEnd = arrangementDate + " 23:59:59";
        List<UsedTime> ulist;
        ulist = arrangementDao.getDisableTime(dayStart, dayEnd, arrangementPlace);
        List<String> disableTimes = new ArrayList<>();
        System.out.println("==========================");
        System.out.println(ulist);
        if (ulist.size() > 0) {
            Integer[][] timeInterval = new Integer[ulist.size()][2];
            int i = 0;
            for (UsedTime usedTime : ulist) {
                int movieTime = Integer.parseInt(usedTime.getMovieTime().replace("分钟", ""));
                System.out.println(movieTime);
                Date startTime = usedTime.getArrangementTime();
                Date endTime = new Date(startTime.getTime() + (movieTime * 60 * 1000));
                SimpleDateFormat forHours = new SimpleDateFormat("HH");
                SimpleDateFormat forMinutes = new SimpleDateFormat("mm");
                System.out.println("startTime" + startTime);
                System.out.println("endTime" + endTime);
                timeInterval[i][0] = Integer.parseInt(forHours.format(startTime)) * 60 + Integer.parseInt(forMinutes.format(startTime));
                timeInterval[i][1] = Integer.parseInt(forHours.format(endTime)) * 60 + Integer.parseInt(forMinutes.format(endTime));
                i++;
            }
            i = 0;
            int startTime = 0, endTime = 0;
            for (int m = 0; m < timeInterval.length; m++) {
                for (int n = 0; n < timeInterval[m].length; n++) {
                    System.out.print(timeInterval[m][n] + ",");
                }
                System.out.print("\t");
            }
            int movieTime = Integer.parseInt(arrangementDao.getMovieTime(movieId).replace("分钟", "")) + 20;
            while (true) {
                String startHours = "", startMinutes = "", endHours = "", endMinutes = "";
                if (i < timeInterval.length) {
                    if (endTime + movieTime < timeInterval[i][0]) {
                        endTime++;
                    } else {
                        if (startTime != endTime) {
                            System.out.println(timeInterval[i][0]);
                            addDisableTime(disableTimes, startTime, endTime, startHours, startMinutes, endHours, endMinutes);
                        }
                        startTime = timeInterval[i][1] + 20;
                        endTime = timeInterval[i][1] + 20;
                        i++;
                    }
                } else {
                    if (endTime + movieTime >= 1440) {
                        if (startTime != endTime) {
                            addDisableTime(disableTimes, startTime, endTime, startHours, startMinutes, endHours, endMinutes);
                        }
                        System.out.println("不添加时间" + startTime + "\t" + endTime);
                        break;
                    } else {
                        endTime++;
                    }
                }


            }
        }
        System.out.println(disableTimes);
        System.out.println("==========================");
        return disableTimes;
    }

    private void addDisableTime(List<String> disableTimes, int startTime, int endTime, String startHours, String startMinutes, String endHours, String endMinutes) {
        System.out.println("添加时间" + startTime + "\t" + endTime);
        if (startTime / 60 < 10) {
            startHours = "0" + startTime / 60;
        } else {
            startHours = startTime / 60 + "";
        }
        if (startTime % 60 < 10) {
            startMinutes = "0" + startTime % 60;
        } else {
            startMinutes = startTime % 60 + "";
        }
        if (endTime / 60 < 10) {
            endHours = "0" + endTime / 60;
        } else {
            endHours = endTime / 60 + "";
        }
        if (endTime % 60 < 10) {
            endMinutes = "0" + endTime % 60;
        } else {
            endMinutes = endTime % 60 + "";
        }
        disableTimes.add(startHours + ":" + startMinutes + ":00" + " - " + endHours + ":" + endMinutes + ":00");
    }
}