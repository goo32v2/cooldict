package com.goo32v2.cooldict.data.sources.interfaces;

import com.goo32v2.cooldict.data.models.DictionaryModel;

/**
 * Created on 22-May-16. (c) CoolDict
 */

public interface DictDataSource extends DataSource<DictionaryModel> {

    void removeAll();

}
