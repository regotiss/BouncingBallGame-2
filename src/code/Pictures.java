package code;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;

public class Pictures 
{
	static Image platform,ball;
	static Applet ap;
	static AudioClip bounce,music;
	static int level=1;
	URL url;
	Pictures(Applet ap)
	{
		
		try
		{
			url=ap.getDocumentBase();
		}
		catch (Exception e)
		{
		}
		bounce=ap.getAudioClip(url,"musics/jump.au");
		music=ap.getAudioClip(url,"musics/dance-zone.au");
		//platform=g.getImage(url,"images/back.jpg");

		this.ap=ap;
	}
}
