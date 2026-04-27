package breakout;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import engine.Actor;
public class Paddle extends Actor{
	public Paddle() {
		String path = getClass().getClassLoader().getResource("breakoutresources/paddle.png").toString();
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
		if(getWorld().isKeyPressed(KeyCode.LEFT)) {
			setX(getX() - 5);
		} 
		if(getWorld().isKeyPressed(KeyCode.RIGHT)) {
			setX(getX() + 5);
		}
	}
}
