package pers.guzx.basicauth.bean;

import lombok.Data;

@Data
public class ResourceBean {
    private String resourceId;
    private String resourceType;
    private String resourceName;

    public ResourceBean(String resourceId, String resourceName) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
    }
}
