package com.hyzy.core.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.google.common.io.Files;
import com.hyzy.core.bean.Setting.WatermarkPosition;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**   
 * @filename ：ImageUtil.java   
 * @description : 工具类 - 图片处理
 * @version ：   V 1.0
 * @author : roni
 * @create : 2011-7-14 下午04:15:06  
 * @Copyright: hyzy Corporation 2014    
 * 
 * Modification History:
 * 	Date			Author			Version			Description
 *--------------------------------------------------------------
 *2011-7-14 下午04:15:06
*/

public class ImageUtil {

	/**
	 * 图片缩放(图片等比例缩放为指定大小，空白部分以白色填充)
	 * 
	 * @param srcBufferedImage
	 *            源图片
	 * @param destFile
	 *            缩放后的图片文件
	 */
	public static void zoom(BufferedImage srcBufferedImage, File destFile, int destHeight, int destWidth) {
		try {
			int imgWidth = destWidth;
			int imgHeight = destHeight;
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			if (srcHeight >= srcWidth) {
				imgWidth = (int) Math.round((((double)destHeight * 1.0D / (double)srcHeight) * (double)srcWidth));
			} else {
				imgHeight = (int) Math.round((((double)destWidth * 1.0D / (double)srcWidth) * (double)srcHeight));
			}
			BufferedImage destBufferedImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, destWidth, destHeight);
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), (destWidth / 2) - (imgWidth / 2), (destHeight / 2) - (imgHeight / 2), null);
			graphics2D.dispose();
			
			//设置图片质量
			FileOutputStream out = new FileOutputStream(destFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(destBufferedImage);
			param.setQuality(0.9f, true);
			encoder.encode(destBufferedImage,param);
			
//			ImageIO.write(destBufferedImage, "JPEG", destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成固定宽度的缩略图，高度等比缩放
	 * @author:roni
	 * @param srcBufferedImage
	 * @param destFile
	 * @param destWidth
	 * @param fixedWidth 是否固定宽度
	*/ 
	public static void zoom(BufferedImage srcBufferedImage, File destFile, int destWidth, int destHeight, boolean fixedWidth) {
		try {
			//如果符合缩放规格
			if(destWidth > 0 || destHeight > 0) {
				int imgWidth = destWidth;
				int imgHeight = destHeight;
				//原图宽
				int srcWidth = srcBufferedImage.getWidth();
				//原图高
				int srcHeight = srcBufferedImage.getHeight();
				
				//如果原图高大于等于原图宽，那么等比计算缩略图宽
				if (srcHeight >= srcWidth) {
					imgWidth = (int) Math.round((((double)destHeight * 1.0D / (double)srcHeight) * (double)srcWidth));
				//否则等比计算缩略图高
				} else {
					imgHeight = (int) Math.round((((double)destWidth * 1.0D / (double)srcWidth) * (double)srcHeight));
				}
				
				//如果固定宽度，重新等比计算缩略图高度
				if(fixedWidth) {
					imgWidth = destWidth;
					imgHeight = (int) Math.round((((double)destWidth * 1.0D / (double)srcWidth) * (double)srcHeight));
				}
				
				//如果等比缩放后的宽或者高大于原图宽或者高，直接拷贝
				if(imgWidth > srcWidth || imgHeight > srcHeight) {
					String fileName = destFile.getName(); 
	                String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
	                ImageIO.write(srcBufferedImage, formatName, destFile);
				} else {
					int type = srcBufferedImage.getType();
					BufferedImage target = null;
					if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			            ColorModel cm = srcBufferedImage.getColorModel();
			            WritableRaster raster = cm.createCompatibleWritableRaster(imgWidth, imgHeight);
			            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
			        } else {
			            target = new BufferedImage(imgWidth, imgHeight, type);
			        }
			
			        Graphics2D g = target.createGraphics();
			        // smoother than exlax:
			        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			        double sx = (double) imgWidth / srcWidth;
			        double sy = (double) imgHeight / srcHeight;
			        g.drawRenderedImage(srcBufferedImage, AffineTransform.getScaleInstance(sx, sy));
			        g.dispose();
			        
			        //设置图片质量
					FileOutputStream out = new FileOutputStream(destFile);
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(target);
					param.setQuality(1.0f, true);
					encoder.encode(target, param);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成固定宽度的缩略图并添加水印，高度等比缩放
	 * @author:roni
	 * @param srcBufferedImage
	 * @param destFile
	 * @param destWidth
	 * @param watermarkFile
	 * @param watermarkPosition
	 * @param alpha
	*/ 
	public static void zoomAndWatermark1(BufferedImage srcBufferedImage, File destFile, int destWidth, int destHeight, File watermarkFile, WatermarkPosition watermarkPosition, int alpha) {
		try {
			//如果符合缩放规格
			if(destWidth > 0 || destHeight > 0) {
				int imgWidth = destWidth;
				int imgHeight = destHeight;
				//原图宽
				int srcWidth = srcBufferedImage.getWidth();
				//原图高
				int srcHeight = srcBufferedImage.getHeight();
				
				//如果原图高大于等于原图宽，那么等比计算缩略图宽
				if (srcHeight >= srcWidth) {
					imgWidth = (int) Math.round((((double)destHeight * 1.0D / (double)srcHeight) * (double)srcWidth));
				//否则等比计算缩略图高
				} else {
					imgHeight = (int) Math.round((((double)destWidth * 1.0D / (double)srcWidth) * (double)srcHeight));
				}
				//如果等比缩放后的宽或者高大于原图宽或者高，直接拷贝
				if(imgWidth > srcWidth || imgHeight > srcHeight) {
					String fileName = destFile.getName(); 
	                String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
	                ImageIO.write(srcBufferedImage, formatName, destFile);
				} else {
					int type = srcBufferedImage.getType();
					BufferedImage target = null;
					if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			            ColorModel cm = srcBufferedImage.getColorModel();
			            WritableRaster raster = cm.createCompatibleWritableRaster(imgWidth, imgHeight);
			            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
			        } else {
			            target = new BufferedImage(imgWidth, imgHeight, type);
			        }
			
			        Graphics2D graphics2D = target.createGraphics();
			        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			        double sx = (double) imgWidth / srcWidth;
			        double sy = (double) imgHeight / srcHeight;
			        graphics2D.drawRenderedImage(srcBufferedImage, AffineTransform.getScaleInstance(sx, sy));
			        
					if (watermarkFile != null && watermarkFile.exists() && watermarkPosition != null && watermarkPosition != WatermarkPosition.no) {
						BufferedImage watermarkBufferedImage = ImageIO.read(watermarkFile);
						int watermarkImageWidth = watermarkBufferedImage.getWidth();
						int watermarkImageHeight = watermarkBufferedImage.getHeight();
						graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha / 100.0F));
						int x = 0;
						int y = 0;
						if (watermarkPosition == WatermarkPosition.topLeft) {
							x = 0;
							y = 0;
						} else if (watermarkPosition == WatermarkPosition.topRight) {
							x = destWidth - watermarkImageWidth;
							y = 0;
						} else if (watermarkPosition == WatermarkPosition.center) {
							x = (imgWidth - watermarkImageWidth) / 2;
							y = (imgHeight - watermarkImageHeight) / 2;
						} else if (watermarkPosition == WatermarkPosition.bottomLeft) {
							x = 0;
							y = imgHeight - watermarkImageHeight;
						} else if (watermarkPosition == WatermarkPosition.bottomRight) {
							x = imgWidth - watermarkImageWidth;
							y = imgHeight - watermarkImageHeight;
						}
						graphics2D.drawImage(watermarkBufferedImage, x, y, watermarkImageWidth, watermarkImageHeight, null);
					}
					graphics2D.dispose();
					
					//设置图片质量
					FileOutputStream out = new FileOutputStream(destFile);
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
					JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(target);
					param.setQuality(1.0f, true);
					encoder.encode(target, param);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加图片水印
	 * 
	 * @param srcBufferedImage
	 *            需要处理的源图片
	 * @param destFile
	 *            处理后的图片文件
	 * @param watermarkFile
	 *            水印图片文件
	 * 
	 */
	public static void imageWatermark(BufferedImage srcBufferedImage, File destFile, File watermarkFile, WatermarkPosition watermarkPosition, int alpha) {
		try {
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			BufferedImage destBufferedImage = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, srcWidth, srcHeight);
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(srcWidth, srcHeight, Image.SCALE_SMOOTH), 0, 0, null);

			if (watermarkFile != null && watermarkFile.exists() && watermarkPosition != null && watermarkPosition != WatermarkPosition.no) {
				BufferedImage watermarkBufferedImage = ImageIO.read(watermarkFile);
				int watermarkImageWidth = watermarkBufferedImage.getWidth();
				int watermarkImageHeight = watermarkBufferedImage.getHeight();
				graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha / 100.0F));
				int x = 0;
				int y = 0;
				if (watermarkPosition == WatermarkPosition.topLeft) {
					x = 0;
					y = 0;
				} else if (watermarkPosition == WatermarkPosition.topRight) {
					x = srcWidth - watermarkImageWidth;
					y = 0;
				} else if (watermarkPosition == WatermarkPosition.center) {
					x = (srcWidth - watermarkImageWidth) / 2;
					y = (srcHeight - watermarkImageHeight) / 2;
				} else if (watermarkPosition == WatermarkPosition.bottomLeft) {
					x = 0;
					y = srcHeight - watermarkImageHeight;
				} else if (watermarkPosition == WatermarkPosition.bottomRight) {
					x = srcWidth - watermarkImageWidth;
					y = srcHeight - watermarkImageHeight;
				}
				graphics2D.drawImage(watermarkBufferedImage, x, y, watermarkImageWidth, watermarkImageHeight, null);
			}
			graphics2D.dispose();
			
			//设置图片质量
			FileOutputStream out = new FileOutputStream(destFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(destBufferedImage);
			param.setQuality(0.9f, true);
			encoder.encode(destBufferedImage,param);
			
//			ImageIO.write(destBufferedImage, "JPEG", destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片缩放并添加图片水印(图片等比例缩放为指定大小，空白部分以白色填充)
	 * 
	 * @param srcBufferedImage
	 *            需要处理的图片
	 * @param destFile
	 *            处理后的图片文件
	 * @param watermarkFile
	 *            水印图片文件
	 * 
	 */
	public static void zoomAndWatermark(BufferedImage srcBufferedImage, File destFile, int destHeight, int destWidth, File watermarkFile, WatermarkPosition watermarkPosition, int alpha) {
		try {
			int imgWidth = destWidth;
			int imgHeight = destHeight;
			int srcWidth = srcBufferedImage.getWidth();
			int srcHeight = srcBufferedImage.getHeight();
			if (srcHeight >= srcWidth) {
				imgWidth = (int) Math.round((((double)destHeight * 1.0D / (double)srcHeight) * (double)srcWidth));
			} else {
				imgHeight = (int) Math.round((((double)destWidth * 1.0D / (double)srcWidth) * (double)srcHeight));
			}
			
			BufferedImage destBufferedImage = new BufferedImage(destWidth, destHeight, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics2D = destBufferedImage.createGraphics();
			graphics2D.setBackground(Color.WHITE);
			graphics2D.clearRect(0, 0, destWidth, destHeight);
			graphics2D.drawImage(srcBufferedImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH), (destWidth / 2) - (imgWidth / 2), (destHeight / 2) - (imgHeight / 2), null);
			if (watermarkFile != null && watermarkFile.exists() && watermarkPosition != null && watermarkPosition != WatermarkPosition.no) {
				BufferedImage watermarkBufferedImage = ImageIO.read(watermarkFile);
				int watermarkImageWidth = watermarkBufferedImage.getWidth();
				int watermarkImageHeight = watermarkBufferedImage.getHeight();
				graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha / 100.0F));
				int x = 0;
				int y = 0;
				if (watermarkPosition == WatermarkPosition.topLeft) {
					x = 0;
					y = 0;
				} else if (watermarkPosition == WatermarkPosition.topRight) {
					x = destWidth - watermarkImageWidth;
					y = 0;
				} else if (watermarkPosition == WatermarkPosition.center) {
					x = (destWidth - watermarkImageWidth) / 2;
					y = (destHeight - watermarkImageHeight) / 2;
				} else if (watermarkPosition == WatermarkPosition.bottomLeft) {
					x = 0;
					y = destHeight - watermarkImageHeight;
				} else if (watermarkPosition == WatermarkPosition.bottomRight) {
					x = destWidth - watermarkImageWidth;
					y = destHeight - watermarkImageHeight;
				}
				graphics2D.drawImage(watermarkBufferedImage, x, y, watermarkImageWidth, watermarkImageHeight, null);
			}
			graphics2D.dispose();
			
			//设置图片质量
			FileOutputStream out = new FileOutputStream(destFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(destBufferedImage);
			param.setQuality(0.9f, true);
			encoder.encode(destBufferedImage,param);
			
//			ImageIO.write(destBufferedImage, "JPEG", destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 处理图像
     * @param source 原图像
     * @param targetW 目标宽度
     * @param targetH 目标高度
     * @return 处理完图像（略缩图）
     */
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {

        // targetW，targetH分别表示目标长和宽
        int type = source.getType();
        
       /* int imgWidth = destWidth;
		int imgHeight = destHeight;
		int srcWidth = srcBufferedImage.getWidth();
		int srcHeight = srcBufferedImage.getHeight();
		if (srcHeight >= srcWidth) {
			imgWidth = (int) Math.round((((double)destHeight * 1.0D / (double)srcHeight) * (double)srcWidth));
		} else {
			imgHeight = (int) Math.round((((double)destWidth * 1.0D / (double)srcWidth) * (double)srcHeight));
		}*/
		
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        // 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
        // 则将下面的if else语句注释即可
        if (sx > sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }

        if (type == BufferedImage.TYPE_CUSTOM) { // handmade
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }

        Graphics2D g = target.createGraphics();
        // smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        g.dispose();
        return target;
    }
    
    /**   
     * 实现图像的等比缩放和缩放后的截取   
     * @param inFilePath 要截取文件的路径   
     * @param outFilePath 截取后输出的路径   
     * @param width 要截取宽度   
     * @param hight 要截取的高度   
     * @param proportion   
     * @throws Exception   
     */   
        
    public static void saveImageAsJpg(String inFilePath, String outFilePath, int width, int hight)throws Exception {
    	File file = new File(inFilePath);
        InputStream in = new FileInputStream(file);
        File saveFile = new File(outFilePath);
    
        BufferedImage srcImage = ImageIO.read(in);
        if (width > 0 || hight > 0) {
            // 原图的大小    
            int sw = srcImage.getWidth();    
            int sh = srcImage.getHeight();    
            // 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去    
            if (sw > width && sh > hight) {
                srcImage = resize(srcImage, width, hight);    
            } else {
                String fileName = saveFile.getName();    
                String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);    
                ImageIO.write(srcImage, formatName, saveFile);    
                return;    
            }    
        }
        // 缩放后的图像的宽和高    
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();
        // 如果缩放后的图像和要求的图像宽度一样，就对缩放的图像的高度进行截取    
        if (w == width) {    
            // 计算X轴坐标    
            int x = 0;    
            int y = h / 2 - hight / 2;
            saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);    
        }    
        // 否则如果是缩放后的图像的高度和要求的图像高度一样，就对缩放后的图像的宽度进行截取    
        else if (h == hight) {    
            // 计算X轴坐标    
            int x = w / 2 - width / 2;
            int y = 0;    
            saveSubImage(srcImage, new Rectangle(x, y, width, hight), saveFile);    
        }    
        in.close();    
    }
    
    /**   
     * 实现缩放后的截图   
     * @param image 缩放后的图像   
     * @param subImageBounds 要截取的子图的范围   
     * @param subImageFile 要保存的文件   
     * @throws IOException   
     */   
    private static void saveSubImage(BufferedImage image, Rectangle subImageBounds, File subImageFile) throws IOException {    
         if (subImageBounds.x < 0 || subImageBounds.y < 0 
        		 || subImageBounds.width - subImageBounds.x > image.getWidth() 
        		 || subImageBounds.height - subImageBounds.y > image.getHeight()) {
             System.out.println("Bad subimage bounds");
             return;    
         }    
         BufferedImage subImage = image.getSubimage(subImageBounds.x, subImageBounds.y, subImageBounds.width, subImageBounds.height);    
         String fileName = subImageFile.getName();
         String formatName = fileName.substring(fileName.lastIndexOf('.') + 1);
         ImageIO.write(subImage, formatName, subImageFile);    
     }

    /**
     * 左右连接2张图片
     **/
    public static BufferedImage leftJoin(BufferedImage ImageOne, BufferedImage ImageTwo) {
        int width1, width2, height1, height2, height;
        try {
            width1 = ImageOne.getWidth();// 图片宽度
            height1 = ImageOne.getHeight();// 图片高度
            width2 = ImageTwo.getWidth();// 图片宽度
            height2 = ImageTwo.getHeight();// 图片高度
            if (height1 > height2)
                height = height1;
            else
                height = height2;
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[width1 * height1];
            ImageArrayOne = ImageOne.getRGB(0, 0, width1, height1, ImageArrayOne, 0, width1);

            int[] ImageArrayTwo = new int[width2 * height2];
            ImageArrayTwo = ImageTwo.getRGB(0, 0, width2, height2, ImageArrayTwo, 0, width2);

            // 生成新图片
            BufferedImage ImageNew = new BufferedImage(width1 + width2, height, BufferedImage.TYPE_INT_RGB);
            ImageNew.setRGB(0, 0, width1, height1, ImageArrayOne, 0, width1);// 设置左半部分的RGB
            ImageNew.setRGB(width1, 0, width2, height, ImageArrayTwo, 0, width2);// 设置右半部分的RGB
            return ImageNew;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上下连接2张图片
     * 
     * **/
    public static BufferedImage topJoin(BufferedImage ImageOne,
            BufferedImage ImageTwo) {
        int width1, width2, height1, height2, width;
        try {
            width1 = ImageOne.getWidth();// 图片宽度
            height1 = ImageOne.getHeight();// 图片高度
            width2 = ImageTwo.getWidth();// 图片宽度
            height2 = ImageTwo.getHeight();// 图片高度
            if (width1 > width2)
                width = width1;
            else
                width = width2;
            // 从图片中读取RGB
            int[] ImageArrayOne = new int[width1 * height1];
            ImageArrayOne = ImageOne.getRGB(0, 0, width1, height1,
                    ImageArrayOne, 0, width1);

            int[] ImageArrayTwo = new int[width2 * height2];
            ImageArrayTwo = ImageTwo.getRGB(0, 0, width2, height2,
                    ImageArrayTwo, 0, width2);

            // 生成新图片
            BufferedImage ImageNew = new BufferedImage(width,
                    height1 + height2, BufferedImage.TYPE_INT_RGB);
            ImageNew.setRGB(0, 0, width1, height1, ImageArrayOne, 0, width1);// 设置上半部分的RGB
            ImageNew.setRGB(0, height1, width2, height2, ImageArrayTwo, 0,
                    width);// 设置下半部分的RGB
            return ImageNew;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 图片切割
     * @param fromImage 图片
     * @param startX 开始x结点（left）
     * @param startY 开始y结点（top）
     * @param w 切割宽度
     * @param h 切割高度
     */
    public static BufferedImage cut(BufferedImage fromImage, int startX, int startY, int w, int h) {
        try {
            Image img;
            ImageFilter cropFilter;
            // 读取源图像
            int height = fromImage.getHeight();
            int width = fromImage.getWidth();
            if (width >= w && height >= h) {
                Image image = fromImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
                // 剪切起始坐标点
                int x = startX;
                int y = startY;
                int destWidth = w; // 切片宽度
                int destHeight = h; // 切片高度
                // 图片比例
                double pw = width;
                double ph = height;
                double m = (double) width / pw;
                double n = (double) height / ph;
                int wth = (int) (destWidth * m);
                int hth = (int) (destHeight * n);
                int xx = (int) (x * m);
                int yy = (int) (y * n);
                // 四个参数分别为图像起点坐标和宽高
                // 即: CropImageFilter(int x,int y,int width,int height)
                cropFilter = new CropImageFilter(xx, yy, wth, hth);
                img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = tag.getGraphics();
                g.drawImage(img, 0, 0, null); // 绘制缩小后的图
                g.dispose();
                return tag;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**图片上加文字**/
    public static void image_text(BufferedImage tag,String text,Font font, int x, int y) {
        Graphics g = tag.getGraphics();
        // g.setColor(Color.BLACK); //以下设置前景色
        g.setXORMode(Color.GREEN);
        g.setFont(font);//new Font("宋体", Font.ITALIC, 24));
        g.drawString(text, x, y);
        g.dispose();
    }
	
	/**
	 * 获取图片文件的类型.
	 * 
	 * @param imageFile
	 *            图片文件对象.
	 * @return 图片文件类型
	 */
	public static String getImageFormatName(File imageFile) {
		try {
			ImageInputStream imageInputStream = ImageIO.createImageInputStream(imageFile);
			Iterator<ImageReader> iterator = ImageIO.getImageReaders(imageInputStream);
			if (!iterator.hasNext()) {
				return null;
			}
			ImageReader imageReader = (ImageReader) iterator.next();
			imageInputStream.close();
			return imageReader.getFormatName().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    public static void main(String args[]) {
        try {
        	/*File uploadMmImageFile = new File("E:\\app\\update\\show\\fd42df527f3946c18e3d4325f59e46a9.jpeg");
        	BufferedImage srcBufferedImage = ImageIO.read(uploadMmImageFile);
        	File destFile = new File("d:\\tmp\\showfd42df527f3946c18e3d4325f59e46a9_sl.jpg");
        	ImageUtil.zoomByCommunity(srcBufferedImage, destFile, 230);*/
        	//ImageUtil.saveImageAsJpg("E:\\app\\update\\show\\fd42df527f3946c18e3d4325f59e46a9.jpeg", "d:\\tmp\\showfd42df527f3946c18e3d4325f59e46a9_sl.jpg", 300, 500);
        	File ddd = new File("E:\\app\\update\\show\\");
        	File watermarkImageFile = new File("D:\\apache-tomcat-6.0.32\\webapps\\ROOT\\upload\\watermark.png");
        	File[] dddFile = ddd.listFiles();
        	
        	for(File file : dddFile) {
        		String fileName = file.getName();
                String formatName = fileName.substring(0, fileName.lastIndexOf('.'));
                ImageUtil.zoom(ImageIO.read(file), new File("d:\\tmp\\" + formatName + "_big.jpg"), 200, 200, true);
        		//ImageUtil.saveImageAsJpg(file.getAbsolutePath(), "d:\\tmp\\" + formatName + "_sl.jpg", 230, 300);
        	}
        	
/*//            File f1 = new File("d:\\1.jpg");
//            File f2 = new File("d:\\2.jpg");
//            BufferedImage img1 = ImageIO.read(f1);
//            BufferedImage img2 = ImageIO.read(f2);
//            BufferedImage out = leftJoin(img2, img1, 0, 100);
//            // out = resize(out, 200, 200);
//            File outFile = new File("d:\\out.jpg");
//            ImageIO.write(out, "jpg", outFile);// 写图片
             File f1 = new File("d:\\tmp\\6d38ee54jw1dl4rertug4j.jpg");
             BufferedImage img1 = ImageIO.read(f1);
             int height = img1.getHeight();
             int width = img1.getWidth();
             for(int i = 0; i < height; i += 100){
                 for(int j = 0; j < width; j += 100){
                	 BufferedImage tmp = ImageUtil.cut(img1, j, i, 100, 100); 
                     File outFile = new File("d:\\tmp\\"+i+"_"+j+".jpg");
                     ImageIO.write(tmp, "jpg", outFile);// 写图片
                 }
             }
//             img1=ImageUtil.cut(img1, 0, 0, 100, 100);
//             File outFile = new File("d:\\out.jpg");
//             ImageIO.write(img1, "jpg", outFile);// 写图片
*/      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}