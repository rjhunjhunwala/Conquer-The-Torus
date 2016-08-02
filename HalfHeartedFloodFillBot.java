/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toroidal.domination;

import java.awt.Color;
import java.util.Stack;

/**
 *
 * @author rohan
 */
public class HalfHeartedFloodFillBot extends Bot{
	public static class Node{
		public final int x,y;
		public Node(int x, int y){
			this.x=x;this.y=y;
		}
	}
private Stack<Integer> nodes = new Stack<>();
	public HalfHeartedFloodFillBot(int team, int inX, int inY) {
		super(team, inX, inY);
	}



	@Override
	public int move() {
		int x,y;

		for(int i = 0;i<4;i++){
	if(isEmpty(Bot.getColorAtSpot(x=getX()+(int) Math.cos(i*Math.PI/2),y= getY()+(int) Math.sin(i*Math.PI/2)))){
		nodes.add((i+2)%4);
		return i+4;
	}
}
		//no empty spots found try to backtrack or just do stuff!
return nodes.isEmpty()?Bot.getRandom(0,2)==0?Bot.getRandom(0, 2)==0?0:1:Bot.getRandom(0, 2)==0?2:3:nodes.pop();
	}
	boolean isEmpty(Color c){
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		return r == 0 && b == 0&& g == 0;
	}
}
