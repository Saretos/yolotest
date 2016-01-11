/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroids.fundamentals;

import asteroids.object.GameObject;

/**
 *
 * @author Yannic
 */
public interface Spawner {
    public boolean spawn(GameObject go);
}
