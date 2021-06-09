package com.zenlabs.z5x5.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zenlabs.z5x5.CustomView.FontTextView;
import com.zenlabs.z5x5.Fragment.CalendarFragment;
import com.zenlabs.z5x5.Fragment.HelpFragment;
import com.zenlabs.z5x5.Fragment.MoreAppFragment;
import com.zenlabs.z5x5.Fragment.ProgressFragment;
import com.zenlabs.z5x5.Fragment.TodayWorkoutFragment;
import com.zenlabs.z5x5.Fragment.WorkoutFragment;
import com.zenlabs.z5x5.Interface.FragmentButtonClickCallBack;
import com.zenlabs.z5x5.R;
import com.zenlabs.z5x5.UIUtils.CustomTypefaceSpan;
import com.zenlabs.z5x5.Utils.Constants;

import java.util.Timer;
import java.util.TimerTask;

import at.grabner.circleprogress.CircleProgressView;
import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, FragmentButtonClickCallBack {

    private Button btnDrawerToggle, btnWorkout, btnProgress, btnCal, btnHelp, btnMore, btnCurrentTab, btnNote;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private BlurView blurBack;
    private CircleProgressView timerProgress;
    private int secondValue = 0;
    private int minValue = 0;
    private FontTextView txtRestMin, txtRestSec, btnStartNextStep;
    private Animation disappearAnimation, appearAnimation;

    private ViewGroup transitionsContainer;
    private Timer clockAnimationTimer = new Timer();
    private FragmentButtonClickCallBack callBack = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDrawerToggle = (Button) findViewById(R.id.btn_menu_toggle);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        btnWorkout = (Button) findViewById(R.id.btn_tab_workout);
        btnProgress = (Button) findViewById(R.id.btn_tab_progress);
        btnCal = (Button) findViewById(R.id.btn_tab_cal);
        btnHelp = (Button) findViewById(R.id.btn_tab_help);
        btnMore = (Button) findViewById(R.id.btn_tab_more);
        btnNote = (Button) findViewById(R.id.btn_note);
        blurBack = (BlurView) findViewById(R.id.blur_background_view);
        timerProgress = (CircleProgressView) findViewById(R.id.clock_progress_view);
        txtRestMin = (FontTextView) findViewById(R.id.text_rest_min);
        txtRestSec = (FontTextView) findViewById(R.id.text_rest_seconds);
        btnStartNextStep = (FontTextView) findViewById(R.id.btn_start_next_set);

        initNavView();
        initUI();
    }

    public void initUI() {
        Typeface typeface = Typeface.createFromAsset(getAssets(), Constants.STRATUM2_BOLD);
        btnWorkout.setTypeface(typeface);
        btnProgress.setTypeface(typeface);
        btnCal.setTypeface(typeface);
        btnHelp.setTypeface(typeface);
        btnMore.setTypeface(typeface);

        btnDrawerToggle.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        btnWorkout.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
        btnCal.setOnClickListener(this);
        btnMore.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
        btnStartNextStep.setOnClickListener(this);
        btnCurrentTab = btnWorkout;
        btnWorkout.setSelected(true);

        Fragment fragment = WorkoutFragment.newInstance(callBack);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        transitionsContainer = (ViewGroup) this.findViewById(R.id.main_container);

        showRestTimer();
    }

    public void initNavView() {
        int[][] state = new int[][] {
                new int[] {-android.R.attr.state_enabled},
                new int[] {android.R.attr.state_enabled},
                new int[] {-android.R.attr.state_checked},
                new int[] { android.R.attr.state_pressed}

        };
        int[] color = new int[] {
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE
        };
        ColorStateList csl = new ColorStateList(state, color);
        int[][] states = new int[][] {
                new int[] {-android.R.attr.state_enabled},
                new int[] {android.R.attr.state_enabled},
                new int[] {-android.R.attr.state_checked},
                new int[] { android.R.attr.state_pressed}

        };
        int[] colors = new int[] {
                Color.WHITE,
                Color.WHITE,
                Color.WHITE,
                Color.WHITE
        };
        ColorStateList csl2 = new ColorStateList(states, colors);
        navigationView.setItemTextColor(csl);
        navigationView.setItemIconTintList(csl2);

        Menu m = navigationView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);
            applyFontToMenuItem(mi);
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "Stratum2-Medium.otf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    private void showRestTimer() {
        blurBack.setVisibility(View.VISIBLE);
        final float radius = 4;
        final View decorView = getWindow().getDecorView();
        //Activity's root View. Can also be root View of your layout
        final View rootView = decorView.findViewById(android.R.id.content);
        //set background, if your root layout doesn't have one
        final Drawable windowBackground = decorView.getBackground();

        blurBack.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(this, true)) //Preferable algorithm, needs RenderScript support mode enabled
                .blurRadius(radius);

        timerProgress.setValue(secondValue);
        clockAnimationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                animateClockProgress();
            }
        }, 0, 50);

        disappearAnimation = AnimationUtils.loadAnimation(this, R.anim.disappear);
        appearAnimation = AnimationUtils.loadAnimation(this, R.anim.appear);

        disappearAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                btnStartNextStep.startAnimation(appearAnimation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        btnStartNextStep.startAnimation(disappearAnimation);
        appearAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                btnStartNextStep.startAnimation(disappearAnimation);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    private void animateClockProgress() {
        if (secondValue < 1200) {
            timerProgress.setValue(secondValue/20.0f);
            secondValue++;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (secondValue/20 == 60) {
                        txtRestSec.setText("00");
                    } else {
                        txtRestSec.setText(zeroPadding(secondValue/20));
                    }
                    txtRestMin.setText(zeroPadding(minValue));
                }
            });
        } else {
            secondValue = 0;
            minValue++;
        }
    }

    private String zeroPadding(int value) {
        if (value < 10) {
            return "0" + String.valueOf(value);
        } else {
            return String.valueOf(value);
        }
    }

    private void stopTimer() {
        clockAnimationTimer.cancel();
        secondValue = 0;
        minValue = 0;
        blurBack.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_login:
                break;
            case R.id.nav_unlock:
                break;
            case R.id.nav_settings:
                break;
            case R.id.nav_like_fb:
                try {
                    getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + Constants.kFacebookId));
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.kFacebookLink));
                }
                startActivity(intent);
                break;
            case R.id.nav_follow_on_twitter:
                try {
                    intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("twitter://user?screen_name=" + Constants.kTwitterId));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(Constants.kTwitterLink)));
                }
                break;
            case R.id.nav_follow_on_instagram:
                intent = new Intent(Intent.ACTION_VIEW);
                try {
                    if (getPackageManager().getPackageInfo("com.instagram.android", 0) != null) {
                        intent.setData(Uri.parse("http://instagram.com/_u/" + Constants.kInstagramId));
                        intent.setPackage("com.instagram.android");
                    }
                } catch (PackageManager.NameNotFoundException ignored) {
                    intent.setData(Uri.parse(Constants.kInstagramLink));
                }
                startActivity(intent);
                break;
            case R.id.nav_rate_us:
                break;
            case R.id.nav_send_feedback:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getResources().getString(R.string.email_app)});
                intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_feedback));

                PackageInfo pInfo = null;
                String versionName = "";
                int versionCode = 0;
                try {
                    pInfo = getPackageManager().getPackageInfo("com.zenlabs.sleepsounds", 0);
                    versionName = pInfo.versionName;
                    versionCode = pInfo.versionCode;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String text = getResources().getString(R.string.feedback_app_name) + "\n" + getResources().getString(R.string.app_version) + " " + versionCode + "/" + versionName + "\n" + getResources().getString(R.string.device_model) + " " + Build.MANUFACTURER.toUpperCase() + ", " + Build.MODEL + "\n" + getResources().getString(R.string.os_version) + " " + android.os.Build.VERSION.RELEASE;
                intent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(intent, getResources().getString(R.string.send_email)));
                break;
            case R.id.nav_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
//                sharingIntent.setType("message/rfc822");
//                sharingIntent.setType("text/html");
                String shareBody = getResources().getString(R.string.share_text);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.share_subject));
                sharingIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//                sharingIntent.putExtra(Intent.EXTRA_STREAM, );
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
                break;
            case R.id.nav_legal:
                break;
            case R.id.nav_restore:
                break;
            case R.id.nav_forum:
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_menu_toggle:
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START, true);
                } else {
                    drawer.openDrawer(GravityCompat.START, true);
                }
                break;
            case R.id.btn_start_next_set:
                stopTimer();
                break;
            case R.id.btn_note:
                break;

            default:
                btnCurrentTab = (Button) v;
                Button[] tabButtons = {btnWorkout, btnProgress, btnCal, btnHelp, btnMore};
                for (int i = 0; i < tabButtons.length; i++) {
                    if (tabButtons[i] == btnCurrentTab) {
                        tabButtons[i].setSelected(true);
                    } else {
                        tabButtons[i].setSelected(false);
                    }
                }

                if (btnCurrentTab == btnWorkout) {
                    Fragment fragment = WorkoutFragment.newInstance(callBack);
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                } else if (btnCurrentTab == btnProgress) {
                    Fragment fragment = ProgressFragment.newInstance();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                } else if (btnCurrentTab == btnCal) {
                    Fragment fragment = CalendarFragment.newInstance();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                } else if (btnCurrentTab == btnHelp) {
                    Fragment fragment = HelpFragment.newInstance();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                } else {
                    Fragment fragment = MoreAppFragment.newInstance();
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                }
        }
    }

    @Override
    public void onButtonClicked(ButtonIds button) {
        switch (button) {
            case START_WORKOUT:
                Fragment fragment = TodayWorkoutFragment.newInstance();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
                break;
        }
    }
}
