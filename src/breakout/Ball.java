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
		if(getY() >= getWorld().getHeight() - getHeight() && dy > 0) {
			dy = -dy;
			BallWorld bw = (BallWorld) getWorld();
			Score s = bw.getScore();
			s.setScore(s.getScore() - 1000);
		}
		if(getY() <= 0 ) {
			dy = -dy;
		}
		
		Paddle pad = getOneIntersectingObject(Paddle.class);
		if(pad != null) {
			dy = -dy;
		}
		
		Brick brick = getOneIntersectingObject(Brick.class);
		if(brick != null) {
			BallWorld bw = (BallWorld) getWorld();
			Score s = bw.getScore();
			s.setScore(s.getScore() + 100);
			
			double centerX = getX() + getWidth() / 2;
			double centerY = getY() + getHeight() / 2;
			double brickLeft = brick.getX();
			double brickRight = brick.getX() + brick.getWidth();
			double brickTop = brick.getY();
			double brickBottom = brick.getY() + brick.getHeight();
			if(centerX >= brickLeft && centerX <= brickRight) {
				dy = -dy;
			} else if(centerY >= brickTop && centerY <= brickBottom) {
				dx = -dx;
			} else {
				dx = -dx;
				dy = -dy;
			}
			getWorld().remove(brick);
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
