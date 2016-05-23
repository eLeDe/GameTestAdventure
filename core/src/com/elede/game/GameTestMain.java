package com.elede.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameTestMain extends ApplicationAdapter {

	OrthographicCamera cam;
	FitViewport viewport;
	
	Stage stage;
	
	KidActor kidActor;
	BackgroundActor backgroundActor;
	
	private boolean followKid = true;
	
	

	@Override
	public void create () {
		//batch = new SpriteBatch();

		
		kidActor = new KidActor();
		backgroundActor = new BackgroundActor();
		
		// Set the viewport
		cam = new OrthographicCamera();
	    viewport = new FitViewport(kidActor.KID_WIDTH * 7, 9.0f/16*kidActor.KID_WIDTH*7, cam);
	    viewport.apply(true);

		stage = new Stage(viewport);
		stage.addActor(backgroundActor);
		stage.addActor(kidActor);
	    
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		checkControls();

		cam.update();
		//batch.setProjectionMatrix(cam.combined);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}
	
	@Override
	public void dispose () {
		stage.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
	}
	
	
	private void checkControls () {
        
		kidActor.checkMovement();
		
	
        
        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
        	cam.zoom -= 0.01;
        }	
        if(Gdx.input.isKeyPressed(Input.Keys.E)){
        	cam.zoom += 0.01;
        }	        
        if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
        	cam.zoom = 1;
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
        	cam.position.y += 2;
        	followKid = false;
        }	
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
        	cam.position.y -= 2;
        	followKid = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
        	cam.position.x -= 2;
        	followKid = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
        	cam.position.x += 2;
        	followKid = false;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)){
        	followKid = true;

        }        
        
        if (followKid == true) {
        	cam.position.x = kidActor.getX() + kidActor.KID_WIDTH/2;
        	cam.position.y = kidActor.getY() + kidActor.KID_HEIGHT/2;
        }
	}
	
}
