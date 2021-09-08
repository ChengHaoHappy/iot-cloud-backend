package com.iot.device.model.crd.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by huqiaoqian on 2020/10/15
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceDesiredMetadata implements Serializable {
    private static final long serialVersionUID = -3742190290854105693L;

    private String type;
}
