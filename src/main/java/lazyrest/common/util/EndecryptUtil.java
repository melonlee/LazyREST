package lazyrest.common.util;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.Key;

/**
 * Created by Melon on 17/3/22.
 */
public class EndecryptUtil {

    public static Key key = new AesCipherService().generateNewKey();

    /**
     * base64进制加密
     *
     * @param content
     * @return
     */
    public static String encrytBase64(String content) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(content), "不能为空");
        byte[] bytes = content.getBytes();
        return Base64.encodeToString(bytes);
    }

    /**
     * base64进制解密
     *
     * @param content
     * @return
     */
    public static String decryptBase64(String content) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(content), "消息摘要不能为空");
        return Base64.decodeToString(content);
    }

    /**
     * 16进制加密
     *
     * @param content
     * @return
     */
    public static String encrytHex(String content) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(content), "不能为空");
        byte[] bytes = content.getBytes();
        return Hex.encodeToString(bytes);
    }

    /**
     * 16进制解密
     *
     * @param content
     * @return
     */
    public static String decryptHex(String content) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(content), "消息摘要不能为空");
        return new String(Hex.decode(content));
    }

    public static String generateKey() {
        AesCipherService aesCipherService = new AesCipherService();
        Key key = aesCipherService.generateNewKey();
        return Base64.encodeToString(key.getEncoded());
    }

    /**
     * AES加密
     *
     * @param content
     * @return
     */

    public static String encrytAES(String content) {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        return aesCipherService.encrypt(content.getBytes(), key.getEncoded()).toHex();
    }

    /**
     * AES解密
     *
     * @param content
     * @return
     */

    public static String decryptAES(String content) {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        return new String(aesCipherService.decrypt(Hex.decode(content), key.getEncoded()).getBytes());
    }

    /**
     * MD5加密
     *
     * @param content
     * @return
     */
    public static String encrytMD5(String content) {
        String salt = RandomUtil.generateMixString(6);
        return new Md5Hash(content, salt, 4).toHex();
    }

    public static void main(String args[]) {

        String code = RandomUtil.generateNum().toString() + 1;
        String a1 = encrytBase64(code);
        System.out.println(a1);
        String a2 = decryptBase64("XMjY1ODc2OTUzMg==");
        System.out.println(a2);
    }
}
