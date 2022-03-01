package com.ConstructionManagement.system.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * am_tower_machine_param_kit
 * @author
 */
public class AmTowerMachineParamKit implements Serializable {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getKitName() {
        return kitName;
    }

    public void setKitName(String kitName) {
        this.kitName = kitName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getKitCount() {
        return kitCount;
    }

    public void setKitCount(Integer kitCount) {
        this.kitCount = kitCount;
    }

    @Override
    public String toString() {
        return "AmTowerMachineParamKit{" +
                "id=" + id +
                ", pid=" + pid +
                ", kitName='" + kitName + '\'' +
                ", unit='" + unit + '\'' +
                ", kitCount=" + kitCount +
                '}';
    }

    private Long id;
    private Long pid;

    private String kitName;

    private String unit;

    private Integer kitCount;

    private static final long serialVersionUID = 1L;

}