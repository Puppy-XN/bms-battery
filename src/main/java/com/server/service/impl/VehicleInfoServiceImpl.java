package com.server.service.impl;

import com.server.mapper.VehicleInfoMapper;
import com.server.model.entity.VehicleInfo;
import com.server.service.VehicleInfoService;
import com.server.utils.uuid.Seq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author Like Old Dream
 * @version 1.0
 * @since 2024/6/20 下午6:37
 */
@Service
public class VehicleInfoServiceImpl implements VehicleInfoService {

    @Autowired
    private VehicleInfoMapper vehicleInfoMapper;

    @Override
    public Boolean insertVehicleInfo(VehicleInfo vehicleInfo) {

        String vid = Seq.getId().substring(0, 16);
        vehicleInfo.setVid(vid);
        return vehicleInfoMapper.insertVehicleInfo(vehicleInfo) > 0;
    }

    @Override
    public Boolean updateVehicleInfo(VehicleInfo vehicleInfo) {
        return vehicleInfoMapper.updateVehicleInfo(vehicleInfo) > 0;
    }

    @Override
    public Boolean deleteVehicleInfoByVid(String vid) {
        return vehicleInfoMapper.deleteVehicleInfoByVid(vid) > 0;
    }


    @Override
    public VehicleInfo selectVehicleInfoByFrameNumber(Long frameNumber) {
        return vehicleInfoMapper.selectVehicleInfoByFrameNumber(frameNumber);
    }

    @Override
    public VehicleInfo selectVehicleInfoByVid(String vid) {
        return vehicleInfoMapper.selectVehicleInfoByVid(vid);
    }

    @Override
    public List<VehicleInfo> selectVehicleInfoList(VehicleInfo vehicleInfo) {
        return vehicleInfoMapper.selectVehicleInfoList(vehicleInfo);
    }
}
