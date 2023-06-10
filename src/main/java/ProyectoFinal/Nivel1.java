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
public class Nivel1 extends SimulationFrame {
	/** Generated serial version id */
	private static final long serialVersionUID = 8357585473409415833L;
	
	// images * @since 5.0.0


	/** The basketball image */
	private static final BufferedImage RED_BIRD = getImageSuppressExceptions("Red.png");
        private static final BufferedImage YELLOW_BIRD = getImageSuppressExceptions("Yellow.png");
        private static final BufferedImage WHITE_BIRD = getImageSuppressExceptions("White.png");
        private static final BufferedImage WHITE_BIRD_EGG = getImageSuppressExceptions("Egg.png");
        private static final BufferedImage WHITE_BIRD_BOOM = getImageSuppressExceptions("Boom.png");
        private static final BufferedImage NORMAL_PIG = getImageSuppressExceptions("Pig.png");
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
	private static final long RIM = 2;
	private static final long OTHER = 4;
	
	private static final CategoryFilter ballFilter = new CategoryFilter(BALL, ALL ^ RIM);
	private static final CategoryFilter rimFilter = new CategoryFilter(RIM, ALL ^ BALL);
	private static final CategoryFilter allFilter = new CategoryFilter(OTHER, ALL);
        private static final CategoryFilter backgroundFilter = new CategoryFilter(OTHER, ALL ^ RIM ^ BALL ^ OTHER);
	
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
//<<<<<<< HEAD
        private final List<SimulationBody> bird = new ArrayList<SimulationBody>();
        private final List<SimulationBody> slingshot = new ArrayList<SimulationBody>();
//=======
        private int totalBlocks;
//>>>>>>> c48287fc0ee247313a616e4983a5f22987415acf
	
	private SimulationBody rim;
	
	// cache of bodies to remove
	private final List<SimulationBody> toRemove = new ArrayList<SimulationBody>();
	
	private final Object SCORE_BEGIN_IDENTIFIER = new Object();
	private final Object SCORE_COMPLETE_IDENTIFIER = new Object();
	
	private final class BallUserData {
		public final Vector2 start = new Vector2();
		public boolean enteredScoreBegin = false;
		public boolean enteredScoreComplete = false;
		public boolean scored = false;
	}
	
	/**
	 * Default constructor.
	 */
	public Nivel1(JFrame daddy) {
		super("Angry Birds - Level 1");
		
                super.setMousePickingEnabled(false);
                
                this.papa = (menuNIveles)daddy;
                
		this.up = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_UP);
		this.down = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_DOWN);
		this.left = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_LEFT);
		this.right = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_RIGHT);
		this.angleUp = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_PAGE_UP);
		this.angleDown = new BooleanStateKeyboardInputHandler(this.canvas, KeyEvent.VK_PAGE_DOWN);
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
                
                this.nP = 3;    //Constantes
                this.nC = 1;    //Constantes
                this.nPajaros = nP;
                this.nCerdos = nC;
                this.canMove = true;
                this.circle = new ImageBody(RED_BIRD);
                this.powerUsed = false;
                this.huevaso = false;
                this.nColisiones = 4;
                this.blockPower = false;
        
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#printControls()
	 */
	@Override
	protected void printControls() {
		super.printControls();
		
		printControl("Move Up", "Up", "Use the up key to move the shoot position up");
		printControl("Move Down", "Down", "Use the down key to move the shoot position down");
		printControl("Move Left", "Left", "Use the left key to move the shoot position left");
		printControl("Move Right", "Right", "Use the right key to move the shoot position right");
		printControl("Angle Up", "Pg Up", "Use the page up key to increase the shoot angle");
		printControl("Angle Down", "Pg Down", "Use the page down key to decrease the shoot angle");
		printControl("Increase Power", "+", "Use the + key to increase the shoot power");
		printControl("Decrease Power", "-", "Use the - key to decrease the shoot power");
		printControl("Shoot", "s", "Use the s key to shoot a basketball");
		printControl("Show Path", "p", "Use the p key to show the travel path");
	}
	
	/**
	 * Creates game objects and adds them to the world.
	 */
	protected void initializeWorld() {
                this.start.set(-28.0, 0.95);
		//this.start.set(-10.0, -3.0);
		this.direction.set(new Vector2(Math.toRadians(45)));
		this.power = 20.0;
                
                nPajaros = nP;  //Para reseteo
                nCerdos = nC;
                powerUsed = false;
                huevaso = false;
                nColisiones = 0;
                blockPower = false;
		
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
		
		// create the rim
		SimulationBody rim = new SimulationBody(new Color(255, 69, 0));
		bf = rim.addFixture(Geometry.createRectangle(2.0, 0.2));
		bf.setFilter(rimFilter);
		bf = rim.addFixture(Geometry.createRectangle(0.2, 0.2));
		bf.setFilter(allFilter);
		bf.getShape().translate(-1.0, 0.0);
		rim.setMass(MassType.INFINITE);
		rim.translate(9.5, 50.0);
		this.world.addBody(rim);
                
		// save for rendering later 
		// NOTE: in the real world you'd implement rendering a smarter
		// way by grouping objects into layers
		this.rim = rim;
		
                
		// for scoring setup some sensor bodies
		SimulationBody sensorScoreBegin = new SimulationBody(new Color(255, 0, 0, 0));
		sensorScoreBegin.setUserData(SCORE_BEGIN_IDENTIFIER);
		bf = sensorScoreBegin.addFixture(Geometry.createRectangle(2.0, 2.0));
		bf.setSensor(true); 
		sensorScoreBegin.setMass(MassType.INFINITE);
		sensorScoreBegin.translate(9.5, 5.0);
		this.world.addBody(sensorScoreBegin);
		
		SimulationBody sensorScoreAdd = new SimulationBody(new Color(0, 255, 0, 0));
		sensorScoreAdd.setUserData(SCORE_COMPLETE_IDENTIFIER);
		bf = sensorScoreAdd.addFixture(Geometry.createRectangle(1.7, 1.25));
		bf.setSensor(true);
		sensorScoreAdd.setMass(MassType.INFINITE);
		sensorScoreAdd.translate(9.5, 3.0);
		this.world.addBody(sensorScoreAdd);
                
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
                                if(isBird(body)){  //(INTENTO)Arrgelar problema donde el huevo no se elimina y genera otra oportunidad de poder
                                    powerUsed = true;
                                }
			}
		};
		this.world.addBoundsListener(bl);
                
                //CREATE 
                /*
                FileOutputStream archFOS;
                ObjectOutputStream os;
                
                Score initializer = new Score();
                initializer.levels.add(new Level(true, false, 0, 0));
                initializer.levels.add(new Level(false, false, 0, 0));
                initializer.levels.add(new Level(false, false, 0, 0));
                initializer.levels.add(new Level(false, false, 0, 0));
                initializer.levels.add(new Level(false, false, 0, 0));
                initializer.levels.add(new Level(false, false, 0, 0));
                
                try{
                    archFOS = new FileOutputStream("Score.angryBirds");
                    os = new ObjectOutputStream(archFOS);
                    
                    os.writeObject(initializer);
                }catch(IOException e){
                    e.printStackTrace();
                }
                */
		
		// use a CollisionListener to detect when the body is in the scoring zones
		CollisionListener<SimulationBody, BodyFixture> cl = new CollisionListenerAdapter<SimulationBody, BodyFixture>() {
                        
                        
			@Override
			public boolean collision(NarrowphaseCollisionData<SimulationBody, BodyFixture> collision) {
				SimulationBody b1 = collision.getBody1();
				SimulationBody b2 = collision.getBody2();
				if (isBall(b1) && isScoreBegin(b2)) {
					BallUserData bud = (BallUserData)b1.getUserData();
					if (!bud.scored) {
						bud.enteredScoreBegin = true;
						bud.enteredScoreComplete = false;
					}
				} else if (isBall(b1) && isScoreComplete(b2)) {
					BallUserData bud = (BallUserData)b1.getUserData();
					if (!bud.scored && bud.enteredScoreBegin) {
						bud.enteredScoreComplete = true;
					} else {
						bud.enteredScoreBegin = false;
					}
				}else if (isBall(b1) && isPig(b2)){
                                    nCerdos--;
                                }
                                else if(isBlock(b1) && isPig(b2)){
                                    nCerdos--;
                                }else if(isBird(b1)){
                                    if(nColisiones == 350){
                                        powerUsed = false;
                                        toRemove.add(world.getBody(world.getBodyCount()-1));
                                        // Mostrar el siguiente pajaro en la resortera
                                          // Dibiuar en la resortera el siguiente pajaro
                                          
                                        if(nPajaros >0){    
                                            SimulationBody birdRR;
                                            switch(nPajaros){
                                                case 2: {
                                                    birdRR = new ImageBody(YELLOW_BIRD);
                                                    BodyFixture bf = birdRR.addFixture(Geometry.createCircle(1.0));
                                                    bf.setFilter(backgroundFilter);
                                                    birdRR.setMass(MassType.INFINITE);
                                                    birdRR.translate(-24.0,2);
                                                    world.addBody(birdRR);
                                                    break;
                                                }
                                                case 1: {
                                                    birdRR = new ImageBody(WHITE_BIRD);
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
                                }//System.out.println("power " + powerUsed + "   " + blockPower + "    " + nPajaros + "  " );
                                if(isBall(b1) && b1 == world.getBody(world.getBodyCount()-1) && powerUsed == false){
                                    powerUsed = true;
                                    blockPower = true;  //Bloquea el uso del poder cuando el pajaro ya chocó y no uso su poder
                                }
                                if (isBall(b1) && b1 == world.getBody(world.getBodyCount()-1) && powerUsed == true && nPajaros == 0 && blockPower == false) {  //Para diferenciar el pajaro del huevo
                                    if(huevaso == false){  //Limitar a un huevo
                                        
                                        if(nColisiones == 0){  //Elimina el huevo lanzado
                                            toRemove.add(world.getBody(world.getBodyCount()-1));
                                            nColisiones++;
                                        }
                                        
                                        huevaso = true;  //Se puede dejar asi, solo cuando haya un solo pajaro blanco
                                        Vector2 position = circle.getTransform().getTranslation();  //Obtener coordenadas del objeto en el mundo
                                        
                                        // create a circle
                                        BallUserData data = new BallUserData();
                                        data.start.x = position.x;
                                        data.start.y = position.y;
                                        circle = new ImageBody(WHITE_BIRD_BOOM);
                                        BodyFixture bf = circle.addFixture(Geometry.createCircle(3), 200.0, 0, .5);
                                        bf.setFilter(ballFilter);
                                        circle.setColor(Color.white);
                                        circle.setUserData(data);
                                        circle.setMass(MassType.NORMAL);
                                        circle.translate(world.getBody(world.getBodyCount()-1).getWorldCenter().x , world.getBody(world.getBodyCount()-1).getWorldCenter().y);
                                        //Se añade "bomba de huevo"
                                        world.addBody(circle);
                                    }else{
                                        if(nColisiones == 15){//  Detecta el limite de colisiones, para eliminar el objeto "bomba"
                                            nColisiones++;
                                            toRemove.add(world.getBody(world.getBodyCount()-1));
                                        }
                                        nColisiones++;
                                    }
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
                                
				return super.collision(collision);
			}
		};
		this.world.addCollisionListener(cl);
		
		// use a ContactListener to detect when the body leaves the scoring zone
		ContactListener<SimulationBody> ccl = new ContactListenerAdapter<SimulationBody>() {
			@Override
			public void end(ContactCollisionData<SimulationBody> collision, Contact contact) {
				SimulationBody b1 = collision.getBody1();
				SimulationBody b2 = collision.getBody2();
				
				if (isBall(b1) && isScoreComplete(b2)) {
					BallUserData bud = (BallUserData)b1.getUserData();
					// 1. if the ball hasn't been scored yet
					// 2. if the ball entered the score begin region
					// 3. if the ball entered the score complete region
					// 4. if it's now exiting the score complete region
					// then count it as a score
					if (!bud.scored && bud.enteredScoreBegin && bud.enteredScoreComplete) {
						bud.scored = true;
						
						// was it a two pointer or three pointer?
						
					} else {
						bud.enteredScoreBegin = false;
						bud.enteredScoreComplete = false;
					}
				}
				super.end(collision, contact);
			}
		};
		this.world.addContactListener(ccl);
                
        // Create and add the custom MouseAdapter (Para actualizar velocidad y direccion de los pajaros, etc)
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    
                    if(nPajaros == 1){ //Cambiar a numPajaro-1
                        if(powerUsed == false && blockPower == false){  //Limitar el poder a solo un uso
                            powerUsed = true;
                            //Esta declarado "global", para acceder a el desde otras funciones (poderes de los pajaros)
                            circle.setLinearVelocity(circle.getLinearVelocity().x*3, circle.getLinearVelocity().y*3);
                        }
                    }
                    if(nPajaros == 0){ //Cambiar a numPajaro-1
                        if(powerUsed == false && blockPower == false){
                            powerUsed = true;
                            
                            circle.setLinearVelocity(45.0, 45.0);
                            Vector2 position = circle.getTransform().getTranslation();  //Obtener coordenadas del objeto en el mundo
                            
                            // create a circle
                            BallUserData data = new BallUserData();
                            data.start.x = position.x;
                            data.start.y = position.y;
			
                            circle = new ImageBody(WHITE_BIRD_EGG);
                            circle.setUserData(data);
                            BodyFixture bf = circle.addFixture(Geometry.createCircle(0.5), 0.5, 100, 0.1);
                            bf.setFilter(ballFilter);
                            circle.setAngularVelocity(2.0);
                            circle.setMass(MassType.NORMAL);
                            circle.translate(position.x,position.y-1);
                            world.addBody(circle);
                        }
                    }
                }
            }
        });
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
        
        private void createImgBlock(BodyFixture bf, double w, double h, double density, double friction, MassType m, double x, double y, BufferedImage img){
            //Bloques con imagen
            ImageBody circle = new ImageBody(img);
            bf = circle.addFixture(Geometry.createRectangle(w, h), density, friction, 0.5);
            bf.setFilter(ballFilter);
            circle.setMass(m);
            circle.translate(x, y);
            blocks.add(circle);
            this.world.addBody(circle);
        }
        
        private boolean isPig(SimulationBody b2){
            for(SimulationBody c : pigs){
                if(b2==c){
                    toRemove.add(b2);
                    //System.out.println("Colission");
                    return true;
                }
                //System.out.println("for");
            }
            //System.out.println("a");
            return false;
        }

        private boolean isBlock(SimulationBody b2){
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
        
        private boolean isSlingshot(SimulationBody b2){
            for(SimulationBody e : slingshot){
                if(b2 == e)
                    return true;

            }
            return false;
        }

        private void createStructureAndPigs(BodyFixture bf){

            //Bloques
            createBlock(bf, Color.DARK_GRAY, 1, 6, 4, 15, MassType.NORMAL, 18, 0, 1500); // a la calidad se le resta la velocidad del otro objeto en una colision,
            createBlock(bf, Color.DARK_GRAY, 1, 6, 4, 15, MassType.NORMAL, 24, 0,1500); // cuando la calidad sea negativa el objeto se remueve del mundo
            createBlock(bf, new Color(121,92,50), 8, 1, 4, 15, MassType.NORMAL, 21, 3, 800);
            createBlock(bf, new Color(121, 92, 50), 0.8, 3.5, 4, 15, MassType.NORMAL, 19, 4, 800);
            createBlock(bf, new Color(121, 92, 50), 0.8, 3.5, 4, 15, MassType.NORMAL, 23, 4, 800);
            createBlock(bf, new Color(121, 92, 50), 5, 0.5, 4, 15, MassType.NORMAL, 21, 6, 800);

            createPig(bf, "pig2.png", 1.5, 1, 0.2, MassType.NORMAL, 21,  -2);

        }
        
	
	private boolean isBall(SimulationBody body) {
		return body.getUserData() instanceof BallUserData;
	}
	
	private boolean isScoreBegin(SimulationBody body) {
		return body.getUserData() == SCORE_BEGIN_IDENTIFIER;
	}
	
	private boolean isScoreComplete(SimulationBody body) {
		return body.getUserData() == SCORE_COMPLETE_IDENTIFIER;
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
		this.direction.set(new Vector2(Math.toRadians(45)));
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
                    //powerUsed = false;  //Resetear la posibilidad de usar poder
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
		g.drawString(String.format("Power: %1$.2f", this.power), 20, 70);
		g.drawString(String.format("Angle: %1$.2f", Math.toDegrees(this.direction.getDirection())), 20, 85);
		g.drawString(String.format("Position: (%1$.2f, %2$.2f)", this.start.x, this.start.y), 20, 100);
		
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
		g.setColor(Color.RED);
		g.draw(new Line2D.Double(x, y, vx * scale * t * 20 + x, vy * scale * t * 20 + y));
		g.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
		
		if (this.path.isActive()) {
			g.setColor(new Color(150, 150, 150, 100));
			for(int i = 0; i < 1000; i++) {
				g.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
				
				// integrate to get new velocity
				vy += gy * t;
				
				// integrate to get new position
				x += vx * t * scale;
				y += vy * t * scale;
			}
		}
		
		// render the rim again because we want it to 
		// look like the bballs are going through the hoop
		this.render(g, elapsedTime, this.rim);
	}
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#handleEvents()
	 */
	@Override
	protected void handleEvents() {
		super.handleEvents();
		
		if (this.left.isActive()) {
			this.start.x -= 0.05;
		}
		if (this.right.isActive()) {
			this.start.x += 0.05;
			if (this.start.x >= 7.5) {
				this.start.x = 7.5;
			}
		}
		if (this.down.isActive()) {
			this.start.y -= 0.05;
		}
		if (this.up.isActive()) {
			this.start.y += 0.05;
			if (this.start.y >= 3.0) {
				this.start.y = 3.0;
			}
		}
		if (this.angleUp.isActive()) {
			this.direction.rotate(0.01);
		}
		if (this.angleDown.isActive()) {
			this.direction.rotate(-0.01);
		}
		if (this.plus.isActive()) {
			this.power += 0.05;
		}
		if (this.minus.isActive()) {
			this.power -= 0.05;
		}
		
		if (this.shoot.isActiveButNotHandled()) {
			this.shoot.setHasBeenHandled(true);
                        System.out.println(nPajaros + "  " + canMove);
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
                                circle = new ImageBody(WHITE_BIRD);
                            }else if(nPajaros == 2){
                                circle = new ImageBody(YELLOW_BIRD);
                            }else if(nPajaros == 3){
                                circle = new ImageBody(RED_BIRD);  //Primer pajaro que sale
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
	
	/* (non-Javadoc)
	 * @see org.dyn4j.samples.framework.SimulationFrame#onPickingStart(org.dyn4j.samples.framework.SimulationBody)
	 */
	@Override
	protected void onBodyMousePickingStart(SimulationBody body) {
		super.onBodyMousePickingStart(body);
		
		// if the user picks up a ball using the mouse
		// disable scoring for that ball   
		if (isBall(body)) {
			BallUserData bud = (BallUserData)body.getUserData();
			bud.scored = true;
			bud.enteredScoreBegin = false;
			bud.enteredScoreComplete = false;
		}
                
                if(isSlingshot(body)){
                    //Resortera
                }
	}
	
        //Funcion que determine el final del juego (Cuando gasta todos sus pajaros o mata a todos los cerdos)
        private void endgame(){
            if(nPajaros == 0 && nCerdos !=0){ //Perdió la partida (Se quedó sin pajaros y no mató a todos los cerdos)
                // Lamada al frame de puntuacion (con un -1 de parámetro que indica derrota)
                new EndGame(score, 0, 1, -1, this);
                System.out.println("PERDISTE");
                
                //System.out.println("PERDISTE LA PARTIDA *************************");
            }else{  //Ganó la partida (puede que tenga pajaros de sobra, o no)
                // Lamada al frame de puntuacion (con un numero positivo de parámetro que indica los pajaros restantes)
                //calcular puntuacion
                System.out.println("GANASTE");
                new EndGame(score+(nPajaros*10000), nPajaros+1, 1, 1, this);
                //System.out.println("GANASTE LA PARTIDA *************************");
            }
            //this.dispose();
            this.stop();
        }
        
        
	/**
	 * Entry point for the example application.
	 * @param args command line arguments
	 */
	/*public static void main(String[] args) {
		Nivel1 simulation = new Nivel1();
		simulation.run();
	}*/
}
