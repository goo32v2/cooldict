package com.goo32v2.cooldict.injection;

import com.goo32v2.cooldict.view.activities.DictionaryManagerActivity;
import com.goo32v2.cooldict.view.activities.WordDetailActivity;
import com.goo32v2.cooldict.view.activities.WordListActivity;
import com.goo32v2.cooldict.view.activities.WordManagerActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created on 02-Jul-16. (c) CoolDict
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    void inject(WordListActivity wordListActivity);
    void inject(WordDetailActivity wordDetailActivity);
    void inject(WordManagerActivity wordManagerActivity);
    void inject(DictionaryManagerActivity dictionaryManagerActivity);
}
