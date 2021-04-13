

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Img
{
	private Image _image;//מאפיין מסוג אימג' שיכיל את התמונה עצמה
	private int x, y, width, height;//איקס ו-וואי יכילו את מיקום התמונה בחלון, האורך והרוחב יכילו את האורך והרוחב של התמונה
	
	/**
	 * the constructor of Img. sets the x,y,width and height of the image. it is also sets the image's information with the path.
	 * @param path - the source of the image
	 * @param x - parameter for the x cordination of the image in the window. 
	 * @param y - parameter for the y cordination of the image in the window. 
	 * @param width - parameter for the width of the image in the window. 
	 * @param height - parameter for the height of the image in the window. 
	 */
	public Img(String path, int x, int y, int width, int height)
	{
		try{
		_image = new ImageIcon(this.getClass().getClassLoader().getResource(path)).getImage(); 
		}
		catch(NullPointerException e)
		{
			System.out.println("the picture: "+path+" is not exsit");
		}
		setImgCords(x, y);
		setImgSize(width, height);
	}
	
	/**
	 * this function draws the image into the window, using the g graphics object.
	 * @param g - the graphics object which draws the image.
	 */
	public void drawImg(Graphics g) 
	{
		Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(_image, x, y, width, height, null);
	}
	
	public void setImgCords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setImgSize(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void setImg(Image image)
	{
		_image = image;
	}
	
	/**
     * Rotates an image. Actually rotates a new copy of the image.
     * 
     * @param img The image to be rotated
     * @param angle The angle in degrees
     * @return The rotated image
     */
    public Image rotate(double angle){
        double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = _image.getWidth(null), h = _image.getHeight(null);
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
                * cos + w * sin);
        BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
        Graphics2D g = bimg.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(Math.toRadians(angle), w / 2, h / 2);
        g.drawRenderedImage(toBufferedImage(_image), null);
        g.dispose();
        return toImage(bimg);
    }
    
    /**
     * Creates an empty image with transparency
     * 
     * @param width The width of required image
     * @param height The height of required image
     * @return The created image
     */
    public Image getEmptyImage(int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return toImage(img);
    }
    
    /**
     * Converts a given BufferedImage into an Image
     * 
     * @param bimage The BufferedImage to be converted
     * @return The converted Image
     */
    public Image toImage(BufferedImage bimage){
        // Casting is enough to convert from BufferedImage to Image
        Image img = (Image) bimage;
        return img;
    }
    
    /**
     * Converts a given Image into a BufferedImage
     * 
     * @param img The Image to be converted
     * @return The converted BufferedImage
     */
    public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        // Return the buffered image
        return bimage;
    }

}


