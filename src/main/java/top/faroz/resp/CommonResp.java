package top.faroz.resp;

/**
 * @ClassName CommonResp
 * @Description 通用返回值
 * @Author FARO_Z
 * @Date 2021/5/15 下午2:17
 * @Version 1.0
 **/
public class CommonResp<T> {
    /**
     * 是否成功
     */
    private boolean success = true;

    /**
     * 若不成功，包含的错误信息
     */
    private String message="";

    /**
     * 返回的信息（任意类型）
     */
    private T content;

    public CommonResp(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }

    public CommonResp() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommonResp{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
