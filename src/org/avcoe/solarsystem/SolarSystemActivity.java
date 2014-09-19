package org.avcoe.solarsystem;

import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.RotationByModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.debug.Debug;
import org.avcoe.andengine.modified.CropResolutionPolicy;

import android.content.Intent;

/**
 * @author Paras Waykole
 */

public class SolarSystemActivity extends BaseGameActivity implements IOnSceneTouchListener{

	//setup
	private ZoomCamera mCamera;
	CropResolutionPolicy crp;
	int WIDTH = 800;
	int HEIGHT = 480;
	
	//camera moving data
	float lx = 0, ly =0;
	
	//Graphics Data
	BuildableBitmapTextureAtlas bgAtlas;
	BuildableBitmapTextureAtlas sunAtlas;
	BuildableBitmapTextureAtlas planetsAtlas;
	BuildableBitmapTextureAtlas orbitAtlas;
	BuildableBitmapTextureAtlas orbitAtlas1;
	BuildableBitmapTextureAtlas orbitAtlas2;
	BuildableBitmapTextureAtlas astroidBeltAtlas;
	BuildableBitmapTextureAtlas orbitAtlas3;
	BuildableBitmapTextureAtlas btnAtlas;
	
	//regions of bgAtlas
	ITextureRegion bgregion;
	
	//regions of sunAtlas
	ITextureRegion sunregion;
	
	//regions of planetsAtlas
	ITextureRegion mercuryregion;
	ITextureRegion venusregion;
	ITextureRegion earthregion;
	ITextureRegion marsregion;
	ITextureRegion jupiterregion;
	ITextureRegion saturnregion;
	ITextureRegion uranusregion;
	ITextureRegion neptuneregion;
	
	//regions of orbitAtlas 1
	ITextureRegion mercuryOrbitregion;
	ITextureRegion venusOrbitregion;
	ITextureRegion earthOrbitregion;
	ITextureRegion marsOrbitregion;
	ITextureRegion astroidBeltregion;
	ITextureRegion jupiterOrbitregion;
	
	//regions of btnAtlas
	ITextureRegion pzoombtnregion;
	ITextureRegion mzoombtnregion;
	
	@Override
	public Engine onCreateEngine(EngineOptions pEngineOptions) 
	{
		return new LimitedFPSEngine(pEngineOptions, 70);
	}
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		
		mCamera = new ZoomCamera(0, 0, WIDTH, HEIGHT);
		crp = new CropResolutionPolicy(WIDTH,HEIGHT);

		EngineOptions engineOptions = new EngineOptions(true,ScreenOrientation.LANDSCAPE_FIXED, crp, mCamera);
		
		engineOptions.getRenderOptions().setDithering(true);
		engineOptions.getRenderOptions().setMultiSampling(true);
		
		return engineOptions;
	}

	@Override
	public void onCreateResources(OnCreateResourcesCallback callback)
	{
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("graphics/");
		
		bgAtlas = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),800,800,TextureOptions.REPEATING_BILINEAR);
		bgregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bgAtlas,getApplicationContext(),"background.jpg");
		try {
			bgAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
			bgAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		sunAtlas = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),300,300,TextureOptions.BILINEAR);
		sunregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(sunAtlas,getApplicationContext(),"sun.png");
		try {
			sunAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,0,0));
			sunAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		orbitAtlas = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),1024,1024,TextureOptions.BILINEAR);
		mercuryOrbitregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(orbitAtlas,getApplicationContext(),"orbits/omercury.png");
		venusOrbitregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(orbitAtlas,getApplicationContext(),"orbits/ovenus.png");
		try {
			orbitAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,1));
			orbitAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		orbitAtlas1 = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),565,565,TextureOptions.BILINEAR);
		earthOrbitregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(orbitAtlas1,getApplicationContext(),"orbits/oearth.png");
		try {
			orbitAtlas1.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,1));
			orbitAtlas1.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		orbitAtlas2 = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),745,745,TextureOptions.BILINEAR);
		marsOrbitregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(orbitAtlas2,getApplicationContext(),"orbits/omars.png");
		try {
			orbitAtlas2.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,1));
			orbitAtlas2.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		astroidBeltAtlas = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),910,910,TextureOptions.BILINEAR);
		astroidBeltregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(astroidBeltAtlas, getApplicationContext(),"orbits/astroidbelt.png");
		try {
			astroidBeltAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,1));
			astroidBeltAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		orbitAtlas3 = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),1005,1005,TextureOptions.BILINEAR);
		jupiterOrbitregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(orbitAtlas3, getApplicationContext(),"orbits/ojupiter.png");
		try {
			orbitAtlas3.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,1));
			orbitAtlas3.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}

		planetsAtlas = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),1024,1024,TextureOptions.BILINEAR);
		mercuryregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(planetsAtlas,getApplicationContext(),"mercury.png");
		venusregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(planetsAtlas,getApplicationContext(),"venus.png");
		earthregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(planetsAtlas,getApplicationContext(),"earth.png");
		marsregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(planetsAtlas,getApplicationContext(),"mars.png");
		jupiterregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(planetsAtlas,getApplicationContext(),"jupiter.png");
		saturnregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(planetsAtlas,getApplicationContext(),"saturn.png");
		uranusregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(planetsAtlas,getApplicationContext(),"uranus.png");
		neptuneregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(planetsAtlas,getApplicationContext(),"neptune.png");
		try {
			planetsAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,1));
			planetsAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		btnAtlas = new BuildableBitmapTextureAtlas(mEngine.getTextureManager(),200,200,TextureOptions.BILINEAR);
		pzoombtnregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(btnAtlas,getApplicationContext(),"pzoom.png");
		mzoombtnregion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(btnAtlas,getApplicationContext(),"mzoom.png");
		try {
			btnAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0,1,1));
			btnAtlas.load();
		} catch(TextureAtlasBuilderException e) {
			Debug.e(e);
		}
		
		callback.onCreateResourcesFinished();
	}

	@Override
	public void onCreateScene(OnCreateSceneCallback callback){
		
		Scene scene = new Scene();
		
		
		scene.attachChild(new Sprite(0,0,bgregion,getVertexBufferObjectManager()));
		
		scene.attachChild(new Sprite(708,0,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(1416,0,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(2124,0,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-708,0,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-1416,0,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-2124,0,bgregion,getVertexBufferObjectManager()));
		
		scene.attachChild(new Sprite(0,-707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(0,707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(0,1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(0,2121,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(0,-1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(0,-2121,bgregion,getVertexBufferObjectManager()));

		scene.attachChild(new Sprite(708,-707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(708,707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(708,1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(708,2121,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(708,-1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(708,-2121,bgregion,getVertexBufferObjectManager()));
		
		scene.attachChild(new Sprite(-708,-707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-708,707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-708,1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-708,2121,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-708,-1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-708,-2121,bgregion,getVertexBufferObjectManager()));
		

		scene.attachChild(new Sprite(1416,-707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(1416,707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(1416,1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(1416,2121,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(1416,-1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(1416,-2121,bgregion,getVertexBufferObjectManager()));
		

		scene.attachChild(new Sprite(-1416,-707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-1416,707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-1416,1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-1416,2121,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-1416,-1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-1416,-2121,bgregion,getVertexBufferObjectManager()));
		

		scene.attachChild(new Sprite(2124,-707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(2124,707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(2124,1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(2124,2121,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(2124,-1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(2124,-2121,bgregion,getVertexBufferObjectManager()));
		

		scene.attachChild(new Sprite(-2124,-707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-2124,707,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-2124,1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-2124,2121,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-2124,-1414,bgregion,getVertexBufferObjectManager()));
		scene.attachChild(new Sprite(-2124,-2121,bgregion,getVertexBufferObjectManager()));

		scene.setOnSceneTouchListener(this);
		
		callback.onCreateSceneFinished(scene);
	}

	@Override
	public void onPopulateScene(Scene scene, OnPopulateSceneCallback callback)
	{
		
		createSolarSystem(scene);
		callback.onPopulateSceneFinished();
	}
	
	private void createSolarSystem(Scene scene)
	{
		Sprite sun = new Sprite(325,165,sunregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Sun",0);
					break;
	     		}
	     		return true;
			}
		};
		scene.registerTouchArea(sun);
		scene.attachChild(sun);
		
		
		
		Sprite morbit = new Sprite(250,90,mercuryOrbitregion,getVertexBufferObjectManager());
		//Sprite mercury = new Sprite(535,225,mercuryregion,getVertexBufferObjectManager());
		Sprite mercury = new Sprite(285,150,mercuryregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Mercury",1);
					break;
	     		}
	     		return true;
			}
		};
		scene.registerTouchArea(mercury);
		
		RotationByModifier mercury_rbm = new RotationByModifier(10.0f,360);
		LoopEntityModifier mercury_lm = new LoopEntityModifier(mercury_rbm);
		morbit.registerEntityModifier(mercury_lm);
		
		RotationByModifier mercury_rbm1 = new RotationByModifier(292,360);
		LoopEntityModifier mercury_lm1 = new LoopEntityModifier(mercury_rbm1);
		mercury.registerEntityModifier(mercury_lm1);
		
		scene.attachChild(morbit);
		morbit.attachChild(mercury);
		
		
		
		Sprite vorbit = new Sprite(190,30,venusOrbitregion,getVertexBufferObjectManager());
		Sprite venus = new Sprite(385,210,venusregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Venus",2);
					break;
	     		}
	     		return true;
			}
		};
		scene.registerTouchArea(venus);
		
		RotationByModifier venus_rbm = new RotationByModifier(20,360);
		LoopEntityModifier venus_lm = new LoopEntityModifier(venus_rbm);
		vorbit.registerEntityModifier(venus_lm);
		
		RotationByModifier venus_rbm1 = new RotationByModifier(583.75f,360);
		LoopEntityModifier venus_lm1 = new LoopEntityModifier(venus_rbm1);
		venus.registerEntityModifier(venus_lm1);
		
		scene.attachChild(vorbit);
		vorbit.attachChild(venus);
		
		
		
		Sprite eorbit = new Sprite(120,-40,earthOrbitregion,getVertexBufferObjectManager());
		Sprite earth = new Sprite(525,280,earthregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Earth",3);
					break;
	     		}
	     		return true;
			}
		};
		
		scene.registerTouchArea(earth);
		
		RotationByModifier earth_rbm = new RotationByModifier(30,360);
		LoopEntityModifier earth_lm = new LoopEntityModifier(earth_rbm);
		eorbit.registerEntityModifier(earth_lm);
		
		RotationByModifier earth_rbm1 = new RotationByModifier(5,360);
		LoopEntityModifier earth_lm1 = new LoopEntityModifier(earth_rbm1);
		earth.registerEntityModifier(earth_lm1);
		
		scene.attachChild(eorbit);
		eorbit.attachChild(earth);
		
		
		
		Sprite marsorbit = new Sprite(30,-130,marsOrbitregion,getVertexBufferObjectManager());
		Sprite mars = new Sprite(720,370,marsregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Mars",4);
					break;
	     		}
	     		return true;
			}
		};
		scene.registerTouchArea(mars);
		
		
		RotationByModifier mars_rbm = new RotationByModifier(55,360);
		LoopEntityModifier mars_lm = new LoopEntityModifier(mars_rbm);
		marsorbit.registerEntityModifier(mars_lm);
		
		RotationByModifier mars_rbm1 = new RotationByModifier(5,360);
		LoopEntityModifier mars_lm1 = new LoopEntityModifier(mars_rbm1);
		mars.registerEntityModifier(mars_lm1);
		
		scene.attachChild(marsorbit);
		marsorbit.attachChild(mars);
		
		
		
		Sprite astroidbelt = new Sprite(-52.5f,-212.5f,astroidBeltregion,getVertexBufferObjectManager());
		
		RotationByModifier astroidbelt_rbm = new RotationByModifier(120,360);
		LoopEntityModifier astroidbelt_lm = new LoopEntityModifier(astroidbelt_rbm);
		astroidbelt.registerEntityModifier(astroidbelt_lm);
		
		scene.attachChild(astroidbelt);
		
		
		
		Sprite jupiterorbit = new Sprite(-200,-360,jupiterOrbitregion,getVertexBufferObjectManager());
		Sprite jupiter = new Sprite(1140,540,jupiterregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Jupiter",5);
					break;
	     		}
	     		return true;
			}
		};
		scene.registerTouchArea(jupiter);
		jupiterorbit.setSize(1200,1200);
		jupiterorbit.setRotationCenter(600,600);
		
		RotationByModifier jupiter_rbm = new RotationByModifier(360,360);
		LoopEntityModifier jupiter_lm = new LoopEntityModifier(jupiter_rbm);
		jupiterorbit.registerEntityModifier(jupiter_lm);
		
		RotationByModifier jupiter_rbm1= new RotationByModifier(2,360);
		LoopEntityModifier jupiter_lm1 = new LoopEntityModifier(jupiter_rbm1);
		jupiter.registerEntityModifier(jupiter_lm1);
		
		scene.attachChild(jupiterorbit);
		jupiterorbit.attachChild(jupiter);
		
		
		
		Sprite saturnorbit = new Sprite(-400,-560,jupiterOrbitregion,getVertexBufferObjectManager());
		Sprite saturn = new Sprite(1495,695,saturnregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Saturn",6);
					break;
	     		}
	     		return true;
			}
		};
		scene.registerTouchArea(saturn);
		saturnorbit.setSize(1600,1600);
		saturnorbit.setRotationCenter(800,800);
		saturnorbit.setRotation(120.0f);
		
		RotationByModifier saturn_rbm = new RotationByModifier(870,360);
		LoopEntityModifier saturn_lm = new LoopEntityModifier(saturn_rbm);
		saturnorbit.registerEntityModifier(saturn_lm);
		
		RotationByModifier saturn_rbm1 = new RotationByModifier(2.5f,360);
		LoopEntityModifier saturn_lm1 = new LoopEntityModifier(saturn_rbm1);
		saturn.registerEntityModifier(saturn_lm1);
		
		scene.attachChild(saturnorbit);
		saturnorbit.attachChild(saturn);
		
		
		
		Sprite uranusorbit = new Sprite(-600,-760,jupiterOrbitregion,getVertexBufferObjectManager());
		Sprite uranus = new Sprite(1950,950,uranusregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Uranus",7);
					break;
	     		}
	     		return true;
			}
		};
		scene.registerTouchArea(uranus);
		uranusorbit.setSize(2000,2000);
		uranusorbit.setRotationCenter(1000,1000);
		uranusorbit.setRotation(270.0f);
		
		RotationByModifier uranus_rbm = new RotationByModifier(2520,360);
		LoopEntityModifier uranus_lm = new LoopEntityModifier(uranus_rbm);
		uranusorbit.registerEntityModifier(uranus_lm);
		
		RotationByModifier uranus_rbm1 = new RotationByModifier(4.0f,360);
		LoopEntityModifier uranus_lm1 = new LoopEntityModifier(uranus_rbm1);
		uranus.registerEntityModifier(uranus_lm1);
		
		scene.attachChild(uranusorbit);
		uranusorbit.attachChild(uranus);
		
		
		
		Sprite neptuneorbit = new Sprite(-800,-960,jupiterOrbitregion,getVertexBufferObjectManager());
		Sprite neptune = new Sprite(2350,1150,neptuneregion,getVertexBufferObjectManager())
		{
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_UP:
					showInfoActivity("Neptune",8);
					break;
	     		}
	     		return true;
			}
		};
		scene.registerTouchArea(neptune);
		neptuneorbit.setSize(2400,2400);
		neptuneorbit.setRotationCenter(1200, 1200);
		neptuneorbit.setRotation(315.0f);
		
		RotationByModifier neptune_rbm = new RotationByModifier(4950,360);
		LoopEntityModifier neptune_lm = new LoopEntityModifier(neptune_rbm);
		neptuneorbit.registerEntityModifier(neptune_lm);
		
		RotationByModifier neptune_rbm1 = new RotationByModifier(4.0f,360);
		LoopEntityModifier neptune_lm1 = new LoopEntityModifier(neptune_rbm1);
		neptune.registerEntityModifier(neptune_lm1);
		
		scene.attachChild(neptuneorbit);
		neptuneorbit.attachChild(neptune);
		
		mCamera.setZoomFactor(0.2f);
		scene.registerUpdateHandler(new TimerHandler(0.01f,new ITimerCallback(){

			@Override
			public void onTimePassed(TimerHandler th) {
				if(mCamera.getZoomFactor()<=1.0f)
				{
					mCamera.setZoomFactor(mCamera.getZoomFactor()+0.005f);
					th.reset();
				}
			}
			
		}));
		
		
		HUD h = new HUD();
		mCamera.setHUD(h);
		
		Sprite mbtn = new Sprite(740-crp.getMarginHorizontal(),420-crp.getMarginVertical(),mzoombtnregion,getVertexBufferObjectManager()){
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_DOWN:
	     			if(mCamera.getZoomFactor()>0.2f)
	     				mCamera.setZoomFactor(mCamera.getZoomFactor()-0.05f);
					break;
	     		}
	     		return true;
			}
		};
		h.registerTouchArea(mbtn);
		h.attachChild(mbtn);
		
		Sprite pbtn = new Sprite(680-crp.getMarginHorizontal(),420-crp.getMarginVertical(),pzoombtnregion,getVertexBufferObjectManager()){
			@Override
	     	public boolean onAreaTouched(final TouchEvent pAreaTouchEvent, final float pTouchAreaLocalX, final float pTouchAreaLocalY){
	     		switch(pAreaTouchEvent.getAction()){
	     		case TouchEvent.ACTION_DOWN:
					if(mCamera.getZoomFactor()<1.0f)
						mCamera.setZoomFactor(mCamera.getZoomFactor()+0.05f);
					break;
	     		}
	     		return true;
			}
		};
		h.registerTouchArea(pbtn);
		h.attachChild(pbtn);
	}

	@Override
	public boolean onSceneTouchEvent(Scene scene, TouchEvent te) {
		
		if(te.isActionDown())
		{
			lx = te.getX();
			ly = te.getY();
			
			//mCamera.setZoomFactor(0.25f);
			return true;
		}
		
		if(te.isActionMove())
		{
			/*
			if(te.getX()<lx)
				mCamera.offsetCenter(lx-te.getX(),0);
			if(te.getX()>lx)
				mCamera.offsetCenter(te.getX()-lx,0);
			if(te.getY()<ly)
				mCamera.offsetCenter(0,ly-te.getY());
			if(te.getY()>ly)
				mCamera.offsetCenter(0,te.getY()-ly);
			*/
			
			if(te.getX()<lx-10)
				mCamera.offsetCenter(10,0);
			if(te.getX()>lx+10)
				mCamera.offsetCenter(-10,0);
			if(te.getY()<ly-10)
				mCamera.offsetCenter(0,10);
			if(te.getY()>ly+10)
				mCamera.offsetCenter(0,-10);
			
			
			lx = te.getX();
			ly = te.getY();
			return true;
		}
		
		if(te.isActionUp())
		{
			lx = te.getX();
			ly = te.getY();
			//mCamera.setZoomFactor(1.0f);
			return true;
		}
		
		return false;
	}

	
	private void showInfoActivity(final String name,final int no)
	{
		this.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				Intent i = new Intent(SolarSystemActivity.this,InfoActivity.class);
				i.putExtra("planetname",name);
				i.putExtra("planetno",no);
				SolarSystemActivity.this.startActivity(i);
			}
			
		});
	}
	
}
