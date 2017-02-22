package studio.uphie.one.interfaces;

import com.loopj.android.http.RequestParams;

import studio.uphie.one.common.HttpData;
import studio.uphie.one.common.HttpError;

/**
 * Created by beforenight on 2016/3/24.
 * Email: beforenight@163.com
 */
public interface IHttp
{

    void getHttpData(String url, RequestParams param, HttpData httpData);

    void onDataOk(String url, String data);

    void onDataError(String url, HttpError error);

    void onRestoreData(String url);
}
