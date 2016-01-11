/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.object;

/**
 *
 * @author Yannic
 */
public class RotationVector {
    float rotation;
    float accel;
    
    public RotationVector(float rot, float ac){
        rotation = rot;
        accel = ac;
    }
}
