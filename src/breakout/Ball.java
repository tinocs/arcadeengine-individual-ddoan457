package breakout;
import java.util.List;

import engine.Actor;
import javafx.scene.image.Image;
public class Ball extends Actor {
	int dx;
	int dy;
	@Override
	public void act(long now) {
		move(dx, dy);
		if(getX() <= 0 || getX() >= getWorld().getWidth() - getWidth()) {
			dx = -dx;
		}
		if(getY() <= 0 || getY() >= getWorld().getHeight() - getHeight()) {
			dy = -dy;
		}
		
		Paddle pad = getOneIntersectingObject(Paddle.class);
		if(pad != null) {
			dy = -dy;
		}
		
	}
	
	public Ball() {
		String path = getClass().getClassLoader().getResource("breakoutresources/ball.png").toString();
		if(path == null) {
			System.out.println("null");
		} else {
			System.out.println("loaded");
		}
		Image img = new Image(path, 30, 30, true, true);
		setImage(img);
		dx = 5;
		dy = 5;
	}
}
