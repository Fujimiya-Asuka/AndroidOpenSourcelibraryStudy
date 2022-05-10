package com.orhanobut.logger;

import android.os.Handler;
import android.os.HandlerThread;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.orhanobut.logger.Utils.checkNotNull;

import com.xlog.XLog;

/**
 * CSV formatted file logging for Android.
 * Writes to CSV the following data:
 * epoch timestamp, ISO8601 timestamp (human-readable), log level, tag, log message.
 */
public class CsvFormatStrategy implements FormatStrategy {

  private static final String NEW_LINE = System.getProperty("line.separator");
  private static final String NEW_LINE_REPLACEMENT = " <br> ";
  private static final String SEPARATOR = ",";

  @NonNull private final Date date;
  @NonNull private final SimpleDateFormat dateFormat;
  @NonNull private final com.orhanobut.logger.LogStrategy logStrategy;
  @Nullable private final String tag;



  private CsvFormatStrategy(@NonNull Builder builder) {
    checkNotNull(builder);

    date = builder.date;
    dateFormat = builder.dateFormat;
    logStrategy = builder.logStrategy;
    tag = builder.tag;
  }

  @NonNull public static Builder newBuilder() {
    return new Builder();
  }

  @Override public void log(int priority, @Nullable String onceOnlyTag, @NonNull String message) {
    checkNotNull(message);

    String tag = formatTag(onceOnlyTag);

    date.setTime(System.currentTimeMillis());

    StringBuilder builder = new StringBuilder();

    // machine-readable date/time
//    builder.append(Long.toString(date.getTime()));

    // human-readable date/time
//    builder.append(SEPARATOR);
    builder.append(dateFormat.format(date));

    // level
    builder.append(SEPARATOR);
    builder.append(com.orhanobut.logger.Utils.logLevel(priority));

    // tag
    builder.append(SEPARATOR);
    builder.append(tag);

    // message
    if (message.contains(NEW_LINE)) {
      // a new line would break the CSV format, so we replace it here
      message = message.replaceAll(NEW_LINE, NEW_LINE_REPLACEMENT);
    }
    builder.append(SEPARATOR);
    builder.append(message);

    // new line
    builder.append(NEW_LINE);

    logStrategy.log(priority, tag, builder.toString());
  }

  @Nullable private String formatTag(@Nullable String tag) {
    if (!com.orhanobut.logger.Utils.isEmpty(tag) && !com.orhanobut.logger.Utils.equals(this.tag, tag)) {
      return this.tag + "-" + tag;
    }
    return this.tag;
  }

  public static final class Builder {
    private static final int MAX_BYTES = XLog.Companion.getLogFileSize() * 1024;
    private String logPath=null;

    Date date;
    SimpleDateFormat dateFormat;
    com.orhanobut.logger.LogStrategy logStrategy;
    String tag = "DiskLog";

    private Builder() {
    }

    @NonNull public Builder date(@Nullable Date val) {
      date = val;
      return this;
    }

    @NonNull public Builder dateFormat(@Nullable SimpleDateFormat val) {
      dateFormat = val;
      return this;
    }

    @NonNull public Builder logStrategy(@Nullable LogStrategy val) {
      logStrategy = val;
      return this;
    }

    @NonNull public Builder tag(@Nullable String tag) {
      this.tag = tag;
      return this;
    }


    @NonNull public CsvFormatStrategy build() {
      if (date == null) {
        date = new Date();
      }
      if (dateFormat == null) {
        dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss.SSS", Locale.CHINA);
      }
      if (logStrategy == null) {
//        String diskPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String folder=null;
        if(logPath!=null){
          folder = logPath + File.separatorChar + "logger";
        }else {
          folder = XLog.Companion.getLogPath();
        }
        HandlerThread ht = new HandlerThread("AndroidFileLogger." + folder);
        ht.start();
        Handler handler = new com.orhanobut.logger.DiskLogStrategy.WriteHandler(ht.getLooper(), folder, MAX_BYTES);
        logStrategy = new DiskLogStrategy(handler);
      }
      return new CsvFormatStrategy(this);
    }

    public CsvFormatStrategy build(String path){
      logPath=path;
      return build();
    }

  }
}
