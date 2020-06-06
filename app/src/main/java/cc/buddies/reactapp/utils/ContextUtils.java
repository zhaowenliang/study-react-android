package cc.buddies.reactapp.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.Nullable;

public class ContextUtils {

    /**
     * Returns the nearest context in the chain (as defined by ContextWrapper.getBaseContext()) which
     * is an instance of the specified type, or null if one could not be found
     *
     * @param context Initial context
     * @param clazz   Class instance to look for
     * @param <T>
     * @return the first context which is an instance of the specified class, or null if none exists
     */
    @Nullable
    public static <T> T findContextOfType(@Nullable Context context, Class<? extends T> clazz) {
        while (!(clazz.isInstance(context))) {
            if (context instanceof ContextWrapper) {
                Context baseContext = ((ContextWrapper) context).getBaseContext();
                if (context == baseContext) {
                    return null;
                } else {
                    context = baseContext;
                }
            } else {
                return null;
            }
        }
        return (T) context;
    }

    /**
     * Returns the {@link Activity} given a {@link Context} or null if there is no {@link Activity},
     * taking into account the potential hierarchy of {@link ContextWrapper ContextWrappers}.
     */
    @Nullable
    public static Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    /**
     * 判断Context上下文是否存在
     *
     * @param ctx Context
     * @return boolean
     */
    public static boolean isContextExist(Context ctx) {
        Activity activity = getActivity(ctx);

        if (activity != null) {
            return !activity.isFinishing();
        } else {
            return ctx instanceof Application;
        }
    }

}
