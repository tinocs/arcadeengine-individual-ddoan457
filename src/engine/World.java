package engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public abstract class World extends Pane{
	private AnimationTimer timer;
	private boolean isRunning;
	private Set<KeyCode>  pressed;
	private boolean isSetWidth;
	private boolean isSetHeight;
	
	private boolean timerStopped;
	
	
	
	public World() {

	    pressed = new HashSet<>();
	    isRunning = false;

	    widthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
	            if (newVal.doubleValue() > 0) {
	                isSetWidth = true;
	                if (isSetWidth && isSetHeight) {
	                    onDimensionsInitialized();
	                }
	            }
	        }
	    });

	    heightProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> obs, Number oldVal, Number newVal) {
	            if (newVal.doubleValue() > 0) {
	                isSetHeight = true;
	                if (isSetWidth && isSetHeight) {
	                    onDimensionsInitialized();
	                }
	            }
	        }
	    });

	    sceneProperty().addListener(new ChangeListener<Scene>() {
	        @Override
	        public void changed(ObservableValue<? extends Scene> obs, Scene oldScene, Scene newScene) {
	            if (newScene != null) {
	                requestFocus();
	            }
	        }
	    });

	    setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            pressed.add(e.getCode());
	        }
	    });

	    setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent e) {
	            pressed.remove(e.getCode());
	        }
	    });

	    timer = new AnimationTimer() {
	        @Override
	        public void handle(long now) {
	            act(now);
	            for (int i = 0; i < getChildren().size(); i++) {
	                Node node = getChildren().get(i);
	                if (node instanceof Actor) {
	                    Actor actor = (Actor) node;
	                    if (getChildren().contains(actor)) {
	                        actor.act(now);
	                    }
	                }
	            }
	        }
	    };
	}
	
	
	
	
	
	
	public void start() {
		timer.start();
		timerStopped = false;
	}
	public void stop() {
		timer.stop();
		timerStopped = true;
	}
	public boolean isStopped() {
		return timerStopped;
	}
	
	public void add(Actor a) {
		getChildren().add(a);
		a.addedToWorld();
	}
	public void remove(Actor a) {
		getChildren().remove(a);
	}
	
	public <A extends Actor> List<A> getObjects(Class<A> cls){
		List<A> list = new ArrayList<>();
		for(int i = 0; i < getChildren().size(); i ++) {
			if(cls.isInstance(getChildren().get(i))) {
				list.add(cls.cast(getChildren().get(i)));
			}
		}
		return list;
	}
	
	public <A extends Actor> List<A> getObjectsAt(double x, double y, Class<A> cls){
		List<A> list = new ArrayList<>();
		for(int i = 0; i < getChildren().size(); i ++) {
			if(cls.isInstance(getChildren().get(i))) {
				Actor actor = (Actor) getChildren().get(i);
				
				if(actor.getBoundsInParent().contains(x, y)){
					list.add(cls.cast(actor));
				}
			}
		}
		return list;
	}
	
	
	public boolean isKeyPressed(KeyCode code) {
		if(pressed.contains(code)) {
			return true;
		}
		return false;
	}
	
	public abstract void onDimensionsInitialized();
	
	public abstract void act(long now);
}
