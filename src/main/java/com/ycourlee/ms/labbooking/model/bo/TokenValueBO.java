package com.ycourlee.ms.labbooking.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

/**
 * @author yongjiang
 */
@Setter
@Getter
@ToString
public class TokenValueBO {

    private List<Integer> roleIdList;

    private Set<String> ownApiSet;
}
