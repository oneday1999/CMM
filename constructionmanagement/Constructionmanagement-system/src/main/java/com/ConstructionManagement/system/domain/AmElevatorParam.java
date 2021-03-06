package com.ConstructionManagement.system.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * am_elevator_param
 * @author
 */

public class AmElevatorParam implements Serializable {
    @Excel(name = "id", isColumnHidden = true, needMerge = true)
    private Long id;

    /**
     * 厂家
     */
    @Excel(name = "厂家", width = 20, needMerge = true)
    private String vender;

    /**
     * 底笼尺寸【长、宽、高】
     */
    @Excel(name = "底笼尺寸",suffix = "米", width = 20, needMerge = true)
    private String bottomCageSize;

    /**
     * 吊笼内空尺寸【长、宽、高】
     */
    @Excel(name = "吊笼内尺寸",suffix = "米",width = 20, needMerge = true)
    private String cageSize;

    /**
     * 吊笼重量
     */
    @Excel(name = "吊笼重量", suffix="kg", width = 20, needMerge = true)
    private Double cageWeight;

    /**
     * 标准节【长、宽、高】
     */
    @Excel(name = "标准节", width = 20, needMerge = true)
    private String standardKnot;

    /**
     * 标准节重量
     */
    @Excel(name = "标准节重量",suffix="kg", width = 20, needMerge = true)
    private Double standardSectionWeight;

    /**
     * 最大高度
     */
    @Excel(name = "最大高度", suffix = "米",width = 20, needMerge = true)
    private Double maxHeight;

    /**
     * 额定载荷
     */
    @Excel(name = "额定载荷", width = 20, needMerge = true)
    private Double specifiedLoad;

    /**
     * 额定乘员
     */
    @Excel(name = "额定乘员",suffix = "人",width = 20, needMerge = true)
    private Integer ratedOccupant;

    /**
     * 起升速度
     */
    @Excel(name = "起升速度", suffix = "m/min",width = 20, needMerge = true)
    private Double upDownSpeed;

    /**
     * 总功率
     */
    @Excel(name = "总功率", suffix = "kw", width = 20, needMerge = true)
    private Double totalPower;

    /**
     * 额定电压
     */
    @Excel(name = "额定电压",suffix = "v",width = 20, needMerge = true)
    private Integer ratedVoltage;

    /**
     * 额定电流
     */
    @Excel(name = "额定电流",suffix = "A",width = 20, needMerge = true)
    private Integer ratedCurrent;

    /**
     * 减速机速比
     */
    @Excel(name = "减速机速比", width = 20, needMerge = true)
    private String reduceSpeedRatio;

    /**
     * 限速器制动力矩
     */
    @Excel(name = "限速器制动力矩",suffix="KN", width = 20, needMerge = true)
    private Double speedLimiterBrakeTorque;

    /**
     * 制动速度
     */
    @Excel(name = "制动速度",suffix = "m/min",width = 20, needMerge = true)
    private Double retroSpeed;

    /**
     * 防坠器
     */
    private Long protectorId;

    /**
     * 附着要求
     */
    @Excel(name = "附着要求", width = 20, needMerge = true)
    private String attachmentRequirements;

    /**
     * 基础要求
     */
    @Excel(name = "基础要求", width = 20, needMerge = true)
    private String basicRequirements;

    /**
     * 供电要求
     */
    @Excel(name = "供电要求", width = 20, needMerge = true)
    private String supplyPowerRequirements;

    /**
     * 备注
     */
    private String remark;

    /**
     * 插入人
     */
    private String insertPerson;

    /**
     * 插入人部门id
     */
    private Long insertPersonDepartId;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 更新人部门id
     */
    private Long updatePersonDepartId;

    /**
     * 插入时间
     */
    private Date insertDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;
    private List<AmElevatorParamKit> amElevatorParamKits;
    private List<AmElevatorParamPart> amElevatorParamParts;
    /**
     * 绑定的部件导出
     */
    @ExcelCollection(name = "部件信息")
    private List<AmTowerMachinePartExport> partsExport;

    public List<AmTowerMachinePartExport> getPartsExport() {
        return partsExport;
    }

    public void setPartsExport(List<AmTowerMachinePartExport> partsExport) {
        this.partsExport = partsExport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public String getBottomCageSize() {
        return bottomCageSize;
    }

    public void setBottomCageSize(String bottomCageSize) {
        this.bottomCageSize = bottomCageSize;
    }

    public String getCageSize() {
        return cageSize;
    }

    public void setCageSize(String cageSize) {
        this.cageSize = cageSize;
    }

    public Double getCageWeight() {
        return cageWeight;
    }

    public void setCageWeight(Double cageWeight) {
        this.cageWeight = cageWeight;
    }

    public String getStandardKnot() {
        return standardKnot;
    }

    public void setStandardKnot(String standardKnot) {
        this.standardKnot = standardKnot;
    }

    public Double getStandardSectionWeight() {
        return standardSectionWeight;
    }

    public void setStandardSectionWeight(Double standardSectionWeight) {
        this.standardSectionWeight = standardSectionWeight;
    }

    public Double getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Double getSpecifiedLoad() {
        return specifiedLoad;
    }

    public void setSpecifiedLoad(Double specifiedLoad) {
        this.specifiedLoad = specifiedLoad;
    }

    public Integer getRatedOccupant() {
        return ratedOccupant;
    }

    public void setRatedOccupant(Integer ratedOccupant) {
        this.ratedOccupant = ratedOccupant;
    }

    public Double getUpDownSpeed() {
        return upDownSpeed;
    }

    public void setUpDownSpeed(Double upDownSpeed) {
        this.upDownSpeed = upDownSpeed;
    }

    public Double getTotalPower() {
        return totalPower;
    }

    public void setTotalPower(Double totalPower) {
        this.totalPower = totalPower;
    }

    public Integer getRatedVoltage() {
        return ratedVoltage;
    }

    public void setRatedVoltage(Integer ratedVoltage) {
        this.ratedVoltage = ratedVoltage;
    }

    public Integer getRatedCurrent() {
        return ratedCurrent;
    }

    public void setRatedCurrent(Integer ratedCurrent) {
        this.ratedCurrent = ratedCurrent;
    }

    public String getReduceSpeedRatio() {
        return reduceSpeedRatio;
    }

    public void setReduceSpeedRatio(String reduceSpeedRatio) {
        this.reduceSpeedRatio = reduceSpeedRatio;
    }

    public Double getSpeedLimiterBrakeTorque() {
        return speedLimiterBrakeTorque;
    }

    public void setSpeedLimiterBrakeTorque(Double speedLimiterBrakeTorque) {
        this.speedLimiterBrakeTorque = speedLimiterBrakeTorque;
    }

    public Double getRetroSpeed() {
        return retroSpeed;
    }

    public void setRetroSpeed(Double retroSpeed) {
        this.retroSpeed = retroSpeed;
    }

    public Long getProtectorId() {
        return protectorId;
    }

    public void setProtectorId(Long protectorId) {
        this.protectorId = protectorId;
    }

    public String getAttachmentRequirements() {
        return attachmentRequirements;
    }

    public void setAttachmentRequirements(String attachmentRequirements) {
        this.attachmentRequirements = attachmentRequirements;
    }

    public String getBasicRequirements() {
        return basicRequirements;
    }

    public void setBasicRequirements(String basicRequirements) {
        this.basicRequirements = basicRequirements;
    }

    public String getSupplyPowerRequirements() {
        return supplyPowerRequirements;
    }

    public void setSupplyPowerRequirements(String supplyPowerRequirements) {
        this.supplyPowerRequirements = supplyPowerRequirements;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getInsertPerson() {
        return insertPerson;
    }

    public void setInsertPerson(String insertPerson) {
        this.insertPerson = insertPerson;
    }

    public Long getInsertPersonDepartId() {
        return insertPersonDepartId;
    }

    public void setInsertPersonDepartId(Long insertPersonDepartId) {
        this.insertPersonDepartId = insertPersonDepartId;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Long getUpdatePersonDepartId() {
        return updatePersonDepartId;
    }

    public void setUpdatePersonDepartId(Long updatePersonDepartId) {
        this.updatePersonDepartId = updatePersonDepartId;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<AmElevatorParamKit> getAmElevatorParamKits() {
        return amElevatorParamKits;
    }

    public void setAmElevatorParamKits(List<AmElevatorParamKit> amElevatorParamKits) {
        this.amElevatorParamKits = amElevatorParamKits;
    }

    public List<AmElevatorParamPart> getAmElevatorParamParts() {
        return amElevatorParamParts;
    }

    public void setAmElevatorParamParts(List<AmElevatorParamPart> amElevatorParamParts) {
        this.amElevatorParamParts = amElevatorParamParts;
    }
}
