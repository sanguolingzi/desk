package com.cunw.cloud.familydesk.common.model;

import lombok.Data;

import java.util.List;

@Data
public class SysApplicationCenterInfo {
    private  SysApplicationCenterVO applicationCenterVO;
    private List<String> imgUrls;
}
