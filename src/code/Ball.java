package code;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
public class Ball
{
	private int x=400;
	private int y=25;
	private double dx=0;
	private double dy=0;
	private double gameDy=-75;
	private int radius=20;
	private double gravity=13;
	private double xFriction=.9;
	private double energyloss=1;
	private double dt=.2;
	private int agility=3;
	private Image b;
	private int maxSpeed=20; 
	Applet ap;
	private boolean game_over=false;

	Ball(){};
	Ball(int a,int b)
	{
		x=a;
		y=b;
	}
	Ball(int a,int b,Image i)
	{
		this.b=i;
		x=a;
		y=b;
	}
	public void update(Applet sp)
	{
		ap=sp;
		if(x+dx>sp.getWidth()-radius-1)
			{
				x=sp.getWidth()-radius-1;
				dx=-dx;
			}
			else if(x+dx<0+radius)
			{
				x=0+radius;
				dx=-dx;
			}
			else
				x+=dx;

			if(y==sp.getHeight()-radius-1)
			{
				dx*=xFriction;
				if(Math.abs(dx)<.8)
					dx=0;

			}

			if(y-200>sp.getHeight()-radius-1)
			{
				game_over=true;
			}
			else
			{
				dy+=gravity*dt;
				y+=dy*dt+0.5*gravity*dt*dt;
			}

	}

	public void moveLeft()
	{
		if(dx-agility>-maxSpeed)
			dx-=agility;
	}

	public void moveRight()
	{
		if(dx+agility<maxSpeed)
			dx+=agility;
	}

	public double getGameDy()
	{
		return gameDy;
	}
	public void setGameDy(double x)
	{
		this.gameDy=gameDy;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public double getDx()
	{
		return dx;
	}
	public double getDy()
	{
		return dy;
	}
	public double getGravity()
	{
		return gravity;
	}

	public int getRadius()
	{
		return radius;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int x)
	{
		y=x;
	}
	public void setDx(double x)
	{
		dx=x;
	}
	public void setDy(double x)
	{
		dy=x;
	}
	public void setGravity(double x)
	{
		gravity=x;
	}
	public int getAgility()
	{
		return agility;
	}
	public void setAgility(int x)
	{
		this.agility=x;
	}
	public int getMaxSpeed()
	{
		return maxSpeed;
	}
	public void setMaxSpeed(int x)
	{
		this.maxSpeed=x;
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.green);

		g.fillOval(x-radius,y-radius,radius+radius,radius+radius);
		g.drawImage(b,x-radius,y-radius,radius+radius,radius+radius,ap);
		
	}

	public boolean getGameOver()
	{
		return game_over;
	}
}