package ktiptv;


import com.kt.sso.SSOClient;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public final class Constants {


    public static final SSOClient SSO_CLIENT = SSOClient.getInstance();

    public static final String PIN_NUMBER = SSO_CLIENT.getPINNumber();

    public static final String SAID = SSO_CLIENT.getSAID();
}
