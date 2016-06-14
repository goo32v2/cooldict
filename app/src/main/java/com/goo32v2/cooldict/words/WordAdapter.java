package com.goo32v2.cooldict.words;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.goo32v2.cooldict.R;
import com.goo32v2.cooldict.data.models.WordModel;
import com.goo32v2.cooldict.words.interfaces.ItemListener;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 12-Jun-16. (c) CoolDict
 */

public class WordAdapter extends BaseAdapter {

    private List<WordModel> mWords;
    private ItemListener<WordModel> mItemListener;

    public WordAdapter(List<WordModel> words, ItemListener<WordModel> itemListener) {
        setList(words);
        mItemListener = itemListener;
    }

    public void replaceData(List<WordModel> words){
        setList(words);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mWords.size();
    }

    @Override
    public WordModel getItem(int position) {
        return mWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            rowView = inflater.inflate(R.layout.word_item, parent, false);
        }

        final WordModel word = getItem(position);

        TextView originalTV = (TextView) rowView.findViewById(R.id.original_word);
        TextView translatedTV = (TextView) rowView.findViewById(R.id.translated_word);

        originalTV.setText(word.getOriginalWord());
        translatedTV.setText(word.getTranslatedWord());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemListener.onClick(word);
            }
        });


        return rowView;
    }

    public void setList(List<WordModel> list) {
        this.mWords = checkNotNull(list);
    }
}
