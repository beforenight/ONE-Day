package studio.uphie.one.ui.article;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import io.paperdb.Paper;
import studio.uphie.one.R;
import studio.uphie.one.abs.AbsBaseFragment;
import studio.uphie.one.common.Api;
import studio.uphie.one.common.Constants;
import studio.uphie.one.common.HttpData;
import studio.uphie.one.common.HttpError;
import studio.uphie.one.utils.JsonUtil;
import studio.uphie.one.utils.TimeUtil;
import studio.uphie.one.widgets.LikeView;

/**
 * Created by beforenight on 2016/3/23.
 * Email: beforenight@163.com
 */
public class ArticleContentFragment extends AbsBaseFragment implements LikeView.OnLikeChangedListener
{

    @Bind(R.id.text_article_date)
    TextView textArticleDate;
    @Bind(R.id.text_article_title)
    TextView text_ArticleTitle;
    @Bind(R.id.text_article_author)
    TextView text_ArticleAuthor;
    @Bind(R.id.text_article_content)
    TextView text_ArticleContent;
    @Bind(R.id.text_article_editor)
    TextView text_ArticleEditor;
    @Bind(R.id.text_article_author_addition)
    TextView text_ArticleAuthorAddition;
    @Bind(R.id.text_article_author_weibo)
    TextView text_ArticleAuthorWeibo;
    @Bind(R.id.text_article_author_intro)
    TextView text_ArticleAuthorIntro;
    @Bind(R.id.article_content)
    LinearLayout articleContent;
    @Bind(R.id.liv_article)
    LikeView lvArticle;

    private Article curArticle;

    @Override
    public int getLayoutId()
    {
        return R.layout.layout_article;
    }

    @Override
    public void init()
    {

        lvArticle.addOnLikeChangeListener(this);

        Bundle bundle = getArguments();
        String date = bundle.getString(Constants.KEY_DATE);
        index = bundle.getInt(Constants.KEY_INDEX);

        curDate = TimeUtil.getPreviousDate(date, index);

        RequestParams params = new RequestParams();
        params.put("strMS", 1);
        params.put("strDate", date);
        params.put("strRow", index);
        getHttpData(Api.URL_ARTICLE, params, new HttpData("result", "contentEntity"));
    }

    @Override
    public void onDataOk(String url, String data)
    {
        switch (url)
        {
            case Api.URL_ARTICLE:
                Article article = JsonUtil.getEntity(data, Article.class);
                //刷新显示界面
                refreshUI(article);
                //写入缓存
                if (article != null)
                {
                    Paper.book().write(Constants.TAG_ARTICLE + curDate, article);
                }
                ArticleFragment.getInstance().pager.onRefreshComplete();
                break;
            case Api.URL_LIKE_OR_CANCLELIKE:
                try
                {
                    JSONObject jsonObject = new JSONObject(data);
                    int likeCount = jsonObject.optInt("strPraisednumber");
                    //若实际的喜欢数量与LikeView自增的结果值不同，显示实际的数量
                    if (likeCount != lvArticle.getLikeCount())
                    {
                        lvArticle.setText(likeCount + "");
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onDataError(String url, HttpError error)
    {
        switch (url)
        {
            case Api.URL_ARTICLE:
                ArticleFragment.getInstance().pager.onRefreshComplete();
                //没有数据，删除并销毁自己
                finish();
                break;
        }
    }

    @Override
    public void onRestoreData(String url)
    {
        if (url.equals(Api.URL_ARTICLE))
        {
            Article article = Paper.book().read(Constants.TAG_ARTICLE + curDate, null);
            refreshUI(article);
            ArticleFragment.getInstance().pager.onRefreshComplete();
        }
    }

    @Override
    public void onLikeChanged()
    {
        RequestParams requestParams = new RequestParams();
        requestParams.put("strPraiseItemId", curArticle.strContentId);
        requestParams.put("strDeviceId", "");
        requestParams.put("strAppName", "ONE");
        requestParams.put("strPraiseItem", "CONTENT");
        getHttpData(Api.URL_LIKE_OR_CANCLELIKE, requestParams, new HttpData("result", "entPraise"));
    }


    public Article getContentData()
    {
        return curArticle;
    }

    @Override
    public void refreshUI(Object data)
    {
        Article article = (Article) data;
        if (article == null)
        {
            if (!isFirstPage())
            {
                //如果不是第一个，销毁之
                finish();
            }
            return;
        }
        if (isExpired())
        {
            finish();
        }
        curArticle = article;
        articleContent.setVisibility(View.VISIBLE);
        //上架日期
        textArticleDate.setText(TimeUtil.getEngDate(article.strContMarketTime));
        //标题
        text_ArticleTitle.setText(article.strContTitle);
        //作者
        text_ArticleAuthor.setText(article.strContAuthor);
        //文章内容，内容中有HTML标记，用于正常显示段落，需要解析
        text_ArticleContent.setText(Html.fromHtml(article.strContent));
        //责任编辑
        text_ArticleEditor.setText(article.strContAuthorIntroduce);
        //喜欢的数量
        lvArticle.setText(article.strPraiseNumber);
        //作者
        text_ArticleAuthorAddition.setText(article.strContAuthor);
        //微博名
        text_ArticleAuthorWeibo.setText(article.sWbN);
        //作者简介
        text_ArticleAuthorIntro.setText(article.sAuth);
    }

    @Override
    public void finish()
    {
        ArticleFragment.adapter.removeFromAdapter(this);
    }

    public static ArticleContentFragment newInstance(int index)
    {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_DATE, TimeUtil.getDate());
        bundle.putInt(Constants.KEY_INDEX, index);
        ArticleContentFragment fragment = new ArticleContentFragment();
        fragment.setArguments(bundle);

        return fragment;
    }
}
