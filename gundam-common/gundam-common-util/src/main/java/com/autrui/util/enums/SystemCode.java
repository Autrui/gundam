package com.autrui.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Autrui
 * @date 2023/8/14
 * @apiNote
 */
@Getter
@AllArgsConstructor
public enum SystemCode {

    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    BUSINESS_ERROR(-1, "业务操作失败: ({0})"),
    ;

    private final Integer code;
    private final String msg;

}
