package breakout;

import javafx.scene.image.Image;
import engine.Actor;
public class Paddle extends Actor{
	public Paddle() {
		String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
		if(path == null) {
			System.out.println("null");
		} else {
			System.out.println("loaded");
		}
		Image img = new Image(path, 30, 30, true, true);
		setImage(img);
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
}
