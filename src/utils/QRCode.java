package utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;

/**
 *
 * @author huanl
 */
public class QRCode {
//    private static final String qrImageOutput = "logos\\qrCode\\qr.png";

    public static void generateQRCode(String text, int width, int height, String filePath) throws Exception {
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        
        QRCodeWriter qrwitter = new QRCodeWriter();
        BitMatrix bmobj = qrwitter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        Path pobj = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bmobj, "PNG", pobj);
    }
}
