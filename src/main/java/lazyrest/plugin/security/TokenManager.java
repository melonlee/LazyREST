package lazyrest.plugin.security;

/**
 * Created by Melon on 17/2/16.
 */
public interface TokenManager {

    String createToken(String uid);

    boolean checkToken(String token);
}
