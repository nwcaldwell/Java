package view.cgi;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import view.MediaController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**This class will be used to load resources into the graphics card.
 * The texture factory allows a program to access a texture, while
 * ensuring that textures are only loaded once.
 * 
 * While static fields are generally considered to break encapsulation,
 * the graphics card is, unavoidably, a single shared resource.
 * the best we can do is to manage it well.*/
public class TextureFactory {
	
	/**number of bits in RGB representation*/
	public static final int RGB = 3;

	/**represents the ID of a missing texture.
	 * If a texture is not found, an exception trace
	 * will be printed, but the program will continue
	 * running with the texture given by this ID.*/
	public static final int MISSING_TEXTURE=1;
	
	/**a has map of the textures that have been loaded.
	 * maps the filename to the texture ID*/
	private static HashMap<String, Integer> loadedTextures=new HashMap<String, Integer>();
	
	/**a simple factory for creating unique IDs
	 * Nobody else should touch nextID. NOBODY!!!!!*/
	private static class UID{

		/** a counter that increments each time
		 * an ID is returned is given a unique ID.*/
		private int nextID = 0;
		
		/**creates a UID generator starting at the given value.*/
		public UID(int start){
			nextID=start-1;
		}

		/**gets a unique ID*/
		public int getID() {
			nextID++;
			return nextID;
		}
	}
	
	private static UID idFactory=new UID(MISSING_TEXTURE+1);
	
	/**gets a texture from the given filename.
	 * If the texture is already loaded,
	 * will simply return the loaded texture's ID.*/
	public static int getTexture(String filename){
		
		//if this texture is not already loaded,
		//load it now
		if (!loadedTextures.containsKey(filename)){
			try {
				loadNewTexture(filename, idFactory.getID());
			} catch (IOException e) {
				e.printStackTrace();
				return MISSING_TEXTURE;
			}
		}
		
		//return the corrosponding int value
		return loadedTextures.get(filename).intValue();
	}
	
	/**loads a new texture with a unique ID
	 * into the loadedTextures map.
	 * @throws IOException */
	private static void loadNewTexture(String filename, int textureID) throws IOException{
		
		//load a texture to the graphics card
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		//prepare to receive image data from RGB integers
		GL11.glPixelStorei(GL11.GL_UNPACK_ALIGNMENT, 1);
		
		//set the texture to wrap top and bottom.
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri (GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		//give it a nice linear filter (looks bettter than nearest filtering for most things)
		GL11.glTexParameteri (GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri (GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		
		//modulate allows for shading
		GL11.glTexEnvf(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		
		//load the image
		BufferedImage image = MediaController.getInstance().getImage(filename);
		
		//if the image was not loaded, just make the default texture return.
		if (image == null){
			loadedTextures.put(filename, MISSING_TEXTURE);
			throw new IOException("texture file not found: "+filename);
		}
		//BufferedImage image = ImageIO.read(new File(filename));
		
		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0,
				image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth()
				* image.getHeight() * RGB); // 4 for RGBA, 3 for RGB

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
				//buffer.put((byte) ((pixel >> 24) & 0xFF)); 
				// Alpha component.
				// Only for RGBA
				//also, I have no idea how to use this
				//also, alpha in 3d graphics is a bigger pain than rotation without
				//hardware acceleration.
			}
		}
		
		buffer.flip(); // FOR THE LOVE OF GOD DO NOT FORGET THIS
		
		//actually load the image
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, image.getWidth(), image.getHeight(), 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buffer);
		
		//finally, put the string ID pair into the hash map
		loadedTextures.put(filename, textureID);
	}
	
	/**Loads a default texture for use when a requested image cannot be found.*/
	public static final void loadMissingTexture(String missingTexture) throws IOException{
		loadNewTexture(missingTexture, MISSING_TEXTURE);
	}
}
