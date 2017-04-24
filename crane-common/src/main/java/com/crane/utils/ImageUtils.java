package com.crane.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crane.exception.BusinessException;

/**
* @author  Crane:
* @version 5.0
* @time 2017年4月3日 下午10:30:47
* 
*/
public class ImageUtils {
	 private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	    /**
	     * 获取图片的后缀
	     */
	    private static final String[] static_ext = {"jpg", "png", "gif", "bmp", "JPG", "PNG", "GIF", "BMP"};

	    /**
	     * 头像，和背景图片不获取
	     */
	    private static final String EMOTION = "emotion", GREY = "grey.gif";

	    private static final int UPLOAD_STR_LENGTH = 7;

	    @SuppressWarnings("unchecked")
	    public static String getImages(String content) {

	        StringBuilder sbf = new StringBuilder();
	        HtmlCleaner htmlCleaner = new HtmlCleaner();
	        TagNode allNode = htmlCleaner.clean(content);
	        List<TagNode> nodeList = (List<TagNode>) allNode.getElementListByName("img", true);
	        String image = "";
	        if (nodeList != null) {
	            for (TagNode node : nodeList) {
	                image = String.valueOf(node.getAttributeByName("src")).trim();
	                if (StringTools.isEmpty(image)) {
	                    image = String.valueOf(node.getAttributeByName("data-original")).trim();
	                }
	                if (StringTools.isEmpty(image)) {
	                    continue;
	                }
	                if (!image.contains(EMOTION) && !image.contains(GREY) && image.contains(".") && ArrayUtils.contains(static_ext, image
	                        .substring(image.lastIndexOf(".") + 1))) {
	                    sbf.append(image + "|");
	                }
	            }
	        }
	        if (sbf.length() > 0) {
	            sbf.substring(sbf.lastIndexOf("|"));
	        }
	        return sbf.toString();
	    }

	    /**
	     * geThumbnail:(创建缩略图). <br/>
	     * @param topicImage
	     * @param isFullPath
	     * @return
	     * @since JDK 1.7
	     */
	    public static String createThumbnail(String topicImage, boolean isFullPath) {
	        StringBuilder topicImageSmall = new StringBuilder();
	        if (!StringUtils.isEmpty(topicImage)) {
	            String realPath = ServerUtils.getImageFolder();
	            String[] topoicImages = topicImage.split("\\|");
	            int smallCount = topoicImages.length;// 缩略图只需要生成三张即可
	            if (smallCount > Constants.MAXTHUMBNAILCOUNT) {
	                smallCount = Constants.MAXTHUMBNAILCOUNT;
	            }
	            for (int i = 0; i < smallCount; i++) {
	                String sourcePath = null;
	                try {
	                    String img = topoicImages[i];
	                    if (isFullPath) {
	                        img = img.substring(img.indexOf("upload") + UPLOAD_STR_LENGTH);
	                    }
	                    sourcePath = realPath + img;
	                    String smallSavePath = sourcePath + "_s.jpg";
	                    String smallPath = img + "_s.jpg";
	                    BufferedImage src = ImageIO.read(new File(sourcePath));
	                    //图片宽度小于等于150不生成缩略语
	                    if (src.getWidth() <= Constants.THUMBNAILWIDTH) {
	                        topicImageSmall.append(img).append("|");
	                        continue;
	                    }
	                    BufferedImage dst = ScaleFilter.getThumbnail(src, Constants.THUMBNAILWIDTH, Constants.THUMBNAILHEIGHT);
	                    ImageIO.write(dst, "JPEG", new File(smallSavePath));
	                    topicImageSmall.append(smallPath).append("|");
	                } catch (Exception e) {
	                    logger.error("获取图片缩略图异常,图片路径:{}", sourcePath, e);
	                    continue;
	                }
	            }
	        }

	        return topicImageSmall.toString();
	    }


	    public static void copyTemp2Real(String filePath) throws BusinessException {
	        String tempFilePath = ServerUtils.getImageFolder() + Constants.PATH_TEMP_UPLOAD + filePath;
	        File file = new File(tempFilePath);
	        if (!file.exists()) {
	            throw new BusinessException("图片不存在");
	        }

	        File targetFile = new File(ServerUtils.getImageFolder() + filePath);
	        try {
	            org.apache.commons.io.FileUtils.copyFile(file, targetFile);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        file.delete();
	    }


	    public static void main(String[] args) {
	        /*
	         * BufferedImage src = null; String sourcePath = "E:/01.jpg"; String
			 * smallPath = "E:/01_s.jpg"; try { // 生成的缩略图 最宽100，最高100 int cutHeight
			 * = 100; src = ImageIO.read(new File(sourcePath)); int width =
			 * src.getWidth(); int height = src.getHeight(); if (width>height) {
			 * cutHeight = height * 100 / width;; } BufferedImage dst = new
			 * ScaleFilter(cutHeight).filter(src, null); ImageIO.write(dst, "JPEG",
			 * new File(smallPath)); } catch (IOException e) { e.printStackTrace();
			 * }
			 */
	    }
}
