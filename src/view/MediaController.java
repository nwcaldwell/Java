package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

//TODO [Jorge][Sydney]

public class MediaController {

    private static MediaController mediaControllerInstance = new MediaController();
    private HashMap<String, String> stringTemplates;
    private HashMap<String, BufferedImage> loadedImages;
    // TODO implement private HashMap<String, Audio> loadedAudio
    // TODO implement private HashMap<String, 3DModel> loaded3DModel

    private MediaController() {
        initStrings();
    }

    public static MediaController getInstance() {
        return mediaControllerInstance;
    }

    public String getString( String alias, Object... data) {

        String stringTemplate = null;

        try
        {
            stringTemplate = stringTemplates.get(alias);
            if(stringTemplate == null)
            {
                throw new Exception("No string found for alias:" + alias);

            }
        }
        catch (Exception e)
        {
            System.out.println( e.getMessage() );
        }

        return String.format( stringTemplates.get(alias), data);
    }

    public BufferedImage getImage( String imgName ) {

        BufferedImage img = loadedImages.get( imgName );

        if( img == null )
        {
            loadImage( imgName );
            img = loadedImages.get( imgName );
        }

        return img;
    }

    private void initStrings() {

        try
        {
            // TODO modify the file location for the project
            BufferedReader br = new BufferedReader(new FileReader("strings.txt"));
            String currentLine;
            int splitIndex;
            String stringAlias;

            while ( ( currentLine = br.readLine() ) != null )
            {
                splitIndex = currentLine.indexOf(",");

                try
                {
                    stringAlias = currentLine.substring(0, splitIndex);
                    if (stringTemplates.get(stringAlias) != null)
                    {
                        throw new Exception("The key '" + stringAlias + "' is duplicated in the strings.txt file" );
                    }

                    stringTemplates.put(
                            currentLine.substring(0, splitIndex),
                            currentLine.substring( (splitIndex + 1) ) );
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }
        catch (IOException e)
        {
            System.out.println("There was an issue reading the strings File.");
        }
    }

    private void loadImage(String imgName) {

        BufferedImage newImage;

        try {

            // TODO modify the file location for the project
            InputStream imgInput = getClass().getResourceAsStream( imgName );
            newImage  = ImageIO.read( imgInput );

            if (newImage == null) {

                throw new Exception( "No image found with the name:" + imgName );
            }

            loadedImages.put( imgName, newImage );

        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }
}
