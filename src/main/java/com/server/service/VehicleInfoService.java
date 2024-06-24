package com.server.service;

import com.server.model.entity.VehicleInfo;

import java.util.List;

/**
 * TODO
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午6:37
 */
public interface VehicleInfoService {

    /**
     * 查询车辆信息
     *
     * @param frameNumber 车架编号
     * @return 车辆信息
     */
    public VehicleInfo selectVehicleInfoByFrameNumber(Long frameNumber);
    public VehicleInfo selectVehicleInfoByVid(String vid);

    /**
     * 查询车辆信息列表
     *
     * @param vehicleInfo 车辆信息
     * @return 车辆信息集合
     */
    public List<VehicleInfo> selectVehicleInfoList(VehicleInfo vehicleInfo);

    /**
     * 新增车辆信息
     *
     * @param vehicleInfo 车辆信息
     * @return 结果
     */
    public Boolean insertVehicleInfo(VehicleInfo vehicleInfo);

    /**
     * 修改车辆信息
     *
     * @param vehicleInfo 车辆信息
     * @return 结果
     */
    public Boolean updateVehicleInfo(VehicleInfo vehicleInfo);

    /**
     * 删除车辆信息信息
     *
     * @param vid 车辆信息主键
     * @return 结果
     */
    public Boolean deleteVehicleInfoByVid(String vid);


}
