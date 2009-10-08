package ktiptv;


import com.kt.sso.SSOClient;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class Constants {


    public static final String PIN_NUMBER =
        SSOClient.getInstance().getPINNumber();

    public static final String SAID = SSOClient.getInstance().getSAID();
}
