package com.example.dtmovie.constant;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({ITabsString.TITLE_INFO, ITabsString.TITLE_TRAILER,
        ITabsString.TITLE_CAST, ITabsString.TITLE_PRODUCER, ITabsString.TITLE_SIMILAR})
@Retention(RetentionPolicy.SOURCE)
public @interface ITabsString {

    String TITLE_INFO = "Information";
    String TITLE_TRAILER = "Trailer";
    String TITLE_SIMILAR = "Similar";
    String TITLE_CAST = "Casts";
    String TITLE_PRODUCER = "Producer";
}
