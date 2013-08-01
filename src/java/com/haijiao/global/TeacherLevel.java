/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.global;

/**
 *
 * @author fish
 */
public class TeacherLevel {
    private static Double levelmap[] = {0.08,0.05};
    
    public static double getPercentage(int level){
        if(level >= levelmap.length || level < 0)
            return 0;
        else 
            return levelmap[level];
    }
}
