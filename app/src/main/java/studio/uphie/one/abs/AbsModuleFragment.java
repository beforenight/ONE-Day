package studio.uphie.one.abs;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.handmark.pulltorefresh.extras.viewpager.PullToRefreshViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import studio.uphie.one.R;
import studio.uphie.one.interfaces.IInit;
import studio.uphie.one.interfaces.IShare;
import studio.uphie.one.interfaces.ShareChannel;
import studio.uphie.one.ui.FragmentAdapter;
import studio.uphie.one.ui.article.Article;
import studio.uphie.one.ui.home.Home;
import studio.uphie.one.ui.question.Question;
import studio.uphie.one.ui.thing.Thing;
import studio.uphie.one.utils.TextToast;

/**
 * Created by beforenight on 2015/10/30.
 * Email: beforenight@163.com
 */
public abstract class AbsModuleFragment extends Fragment implements IInit, ViewPager.OnPageChangeListener, IShare
{

    public static FragmentAdapter adapter;
    public static AbsModuleFragment instance;
    @Bind(R.id.pager)
    public PullToRefreshViewPager pager;
    public ShareDialog shareDialog;

    public static AbsModuleFragment getInstance()
    {
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);
        shareDialog = new ShareDialog(this);
        CallbackManager callbackManager = CallbackManager.Factory.create();
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>()
        {
            @Override
            public void onCancel()
            {

            }

            @Override
            public void onError(FacebookException error)
            {
                TextToast.shortShow(getString(R.string.share_fail));
            }

            @Override
            public void onSuccess(Sharer.Result result)
            {

            }
        });
        pager.setOnPageChangeListener(this);
        //禁止刷新，避免因为刷新数据导致页面出现bug
        //        pager.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ViewPager>()
        //        {
        //            @Override
        //            public void onRefresh(PullToRefreshBase<ViewPager> refreshView)
        //            {
        //                refresh();
        //            }
        //        });
        adapter = new FragmentAdapter(getChildFragmentManager(), new ArrayList<AbsBaseFragment>());
        pager.setAdapter(adapter);
        init();
        instance = this;
        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    public abstract void refresh();

    @Override
    public void share(int channel, Object data)
    {
        if (data == null)
        {
            return;
        }
        switch (channel)
        {
            case ShareChannel.FACEBOOK:
                if (data instanceof Home)
                {
                    Home home = (Home) data;
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setImageUrl(Uri.parse(home.strThumbnailUrl))
                            .setContentDescription(home.strContent)
                            .setContentTitle("「ONE·一个」 【句子】" + home.strMarketTime)
                            .setContentUrl(Uri.parse(home.sWebLk)).build();
                    if (shareDialog.canShow(content, ShareDialog.Mode.AUTOMATIC))
                    {
                        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                    }
                }
                else if (data instanceof Article)
                {
                    Article article = (Article) data;
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentDescription(article.strContent.substring(0, 30) + "……")
                            .setContentTitle("「ONE·一个」 【文章】" + article.strContMarketTime)
                            .setContentUrl(Uri.parse(article.sWebLk)).build();
                    if (shareDialog.canShow(content, ShareDialog.Mode.AUTOMATIC))
                    {
                        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                    }
                }
                else if (data instanceof Question)
                {
                    Question question = (Question) data;
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentDescription(question.strQuestionTitle)
                            .setContentTitle("「ONE·一个」 【问题】" + question.strQuestionMarketTime)
                            .setContentUrl(Uri.parse(question.sWebLk)).build();
                    if (shareDialog.canShow(content, ShareDialog.Mode.AUTOMATIC))
                    {
                        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                    }
                }
                else
                {
                    Thing thing = (Thing) data;
                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setImageUrl(Uri.parse(thing.strBu))
                            .setContentDescription(thing.strTc)
                            .setContentTitle("「ONE·一个」 【东西】" + thing.strTm)
                            .setContentUrl(Uri.parse(thing.strWu)).build();
                    if (shareDialog.canShow(content, ShareDialog.Mode.AUTOMATIC))
                    {
                        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
                    }
                }
                break;
            case ShareChannel.GOOGLE_PLUS:
            case ShareChannel.TWITTER:
            case ShareChannel.WECHAT:
            case ShareChannel.WEIBO:
            case ShareChannel.QQ:
            case ShareChannel.QZONE:
                TextToast.longShow(getString(R.string.not_support_share));
                break;
        }
    }
}
