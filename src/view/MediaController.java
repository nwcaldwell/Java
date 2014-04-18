package view;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

//TODO [Jorge][Sydney]

public class MediaController {

    private static MediaController mediaControllerInstance = new MediaController();
    private HashMap<String, String> stringTemplates = new HashMap<String,String>();
    private HashMap<String, BufferedImage> loadedImages = new HashMap<String, BufferedImage>();
    private HashMap<String,File> loadedFiles  = new HashMap<String, File>();
    private static final String STRINGS_FILE_NAME = "/strings.txt";
    private static final String IMGS_FOLDER = "/imgs/";

    private MediaController() {
        initStrings();
    }

    public static MediaController getInstance() {
        return mediaControllerInstance;
    }

    public String getString( String alias, Object... data) {

        String stringTemplate;

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

    public File getFile( String fileName ) {

        File file = loadedFiles.get( fileName );

        if( file == null )
        {
            loadFile(fileName);
            file = loadedFiles.get(fileName);
        }

        return file;
    }

    private void initStrings() {

        try
        {
            BufferedReader br = new BufferedReader(new FileReader( getClass().getResource(STRINGS_FILE_NAME).getPath() )  );
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
            InputStream imgInput = getClass().getResourceAsStream( IMGS_FOLDER + imgName );
            newImage  = ImageIO.read( imgInput );

            if (newImage == null) {

                throw new Exception( "No image found with the name:" + imgName );
            }

            loadedImages.put( imgName, newImage );

        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

    private void loadFile(String fileName) {
        File newFile;

        try {
            newFile = new File( getClass().getResource( fileName ).getPath() );

            if (newFile == null) {

                throw new Exception( "No image found with the name:" + fileName );
            }

            loadedFiles.put( fileName, newFile );

        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }
}
