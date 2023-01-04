package cn.edu.zjut.uhcms.utils;

 
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.axis.encoding.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
 
public class QRCodeGenerator {
	
	public static final int QR_COLOR_GREEN = 0xFF00FF00;
	public static final int QR_COLOR_YELLOW = 0xFFFFFF00;
	public static final int QR_COLOR_RED = 0xFFFF0000;
	
	private static final String QR_CODE_IMAGE_PATH = "MyQRCode.png";
	
	private static void generateQRCodeImage(String text, int width, int height, String filePath) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		
		Path path = FileSystems.getDefault().getPath(filePath);
		
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		
	}
    public static BufferedImage createImage(String content, int rgb) throws Exception {
        Hashtable hints = new Hashtable();
        int width = 400;
        int height = 400;
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, width, height, hints);
        int tempHeight = height;
        BufferedImage image = new BufferedImage(width, tempHeight,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	
                image.setRGB(x, y, bitMatrix.get(x, y) ? rgb
                        : 0xFFFFFFFF);
            }
        }
        return image;
    }

	public static void main(String[] args) throws IOException {
        BufferedImage image = null;
		try {
			image = createImage("gnls", QR_COLOR_GREEN);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", stream);
        String base64 = Base64.encode(stream.toByteArray());
        System.out.println(base64);
	}
	
 
}