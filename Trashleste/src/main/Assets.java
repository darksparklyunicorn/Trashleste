package main;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 16, height = 16;
	public static BufferedImage[] MadR,MadRup,MadRdown, MadL, MadLup, MadLdown, MadLwall, MadRwall;
	public static BufferedImage dia1, dia2, dia3;
	public static BufferedImage RoundRock, RightEndRock, LeftEndRock, BottomEndRock, TopEndRock, HorizontalRock, 
								VerticalRock, TLCornerRock, TRCornerRock, BLCornerRock, BRCornerRock, TopEdgeRock,
								BotEdgeRock, LeftEdgeRock, RightEdgeRock, RegCenterRock, GoldCenterRock, //Spike, 
								BreakRock0, BreakRock1, BreakRock2, SpringUp, SpringDown, Air,
								
								SpikeUp, SpikeLeft, SpikeDown, SpikeRight,
								EndCrystal;
	public static BufferedImage magmaBlock;
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/Textures/madeline idle.png"));
		SpriteSheet diamondTexture = new SpriteSheet(ImageLoader.loadImage("/Textures/diamond.png"));
		SpriteSheet foreground = new SpriteSheet(ImageLoader.loadImage("/Textures/Foreground Tiles.png"));
		SpriteSheet background = new SpriteSheet(ImageLoader.loadImage("/Textures/backgroundTiles.png"));
		
		MadR = new BufferedImage[2];
		MadRdown = new BufferedImage[2];
		MadRup = new BufferedImage[2];
		MadL = new BufferedImage[2];
		MadLdown = new BufferedImage[2];
		MadLup = new BufferedImage[2];
		MadLwall = new BufferedImage[2];
		MadRwall = new BufferedImage[2];
		
		for (int i=0; i<2; i++) {
		MadR[i] = sheet.crop(0, 0 + i*height, width, height/2);
		MadRdown[i] = sheet.crop(width, 0 + i*height, width, height/2);
		MadRup[i] = sheet.crop(2*width, 0 + i*height, width, height/2);
		MadL[i] = sheet.crop(3*width, 0 + i*height, width, height/2);
		MadLdown[i] = sheet.crop(0, height/2 + i*height, width, height/2);
		MadLup[i] = sheet.crop(width, height/2 + i*height, width, height/2);
		MadLwall[i] = sheet.crop(2*width, height/2 + i*height, width, height/2);
		MadRwall[i] = sheet.crop(3*width, height/2 + i*height, width, height/2);
		
		}

		
		dia1 = diamondTexture.crop(0, 0, width/2, 11*height/16);
		dia2 = diamondTexture.crop(width, 0, width/2, 11*height/16);
		dia3 = diamondTexture.crop(2*width, 0, width/2, 11*height/16);
		
	 		 RoundRock = foreground.crop(0, 0, width/2, height/2);
		  RightEndRock = foreground.crop(width/2, 0, width/2, height/2);
		   LeftEndRock = foreground.crop(width, 0, width/2, height/2);
		 BottomEndRock = foreground.crop(width*3/2, 0, width/2, height/2); 
		    TopEndRock = foreground.crop(2*width, 0, width/2, height/2);
		HorizontalRock = foreground.crop(width*5/2, 0, width/2, height/2);        
		  VerticalRock = foreground.crop(0, height/2, width/2, height/2);
		  TLCornerRock = foreground.crop(width/2, height/2, width/2, height/2);
		  TRCornerRock = foreground.crop(width, height/2, width/2, height/2);
		  BLCornerRock = foreground.crop(width*3/2, height/2, width/2, height/2);
		  BRCornerRock = foreground.crop(2*width, height/2, width/2, height/2); 
		   TopEdgeRock = foreground.crop(width*5/2, height/2, width/2, height/2);
		   BotEdgeRock = foreground.crop(0, height, width/2, height/2);
		  LeftEdgeRock = foreground.crop(width/2, height, width/2, height/2);
		 RightEdgeRock = foreground.crop(width, height, width/2, height/2); 
		 RegCenterRock = foreground.crop(width*3/2, height, width/2, height/2);
		GoldCenterRock = foreground.crop(2*width, height, width/2, height/2);  
//		         Spike = foreground.crop(width*5/2, height, width/2, height/2);
		    BreakRock0 = foreground.crop(0, height*3/2, width/2, height/2); 
		    BreakRock1 = foreground.crop(width/2, height*3/2, width/2, height/2);
		    BreakRock2 = foreground.crop(width, height*3/2, width/2, height/2);
		      SpringUp = foreground.crop(width*3/2, height*3/2, width/2, height/2);
		    SpringDown = foreground.crop(2*width, height*3/2, width/2, height/2); 
		           Air = foreground.crop(width*5/2, height*3/2, width/2, height/2);
		       SpikeUp = foreground.crop(0, height*5/2, width/2, height/2); 
			 SpikeLeft = foreground.crop(width/2, height*5/2, width/2, height/2);
		 	 SpikeDown = foreground.crop(width, height*5/2, width/2, height/2);
			SpikeRight = foreground.crop(width*3/2, height*5/2, width/2, height/2);
			EndCrystal = foreground.crop(0, 3*height, width*3/2, height*3/2);
		           
		           
	    magmaBlock = background.crop(0, 0, width*2, height*2);
	
	}

}
