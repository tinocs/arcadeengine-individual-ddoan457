package breakout;
import engine.World;
public class BallWorld extends World{
	
	public BallWorld(int width, int height) {
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void onDimensionsInitialized() {
		Ball b1 = new Ball();
		b1.setX(getWidth() / 2 - b1.getWidth() / 2);
		b1.setY(getHeight() / 2 - b1.getHeight() / 2);
		add(b1);
		
		Paddle p1 = new Paddle();
		p1.setX(200);
		p1.setY(100);
		add(p1);
	
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
