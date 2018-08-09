package com.xf.oschina.module.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.xf.oschina.R;
import com.xf.oschina.base.BaseActivity;
import com.xf.oschina.base.BaseFragment;
import com.xf.oschina.common.CommonPagerAdapter;
import com.xf.oschina.common.TabListFragment;
import com.xf.oschina.databinding.ActivityMainBinding;
import com.xf.oschina.module.book.ui.SearchActivity;
import com.xf.oschina.module.home.ui.MovieFragment;
import com.xf.oschina.module.story.ui.StoryFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class MainActivity extends BaseActivity implements HasSupportFragmentInjector, SearchView.OnQueryTextListener {
    private ActivityMainBinding binding;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    private long tempTime = 0;
    private static final String TAG_EXIT = "exit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSwipeBackEnable(false);
        setSupportActionBar(binding.search);
        binding.rbBook.setChecked(true);
        initeListener();
        initePager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);    // 显示“开始搜索”的按钮
        searchView.setQueryRefinementEnabled(true); // 提示内容右边提供一个将提示内容放到搜索框的按钮
        //1.查找指定的MemuItem
        MenuItem menuItem = menu.findItem(R.id.action_search);
        //2.设置SearchView v7包方式
        View view = MenuItemCompat.getActionView(menuItem);
        if (view != null) {
//            searchView = (SearchView) view;
//            //4.设置SearchView 的查询回调接口
            searchView.setOnQueryTextListener(this);

            //在搜索输入框没有显示的时候 点击Action ,回调这个接口，并且显示输入框
//            searchView.setOnSearchClickListener();
            //当自动补全的内容被选中的时候回调接口
//            searchView.setOnSuggestionListener();

            //可以设置搜索的自动补全，或者实现搜索历史
//            searchView.setSuggestionsAdapter();

        }

        return true;
    }

    /**
     * 当用户在输入法中点击搜索按钮时,或者输入回车时,调用这个方法，发起实际的搜索功能
     *
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        if (TextUtils.isEmpty(query))
            showToast("请输入内容");
        showToast(query);
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        intent.putExtra("searchText", query);
        intent.putExtra("searchType", 1);
        startActivity(intent);
        return true;
    }

    /**
     * 每一次输入字符，都会调用这个方法，实现搜索的联想功能
     *
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void retry() {

    }

    private void initePager() {
        List<BaseFragment> list = new ArrayList<>();

        list.add(setupData(1));
        list.add(setupData(2));
        list.add(new MovieFragment());
        binding.viewpager.setAdapter(new CommonPagerAdapter(getSupportFragmentManager(), list));
        binding.viewpager.setOffscreenPageLimit(list.size());
    }

    private TabListFragment setupData(int type) {
        TabListFragment tabListFragment = new TabListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        tabListFragment.setArguments(bundle);
        return tabListFragment;
    }

    private void initeListener() {
        binding.rgTab.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_book:
                    binding.viewpager.setCurrentItem(0);
                    break;
                case R.id.rb_story:
                    binding.viewpager.setCurrentItem(1);
                    break;
                case R.id.rb_movie:
                    binding.viewpager.setCurrentItem(2);
                    break;
            }
        });
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.rbBook.setChecked(true);
                        break;
                    case 1:
                        binding.rbStory.setChecked(true);
                        break;
                    case 2:
                        binding.rbMovie.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - tempTime < 1000) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(TAG_EXIT, true);
                startActivity(intent);
            } else {
                showToast("再按一次退出阅读");
                tempTime = System.currentTimeMillis();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                this.finish();
            }
        }
    }
}
