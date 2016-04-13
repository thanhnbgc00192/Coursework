/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author JelZ
 */
public class Position {
    private String p_acronym;
    private String p_position;

    public Position(String p_acronym, String p_position) {
        this.p_acronym = p_acronym;
        this.p_position = p_position;
    }

    public Position() {
    }

    public Position(String p_position) {
        this.p_position = p_position;
    }

    public String getP_acronym() {
        return p_acronym;
    }

    public void setP_acronym(String p_acronym) {
        this.p_acronym = p_acronym;
    }

    public String getP_position() {
        return p_position;
    }

    public void setP_position(String p_position) {
        this.p_position = p_position;
    }
    
    
}
