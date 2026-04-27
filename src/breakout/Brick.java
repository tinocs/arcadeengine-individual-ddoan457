package breakout;

import engine.Actor;
import javafx.scene.image.Image;

public class Brick extends Actor{
	
	public Brick() {
		String path = getClass().getClassLoader().getResource("breakoutresources/brick.png").toString();
		if(path == null) {
			System.out.println("null");
		} else {
			System.out.println("loaded");
		}
		Image img = new Image(path, 40, 40, true, true);
		setImage(img);
	}
	
	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	
}
