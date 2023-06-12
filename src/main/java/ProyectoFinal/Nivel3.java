/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal;
/*
 * Copyright (c) 2010-2022 William Bittle  http://www.dyn4j.org/
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met:
 * 
 *   * Redistributions of source code must retain the above copyright notice, this list of conditions 
 *     and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above copyright notice, this list of conditions 
 *     and the following disclaimer in the documentation and/or other materials provided with the 
 *     distribution.
 *   * Neither the name of dyn4j nor the names of its contributors may be used to endorse or 
 *     promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR 
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND 
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL 
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, 
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import org.dyn4j.samples.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import menus.menuNIveles;

import org.dyn4j.collision.AxisAlignedBounds;
import org.dyn4j.collision.CategoryFilter;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.dynamics.contact.Contact;
import org.dyn4j.dynamics.joint.DistanceJoint;
import org.dyn4j.dynamics.joint.RevoluteJoint;
import org.dyn4j.geometry.Circle;
import org.dyn4j.geometry.Convex;
import org.dyn4j.geometry.Geometry;
import org.dyn4j.geometry.MassType;
import org.dyn4j.geometry.Transform;
import org.dyn4j.geometry.Vector2;
import org.dyn4j.samples.framework.Camera;
import org.dyn4j.samples.framework.SimulationBody;
import org.dyn4j.samples.framework.SimulationFrame;
import org.dyn4j.samples.framework.input.BooleanStateKeyboardInputHandler;
import org.dyn4j.samples.framework.input.Key;
import org.dyn4j.samples.framework.input.ToggleStateKeyboardInputHandler;
import org.dyn4j.world.ContactCollisionData;
import org.dyn4j.world.NarrowphaseCollisionData;
import org.dyn4j.world.listener.BoundsListener;
import org.dyn4j.world.listener.BoundsListenerAdapter;
import org.dyn4j.world.listener.CollisionListener;
import org.dyn4j.world.listener.CollisionListenerAdapter;
import org.dyn4j.world.listener.ContactListener;
import org.dyn4j.world.listener.ContactListenerAdapter;

/**
 * A scene where the user can play basket ball by configuring the trajectory.
 * @author William Bittle
 * @version 5.0.0
 * @since 5.0.0
 */

/**
 *
 * @author César Reyes Torres
 */
public class Nivel3 extends SimulationFrame{
    	/** Generated serial version id */
	private static final long serialVersionUID = 8357585473409415833L;
	
	// images * @since 5.0.0


	/** The basketball image */
	private static final BufferedImage RED_BIRD = getImageSuppressExceptions("Red.png");
        private static final BufferedImage YELLOW_BIRD = getImageSuppressExceptions("Yellow.png");
        private static final BufferedImage WHITE_BIRD = getImageSuppressExceptions("White.png");
        private static final BufferedImage WHITE_BIRD_EGG = getImageSuppressExceptions("Egg.png");
        private static final BufferedImage WHITE_BIRD_BOOM = getImageSuppressExceptions("Boom.png");
        private static final BufferedImage NORMAL_PIG = getImageSuppressExceptions("Pig2.png");
        private static final BufferedImage BACKGROUND = getImageSuppressExceptions("backgroundR.png");
        
	/** Helper function to read the images from the class path */
	private static final BufferedImage getImageSuppressExceptions(String imagenSeleccionada) {
		try {
                        
			return ImageIO.read(new File(imagenSeleccionada));
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * A custom body that uses an image instead.
	 * @author William Bittle
	 * @version 3.2.1
	 * @since 3.2.0
	 */
	private final class ImageBody extends SimulationBody {
		/** The image to use, if required */
		private final BufferedImage image;
		
		public ImageBody(BufferedImage image) {
			this.image = image;
		}
		
		/* (non-Javadoc)
		 * @see org.dyn4j.samples.SimulationBody#renderFixture(java.awt.Graphics2D, double, org.dyn4j.dynamics.BodyFixture, java.awt.Color)
		 */
		@Override
		protected void renderFixture(Graphics2D g, double scale, BodyFixture fixture, Color color) {
			// do we need to render an image?
			if (this.image != null) {
				// get the shape on the fixture
				Convex convex = fixture.getShape();
				// check the shape type
				if (convex instanceof Circle) {
					// cast the shape to get the radius
					Circle c = (Circle) convex;
					double r = c.getRadius();
					Vector2 cc = c.getCenter();
					int x = (int)Math.ceil((cc.x - r) * scale);
					int y = (int)Math.ceil((cc.y - r) * scale);
					int w = (int)Math.ceil(r * 2 * scale);
						// lets us an image instead
						g.drawImage(this.image, x, y, w, w, null);
				}
			} else {
				// default rendering
				super.renderFixture(g, scale, fixture, color);
			}
		}
	}
	
	private static final long ALL = Long.MAX_VALUE;
	private static final long BALL = 1;
	private static final long OTHER = 4;
	
	private static final CategoryFilter ballFilter = new CategoryFilter(BALL,ALL);
	private static final CategoryFilter allFilter = new CategoryFilter(OTHER, ALL);
        private static final CategoryFilter backgroundFilter = new CategoryFilter(OTHER, ALL ^ BALL ^ OTHER);
	
	// input control
	
	private final BooleanStateKeyboardInputHandler up;
	private final BooleanStateKeyboardInputHandler down;
	private final BooleanStateKeyboardInputHandler angleUp;
	private final BooleanStateKeyboardInputHandler angleDown;
	private final BooleanStateKeyboardInputHandler left;
	private final BooleanStateKeyboardInputHandler right;
	
	private final BooleanStateKeyboardInputHandler plus;
	private final BooleanStateKeyboardInputHandler minus;
	
	private final BooleanStateKeyboardInputHandler shoot;
	private final ToggleStateKeyboardInputHandler path;
	
	private final Vector2 start = new Vector2();
	private final Vector2 direction = new Vector2();
	private double power = 0.0;
	private int score = 0;
        
        private final int nP;
        private final int nC;
        private int nPajaros;
        private int nCerdos;
        private boolean canMove;    //Determina si ya podemos lazar otro pajaro, o los objetos en el mundo se siguen moviendo
        private ImageBody circle;   //Guardará los datos del pajaro actual creado (Para modificar poderes)
        private boolean powerUsed;
        private boolean huevaso;
        private int nColisiones;
        private boolean blockPower;
        protected menuNIveles papa;
        
        private final List<SimulationBody> structure = new ArrayList<SimulationBody>();
        private final List<SimulationBody> pigs = new ArrayList<SimulationBody>();
        private final List<SimulationBody> blocks = new ArrayList<SimulationBody>();

        private final List<SimulationBody> bird = new ArrayList<SimulationBody>();
        private final List<SimulationBody> slingshot = new ArrayList<SimulationBody>();
        private final List<SimulationBody> allowedBlocks = new ArrayList<SimulationBody>();

        private int totalBlocks;
        private boolean canKill;

	
	private SimulationBody rim;
	
	// cache of bodies to remove
	private final List<SimulationBody> toRemove = new ArrayList<SimulationBody>();
	
	private final class BallUserData {
		public final Vector2 start = new Vector2();
	}
	
	/**
	 * Default constructor.
	 */
	//public Nivel3(JFrame daddy) {
        public Nivel3() {
		super("Angry Birds - Level 3");
		
                super.setMousePickingEnabled(false);
                
                //this.papa = (menuNIveles)daddy;
                
		this.up = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_UP);
		this.down = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_DOWN);
		this.left = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_LEFT);
		this.right = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_RIGHT);
		this.angleUp = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_W);
		this.angleDown = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_A);
		this.plus = new BooleanStateKeyboardInputHandler(this.canvas, new Key(KeyEvent.VK_PLUS), new Key(KeyEvent.VK_ADD), new Key(KeyEvent.VK_EQUALS, KeyEvent.SHIFT_DOWN_MASK));
		this.minus = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_MINUS, KeyEvent.VK_SUBTRACT);
		this.shoot = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_S);
		this.path = new ToggleStateKeyboardInputHandler(this.canvas, KeyEvent.VK_P);
		
		this.up.install();
		this.down.install();
		this.left.install();
		this.right.install();
		this.angleDown.install();
		this.angleUp.install();
		this.plus.install();
		this.minus.install();
		this.shoot.install();
		this.path.install();
                
                this.nP = 2;    //Constantes
                this.nC = 3;    //Constantes
                this.nPajaros = nP;
                this.nCerdos = nC;
                this.canMove = true;
                this.circle = new ImageBody(RED_BIRD);
                this.powerUsed = false;
                this.huevaso = false;
                this.nColisiones = 4;
                this.blockPower = false;
                this.canKill = true;
        
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#printControls()
	 */
	@Override
	protected void printControls() {
		super.printControls();
		
		printControl("Angle Up", "W", "Use the page up key to increase the shoot angle");
		printControl("Angle Down", "A", "Use the page down key to decrease the shoot angle");
		printControl("Increase Power", "+", "Use the + key to increase the shoot power");
		printControl("Decrease Power", "-", "Use the - key to decrease the shoot power");
		printControl("Shoot", "s", "Use the s key to shoot a basketball");
		printControl("Show Path", "p", "Use the p key to show the travel path");
	}
	
	/**
	 * Creates game objects and adds them to the world.
	 */
	protected void initializeWorld() {
                this.start.set(-25.0, 1.0);
		this.direction.set(new Vector2(Math.toRadians(15)));
		this.power = 15.0;
                
                //Para reseteo
                nPajaros = nP;  
                nCerdos = nC;
                powerUsed = false;
                huevaso = false;
                nColisiones = 0;
                blockPower = false;
                canKill = true;
		
                //Limites del mundo (Cualquiera que exceda, se elimina)
		AxisAlignedBounds bounds = new AxisAlignedBounds(146, 200);
		bounds.translate(25.0, 18.0);
		this.world.setBounds(bounds);
                
		// create the floor
		SimulationBody court = new SimulationBody(new Color(222, 184, 135));
		BodyFixture bf = court.addFixture(Geometry.createRectangle(143, 20)); //Longitud exacta calculada
		bf.setFilter(allFilter);
		court.setMass(MassType.INFINITE);
		// move the floor down a bit
		court.translate(10.0, -14.0);  //Translate exacto calculado
		this.world.addBody(court);
                
                // Create the background
                ImageBody body = new ImageBody(BACKGROUND);
                bf = body.addFixture(Geometry.createCircle(80));
                bf.setFilter(backgroundFilter);
                body.setMass(MassType.INFINITE);
                body.translate(20.0,14.35);
                this.world.addBody(body);
                
                createStructureAndPigs(bf);
		
                //  Create the Slingshot
                body = new ImageBody(RED_BIRD);  // Primer pajaro a usar
                bf = body.addFixture(Geometry.createCircle(1.0));
                bf.setFilter(backgroundFilter);
                body.setMass(MassType.INFINITE);
                body.translate(-24.0,2);
                this.world.addBody(body);
		
		// listen for basketballs leaving the bounds
		BoundsListener<SimulationBody, BodyFixture> bl = new BoundsListenerAdapter<SimulationBody, BodyFixture>() {
			@Override
			public void outside(SimulationBody body) {
				toRemove.add(body);
                                if(isBird(body)){  //Arrgelar problema donde el huevo no se elimina y genera otra oportunidad de poder
                                    powerUsed = true;
                                    if(nPajaros >0){    
                                            SimulationBody birdRR;
                                            switch(nPajaros){  // Cambiar dependiendo el pajaro (Incluso agregar case)
                                                case 1: {
                                                    birdRR = new ImageBody(YELLOW_BIRD);
                                                    BodyFixture bf = birdRR.addFixture(Geometry.createCircle(1.0));
                                                    bf.setFilter(backgroundFilter);
                                                    birdRR.setMass(MassType.INFINITE);
                                                    birdRR.translate(-24.0,2);
                                                    world.addBody(birdRR);
                                                    break;
                                                }
                                            }
                                        }
                                }
			}
		};
		this.world.addBoundsListener(bl);
                
		// Detectar colisiones entre diferentes objetos
		CollisionListener<SimulationBody, BodyFixture> cl = new CollisionListenerAdapter<SimulationBody, BodyFixture>() {
                        
			@Override
			public boolean collision(NarrowphaseCollisionData<SimulationBody, BodyFixture> collision) {
				SimulationBody b1 = collision.getBody1();
				SimulationBody b2 = collision.getBody2();
				if (isBall(b1) && isPig(b2) && canKill == true){  //Todo esto no permite matar dos cerdos seguidos (Para arreglar bug)
                                    canKill = false;
                                    killPig(b2);
                                    killSound();
                                }else if (isBird(b2 ) && isPig(b1) && canKill == true){
                                    canKill = false;
                                    killPig(b1);
                                    killSound();
                                }else if(isBlock(b1) && isPig(b2) && canKill == true){
                                    canKill = false;
                                    killPig(b2);
                                    killSound();
                                }else if(isBlock(b2) && isPig(b1) && canKill == true){
                                    canKill = false;
                                    killPig(b1);
                                    killSound();
                                }
                                if(isBird(b1) && isBlock(b2)){
                                    canKill = true;
                                }else if(isBlock(b1) && isBird(b2)){
                                    canKill = true;
                                }else if(isBird(b1) && isAllowedBlock(b2)){
                                    canKill = true;
                                }else if(isAllowedBlock(b1) && isBird(b2)){
                                    canKill = true;
                                }
                                if(isBird(b1)){
                                    if(nColisiones == 200){
                                        powerUsed = false;
                                        toRemove.add(world.getBody(world.getBodyCount()-1));
                                        // Mostrar el siguiente pajaro en la resortera (En caso de que muera desaparezca por colicion)
                                        // El otro caso esta en outside, cuando el pajaro sale del mundo delimitado
                                        if(nPajaros >0){    
                                            SimulationBody birdRR;
                                            switch(nPajaros){  // Cambiar dependiendo el pajaro (Incluso agregar case)
                                                case 1: {
                                                    birdRR = new ImageBody(YELLOW_BIRD);
                                                    BodyFixture bf = birdRR.addFixture(Geometry.createCircle(1.0));
                                                    bf.setFilter(backgroundFilter);
                                                    birdRR.setMass(MassType.INFINITE);
                                                    birdRR.translate(-24.0,2);
                                                    world.addBody(birdRR);
                                                    break;
                                                }
                                            }
                                        }
                                    }else{
                                        nColisiones++;
                                    }
                                }
                                if(isBall(b1) && b1 == world.getBody(world.getBodyCount()-1) && powerUsed == false){
                                    powerUsed = true;
                                    blockPower = true;  //Bloquea el uso del poder cuando el pajaro ya chocó y no uso su poder
                                }
                                
                                if(isBlock(b2) && isBall(b1)){
                                   score+=1000;
                                    b2.quality -= (b1.getLinearVelocity().getMagnitude()/4);
                                   
                                    if (b2.quality<=0){ 
                                        toRemove.add(b2);
                                        score+=5000;
                                    }else{ // si la calidad sigue siendo buena entonces solo hacemos mas oscuro el bloque
                                        b2.setColor(b2.underColor);
                                    }
                                }  
                                if(isAllowedBlock(b2) && isBall(b1)){
                                   score+=1000;
                                    b2.quality -= (b1.getLinearVelocity().getMagnitude()/4);
                                   
                                    if (b2.quality<=0){ 
                                        toRemove.add(b2);
                                        score+=5000;
                                    }else{ // si la calidad sigue siendo buena entonces solo hacemos mas oscuro el bloque
                                        b2.setColor(b2.underColor);
                                    }
                                }  
                                
				return super.collision(collision);
			}
		};
		this.world.addCollisionListener(cl);
		
        // Create and add the custom MouseAdapter (Para actualizar velocidad y direccion de los pajaros, etc)
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if(nPajaros == 0){ //Cambiar a numPajaro-1 (Pajaro amarillo)
                        if(powerUsed == false && blockPower == false){  //Limitar el poder a solo un uso
                            powerUsed = true;
                            //Esta declarado "global", para acceder a el desde otras funciones (poderes de los pajaros)
                            circle.setLinearVelocity(circle.getLinearVelocity().x*3, circle.getLinearVelocity().y*3);
                        }
                    }
                }
            }
        });
        }
        
        private void killSound(){
            new Thread(){
                public void run(){
                     try {
                          AudioInputStream ab = AudioSystem.getAudioInputStream(new File("deadPig.wav"));
                          Clip clip = AudioSystem.getClip();

                          clip.open(ab);
                          clip.start();

                          // Wait for the clip to finish playing
                          Thread.sleep(clip.getMicrosecondLength() / 1000);

                          clip.stop();
                          clip.close();
                      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                          // Proper exception handling/logging here
                          e.printStackTrace();
                      }
                }  
            }.start();
        }
        
        //Add to the world a block
        private void createBlock(BodyFixture bf, Color color, double width, double height, double density, double friction, MassType m, double x, double y, int quality){

            SimulationBody blockC = new SimulationBody(color, quality);
            bf = blockC.addFixture(Geometry.createRectangle(width, height));
            bf.setFilter(ballFilter);
            bf.setDensity(density);
            bf.setFriction(friction);
            blockC.setMass(m);
            blockC.translate(x, y);
            blockC.setLinearDamping(0.8);
            blocks.add(blockC);

            this.world.addBody(blockC);
        }
        
        //Add to the world a allowed block (can touch birds)
        private void createAllowedBlock(BodyFixture bf, Color color, double width, double height, double density, double friction, MassType m, double x, double y, int quality){
            SimulationBody blockC = new SimulationBody(color, quality);
            bf = blockC.addFixture(Geometry.createRectangle(width, height));
            bf.setFilter(ballFilter);
            bf.setDensity(density);
            bf.setFriction(friction);
            blockC.setMass(m);
            blockC.translate(x, y);
            blockC.setLinearDamping(0.8);
            allowedBlocks.add(blockC);
            this.world.addBody(blockC);
        }

        private void createPig(BodyFixture bf, String classPath, double radius, double density, double friction, MassType m, double x, double y){
            //Cerdos
            ImageBody circle = new ImageBody(NORMAL_PIG);
            //circle.setUserData(data);
            bf = circle.addFixture(Geometry.createCircle(radius), density, friction, 0.5);
            bf.setFilter(ballFilter);
            circle.setMass(m);
            circle.translate(x, y);
            //circle.setLinearVelocity(this.direction.x * this.power, this.direction.y * this.power);
            pigs.add(circle);
            this.world.addBody(circle);
        }
        
        private boolean isPig(SimulationBody b2){
            for(SimulationBody c : pigs){
                if(b2==c){
                    return true;
                }
            }
            return false;
        }
        
        private void killPig(SimulationBody b2){
            for(SimulationBody c : pigs){
                if(b2==c){
                    toRemove.add(b2);
                    nCerdos--;
                }
            }
        }

        private boolean isBlock(SimulationBody b2){
            for(SimulationBody e : blocks){
                if(b2 == e)
                    return true;

            }
            return false;
        }
        
        private boolean isAllowedBlock(SimulationBody b2){
            for(SimulationBody e : blocks){
                if(b2 == e)
                    return true;

            }
            return false;
        }
        
        private boolean isBird(SimulationBody b2){
            for(SimulationBody e : bird){
                if(b2 == e)
                    return true;

            }
            return false;
        }

        private void createStructureAndPigs(BodyFixture bf){
            //Bloques
            createBlock(bf, new Color(75, 60, 30), 19, 3, 14, 30, MassType.INFINITE, 24, -2.5, 99999);
            createBlock(bf, new Color(121, 92, 50), 2, 6, 14, 30, MassType.NORMAL, 16, 2, 10);
            createBlock(bf, new Color(121, 92, 50), 2, 6, 14, 30, MassType.NORMAL, 32, 2, 100);
            createBlock(bf, new Color(121, 92, 50), 2, 6, 14, 30, MassType.NORMAL, 24, 2, 100);
            createBlock(bf, new Color(121, 92, 50), 14, 1, 14, 30, MassType.NORMAL, 24, 5.5, 250);
            createBlock(bf, new Color(121, 92, 50), 14, 1, 14, 30, MassType.NORMAL, 24, 6.5, 250);
            createAllowedBlock(bf, new Color(121, 92, 50), 2, 2, 4, 30, MassType.NORMAL, 20, 0, 5);
            createAllowedBlock(bf, new Color(121, 92, 50), 2, 2, 4, 30, MassType.NORMAL, 28, 0, 5);
            createBlock(bf, Color.DARK_GRAY, 2, 2, 14, 30, MassType.NORMAL, 16, 6, 600);
            createBlock(bf, Color.DARK_GRAY, 2, 2, 14, 30, MassType.NORMAL, 32, 6, 600);
            createBlock(bf, new Color(125, 255, 255), 1.5, 6, 14, 30, MassType.NORMAL, 20, 10, 10);
            createBlock(bf, new Color(125, 255, 255), 1.5, 6, 41, 30, MassType.NORMAL, 28, 10, 10);
            createBlock(bf, new Color(121, 92, 50), 1.5, 6, 14, 30, MassType.NORMAL, 24, 10, 200);
            createBlock(bf, new Color(121, 92, 50), 8, 1, 14, 30, MassType.NORMAL, 24, 13.5, 200);
            createAllowedBlock(bf, new Color(121, 92, 50), 1, 1, 4, 30, MassType.NORMAL, 24, 14.5, 5);
            
            //Cerdos
            createPig(bf, "pig2.png", 1.5, 1, 0.2, MassType.NORMAL, 20,  2.5);
            createPig(bf, "pig2.png", 1.5, 1, 0.2, MassType.NORMAL, 28,  2.5);
            createPig(bf, "pig2.png", 1.5, 1, 0.2, MassType.NORMAL, 24,  16.5);
        }
        
	
	private boolean isBall(SimulationBody body) {
		return body.getUserData() instanceof BallUserData;
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#reset()
	 */
	@Override
	public void reset() {
		super.reset();
		this.score = 0;
		// other stuff gets reset in initializeWorld
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#initializeCamera(org.dyn4j.samples.framework.Camera)
	 */
	@Override
	protected void initializeCamera(Camera camera) {
		super.initializeCamera(camera);
		camera.scale = 15.0;
		camera.offsetX = 0.0;
		camera.offsetY = -100.0;
               
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#initializeSettings()
	 */
	@Override
	protected void initializeSettings() {
		super.initializeSettings();
		this.start.set(-10.0, -3.0);
		this.direction.set(new Vector2(Math.toRadians(15)));
		this.power = 15.0;
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#render(java.awt.Graphics2D, double)
	 */
	@Override
	protected void render(Graphics2D g, double elapsedTime) {
		super.render(g, elapsedTime);
                
                // Detectar si existe algun objeto en movimiento (Ayuda con la parte de finalizar juego)
                boolean allObjectsStopped = true;
                for (SimulationBody body : world.getBodies()) {  //Verifica si algun objeto del mundo tiene velocidad
                    // Verificar la velocidad lineal del cuerpo
                    if (!body.getLinearVelocity().isZero()) {
                        allObjectsStopped = false;
                        break;
                    }
                }
                if (allObjectsStopped) {
                    // Todos los objetos están sin moverse
                    blockPower = false;  //Resetear la posibilidad
                    if(nPajaros == 0 || nCerdos == 0){ //Si gasto todos los pajaros o Si mató a todos los cerdos
                        canMove = false;  //Ayuda a que no se pueda lanzar otro pajaro, a menos que todo este inmovil
                        endgame(); //Fin del juego
                    }else{
                        canMove = true;
                    }
                } else {
                    // Al menos un objeto se está moviendo
                    canMove = false;
                }

                
		AffineTransform tx = g.getTransform();
		g.scale(1, -1);
		g.translate(-this.getWidth() * 0.5 - this.getCameraOffsetX(), -this.getHeight() * 0.5 + this.getCameraOffsetY());
		
		// render the score
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 25));
		g.drawString("Score: " + this.score, 20, 50);
		
		// render the power and angle
		g.setColor(Color.WHITE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 12));
                g.drawString(String.format("Pause: space"), 20, 70);
		g.drawString(String.format("Restart: R"), 20, 86);
		g.drawString(String.format("Restart camera: H"), 20, 100);
                g.drawString(String.format("Throw: S"), 20, 114);
                
                //Birds
                g.drawString(String.format("%d  REMAINING BIRDS", nPajaros), 900, 50);
		
		g.setTransform(tx);
		
		// draw the trajectory based on the current
		// start position, direction, and power
		final double scale = this.getCameraScale();
		
		double x = start.x * scale;
		double y = start.y * scale;

		double vx = direction.x * power;
		double vy = direction.y * power;
		
		double gy = this.world.getGravity().y;
		double t = this.world.getSettings().getStepFrequency();
		
		// draw the helper angle, power, position vector
		g.setColor(Color.GRAY);
		//g.draw(new Line2D.Double(x, y, vx * scale * t * 20 + x, vy * scale * t * 20 + y));
		g.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
		
		if (this.path.isActive() == false) {  //Que al inicar se dibuje (cuando no se preciona P)
			g.setColor(new Color(150, 150, 150, 100));
                        for(int i = 0; i < 22; i++) {  //Original 1000 iteraciones
				g.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
				
				// integrate to get new velocity
				vy += gy * t;
				
				// integrate to get new position
				x += vx * t * scale;
				y += vy * t * scale;
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#handleEvents()
	 */
	@Override
	protected void handleEvents() {
		super.handleEvents();
		if (this.angleUp.isActive()) {
                    if(Math.toDegrees(this.direction.getDirection()) < 65){   //Limita el angulo a 65 grados max
                        this.direction.rotate(0.01);
                    }
		}
		if (this.angleDown.isActive()) {
                    if(Math.toDegrees(this.direction.getDirection()) > 10){   //Limita el angulo a 10 grados min
                        this.direction.rotate(-0.01);
                    }
		}
		if (this.plus.isActive()) {   //Limita la fuerza a 35 max
                    if(this.power < 35){
                        this.power += 0.05;
                    }
		}
		if (this.minus.isActive()) {   //Limita la fuerza a 15 min
                    if(this.power > 15){
                        this.power -= 0.05;
                    }
		}
		
		if (this.shoot.isActiveButNotHandled()) {
			this.shoot.setHasBeenHandled(true);
			if(nPajaros > 0 && canMove == true){  //Limita los pajaros (No permite que se lancen mas del limite) Y solo lo permite cuando nada se mueve
                            //shooting audio resortera
                            new Thread(){
                                public void run(){
                                     try {
                                          AudioInputStream ab = AudioSystem.getAudioInputStream(new File("Shoot.wav"));
                                          Clip clip = AudioSystem.getClip();

                                          clip.open(ab);
                                          clip.start();

                                          // Wait for the clip to finish playing
                                          Thread.sleep(clip.getMicrosecondLength() / 1000);

                                          clip.stop();
                                          clip.close();
                                      } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
                                          // Proper exception handling/logging here
                                          e.printStackTrace();
                                      }
                                }  
                            }.start();
                            
                            
                            //Borramos cuerpo anterior (Resortera)
                            toRemove.add(world.getBody(world.getBodyCount()-1));
                            
                            // create a circle
                            BallUserData data = new BallUserData();
                            data.start.x = start.x;
                            data.start.y = start.y;
			
                            //Esta declarado "global", para acceder a el desde otras funciones (poderes de los pajaros)
                            if(nPajaros == 1){
                                circle = new ImageBody(YELLOW_BIRD);
                                BodyFixture bf = circle.addFixture(Geometry.createCircle(1.0), 20.0, 30.0, 0.0);
                                bf.setFilter(ballFilter);
                            }else if(nPajaros == 2){
                                circle = new ImageBody(RED_BIRD);
                                BodyFixture bf = circle.addFixture(Geometry.createCircle(1.0), 10.0, 30.0, 0.0);
                                bf.setFilter(ballFilter);
                            }
                            //Se lanzará un pajaro
                            nPajaros--;
                            blockPower = false; //Reiniciar posibiliad de usar su poder
                            powerUsed = false;  //Posible solucion
                            nColisiones = 0;
                            
                            circle.setUserData(data);
                            BodyFixture bf = circle.addFixture(Geometry.createCircle(1.0), 10.0, 30.0, 0.0);
                            bf.setFilter(ballFilter);
                            circle.setMass(MassType.NORMAL);
                            //circle.translate(start);
                            circle.translate(start);
                            circle.setLinearVelocity(this.direction.x * this.power, this.direction.y * this.power);
                            bird.add(circle);
                            this.world.addBody(circle);
                        }
		}
		for (SimulationBody b : this.toRemove) {
			this.world.removeBody(b);
		}
	}
	
        //Funcion que determine el final del juego (Cuando gasta todos sus pajaros o mata a todos los cerdos)
        private void endgame(){
            if(nPajaros == 0 && nCerdos !=0){ //Perdió la partida (Se quedó sin pajaros y no mató a todos los cerdos)
                // Llamada al frame de puntuación (con un -1 de parámetro que indica derrota)
                new EndGame(score, 0, 3, -1, this);
            }else{  //Ganó la partida (puede que tenga pajaros de sobra, o no)
                //calcular puntuacion
                new EndGame(score+(nPajaros*10000), nPajaros+1, 3, 1, this);
            }
            this.stop();
            //this.dispose();
        }
        
        
	/**
	 * Entry point for the example application.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Nivel3 simulation = new Nivel3();
		simulation.run();
	}
}
