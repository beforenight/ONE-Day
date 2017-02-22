package studio.uphie.one.ui;

import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import studio.uphie.one.abs.AbsBaseFragment;

/**
 * Created by beforenight on 2016/3/23.
 * Email: beforenight@163.com
 */
public class FragmentAdapter<T extends AbsBaseFragment> extends android.support.v4.app.FragmentPagerAdapter
{

    private List<T> fragmentList;
    private boolean canLoadMore = true;

    public FragmentAdapter(FragmentManager fm, List<T> fragmentList)
    {
        super(fm);
        this.fragmentList = fragmentList == null ? new ArrayList<T>() : fragmentList;
    }

    /**
     * 增加一个Fragment
     */
    public void add(T fragment)
    {
        fragmentList.add(fragment);
        notifyDataSetChanged();
    }

    public boolean canLoadMore()
    {
        return canLoadMore;
    }

    @Override
    public int getCount()
    {
        return fragmentList.size();
    }

    @Override
    public T getItem(int position)
    {
        if (fragmentList.size() > 0 && fragmentList.size() > position)
        {
            return fragmentList.get(position);
        }
        return null;
    }

    /**
     * 删除所有的Fragment
     */
    public void removeAll()
    {
        for (AbsBaseFragment f : fragmentList)
        {
            f.onDestroyView();
            f.onDestroy();
        }
        fragmentList.clear();
        notifyDataSetChanged();
    }

    /**
     * 从Adapter中移除Fragment
     *
     * @param fragment 要移除的Fragment
     */
    public void removeFromAdapter(T fragment)
    {
        fragmentList.remove(fragment);
        fragment.onDestroy();
        notifyDataSetChanged();
        canLoadMore = false;
    }

    /**
     * 更新Fragment
     *
     * @param newFragments 新的Fragment
     */
    public void replaceAll(List<T> newFragments)
    {
        removeAll();
        fragmentList.addAll(newFragments);
        notifyDataSetChanged();
        canLoadMore = true;
    }
}
