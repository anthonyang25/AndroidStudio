package com.example.android.otherLang;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    /**
     * OnCompletion Listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//add a return button back to parent activity

        //create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "Yī\n" + "一", R.drawable.number_one, R.raw.num_one_chn));
        words.add(new Word("two", "Èr\n" + "二", R.drawable.number_two, R.raw.num_two_chn));
        words.add(new Word("three", "Sān\n" + "三", R.drawable.number_three, R.raw.num_three_chn));
        words.add(new Word("four", "Sì\n" + "四", R.drawable.number_four, R.raw.num_four_chn));
        words.add(new Word("five", "Wǔ\n" + "五", R.drawable.number_five, R.raw.num_five_chn));
        words.add(new Word("six", "Liù\n" + "六", R.drawable.number_six, R.raw.num_six_chn));
        words.add(new Word("seven", "Qī\n" + "七", R.drawable.number_seven, R.raw.num_seven_chn));
        words.add(new Word("eight", "Bā\n" + "八", R.drawable.number_eight, R.raw.num_eight_chn));
        words.add(new Word("nine", "Jiǔ\n" + "九", R.drawable.number_nine, R.raw.num_nine_chn));
        words.add(new Word("ten", "Shí\n" + "十", R.drawable.number_ten, R.raw.num_ten_chn));

        //find LinearLayout called root_view
//        LinearLayout rootView = (LinearLayout) findViewById(R.id.root_view);
//        for(int i=0; i<words.size();i++){
//            TextView wordView = new TextView(this); //create a new child text view in java
//            wordView.setText(words.get(i)); //set text at current index
//            rootView.addView(wordView); //add the child view to root view
//        }

        //need custom arrayAdapter to provide View obj to ListView

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers); // list of data + instruction to make list item view
        ListView listView = (ListView) findViewById(R.id.list); //reference listView in xml, listView -> 'empty container'
        listView.setAdapter(adapter);   //3.pass and display the list of 'rows' to word_list/        GridView gridview = (GridView) findViewById(R.id.grid_view);
//        gridview.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Release the media player if it currently exists because we are about to play a different sound file
                releaseMediaPlayer();

                /**Get the {@link Word} object from the words array at the given position the user clicked on**/
                Word word = words.get(position);

                /**create and setup {@link MediaPlayer} with audio res id from the current word object**/
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();

                // Setup a listener on the media player, so that we can stop and release the media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            // Set the media player back to null.
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
