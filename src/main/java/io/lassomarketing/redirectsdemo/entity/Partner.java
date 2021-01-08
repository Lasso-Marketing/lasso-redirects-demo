package io.lassomarketing.redirectsdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This POJO imitates settings (that we might want to store in our DB)
 * for our partners we share our users with
 *
 *
 * @author David Garibov
 */
@Data
@AllArgsConstructor
public class Partner {
    private String name;
    private String endpointUrl;
    private String userIdPlaceHolder;
}
