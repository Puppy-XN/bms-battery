package com.server.mapper;

import com.server.model.entity.VehicleInfo;

import java.util.List;

/**
 * TODO
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午6:24
 */
public interface VehicleInfoMapper {


    /**
     * 查询车辆信息列表
     *
     * @param vehicleInfo 车辆信息
     * @return 车辆信息集合
     */
    public List<VehicleInfo> selectVehicleInfoList(VehicleInfo vehicleInfo);


    /**
     * 查询车辆信息
     *
     * @param frameNumber 车架编号
     * @return 车辆信息
     */
    public VehicleInfo selectVehicleInfoByFrameNumber(Long frameNumber);
    public VehicleInfo selectVehicleInfoByVid(String vid);

    /**
     * 新增车辆信息
     *
     * @param vehicleInfo 车辆信息
     * @return 结果
     */
    public int insertVehicleInfo(VehicleInfo vehicleInfo);

    /**
     * 修改车辆信息
     *
     * @param vehicleInfo 车辆信息
     * @return 结果
     */
    public int updateVehicleInfo(VehicleInfo vehicleInfo);

    /**
     * 删除车辆信息
     *
     * @param vid 车辆信息主键
     * @return 结果
     */
    public int deleteVehicleInfoByVid(String vid);
}
