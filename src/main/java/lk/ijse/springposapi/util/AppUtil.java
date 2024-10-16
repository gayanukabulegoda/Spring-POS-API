package lk.ijse.springposapi.util;

import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

public class AppUtil {
    public static String toBase64(MultipartFile file) {
        try {
            byte[] fileByteCollection = file.getBytes();
            return Base64.getEncoder().encodeToString(fileByteCollection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
