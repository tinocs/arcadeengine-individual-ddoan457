package breakout;
import engine.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
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
		p1.setX(getWidth() / 2 - p1.getWidth() / 2);
		p1.setY(900);
		add(p1);
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				p1.setX(event.getX());
			}
			
		});
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}

}
