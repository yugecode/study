package cn.yuge.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luojiayu
 * @date 2020/9/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultTemplate<T> {

    public static final String SUCESS_CODE = "200";
    public static final String APP_EXCEPTION = "400";
    public static final String UNKNOWN_EXCEPTION = "500";
    // 执行成功，但需要展示返回信息（异常信息）
    public static final String VIEW_MESSAGE_CODE = "200400";
    private Boolean success;
    private String msg;
    private String code;
    private T data;

    public static ResultTemplate ok() {

        return ok(null);
    }

    public static <T> ResultTemplate<T> ok(T data) {
        ResultTemplate<T> response = new ResultTemplate<>();
        response.setCode(SUCESS_CODE);
        response.setMsg("OK");
        response.setSuccess(true);
        response.setData(data);
        return response;
    }
}
