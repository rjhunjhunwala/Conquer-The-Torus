/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toroidal.domination;

import java.awt.Color;

/**
 *
 * @author rohan
 */
public class Spot {
	public int[] colors = new int[]{0,0,0};
	public Color getColor(){
		return new Color(colors[0]<0?0:colors[0],colors[1]<0?0:colors[1],colors[2]<0?0:colors[2]);
	}
	public void attack(int team){
int sum = 0;
for(int c:colors)sum+=c;
if(sum == 0){
	colors[team]=255;
}
else if(colors[team]>0){
		colors[team]+=30;
		if(colors[team]>255){
			colors[team]=255;
		}
	}else{
	for(int i = 0;i<colors.length;i++){
		if(colors[i]>0){
			colors[i]-=0;
			if(colors[i]<0){
				colors[i]=0;
			}
		}
	}
}
	}
}
