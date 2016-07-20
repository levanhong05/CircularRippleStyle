package com.ericlee.CircularRippleStyle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    boolean hidden = true;
    LinearLayout mRevealView;
    ImageButton ib_gallery, ib_contacts, ib_location;
    ImageButton ib_video, ib_audio, ib_camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRevealView = (LinearLayout) findViewById(R.id.reveal_items);
        ib_audio = (ImageButton) findViewById(R.id.audio);
        ib_camera = (ImageButton) findViewById(R.id.camera);
        ib_contacts = (ImageButton) findViewById(R.id.contacts);
        ib_gallery = (ImageButton) findViewById(R.id.gallery);
        ib_location = (ImageButton) findViewById(R.id.location);
        ib_video = (ImageButton) findViewById(R.id.video);


        ib_audio.setOnClickListener(this);
        ib_camera.setOnClickListener(this);
        ib_contacts.setOnClickListener(this);
        ib_gallery.setOnClickListener(this);
        ib_location.setOnClickListener(this);
        ib_video.setOnClickListener(this);

        setSupportActionBar(toolbar);
        mRevealView.setVisibility(View.INVISIBLE);

    }


    // imagebutton click events

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.audio:
                Snackbar.make(v, "Audio Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden = true;
                break;
            case R.id.camera:
                Snackbar.make(v, "Camera Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden = true;
                break;

            case R.id.location:
                Snackbar.make(v, "Location Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden = true;
                break;

            case R.id.contacts:
                Snackbar.make(v, "Contacts Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden = true;
                break;

            case R.id.video:
                Snackbar.make(v, "Video Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden = true;
                break;

            case R.id.gallery:
                Snackbar.make(v, "Gallery Clicked", Snackbar.LENGTH_SHORT).show();
                mRevealView.setVisibility(View.INVISIBLE);
                hidden = true;
                break;

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        // attachment icon click event
        else if (id == R.id.action_attachment) {


            // finding X and Y co-ordinates
            int cx = (mRevealView.getLeft() + mRevealView.getRight());
            int cy = (mRevealView.getTop());

            // to find  radius when icon is tapped for showing layout
            int startradius = 0;
            int endradius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());


            // performing circular reveal when icon will be tapped
            Animator animator = ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, startradius, endradius);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(400);

            //reverse animation
            // to find radius when icon is tapped again for hiding layout


            //  starting radius will be the radius or the extent to which circular reveal animation is to be shown
            int reverse_startradius = Math.max(mRevealView.getWidth(), mRevealView.getHeight());
            //endradius will be zero
            int reverse_endradius = 0;


            // performing circular reveal for reverse animation
            Animator animate = ViewAnimationUtils.createCircularReveal(mRevealView, cx, cy, reverse_startradius, reverse_endradius);


            if (hidden) {

                // to show the layout when icon is tapped
                mRevealView.setVisibility(View.VISIBLE);
                animator.start();
                hidden = false;
            } else {

                mRevealView.setVisibility(View.VISIBLE);

                // to hide layout on animation end
                animate.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mRevealView.setVisibility(View.INVISIBLE);
                        hidden = true;

                    }
                });
                animate.start();

            }


            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
