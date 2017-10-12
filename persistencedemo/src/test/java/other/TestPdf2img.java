package other;

import java.awt.image.BufferedImage;  
import java.awt.image.RenderedImage;  
import java.io.File;  
import java.io.IOException;  
import javax.imageio.ImageIO;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;  
import org.icepdf.core.util.GraphicsRenderingHints;  
/*  
 * pdf 转 图片  
 */  
public class TestPdf2img {
    public static void pdf2Pic(String pdfPath, String path) throws PDFException, PDFSecurityException, IOException, InterruptedException{  
        Document document = new Document();  
        document.setFile(pdfPath);  
        float scale = 2.5f;//缩放比例  
        float rotation = 0f;//旋转角度  
                  
        for (int i = 0; i < document.getNumberOfPages(); i++) {  
            BufferedImage image = (BufferedImage)  
            document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);  
            RenderedImage rendImage = image;  
            try {  
                String imgName = i + ".png";  
                System.out.println(imgName);  
                File file = new File(path + imgName);  
                ImageIO.write(rendImage, "png", file);   
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            image.flush();  
        }  
        document.dispose();  
    }
    
    public static void main(String[] args) {  
        String filePath = "E:\\2017101019125731755.pdf";
        try {
            pdf2Pic(filePath, "D:\\");
        } catch (PDFException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (PDFSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
    }  
}  
