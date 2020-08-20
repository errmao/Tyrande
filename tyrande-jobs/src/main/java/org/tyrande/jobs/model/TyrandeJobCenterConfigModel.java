package org.tyrande.jobs.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Tyrande
 */
@Data
@Component
@ConfigurationProperties(prefix = "xxl.job.admin")
public class TyrandeJobCenterConfigModel {

    private String address;
}
