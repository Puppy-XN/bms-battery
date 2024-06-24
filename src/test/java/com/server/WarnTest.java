package com.server;

import com.alibaba.fastjson2.JSON;
import com.server.base.result.AjaxResult;
import com.server.enums.BatteryTypeEnum;
import com.server.exception.ServiceException;
import com.server.model.dto.VehicleInfoDto;
import com.server.model.entity.VehicleInfo;
import com.server.service.VehicleInfoService;
import com.server.service.WarnService;
import com.server.utils.string.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/21 下午12:54
 */
@SpringBootTest
public class WarnTest {
    @Autowired
    private WarnService warnService;

    @Autowired
    private VehicleInfoService vehicleInfoService;

    @Test
    public void insertGenDatasource() {

        List<Map<String, Object>> params = new ArrayList<>();

        Map<String, Object> carMap = new HashMap<>();
        carMap.put("carId", 1);
        carMap.put("warnId", 1);
        carMap.put("signal", "{\"Mx\":12.0,\"Mi\":0.6}");
        params.add(carMap);

        carMap = new HashMap<>();
        carMap.put("carId", 2);
        carMap.put("warnId", 2);
        carMap.put("signal", "{\"Ix\":12.0,\"Ii\":11.7}");
        params.add(carMap);

        carMap = new HashMap<>();
        carMap.put("carId", 3);
        carMap.put("signal", "{\"Mx\":11.0,\"Mi\":9.6,\"Ix\":12.0,\"Ii\":11.7}");
        params.add(carMap);

        for (Map<String, Object> param : params) {

            Integer carId = (Integer) param.get("carId");

            VehicleInfo vehicleInfo = vehicleInfoService.selectVehicleInfoByFrameNumber(Long.valueOf(carId));
            if (StringUtils.isNull(vehicleInfo)) {
                throw new ServiceException("车架编号为：" + carId + "的车辆不存在！");
            }
        }
        List<Map<String, Object>> res = warnService.warn(params);

        System.out.println("预警接口结果如下：");
        System.out.println(JSON.toJSON(res));
    }


    /**
     * 查询车辆信息列表
     */
    @Test
    public void list() {
        List<VehicleInfo> vehicleInfoList = vehicleInfoService.selectVehicleInfoList(new VehicleInfo());

        System.out.println("查询车辆信息列表结果如下：");
        System.out.println(JSON.toJSON(vehicleInfoList));
    }


    /**
     * 获取车辆信息详细信息
     */
    @Test
    public void getInfo() {

        String vid = "GHIJKL1234567890A";
        VehicleInfo vehicleInfo = vehicleInfoService.selectVehicleInfoByVid(vid);
        System.out.println("查询车辆信息列表结果如下：");
        System.out.println(JSON.toJSON(vehicleInfo));
    }

    /**
     * 新增车辆
     */
    @Test
    public void insertVehicleInfo() {
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setFrameNumber(10086L);
        vehicleInfo.setBatteryType(BatteryTypeEnum.TERNARY_BATTERY.getCode());
        vehicleInfo.setState(90L);
        vehicleInfo.setTotalMileage("100");

        if (StringUtils.isNotNull(vehicleInfoService.selectVehicleInfoByFrameNumber(vehicleInfo.getFrameNumber()))) {
            throw new ServiceException("录入失败，车辆编号已存在");
        }

        BatteryTypeEnum batteryTypeEnum = BatteryTypeEnum.getEnumByValue(vehicleInfo.getBatteryType());
        if (StringUtils.isNull(batteryTypeEnum)) {
            throw new ServiceException("录入失败，电池类型不合法");
        }


        if (vehicleInfoService.insertVehicleInfo(vehicleInfo)) {
            System.out.println("录入成功");
        } else {
            throw new ServiceException("录入失败");
        }
    }


    /**
     * 修改车辆信息
     */
    @Test
    public void edit() {

        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setVid("20240621131945A0");
        vehicleInfo.setFrameNumber(10086L);
        vehicleInfo.setBatteryType(BatteryTypeEnum.TERNARY_BATTERY.getCode());
        vehicleInfo.setState(90L);
        vehicleInfo.setTotalMileage("100");

        if (vehicleInfoService.updateVehicleInfo(vehicleInfo)) {
            System.out.println("修改成功");
        } else {
            throw new ServiceException("修改失败");
        }
    }




    /**
     * 删除车辆信息
     */
    @Test
    public void remove() {
        String vid = "20240621131945A0";
        if (vehicleInfoService.deleteVehicleInfoByVid(vid)) {
            System.out.println("删除成功");
        } else {
            throw new ServiceException("删除失败");
        }
    }

}
