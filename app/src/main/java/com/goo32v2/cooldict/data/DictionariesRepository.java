package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.daos.DictionariesDao;
import com.goo32v2.cooldict.data.database.DatabasePersistence.DictionariesEntry;
import com.goo32v2.cooldict.models.DictionaryModel;

import rx.Observable;

/**
 * Created on 14-May-16. (c) CoolDict
 */
public class DictionariesRepository  extends AbstractRepository<DictionaryModel> {

    public DictionariesRepository(@NonNull DictionariesDao dictionariesDao){
        super(dictionariesDao);
    }

    @Override
    public Observable<DictionaryModel> getOrdered(){
        return get(null, null, DictionariesEntry.COLUMN_TITLE + " ASC");
    }

    @Override
    public Observable<DictionaryModel> getById(@NonNull String id){
        String selection = DictionariesEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { id };

        return get(selection, selectionArgs, null);
    }
}
