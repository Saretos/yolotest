/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

import asteroids.fundamentals.CollisionCircle;

/**
 * Allgemeines Interface für Dinge mit Kollision, sei es eine Kollisionsbox oder ein Kollisionskreis.<br>
 * 
 * <b>Wird eventuell durch AWT.shape und dessen intersect Methode ersetzt!</b>
 * @author Yannic
 *
 */
public interface Collidable {
   
    public CollisionCircle getCollisionCircle();
    
    
    /**
     * 
     * @param c Das Objekt, mit dem das Objekt kollidiert.
     * Wird aufgerufen, wenn in der Arcade-State eine Kollision zweier Objekte passender Fraktion festgestellt wurde. Dient zur Umsetzung der Konsequenzen der Kollision.
     */
    public void collided(Collidable c);
    
    /**
     * Abfrage-Funktion zur Verfügbarkeit der Kollision dieses Objekts. Wird von der Kollisionsabfrage in der Arcade-State berücksichtigt.
     * @return Gibt zurück, ob das Objekt derzeit mit anderen Objekten kollidieren kann.
     */
    public boolean collisionAvailable();
     
}
