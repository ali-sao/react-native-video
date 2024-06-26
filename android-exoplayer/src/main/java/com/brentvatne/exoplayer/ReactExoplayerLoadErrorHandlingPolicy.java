package com.brentvatne.exoplayer;

import java.io.IOException;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy.LoadErrorInfo;
import com.google.android.exoplayer2.C;

public final class ReactExoplayerLoadErrorHandlingPolicy extends DefaultLoadErrorHandlingPolicy {
    private int minLoadRetryCount = 2;

    public ReactExoplayerLoadErrorHandlingPolicy(int minLoadRetryCount) {
        super(minLoadRetryCount);
        this.minLoadRetryCount = minLoadRetryCount;
    }

    @Override
    public long getRetryDelayMsFor(LoadErrorInfo loadErrorInfo) {
        if (loadErrorInfo.errorCount < this.minLoadRetryCount) {
            return Math.min((loadErrorInfo.errorCount - 1) * 1000, 5000); // Default timeout handling
        } else { 
            return C.TIME_UNSET; // Done retrying and will return the error immediately
        }
    }

    @Override
    public int getMinimumLoadableRetryCount(int dataType) {
        return 2;
    }
}