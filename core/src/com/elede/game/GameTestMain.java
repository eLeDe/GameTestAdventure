package com.elede.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class GameTestMain extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	OrthographicCamera cam;
	
	private Texture kidTexture;
	private TextureRegion[][] kidTR;
	private Animation animUP, animDOWN, animLEFT, animRIGHT, anim;
	private int KID_WIDTH = 48;
	private int KID_HEIGHT = 48;
	private Vector2 kidPos;
	private boolean movement = false;
	
	private int WORLD_WIDTH = 1280;
	private int WORLD_HEIGHT = 720;
	
	private float elapsedTime = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		kidTexture = new Texture("george.png");
		kidTR = TextureRegion.split(kidTexture, KID_WIDTH, KID_HEIGHT);

		// Create animations for movement 
		animDOWN  = new Animation(0.2f, kidTR[0][0], kidTR[1][0], kidTR[2][0], kidTR[3][0]);
		animLEFT  = new Animation(0.2f, kidTR[0][1], kidTR[1][1], kidTR[2][1], kidTR[3][1]);
		animUP    = new Animation(0.2f, kidTR[0][2], kidTR[1][2], kidTR[2][2], kidTR[3][2]);
		animRIGHT = new Animation(0.2f, kidTR[0][3], kidTR[1][3], kidTR[2][3], kidTR[3][3]);

		// Set initial position of the kid
		anim = animDOWN;
		kidPos = new Vector2(0, 0);
		anim.getKeyFrame(0, movement);
		
		
		// Set the cam
		cam = new OrthographicCamera();
		cam.setToOrtho(false);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		checkControls();

		cam.update();
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.rect(0, 0, WORLD_WIDTH, WORLD_HEIGHT);
		shapeRenderer.end();
		
		batch.begin();
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.draw(anim.getKeyFrame(elapsedTime, movement), kidPos.x, kidPos.y);
		batch.end();
		
		movement = false;
		
	}
	
	@Override
	public void dispose () {
		
	}
	
	@Override
	public void resize(int width, int height) {
		System.out.println("Cambiando tamaño: " + width + " x " + height);
		if ((float)width/height > 1280.0f/720.0f) {
			cam.zoom = 720.0f/height;
		} else {
			cam.zoom = 1280.0f/width;
		}
		System.out.println("Nuevo zoom: " + cam.zoom);
		cam.setToOrtho(false);
		
	}
	
	
	private void checkControls () {
        
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            anim = animLEFT;
            kidPos.x -= 2;
            if (kidPos.x < 0) kidPos.x = 0;
            movement = true;
            
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        	anim = animRIGHT;
        	kidPos.x += 2;
        	if (kidPos.x > WORLD_WIDTH - KID_WIDTH) 
        		kidPos.x = WORLD_WIDTH - KID_WIDTH;
        	movement = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
        	anim = animUP;
        	kidPos.y += 2;
        	if (kidPos.y > WORLD_HEIGHT - KID_WIDTH) 
        		kidPos.y = WORLD_HEIGHT - KID_WIDTH;        	
        	movement = true;
        }		
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        	anim = animDOWN;
        	kidPos.y -= 2;
        	if (kidPos.y < 0) kidPos.y = 0;
        	movement = true;
        }		
        
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
        }	
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
        	cam.position.y -= 2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
        	cam.position.x -= 2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
        	cam.position.x += 2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)){
        	cam.position.x = WORLD_WIDTH/2;
        	cam.position.y = WORLD_HEIGHT/2;
        }        
	}
	
}
