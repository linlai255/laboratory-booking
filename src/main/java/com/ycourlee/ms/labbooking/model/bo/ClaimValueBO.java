package com.ycourlee.ms.labbooking.model.bo;

import lombok.*;

import java.util.List;

/**
 * @author yongjiang
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClaimValueBO {

    private Integer      userId;
    private Integer      refId;
    private List<RoleBO> roles;

    public void assertValid() {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("user id invalid.");
        }
        if (refId == null || refId <= 0) {
            throw new IllegalArgumentException("ref id invalid.");
        }
        if (roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("roles invalid.");
        }
    }
}
