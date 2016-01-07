//By Sujata Regoti
//Date: 30 May 2015
package code;
import java.applet.Applet;
import javax.swing.*;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
/*<applet code="BallGame.class" width=800 height=600></applet>*/
public class BallGame extends Applet implements Runnable, KeyListener,MouseMotionListener,MouseListener
{	
	private Image i;
	private Graphics doubleG;
	Ball b;	
	PlatForm1 p[]=new PlatForm1[7];
	Image city;
	int score;
	double X=0;
	double Dx=.3;
	URL url;
	Image back,plat,ball;
	int levelCheck=0;
	boolean gameOver=false;
	boolean mouseIn=false,start=false;
	private int cityX;

	public void init()
	{
		
		setSize(800,600);
		JOptionPane.showMessageDialog(this,"Press space to start..");
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		try
		{
			url=getDocumentBase();
		}
		catch (Exception e)
		{
		}
		
		
	}
	public int getScore()
	{
		return score;
	}
	public void setScore(int s)
	{
		score=s;
	}
	public void start()
	{

		score=0;
		Random r=new Random();
		city=getImage(getDocumentBase(),"images/back1.jpg");
		ball=getImage(getDocumentBase(),"images/"+(r.nextInt(8)+1)+"t.png");
		b=new Ball(getWidth()/2+50,getHeight()-120,ball);

		
		
		for(int i=0;i<p.length;i++)
		{
			plat=getImage(getDocumentBase(),"images/"+(r.nextInt(8)+1)+".jpg");
			if(i%3==0)
			p[i]=new PlatForm1(getWidth()/2,getHeight()+i*100,plat);
			else if(i%3==1)
			p[i]=new PlatForm1(getWidth()/2-200,getHeight()+i*100,plat);
			else
			p[i]=new PlatForm1(getWidth()/2+200,getHeight()+i*100,plat);

			
		}
		
		
		Thread t=new Thread(this);
		t.start();

	}
	public void run()
	{
		while(true)
		{
			if(start&&!gameOver)
			{
			for(int i=0;i<p.length;i++)
			{
				int testy=p[i].getY();
				if(testy<(0-p[i].getHeight()))
				{
					Random r=new Random();
					int facki=i;
					if(i==0)
						facki=p.length;
					p[i].setY(p[facki-1].getY()+p[i].getHeight()+1*r.nextInt(25));
				}
			}

			gameOver=b.getGameOver();
			if(b.getY()<=0)
				gameOver=true;
			
			if(!gameOver)
			score++;
			
			b.update(this);

			for(int i=0;i<p.length;i++)
			p[i].update(this,b);

			repaint();
		
			}
			try{
			Thread.sleep(17);
			}
			catch(Exception e){}
			
		}

	}
	public void stop()
	{
	
	}
	public void destroy()
	{
	
	}
	public void update(Graphics g)
	{
		if(i==null)
		{
			i=createImage(this.getSize().width,this.getSize().height);
			doubleG=i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0,0,this.getSize().width,this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);
		g.drawImage(i,0,0,this);
	}

	public void paint(Graphics g)
	{
		g.setColor(new Color(15,77,147));
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(city,(int)cityX,0,getWidth()*2,getHeight(),this);
		b.paint(g);
		
		for(int i=0;i<p.length;i++)
			p[i].paint(g);
		
		String s=Integer.toString(score);
		Font f=new Font("Serif",Font.BOLD,32);
		g.setFont(f);
		g.setColor(Color.black);
		g.drawString(s,getWidth()-150-2,50-2);
		g.setColor(new Color(198,226,255));
		g.drawString(s,getWidth()-150,50);

		if(gameOver){
			
			g.setColor(Color.black);
			g.drawString("GAME OVER",300-2,300-2);
			g.setColor(Color.blue);

			g.drawString("GAME OVER",300,300);
			g.drawRect(290,320,210,40);
			if(mouseIn)
			{
				g.setColor(Color.red);
				g.drawString("Play Again?",310,350);
			}
			else
			{
				g.setColor(Color.white);
				g.drawString("Play Again?",310,350);
			}
			g.setColor(Color.black);
			String str="Your Score:"+Integer.toString(score);
			g.drawString(str,290-2,250-2);
			g.setColor(Color.cyan);		
			g.drawString(str,290,250);
		}
		
		
	}

	public void keyPressed(KeyEvent ke)
	{
		switch(ke.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
						b.moveLeft();
						break;
			case KeyEvent.VK_RIGHT:
						b.moveRight();
						break;
			case KeyEvent.VK_SPACE:
						start=true;
					
		}
	}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}

	public void mouseDragged(MouseEvent me){}
	public void mouseMoved(MouseEvent e)
	{
		
		if(e.getX()>290&&e.getX()<400)
		{
			if(e.getY()>320&&e.getY()<360)
				mouseIn=true;
		}
		else
			mouseIn=false;
		repaint();
		
	}

	public void mouseClicked(MouseEvent me)
	{
		if(mouseIn)
		{
			b=null;
			Random r=new Random();
			ball=getImage(getDocumentBase(),"images/bubbles/"+(r.nextInt(8)+1)+"t.png");

			b=new Ball(getWidth()/2+50,getHeight()-120,ball);
			score=0;
			Pictures.level=1;
			for(int i=0;i<p.length;i++)
		{
			plat=getImage(getDocumentBase(),"images/bricks/"+(r.nextInt(8)+1)+".jpg");
			if(i%3==0)
			p[i]=new PlatForm1(getWidth()/2,getHeight()+i*100,plat);
			else if(i%3==1)
			p[i]=new PlatForm1(getWidth()/2-200,getHeight()+i*100,plat);
			else
			p[i]=new PlatForm1(getWidth()/2+200,getHeight()+i*100,plat);

			
		}
			//Random r=new Random();
						mouseIn=false;
			gameOver=false;
			repaint();
		}
	
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me)
	{}
	public void mousePressed(MouseEvent me)
	{}
	public void mouseReleased(MouseEvent me)
	{}

}