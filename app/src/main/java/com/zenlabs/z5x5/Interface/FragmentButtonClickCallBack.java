package com.zenlabs.z5x5.Interface;

/**
 * Created by admin on 7/22/16.
 */
public interface FragmentButtonClickCallBack {

    enum ButtonIds{
        START_WORKOUT;
    }

    void onButtonClicked(ButtonIds button);
}
