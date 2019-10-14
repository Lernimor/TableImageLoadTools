package lernimor.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.recognition.software.jdeskew.ImageDeskew;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.util.ImageHelper;

public class Begin {
	public static String url = "C:/Users/junheng.lv/Documents/lernimor/myPro2/Tess4jTools/src/lernimor/main/img/TABLE_0_2_2.png";
	
	//对图片进行处理 - 提高识别度
    public static BufferedImage convertImage(BufferedImage image) throws Exception {
        //按指定宽高创建一个图像副本
        //image = ImageHelper.getSubImage(image, 0, 0, image.getWidth(), image.getHeight());
        //图像转换成灰度的简单方法 - 黑白处理
        image = ImageHelper.convertImageToGrayscale(image);
        //图像缩放 - 放大n倍图像
        image = ImageHelper.getScaledInstance(image, image.getWidth() * 10, image.getHeight() * 10);
        return image;
    }
    
    //倾斜处理
    public static BufferedImage covertImage(BufferedImage image) {
    	ImageDeskew id = new ImageDeskew(image);
    	double imageSkewAngle = id.getSkewAngle(); //获取倾斜角度
    	if ((imageSkewAngle > 0.05d || imageSkewAngle < -(0.05d))) {
    		image = ImageHelper.rotateImage(image, -imageSkewAngle); //纠偏图像
    	}
    	return image;
    }

	public static void main(String[] args) {
		File file = new File(url);
		try {
			BufferedImage image = ImageIO.read(file);
			image = convertImage(image);
			ITesseract act = new Tesseract();
			String result = act.doOCR(image);
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
