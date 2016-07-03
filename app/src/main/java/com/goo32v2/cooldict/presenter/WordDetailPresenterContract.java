package com.goo32v2.cooldict.presenter;

import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.view.WordDetailViewContract;

/**
 * Created on 15-Jun-16. (c) CoolDict
 */

public interface WordDetailPresenterContract extends BasePresenter<WordDetailViewContract> {

    void actionDeleteWord(WordModel word);

    void navigateToWordManagerActivity(WordModel word);
}
