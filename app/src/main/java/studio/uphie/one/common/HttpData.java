package studio.uphie.one.common;

/**
 * Created by beforenight on 2016/3/23.
 * Email: beforenight@163.com
 */
public class HttpData
{
    public String result;
    public String data;

    public HttpData(String result, String data)
    {
        this.result = result == null ? "" : result;
        this.data = data == null ? "" : data;
    }
}
