package com.xf.oschina.dagger;

import com.xf.oschina.common.TabListFragment;
import com.xf.oschina.module.book.ui.BookFragment;
import com.xf.oschina.module.home.ui.MovieFragment;
import com.xf.oschina.module.story.ui.StoryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract BookFragment contributeBookFragment();

    @ContributesAndroidInjector
    abstract MovieFragment contributeMovieFragment();

    @ContributesAndroidInjector
    abstract StoryFragment contributeStoryFragment();

    @ContributesAndroidInjector
    abstract TabListFragment contributeTabListFragment();
}
