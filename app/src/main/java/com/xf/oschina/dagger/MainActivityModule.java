package com.xf.oschina.dagger;

import com.xf.oschina.module.book.ui.BookDetailActivity;
import com.xf.oschina.module.book.ui.SearchActivity;
import com.xf.oschina.module.home.MainActivity;
import com.xf.oschina.module.login.activity.LoginActivity;
import com.xf.oschina.module.story.ui.EBookDetailActivity;
import com.xf.oschina.module.story.ui.EBookReadActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract BookDetailActivity contributeBookDetailActivity();

    @ContributesAndroidInjector
    abstract EBookDetailActivity contributeEBookDetailActivity();

    @ContributesAndroidInjector
    abstract EBookReadActivity contributeEBookReadActivity();

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract SearchActivity contributeSearchActivity();

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();
}
