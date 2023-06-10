/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoFinal;

import java.io.Serializable;

/**
 *
 * @author famfi
 */
public class Level implements Serializable{

    public boolean completed;
    public boolean available;
    public int score;
    public int stars;

    public Level(boolean available, boolean completed, int score, int stars){
        this.available = available;
        this.completed=completed;
        this.score = score;
        this.stars = stars;
    }
        
        
    
}
