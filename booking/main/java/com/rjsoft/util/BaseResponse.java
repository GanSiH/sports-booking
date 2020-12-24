package rjsoft.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;

/**
 * Create By GSH on .
 */
@ApiModel("响应消息类")
public class BaseResponse<T> {
    @ApiModelProperty(value = "响应码", required = true)
    private String code;
    @ApiModelProperty(value = "响应消息", required = true)
    private String message;
    @ApiModelProperty("返回数据")
    private T data;
    private Map<String, T> properties = new HashMap();

    public BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static BaseResponse<Object> ofMessage(String code, String message) {
        return new BaseResponse(code, message, (Object) null);
    }

    public static BaseResponse<Object> ofSuccess(Object data) {
        return new BaseResponse(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage(), data);
    }

    public static BaseResponse<Object> ofSuccess(Object data, String message) {
        return new BaseResponse(ResponseStatusEnum.SUCCESS.getCode(), message, data);
    }

    public static BaseResponse<Object> ofStatus(ResponseStatusEnum status) {
        return new BaseResponse(status.getCode(), status.getMessage(), (Object) null);
    }

    public BaseResponse addProperties(String key, T value) {
        this.properties.put(key, value);
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public Map<String, T> getProperties() {
        return this.properties;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setProperties(final Map<String, T> properties) {
        this.properties = properties;
    }
}
