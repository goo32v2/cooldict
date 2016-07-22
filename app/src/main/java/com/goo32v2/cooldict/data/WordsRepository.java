package com.goo32v2.cooldict.data;

import android.support.annotation.NonNull;

import com.goo32v2.cooldict.data.daos.WordsDao;
import com.goo32v2.cooldict.data.database.DatabasePersistence.WordsEntry;
import com.goo32v2.cooldict.models.WordModel;

import rx.Observable;

/**
 * Created on 17-May-16. (c) CoolDict
 */

public class WordsRepository extends AbstractRepository<WordModel> {

    public WordsRepository(@NonNull WordsDao wordsDao) {
        super(wordsDao);
    }

    @Override
    public Observable<WordModel> getOrdered(){
        return get(null, null, WordsEntry.COLUMN_CREATION_DATE + " ASC");
    }

    @Override
    public Observable<WordModel> getById(@NonNull String id){
        String selection = WordsEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { id };

        return get(selection, selectionArgs, null);
    }

    public Observable<WordModel> getByDictionaryId(@NonNull String dictionaryId){
        String selection = WordsEntry.COLUMN_ENTRY_ID + " LIKE ?";
        String[] selectionArgs = { dictionaryId };

        return get(selection, selectionArgs, WordsEntry.COLUMN_CREATION_DATE + " ASC");
    }
}
