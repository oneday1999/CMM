package com.ConstructionManagement.system.mapper;

import com.ConstructionManagement.system.domain.AmEquipmentRequirePart;
import com.ConstructionManagement.system.domain.WmEquipmentEntryPart;

import java.util.List;

public interface WmEquipmentEntryPartMapper {
    int deleteByEquipmentId(Long equimentId);
    int insertSelective(WmEquipmentEntryPart record);
    List<WmEquipmentEntryPart> selectByEquipmentId(Long equimentId);
}
