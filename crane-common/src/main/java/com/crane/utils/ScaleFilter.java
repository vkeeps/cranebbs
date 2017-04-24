package com.crane.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
* @author  Crane:
* @version 5.0
* @time 2017年4月3日 下午10:32:46
* 
*/
public class ScaleFilter {

    /**
     * filter4width:(根据宽度生成图片). <br/>
     * @param src
     * @param width
     * @return
     * @since JDK 1.7
     */
    public static BufferedImage filter4width(BufferedImage src, int width) {
        int sorceW = src.getWidth();
        int sorceH = src.getHeight();
        int height = 0; // 目标文件的高度
        if (sorceW > width) // 目标文件宽度大于指定宽度
        {
            height = width * sorceH / sorceW;
        } else
        // 目标文件宽度小于指定宽度 那么缩略图大小就跟原图一样大
        {
            width = sorceW;
            height = sorceH;
        }

        BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Image scaleImage = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Graphics2D g = dst.createGraphics();
        g.drawImage(scaleImage, 0, 0, width, height, null);
        g.dispose();

        return dst;
    }

    /**
     * filter4Height:(根据高度生成图片). <br/>
     * @param src
     * @param height
     * @return
     * @since JDK 1.7
     */
    public static BufferedImage filter4Height(BufferedImage src, int height) {
        int sorceW = src.getWidth();
        int sorceH = src.getHeight();
        int width = 0; // 目标文件的宽度
        if (sorceH > height) // 目标文件高度度大于指定宽度
        {
            width = height * sorceW / sorceH;
        } else
        // 目标文件宽度小于指定宽度 那么缩略图大小就跟原图一样大
        {
            width = sorceW;
            height = sorceH;
        }

        BufferedImage dst = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Image scaleImage = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Graphics2D g = dst.createGraphics();
        g.drawImage(scaleImage, 0, 0, width, height, null);
        g.dispose();

        return dst;
    }

    /**
     * 生成缩略图
     * getThumbnail:(这里用一句话描述这个方法的作用). <br/>
     * @param src
     * @return
     * @since JDK 1.7
     */
    public static BufferedImage getThumbnail(BufferedImage src, int thumbnailWidth, int thumbnailHeight) {
        //thumbnailWidth 缩略图的宽度   thumbnailHeight 缩略图的高度
        int sorceW = src.getWidth();
        int sorceH = src.getHeight();
        int height = sorceH; // 目标文件的高度
        if (sorceW > thumbnailWidth) { // 目标文件宽度大于指定宽度
            height = thumbnailWidth * sorceH / sorceW;
        } else {// 目标文件宽度小于指定宽度 那么缩略图大小就跟原图一样大
            thumbnailWidth = sorceW;
            height = sorceH;
        }
        // 生成宽度为150的缩略图
        BufferedImage dst = new BufferedImage(thumbnailWidth, height, BufferedImage.TYPE_INT_RGB);
        Image scaleImage = src.getScaledInstance(thumbnailWidth, height, Image.SCALE_SMOOTH);
        Graphics2D g = dst.createGraphics();
        g.drawImage(scaleImage, 0, 0, thumbnailWidth, height, null);
        g.dispose();

        int resultH = dst.getHeight();
        // 高度大于100的，裁剪图片
        if (resultH > thumbnailHeight) {
            resultH = thumbnailHeight;
            dst = dst.getSubimage(0, 0, thumbnailWidth, resultH);
            return dst;
        } else if (resultH < thumbnailHeight) {// 高度小于100的，图片上下生成灰边
            // 生成一张空白图片
            BufferedImage buffimage = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_BGR);
            Graphics2D g2 = (Graphics2D) buffimage.getGraphics();
            Color color = new Color(213, 213, 213);
            g2.setBackground(color);
            g2.clearRect(0, 0, thumbnailWidth, thumbnailHeight);
            // 将图片画到空图片中
            g2.drawImage(dst, null, 0, (thumbnailHeight - resultH) / 2);
            return buffimage;
        }
        return dst;
    }

    public static void main(String[] args) {
        try {
            BufferedImage src = ImageIO.read(new File("D:\\workspace\\ulewo\\ulewo-web\\src\\main\\webapp\\upload\\avatars\\001.jpg"));

            // 裁剪图片
            //BufferedImage subimg = src.getSubimage(0, 100, 50, 50);

            File okfile = new File("D:\\workspace\\ulewo\\ulewo-web\\src\\main\\webapp\\upload\\avatars\\002.jpg");
            ImageIO.write(src, "JPEG", okfile);
            /*BufferedImage dst = getThumbnail(src, 150, 100);
			ImageIO.write(dst, "JPEG", new File(
					"D:\\workspace\\ulewo\\ulewo-web\\src\\main\\webapp\\upload\\avatars\\10603s.jpg"));*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
