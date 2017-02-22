package studio.uphie.one.common;

/**
 * Created by beforenight on 2016/3/24.
 * Email: beforenight@163.com
 */
public class HttpError
{
    /**
     * 错误码
     */
    public int statusCode;
    /**
     * 原因
     */
    public String cause;
    /**
     * 返回结果
     */
    public String response;

    public HttpError()
    {

    }

    public HttpError(int status, String cause, String response)
    {
        this.statusCode = status;
        this.cause = cause;
        this.response = response;
    }
}
