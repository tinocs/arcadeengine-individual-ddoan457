package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class Actor extends ImageView{
	public Actor() {
		super();
	}
	
	public void move(double x, double y) {
		setX(getX() + x);
		setY(getY() + y);
	}
	
	public World getWorld() {
		Node parent = getParent();
		if(parent == null) {
			return null;
		}
		return (World)parent;
	}
	
	public double getWidth() {
		Bounds bounds = getBoundsInParent();
		return bounds.getWidth();
	}
	public double getHeight() {
		Bounds bounds = getBoundsInParent();
		return bounds.getHeight();
	}
	
	public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls){
		List<A> intersecting = new ArrayList<>();
		World world = getWorld();
		
		if(world == null) {
			return intersecting;
		}
		List<A> actors = world.getObjects(cls);
		for(int i = 0; i < actors.size(); i ++) {
			if(actors.get(i) != this && this.getBoundsInParent().intersects(actors.get(i).getBoundsInParent())) {
				intersecting.add(actors.get(i));
			}
		}
		return intersecting;
	}
	
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
		List<A> list = getIntersectingObjects(cls);
		
		if(list.size() == 0) {
			return null;
		}
		return list.get(0);
		
	}
	
	public abstract void act(long now);
	public void addedToWorld() {
		
	}
}
