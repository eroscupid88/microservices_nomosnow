package ca.nomosnow.eventrequest.utils;
import org.springframework.util.Assert;


/**
 * Class UserContextHolder store userContext into local thread for thread safety purposes
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

    /**
     * Static Method getContext available within service return userContext
     * @return userContext
     */
    public static final UserContext getContext(){
        UserContext context = userContext.get();

        if (context == null) {
            context = createEmptyContext();
            userContext.set(context);

        }
        return userContext.get();
    }

    /**
     * Static Method setContext available within service to set context into Thread local storage
     * @param context context
     */
    public static void setContext(UserContext context) {
        Assert.notNull(context, "Only non-null UserContext instances are permitted");
        userContext.set(context);
    }

    /**
     * Static Method createEmptyContext create empty context available within service
     * @return UserContext
     */
    public static final UserContext createEmptyContext(){
        return new UserContext();
    }
}
