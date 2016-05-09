package com.elede.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class KidActor extends Actor {

	private Texture kidTexture;
	private TextureRegion[][] kidTR;
	public Animation animUP, animDOWN, animLEFT, animRIGHT, anim;
	public int KID_WIDTH = 48;
	public int KID_HEIGHT = 48;

	private float elapsedTime = 0;

	private MoveByAction mba;
	private boolean moveFlag = false;

	public KidActor() {

		kidTexture = new Texture("george.png");
		kidTR = TextureRegion.split(kidTexture, KID_WIDTH, KID_HEIGHT);

		// Create animations for movement 
		animDOWN  = new Animation(0.25f, kidTR[0][0], kidTR[1][0], kidTR[2][0], kidTR[3][0]);
		animLEFT  = new Animation(0.25f, kidTR[0][1], kidTR[1][1], kidTR[2][1], kidTR[3][1]);
		animUP    = new Animation(0.25f, kidTR[0][2], kidTR[1][2], kidTR[2][2], kidTR[3][2]);
		animRIGHT = new Animation(0.25f, kidTR[0][3], kidTR[1][3], kidTR[2][3], kidTR[3][3]);

		// Set initial position of the kid
		anim = animDOWN;
		setPosition(0, 0);
		//anim.getKeyFrame(0, movement);

		setBounds(getX(), getY(), KID_WIDTH, KID_HEIGHT);


	}

	@Override
	public void draw(Batch batch, float alpha) {
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.draw(anim.getKeyFrame(elapsedTime, moveFlag), getX(), getY());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (this.getActions().size == 0) {
			moveFlag = false;
		}
	}

	public void checkMovement() {

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if (moveFlag == false) {
				anim = animLEFT;
				if (Math.round(getX()) > 0) {
					mba = new MoveByAction();
					mba.setAmount(-48,0);
					mba.setDuration(1f);
					this.addAction(mba);
				}
				moveFlag = true;
			}
		}

		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if (moveFlag == false) {
				anim = animRIGHT;
				//        	if (getX() < 1440 - KID_WIDTH) 
				//        		moveBy(2, 0);
				//        	movement = true;
				if (Math.round(getX()) < 1440 - KID_WIDTH)	{
					mba = new MoveByAction();
					mba.setAmount(48,0);
					mba.setDuration(1f);
					this.addAction(mba);
				}
				moveFlag = true;
			}


		}


		if(Gdx.input.isKeyPressed(Input.Keys.UP)){

			if (moveFlag == false) {
				anim = animUP;
				if (Math.round(getY()) < 1440 - KID_WIDTH) {        		
					mba = new MoveByAction();
					mba.setAmount(0,48);
					mba.setDuration(1f);
					this.addAction(mba);
				}
				moveFlag = true;

			}
		}


		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){

			if (moveFlag == false) {
				anim = animDOWN;
				if (Math.round(getY()) > 0) {
					mba = new MoveByAction();
					mba.setAmount(0,-48);
					mba.setDuration(1f);
					this.addAction(mba);
				}

				moveFlag = true;

			}	
		}
		
//		if(Gdx.input.isKeyPressed(Input.Keys.V)){
//
//			if (moveFlag == false) {
//				AlphaAction aa = new AlphaAction();
//				
//				moveFlag = true;
//
//			}	
//		}		
	}

}
