package code;
import java.awt.Color;
import java.util.Random;
import java.awt.*;
import java.applet.Applet;

public class PlatForm1
{
	private int dy;
	private int x;
	private int y;
	private int width;
	private int height;
	private Image plat;
	private float frame=0;
	Applet sp;
	
	public PlatForm1()
	{
		dy=-1;
		x=450;
		y=300;
		width=120;
		//plat=Pictures.platform;
		height=40;
	}
	public PlatForm1(int a,int b,Image plat)
	{
		this.plat=plat;
		dy=-1;
		x=a;
		y=b;
		width=120;
		//i=sp.getImage(sp.getDocumentBase(),"images/24.jpg");//
		height=40;
	}
	public void update(Applet sp,Ball b)
	{
		int tester=(int)(frame+.1);
		if(tester<3)
			frame+=.1;
		else
			frame=0;
		y+=-(Pictures.level);
		Random r=new Random();
	
		//plat=sp.getImage(sp.getDocumentBase(),"images/bricks/"+(r.nextInt()+1)+".jpg");//
		checkForCollision(b);
		if(y<(0-height))
		{
			//Random r=new Random();
			//y=sp.getHeight()-40-r.nextInt(400);
			x=r.nextInt(sp.getWidth());
			if((x+width)>(sp.getWidth()))
			{
				x=x-width;
			}
		}
		this.sp=sp;
		

	}
	public void checkForCollision(Ball b)
	{
		int ballX=b.getX();
		int ballY=b.getY();
		int radius=b.getRadius();
		if(ballY+radius>y&&ballY+radius<y+height)
		{
			if(ballX>x&&ballX<x+width)
			{
			double newDy=b.getGameDy();
			b.setY(y-radius);
			b.setDy(newDy);
			//Pictures.bounce.play();
			}
		}
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		//g.fillRect(x,y,width,height);
		
		g.drawImage(plat,x,y,width,height,sp);
		//g.drawImage(plat,x,y,x+width,y+height,0,34*(int)frame,90,34*(int)frame+34,Pictures.ap); 
	}
	public int getY()
	{
		return y;
	}
	public void setY(int x)
	{
		this.y=x;
	}
	public int getHeight()
	{
		return height;
	}
	public void setHeight(int x)
	{
		this.height=x;
	}

}
