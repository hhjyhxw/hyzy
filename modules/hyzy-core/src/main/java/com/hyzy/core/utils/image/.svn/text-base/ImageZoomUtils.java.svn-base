/**
 * 
 */
package com.hyzy.core.utils.image;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;

import com.hyzy.core.bean.Setting;
import com.hyzy.core.bean.Setting.WatermarkPosition;
import com.hyzy.core.utils.CommonUtil;
import com.hyzy.core.utils.SettingUtil;
import com.sun.imageio.plugins.bmp.BMPImageReader;
import com.sun.imageio.plugins.gif.GIFImageReader;
import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import com.sun.imageio.plugins.png.PNGImageReader;

/**
 * 使用jmagic高清压缩图片
 * 使用前需要配置相应的环境
 * @author chengkunxfei
 *
 */
public class ImageZoomUtils {

	
	private static boolean flag = false;
	private static final Color WHITE = Color.white;
	private static final String SPECIFICATION_IMGDIR = "specification";
	private static final String ARTICLE_IMGDIR = "article";
	private static final String ADVERTISEMENT_IMGDIR = "advertisement";
	
	// 水印位置（无、左上、右上、居中、左下、右下）
//	public enum WatermarkPosition {
//		no, topLeft, topRight, center, bottomLeft, bottomRight
//	}
		
	private WatermarkPosition watermarkPosition;
		
		
	public WatermarkPosition getWatermarkPosition() {
			return watermarkPosition;
		}

		public void setWatermarkPosition(WatermarkPosition watermarkPosition) {
			this.watermarkPosition = watermarkPosition;
		}

	public ImageZoomUtils(){
		
	}
	
	/**
	 * 得到图片文件类型
	 * @param file
	 * @return
	 */
	public static String getTypetoFile(File file)
	{
		if (file == null || file.length() == 0L)
			return null;
		String s;
		ImageInputStream imageinputstream;
		Iterator iterator;
		try {
			s = null;
			imageinputstream = ImageIO.createImageInputStream(file);
			iterator = ImageIO.getImageReaders(imageinputstream);
			if (!iterator.hasNext())
				return null;
			ImageReader imagereader = (ImageReader)iterator.next();
			if (imagereader instanceof JPEGImageReader)
				s = "jpg";
			else
			if (imagereader instanceof GIFImageReader)
				s = "gif";
			else
			if (imagereader instanceof BMPImageReader)
				s = "bmp";
			else
			if (imagereader instanceof PNGImageReader)
				s = "png";
			imageinputstream.close();
			return s;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void replaceImageFile(File file, File file1)
	{
		if (file == null)
			return;
		try
		{
			BufferedImage bufferedimage = ImageIO.read(file);
			int i = bufferedimage.getWidth();
			int j = bufferedimage.getHeight();
			BufferedImage bufferedimage1 = new BufferedImage(i, j, 1);
			Graphics2D graphics2d = bufferedimage1.createGraphics();
			graphics2d.setBackground(WHITE);
			graphics2d.clearRect(0, 0, i, j);
			graphics2d.drawImage(bufferedimage, 0, 0, null);
			graphics2d.dispose();
			ImageIO.write(bufferedimage1, "jpg", file1);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
	}
	
	
	/**
	 * 上传图片文件到服务器
	 * @param servletcontext
	 * @param file 要保存的图片文件
	 * @return
	 */
	public static String uploadSpecificationImageFile(ServletContext servletcontext, File file)
	{
		if (file == null){
			return null;
		}
		String s = getTypetoFile(file);
		if (s == null){
			throw new IllegalArgumentException("imageFile format error!");
		}
		String uuid = CommonUtil.getUUID();
		String s1 = Setting.UPLOAD_IMAGE_DIR + SPECIFICATION_IMGDIR + "/" + uuid + ".jpg";
		File file1 = new File(servletcontext.getRealPath(s1));
		File file2 = file1.getParentFile();
		if (!file2.isDirectory())
			file2.mkdirs();
		try
		{
			FileUtils.copyFile(file, file1);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		return s1;
	}

	/**
	 * 上传文章图片文件到服务器
	 * @param servletcontext
	 * @param file 要保存的图片文件
	 * @return
	 */
	public static String uploadArticleImageFile(ServletContext servletcontext, File file)
	{
		if (file == null){
			return null;
		}
		String s = getTypetoFile(file);
		if (s == null){
			throw new IllegalArgumentException("imageFile format error!");
		}
		String uuid = CommonUtil.getUUID();
		String s1 = Setting.UPLOAD_IMAGE_DIR + ARTICLE_IMGDIR + "/" + uuid + ".jpg";
		File file1 = new File(servletcontext.getRealPath(s1));
		File file2 = file1.getParentFile();
		if (!file2.isDirectory())
			file2.mkdirs();
		try
		{
			FileUtils.copyFile(file, file1);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		return s1;
	}
	
	/**
	 * 上传广告图片文件到服务器
	 * @param servletcontext
	 * @param file 要保存的图片文件
	 * @return
	 */
	public static String uploadAdImageFile(ServletContext servletcontext, File file)
	{
		if (file == null){
			return null;
		}
		String s = getTypetoFile(file);
		if (s == null){
			throw new IllegalArgumentException("imageFile format error!");
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		String dateString = simpleDateFormat.format(new Date());
		String uuid = CommonUtil.getUUID();
		String s1 = Setting.UPLOAD_IMAGE_DIR + ADVERTISEMENT_IMGDIR + "/" +dateString+ "/" + uuid + ".jpg";
		File file1 = new File(servletcontext.getRealPath(s1));
		File file2 = file1.getParentFile();
		if (!file2.isDirectory())
			file2.mkdirs();
		try
		{
			FileUtils.copyFile(file, file1);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		return s1;
	}
	
	
	/**
	 * 上传图片文件到服务器
	 * @param servletcontext
	 * @param file 要保存的图片文件
	 * @return
	 */
	public static String uploadImageFile(ServletContext servletcontext, File file)
	{
		if (file == null){
			return null;
		}
		String s = getTypetoFile(file);
		if (s == null){
			throw new IllegalArgumentException("imageFile format error!");
		}
		String s1 = SettingUtil.getSetting().getImageUploadPath();
		File file1 = new File(servletcontext.getRealPath(s1));
		File file2 = file1.getParentFile();
		if (!file2.isDirectory())
			file2.mkdirs();
		try
		{
			FileUtils.copyFile(file, file1);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		return s1;
	}
	
	
	/**
	 * 只压缩图片
	 * @param inputFile 输入图片文件
	 * @param outFile 输出图片文件
	 * @param i 要压缩的图片宽
	 * @param j 要压缩的图片高
	 */
	public static void zoomImage(File inputFile, File outFile, int i, int j){
			zoomBufferImage(inputFile, outFile, i, j);
	}
	
	/**
	 * 压缩并打印水印
	 * @param inputFile 输入图片文件
	 * @param outFile 输出图片文件
	 * @param i 要压缩的图片宽
	 * @param j 要压缩的图片高
	 * @param watermarkImageFile 水印图片文件
	 * @param watermarkPosition 水印位置
	 * @param k
	 */
	public static void zoomImageAndWatermark(File inputFile, File outFile, int i, int j, File watermarkImageFile, WatermarkPosition watermarkPosition, int k){
			zoomAndWatermarkByBufferImage(inputFile, outFile, i, j, watermarkImageFile, watermarkPosition, k);
	}
	
	
	/**
	 * 添加水印
	 * @param inputFile 输入图片文件
	 * @param outFile 输出图片文件
	 * @param watermarkImageFile 水印图片文件
	 * @param watermarkPosition 水印位置
	 * @param i
	 */
	public static void addWatermark(File inputFile, File outFile, File watermarkImageFile, WatermarkPosition watermarkPosition, int i){
			addWatermarkByBufferImage(inputFile, outFile, watermarkImageFile, watermarkPosition, i);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * BufferImage 只添加水印
	 * @param inputFile
	 * @param outFile
	 * @param watermarkImageFile
	 * @param watermarkPosition
	 * @param i
	 */
	private static void addWatermarkByBufferImage(File inputFile, File outFile, File watermarkImageFile, WatermarkPosition watermarkPosition, int i)
    {
		try
		{
			BufferedImage bufferedimage = ImageIO.read(inputFile);
			BufferedImage bufferedimage1 = bufferImageAddWater(bufferedimage, watermarkImageFile, watermarkPosition, i);
			ImageIO.write(bufferedimage1, "jpg", outFile);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		
	}
	
	/**
	 * BufferImage 只压缩图片
	 * @param file
	 * @param file1
	 * @param i
	 * @param j
	 */
	private static void zoomBufferImage(File file, File file1, int i, int j){
		try
		{
			BufferedImage bufferedimage = ImageIO.read(file);
			
			//将图片压缩成自定义大小
			BufferedImage bufferedimage1 = zoomBufferedImage(bufferedimage, i, j);
			
			ImageIO.write(bufferedimage1, "jpg", file1);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
	}
	
	
	/**
	 * BufferImage 压缩并添加水印
	 * @param inputFile
	 * @param outFile
	 * @param i
	 * @param j
	 * @param file2
	 * @param watermarkPosition
	 * @param k
	 */
	private static void zoomAndWatermarkByBufferImage(File inputFile, File outFile, int i, int j, File file2, WatermarkPosition watermarkPosition, int k){
		try
		{
			BufferedImage bufferedimage = ImageIO.read(inputFile);
			
			//先将图片压缩成自定义大小
			BufferedImage bufferedimage1 = zoomBufferedImage(bufferedimage, i, j);
			//再将给图片添加水印
			BufferedImage bufferedimage2 = bufferImageAddWater(bufferedimage1, file2, watermarkPosition, k);
			
			ImageIO.write(bufferedimage2, "jpg", outFile);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
	}
	
	
	
	
	
	/**
	 * 处理图像
	 * @param bufferedimage 原图像
	 * @param i 目标宽度
	 * @param j 目标高度
	 * @return 处理完图像（略缩图）
	 */
	public static BufferedImage zoomBufferedImage(BufferedImage bufferedimage, int i, int j)
	{
		int k = bufferedimage.getWidth();
		int l = bufferedimage.getHeight();
		int i1 = i;
		int j1 = j;
		if (l >= k)
			i1 = (int)Math.round((((double)j * 1.0D) / (double)l) * (double)k);
		else
			j1 = (int)Math.round((((double)i * 1.0D) / (double)k) * (double)l);
		BufferedImage bufferedimage1 = new BufferedImage(i, j, 1);
		Graphics2D graphics2d = bufferedimage1.createGraphics();
		graphics2d.setBackground(WHITE);
		graphics2d.clearRect(0, 0, i, j);
		graphics2d.drawImage(bufferedimage.getScaledInstance(i1, j1, 4), i / 2 - i1 / 2, j / 2 - j1 / 2, null);
		graphics2d.dispose();
		return bufferedimage1;
	}
	
	
	
	
	
	/**
	 * 给图像添加水印
	 * @param bufferedimage 原图像
	 * @param watermarkFile 水平图片文件
	 * @param watermarkPosition 水印位置
	 * @param i
	 * @return 加水印后的图片
	 */
	private static BufferedImage bufferImageAddWater(BufferedImage bufferedimage, File watermarkFile, WatermarkPosition watermarkPosition, int i)
	{
		if (watermarkFile == null || !watermarkFile.exists() || watermarkPosition == null || watermarkPosition == WatermarkPosition.no)
			return bufferedimage;
		int j = bufferedimage.getWidth();
		int k = bufferedimage.getHeight();
		BufferedImage bufferedimage1 = new BufferedImage(j, k, 1);
		Graphics2D graphics2d = bufferedimage1.createGraphics();
		graphics2d.setBackground(WHITE);
		graphics2d.clearRect(0, 0, j, k);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.setComposite(AlphaComposite.getInstance(10, (float)i / 100F));
		BufferedImage bufferedimage2 = null;
		try
		{
			bufferedimage2 = ImageIO.read(watermarkFile);
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		int l = bufferedimage2.getWidth();
		int i1 = bufferedimage2.getHeight();
		int j1 = 0;
		int k1 = 0;
		if (watermarkPosition == WatermarkPosition.topLeft)
		{
			j1 = 0;
			k1 = 0;
		} else
		if (watermarkPosition == WatermarkPosition.topRight)
		{
			j1 = j - l;
			k1 = 0;
		} else
		if (watermarkPosition == WatermarkPosition.center)
		{
			j1 = (j - l) / 2;
			k1 = (k - i1) / 2;
		} else
		if (watermarkPosition == WatermarkPosition.bottomLeft)
		{
			j1 = 0;
			k1 = k - i1;
		} else
		if (watermarkPosition == WatermarkPosition.bottomRight)
		{
			j1 = j - l;
			k1 = k - i1;
		}
		graphics2d.drawImage(bufferedimage2, j1, k1, l, i1, null);
		graphics2d.dispose();
		return bufferedimage1;
	}
	
	
}
