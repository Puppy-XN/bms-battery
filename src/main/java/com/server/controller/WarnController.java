package com.server.controller;


import com.server.base.result.AjaxResult;
import com.server.enums.BatteryTypeEnum;
import com.server.model.dto.VehicleInfoDto;
import com.server.model.entity.VehicleInfo;
import com.server.service.VehicleInfoService;
import com.server.service.WarnService;
import com.server.utils.string.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 代码生成-数据源管理控制器
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/5/27 下午3:21
 */
@RestController
@Api(tags = "预警管理")
@RequestMapping("/api")
public class WarnController {

    @Autowired
    private WarnService warnService;

    @Autowired
    private VehicleInfoService vehicleInfoService;
    @ApiOperation(value = "预警接口")
    @PostMapping("/warn")
    public AjaxResult insertGenDatasource(@Validated @RequestBody List<Map<String, Object>> params) {

        if (StringUtils.isNull(params)) {
            return AjaxResult.error("参数错误，参数不能为空！");
        }
        for (Map<String, Object> param : params) {
            Integer carId = (Integer) param.get("carId");
            VehicleInfo vehicleInfo = vehicleInfoService.selectVehicleInfoByFrameNumber(Long.valueOf(carId));
            if (StringUtils.isNull(vehicleInfo)) {
                return AjaxResult.error("车架编号为：" + carId + "的车辆不存在！");
            }
        }
        return AjaxResult.success("ok", warnService.warn(params));
    }
    @ApiOperation(value = "查询车辆信息列表")
    @GetMapping("/car/list")
    public AjaxResult list() {
        return AjaxResult.success("ok", vehicleInfoService.selectVehicleInfoList(new VehicleInfo()));
    }


    /**
     * 获取车辆信息详细信息
     */
    @ApiOperation(value = "获取车辆信息详细信息")
    @GetMapping(value = "/car/find/{vid}")
    public AjaxResult getInfo(@PathVariable("vid") String vid) {
        return AjaxResult.success(vehicleInfoService.selectVehicleInfoByVid(vid));
    }

    @ApiOperation(value = "录入车辆")
    @PostMapping("/car/insert")
    public AjaxResult insertVehicleInfo(@Validated @RequestBody VehicleInfoDto vehicleInfoDto) {
        VehicleInfo vehicleInfo = new VehicleInfo();
        BeanUtils.copyProperties(vehicleInfoDto, vehicleInfo);

        if (StringUtils.isNotNull(vehicleInfoService.selectVehicleInfoByFrameNumber(vehicleInfo.getFrameNumber()))) {
            return AjaxResult.error("录入失败，车辆编号已存在");
        }

        BatteryTypeEnum batteryTypeEnum = BatteryTypeEnum.getEnumByValue(vehicleInfo.getBatteryType());
        if (StringUtils.isNull(batteryTypeEnum)) {
            return AjaxResult.error("录入失败，电池类型不合法");
        }


        if (vehicleInfoService.insertVehicleInfo(vehicleInfo)) {
            return AjaxResult.success("ok");
        }
        return AjaxResult.error("录入失败");
    }


    /**
     * 修改车辆信息
     */
    @ApiOperation(value = "修改车辆信息")
    @PutMapping("/car/update")
    public AjaxResult edit(@RequestBody VehicleInfo vehicleInfo) {

        if (vehicleInfoService.updateVehicleInfo(vehicleInfo)) {
            return AjaxResult.success("ok");
        }
        return AjaxResult.error("修改失败");
    }

    /**
     * 删除车辆信息
     */
    @ApiOperation(value = "删除车辆信息")
    @DeleteMapping("/car/delete/{vid}")
    public AjaxResult remove(@PathVariable String vid) {

        if (vehicleInfoService.deleteVehicleInfoByVid(vid)) {
            return AjaxResult.success("ok");
        }
        return AjaxResult.error("删除失败");
    }

}

