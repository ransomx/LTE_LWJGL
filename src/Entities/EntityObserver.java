/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Observable;

/**
 *
 * @author Dominik
 */
public class EntityObserver extends Observable{
    public static Entity selected;

    public Entity getSelected() {
        return selected;
    }

    public void setSelected(Entity selected) {
        this.selected = selected;
        this.setChanged();
    }
}
