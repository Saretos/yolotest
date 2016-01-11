package asteroids.state;

import asteroids.ai.AstroidsAI;
import asteroids.ai.Enemy.TestEnemyShip;
import asteroids.background.Background;
import asteroids.background.StarBackground;
import asteroids.camera.Camera;
import asteroids.fundamentals.Input;
import asteroids.fundamentals.Spawner;
import asteroids.object.Asteroid;
import asteroids.object.Collidable;
import asteroids.object.GameObject;
import asteroids.object.PlayerShip;
import asteroids.object.Ship;
import asteroids.object.ShipGenerator;
import asteroids.object.Weapon;
import asteroids.object.bullet.Bullet;
import asteroids.object.particle.Particle;
import asteroids.object.sprite.Sprite;
import asteroids.sound.SoundPlayer;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

import asteroids.sound.MidiPlayer;

/**
 *
 * @author Raildex
 */
public class ArcadeState implements State,Spawner
{
    public ArrayList<GameObject> objects;
    public ArrayList<Collidable> cObjects;
    private final ArrayList<Particle> particles;
    private final ArrayList<Bullet> bullets;
    private Background bg = new StarBackground(300, new Color(255, 255, 200), 6, .1f);
    private PlayerShip player = null;
    private Camera cam;
    Sprite fireBullet;
    Sprite smoke;
    private final String scoreFile = "highscores.txt";
    private StateMachine stateMachine;

    private int score = 0;
    private int gameId;

    private int randomAnzahlAsteroids;

    public ArcadeState(StateMachine s)
    {
        this.stateMachine = s;

        try {
            fireBullet = new Sprite(ImageIO.read(this.getClass().
                    getResource("/gfx/bullets.png")).getSubimage(0, 42, 12,
                    12));
            smoke = new Sprite(ImageIO.read(this.getClass().
                    getResource("/gfx/bullets.png")).getSubimage(180, 169, 11,
                    11));
        } catch (IOException ex) {
            Logger.getLogger(ArcadeState.class.getName()).log(Level.SEVERE, null, ex);
        }
        stateMachine = s;
        objects = new ArrayList<>();
        particles = new ArrayList<>();
        bullets = new ArrayList<>();
        cObjects = new ArrayList<>();
    }

    public GameObject getObject(int id) {
        return objects.get(id);
    }

    @Override
    public void render(Graphics2D g) {
        for (int i = 0; i < objects.size(); i++)
        {
            //if(cam.isInView(o))
            //{
                GameObject o = objects.get(i);
                o.render(g, cam);
            //}
        }

        g.setColor(Color.red);
        g.drawString("Score: ", (1280 / 2) - 45, 35);
        g.drawString(Integer.toString(score), (1280 / 2) + 45, 35);

        //TODO: Wenn keine Asterioden mehr im Bild sind -> Wechsel in die ScoreboardState
        if(randomAnzahlAsteroids==0)
        {
            //TODO: GameID richtig setzen
            gameId++;
            try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(scoreFile, true))))
            {
                //TODO: Name setzen (Einlesen)
                out.print("\nTobias\t" + Integer.toString(score) + " " + gameId);
                out.close();
            }
            catch (IOException e)
            {
            }
            stateMachine.setState(new ScoreboardState(stateMachine));
        }

        //Wechseln in den ScoreBoardState (Anzeige der Highscores)
        //TODO: Anzeige der Highscores
        //TODO: Auslesen aus Datei, Sortieren nach Score, anzeigen

        //Code für viele tolle bunte Linien
        /*
        g.setColor(Color.red);
        for (int i = 0; i < objects.size(); i++)
        {
            GameObject o1 = objects.get(i);
            for (int j = 0; j < objects.size(); j++)
            {
                GameObject o2 = objects.get(j);
                if (o1 == o2)
                    continue;
                if (o1 instanceof PlayerShip || o2 instanceof PlayerShip)
                {
                    g.setColor(Color.green);
                }
                else if (o1 instanceof Bullet || o2 instanceof Bullet)
                {
                    g.setColor(Color.blue);
                }
                else
                {
                    g.setColor(Color.red);
                }
                g.setColor(new Color((float) Math.random(), (float) Math.random(), (float) Math.random()));
                g.drawLine((int) o1.x, (int) o1.y, (int) o2.x, (int) o2.y);
            }
        }*/
    }

    @Override
    public void update(double delta) {
        //TODO sout Updating Arcade state
        //System.out.println("Updating arcade state.");
        bg.update(delta);
        for (int i = 0; i < objects.size(); i++) {
            GameObject o = objects.get(i);
            //TODO sout Asteroid @ ..
            //System.out.println(o.toString());
            o.update(delta);
            if(o.destroyMe)objects.remove(o);
        }

        for (int i = 0; i < bullets.size(); i++)
    {
      bullets.get(i).update(delta);
      if (bullets.get(i).lifeTime >= bullets.get(i).maxLifetime)
      {
        bullets.remove(i);
      }
    }
    for (int i = 0; i < particles.size(); i++)
    {
      particles.get(i).update(delta);
      if (particles.get(i).dead)
      {
        particles.remove(i);
      }
    }
        //Kollision
        for(int i = 0;i<objects.size();i++)
        {
            GameObject temp = objects.get(i);
            if(!(temp instanceof Bullet)&& (temp instanceof Collidable)){
                Collidable o = (Collidable)temp;
                //j = i+1 ?
                for(int j = 0;j<objects.size();j++){
                    GameObject temp2 = objects.get(j);
                    if(temp2 instanceof Collidable){
                        Collidable t = (Collidable)temp2;

                        if((temp.faction != temp2.faction || temp.faction == 'N' || temp2.faction =='N') && o != t && o.collisionAvailable() && t.collisionAvailable()){
                            if(o.getCollisionCircle().checkCollision(t.getCollisionCircle())){
                                o.collided(t);
                                t.collided(o);
                                //Wird noch auf Iteratoren umgestellt, da momentan durch die removes Objekte geskippt werden koennen.
                                if(((GameObject)o).destroyMe)objects.remove(o);
                                if(((GameObject)t).destroyMe)objects.remove(t);
                                //System.out.println("Collision between "+o+" and "+t);
                                if(o instanceof Asteroid || t instanceof Asteroid)
                                {
                                    randomAnzahlAsteroids--;
                                    //TODO: sout Anzahl verbleibender Asteroiden
                                    //System.out.println("Anzahl noch verbleibender Asteroiden: " + randomAnzahlAsteroids);
                                    score+=100;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(player.destroyMe)
        {
            stateMachine.setState(new ScoreboardState(stateMachine));
        }
        //gameObjectListUpdate();
    }


    @Override
    public boolean initState(Input i) {

        try
        {
            Scanner scanner = new Scanner(new File(scoreFile));
            while ((scanner.hasNextLine()))
            {
                if(scanner.hasNext())
                {
                    String t1;
                    int t2;
                    t1 = scanner.next();
                    t2 = scanner.nextInt();
                    try
                    {
                        gameId = scanner.nextInt();
                    }
                    catch (Exception e)
                    {
                        gameId = 0;
                    }
                }
                else
                {
                    gameId = 0;
                }
            }
            scanner.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        MidiPlayer.getInstance().loadMidiFile("Test");
        MidiPlayer.getInstance().play();

        //PlayerShip player = null;
        SoundPlayer.getInstance().loadSound("Laser_Shoot");
        cam = new Camera(0, 0, 1280, 720);
        try {
            player = new PlayerShip(640, 360, 3, 6, 0, 64, 64, new Sprite(ImageIO.read(this.getClass().getResourceAsStream("/gfx/SHIP_BASIC_0.png"))), i,this);
            player.setWeapon(Weapon.Type.FRONT, Weapon.testWeapon);
            player.setGenerator(new ShipGenerator("TEST", 0.5f, 100));
            
            TestEnemyShip e = new TestEnemyShip(640, 660, 3, 6, 0, 32, 32, new Sprite(ImageIO.read(this.getClass().getResourceAsStream("/gfx/SHIP_BASIC_0.png"))), i,this) {

                @Override
                public void fire(Weapon w) {
//                    fireBullets(this, w);
                }
            };
            e.setWeapon(Weapon.Type.FRONT, Weapon.testWeapon);
            e.setGenerator(new ShipGenerator("TEST", 0.5f, 100));
            objects.add(e);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        objects.add(player);
        //System.out.println(player);

        int randomX = 0;
        int randomY = 0;
        int randomImage;
        /**
         * Anzahl der Asteroiden von 1-10 (variabel)
         */
        randomAnzahlAsteroids = (int)(Math.random() * 20) + 1;
        /**
         * Erstellt ein Array in dem die relativen Pfade aller
         * im Ordner befindlichen *.png Bilder speichert
         */
        File dir[] = new File("res/gfx/asteroid/neu").listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".png");
            }
        });
        /**
         * Iteriert über die Anzahl der Asteroiden, holt mit randomImage und dem Array dir[]
         * ein zufällig gewähltes Sprite, und bestimmt den zufällig gewählten Startpunkt (randomX,randomY)
         */

        for (int j = 1; j <= randomAnzahlAsteroids; j++) {
            try {
                randomImage = (int) (Math.random() * dir.length);

                randomX = (int) (Math.random() * 1280);
                randomY = (int) (Math.random() * 720);
                while(!(randomX < ((1280/2)-50) || randomX > ((1280/2)+50) && randomY < ((720/2)-50) || randomY < ((720/2)+50))) {
                    randomX = (int) (Math.random() * 1280);
                    randomY = (int) (Math.random() * 720);
                }
                Sprite img = new Sprite(ImageIO.read(dir[randomImage]));
                objects.add(new Asteroid(randomX, randomY, 0.1f, img.getImage().getWidth(null),
                        img.getImage().getHeight(null), 0, 0, img.getImage(), Math.random(), this));

            } catch (Exception e) {
                Logger.getLogger(ArcadeState.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        new AstroidsAI(objects, player);
        return true;
    }

    @Override
    public boolean exitState() {
        return true;
    }
    
    @Override
    public boolean spawn(GameObject go){
        objects.add(go);
        return true;
    }

    public void createExplosion(double x, double y, int size, int intensity) {
    }

}
