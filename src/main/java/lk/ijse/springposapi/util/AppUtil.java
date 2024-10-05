package lk.ijse.springposapi.util;

import org.springframework.web.multipart.MultipartFile;

public class AppUtil {
    public static String toBase64(MultipartFile file) {
        String base64 = null;
        try {
            byte[] fileByteCollection = file.getBytes();
            base64 = java.util.Base64.getEncoder().encodeToString(fileByteCollection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }
}
