package com.xf.oschina.dagger;

import com.xf.oschina.base.BaseViewModel;
import com.xf.oschina.module.book.module.BookDetailViewModule;
import com.xf.oschina.module.book.module.BookViewModule;
import com.xf.oschina.module.story.module.EBookReadViewModule;
import com.xf.oschina.module.story.module.EbookViewModule;
import com.xf.oschina.module.story.module.StoryViewModule;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(BookViewModule.class)
    abstract BaseViewModel bindBookViewModule(BookViewModule bookViewModule);

    @Binds
    @IntoMap
    @ViewModelKey(BookDetailViewModule.class)
    abstract BaseViewModel bindBookDetailViewModule(BookDetailViewModule bookDetailViewModule);

    @Binds
    @IntoMap
    @ViewModelKey(StoryViewModule.class)
    abstract BaseViewModel bindBookStoryViewModule(StoryViewModule storyViewModule);

    @Binds
    @IntoMap
    @ViewModelKey(EbookViewModule.class)
    abstract BaseViewModel bindEbookViewModule(EbookViewModule ebookViewModule);

    @Binds
    @IntoMap
    @ViewModelKey(EBookReadViewModule.class)
    abstract BaseViewModel bindEBookReadViewModule(EBookReadViewModule eBookReadViewModule);
//
//    @Binds
//    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}