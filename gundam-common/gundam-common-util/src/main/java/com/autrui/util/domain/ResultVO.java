package com.autrui.util.domain;

import com.autrui.util.enums.SystemCode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * @author Autrui
 * @date 2023/8/7
 * @apiNote
 */
@Data
@AllArgsConstructor
public class ResultVO<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private T data;

    private String code;

    private String msg;

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> ok = new ResultVO<>();
        ok.setCode(SystemCode.SUCCESS.getCode());
        ok.setMsg(SystemCode.SUCCESS.getDesc());
        ok.setData(data);
        return ok;
    }

    public static ResultVO<Object> result(SystemCode errorCode) {
        return Objects.equals(errorCode.getCode(), SystemCode.SUCCESS.getCode()) ?
                ResultVO.success(errorCode)
                : ResultVO.error(errorCode);
    }

    public static <T> ResultVO<T> success(T data, String hintMsg) {
        ResultVO<T> ok = new ResultVO<>();
        ok.setCode(SystemCode.SUCCESS.getCode());
        ok.setMsg(hintMsg);
        ok.setData(data);
        return ok;
    }

    public static <T> ResultVO<T> success() {
        ResultVO ok = new ResultVO();
        ok.setCode(SystemCode.SUCCESS.getCode());
        ok.setMsg(SystemCode.SUCCESS.getDesc());
        ok.setData(true);
        return ok;
    }


    public static ResultVO<Boolean> success(Boolean ok, String hintMsg) {

        ResultVO<Boolean> ret = new ResultVO<>();

        if (ok) {
            ret.setCode(SystemCode.SUCCESS.getCode());
            ret.setMsg(SystemCode.SUCCESS.getDesc());
        } else {

            ret.setCode(SystemCode.BUSINESS_ERROR.getCode());
            ret.setMsg(hintMsg);
        }

        ret.setData(ok);

        return ret;
    }

    public static <T> ResultVO<T> error(SystemCode errorCode) {
        ResultVO<T> error = new ResultVO<>();
        error.setCode(errorCode.getCode());
        error.setMsg(errorCode.getDesc());
        return error;
    }

    public static <T> ResultVO<T> error(String errorCode, String errorMsg) {
        ResultVO<T> error = new ResultVO<>();
        error.setCode(errorCode);
        error.setMsg(errorMsg);
        return error;
    }

    public static <T> ResultVO<T> error(String errorMsg) {
        ResultVO<T> error = new ResultVO<T>();
        error.setCode(SystemCode.BUSINESS_ERROR.getCode());
        error.setMsg(errorMsg);
        return error;
    }

    public static <T> ResultVO<T> error(SystemCode errorCode, String... hintMsg) {

        ResultVO<T> error = new ResultVO<>();
        error.setCode(errorCode.getCode());
        error.setMsg(errorCode.getDesc());

        if (null != hintMsg) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : hintMsg) {
                    stringBuilder.append(s);
                }
                error.setMsg(MessageFormat.format(errorCode.getDesc(), stringBuilder));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return error;
    }
    public static <T> ResultVO<T> condition(boolean flag) {
        return flag ? ResultVO.success() : ResultVO.error(SystemCode.FAIL);
    }


}
