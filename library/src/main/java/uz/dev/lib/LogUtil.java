package uz.dev.lib.util;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import java.util.Map;

public class LogUtil {

    public static final String TAG = "UZDev";

    private static boolean logging = false;

    public static void setLogging(boolean logging) {
        LogUtil.logging = logging;
    }

    private static StackTraceElement[] getStackTraceElements() {
        return Thread.currentThread().getStackTrace();
    }

    private static StackTraceElement getStackTraceElement(int pos) {
        return getStackTraceElements()[4 + pos];
    }

    private static String formatPosition(StackTraceElement element) {
        String className = element.getClassName();
        String simpleClassName = className.substring(className.lastIndexOf("."));
        return "[" + simpleClassName + " " + element.getMethodName() + "] ";
    }

    public static String getPositionString() {
        return getPositionString(3);
    }

    public static String getPositionString(int pos) {
        return formatPosition(getStackTraceElement(pos));
    }

    public static String mapToString(Map<String, Object> map) {
        if (map == null) {
            return "Map is null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("===== Map =====\n");
        for (String key : map.keySet()) {
            Object val = map.get(key);
            sb.append('\n').append(key).append(" : ").append(val);
            if (val != null) {
                sb.append(" - ").append(val.getClass().getSimpleName());
            }
        }
        return sb.toString();
    }

    public static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "Bundle Is Null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("===== Bundle =====");
        for (String key : bundle.keySet()) {
            Object val = bundle.get(key);
            sb.append('\n').append(key).append(" : ").append(val);
            if (val != null) {
                sb.append(" - ").append(val.getClass().getSimpleName());
            }
        }
        return sb.toString();
    }


    public static String intentToString(Intent intent) {
        if (intent == null) {
            return "Intent Is Null";
        }
        StringBuilder sb = new StringBuilder("\n===Intent===")
                .append("\naction: ").append(intent.getAction())
                .append("\ncomponent: ").append(intent.getComponent());

        String dataString = intent.getDataString();
        if (!TextUtils.isEmpty(dataString)) {
            sb.append(" data: ").append(dataString);
        }

        return sb.append("\n============\n")
                .append("\n===Extras===\n")
                .append(bundleToString(intent.getExtras()))
                .append("\n============\n")
                .toString();
    }


	/*
     * Stack Trace
	 */

    public static void se(String title, int depth) {
        if (logging) {
            int pos = 2;
            LogUtil.e(String.format("%s (%02d) %s", title, pos, getPositionString(pos)));
            for (int i = 0; i < depth; i++) {
                pos++;
                LogUtil.w(String.format("%s (%02d) %s", title, pos, getPositionString(pos)));
            }
        }
    }

    public static void sw(String title, int depth) {
        if (logging) {
            int pos = 2;
            LogUtil.w(String.format("%s (%02d) %s", title, pos, getPositionString(pos)));
            for (int i = 0; i < depth; i++) {
                pos++;
                LogUtil.i(String.format("%s (%02d) %s", title, pos, getPositionString(pos)));
            }
        }
    }

    public static void si(String title, int depth) {
        if (logging) {
            int pos = 2;
            LogUtil.i(String.format("%s (%02d) %s", title, pos, getPositionString(pos)));
            for (int i = 0; i < depth; i++) {
                pos++;
                LogUtil.d(String.format("%s (%02d) %s", title, pos, getPositionString(pos)));
            }
        }
    }

    public static void sd(String title, int depth) {
        if (logging) {
            int pos = 2;
            LogUtil.d(String.format("%s (%02d) %s", title, pos, getPositionString(pos)));
            for (int i = 0; i < depth; i++) {
                pos++;
                LogUtil.v(String.format("%s (%02d) %s", title, pos, getPositionString(pos)));
            }
        }
    }

	/*
	 * ?몄텧 ?꾩튂留?異쒕젰
	 */

    public static void pv() {
        if (logging) {
            Log.v(TAG, getPositionString(2));
        }
    }

    public static void pd() {
        if (logging) {
            Log.d(TAG, getPositionString(2));
        }
    }

    public static void pi() {
        if (logging) {
            Log.i(TAG, getPositionString(2));
        }
    }

    public static void pw() {
        if (logging) {
            Log.w(TAG, getPositionString(2));
        }
    }

    public static void pe() {
        if (logging) {
            Log.e(TAG, getPositionString(2));
        }
    }

    public static void pe(Exception e) {
        if (logging) {
            Log.e(TAG, getPositionString(2), e);
        }
    }

    public static void pv(Object... objects) {
        if (logging) {
            Log.v(TAG, getPositionString(2) + TextUtils.join(", ", objects));
        }
    }

    public static void pd(Object... objects) {
        if (logging) {
            Log.d(TAG, getPositionString(2) + TextUtils.join(", ", objects));
        }
    }

    public static void pi(Object... objects) {
        if (logging) {
            Log.i(TAG, getPositionString(2) + TextUtils.join(", ", objects));
        }
    }

    public static void pw(Object... objects) {
        if (logging) {
            Log.w(TAG, getPositionString(2) + TextUtils.join(", ", objects));
        }
    }

    public static void pe(Object... objects) {
        if (logging) {
            Log.e(TAG, getPositionString(2) + TextUtils.join(", ", objects));
        }
    }

    public static void pe(Exception e, Object... objects) {
        if (logging) {
            Log.e(TAG, getPositionString(2) + TextUtils.join(", ", objects), e);
        }
    }

	/*
	 * ?ㅻ툕?앺듃 異쒕젰
	 */

    public static void v(Object... objects) {
        if (logging) {
            Log.v(TAG, TextUtils.join(", ", objects));
        }
    }

    public static void d(Object... objects) {
        if (logging) {
            Log.d(TAG, TextUtils.join(", ", objects));
        }
    }

    public static void i(Object... objects) {
        if (logging) {
            Log.i(TAG, TextUtils.join(", ", objects));
        }
    }

    public static void w(Object... objects) {
        if (logging) {
            Log.w(TAG, TextUtils.join(", ", objects));
        }
    }

    public static void e(Object... objects) {
        if (logging) {
            Log.e(TAG, TextUtils.join(", ", objects));
        }
    }

}

