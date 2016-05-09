package com.elede.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BackgroundActor extends Actor {

	private Texture grid;
	
	public BackgroundActor() {	
		grid = new Texture("grid.png");
	}
	
	
	@Override
	public void draw(Batch batch, float alpha) {
		batch.draw(grid, 0, 0);
	}
	
}
