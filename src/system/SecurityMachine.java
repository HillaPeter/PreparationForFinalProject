package system;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class SecurityMachine {
    public static final String DEFAULT_ENCODING = "UTF-8";
    BASE64Encoder enc = new BASE64Encoder();
    BASE64Decoder dec = new BASE64Decoder();

    public String encrypt(String password , String key) {
        password = xorMessage(password, key);
        String encoded = base64encode(password);
        return encoded;
    }

    public String decrypt(String encodedPassword, String key) {
        String password;
        password = base64decode(encodedPassword);
        String theRealOne = xorMessage(password, key);
        return theRealOne;
    }

    public String base64encode(String text) {
        try {
            return enc.encode(text.getBytes(DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public String base64decode(String text) {
        try {
            return new String(dec.decodeBuffer(text), DEFAULT_ENCODING);
        } catch (IOException e) {
            return null;
        }
    }

    public String xorMessage(String message, String key) {
        try {
            if (message == null || key == null) return null;

            char[] keys = key.toCharArray();
            char[] mesg = message.toCharArray();

            int ml = mesg.length;
            int kl = keys.length;
            char[] newmsg = new char[ml];

            for (int i = 0; i < ml; i++) {
                newmsg[i] = (char) (mesg[i] ^ keys[i % kl]);
            }//for i

            return new String(newmsg);
        } catch (Exception e) {
            return null;
        }
    }
}

