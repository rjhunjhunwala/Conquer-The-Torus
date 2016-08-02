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
public class TrumpAfterPenn extends Bot{
int moves;
int direction;
int moveLeftTime;
private final Color myTeam;
boolean[][] visited = new boolean[Bot.getMap().length][Bot.getMap()[0].length];
public TrumpAfterPenn(int team, int inX, int inY) {
		super(team, inX, inY);
switch(team){
	case 0:
		myTeam = Color.red;
	break;
	case 1:
				myTeam=Color.GREEN;
		break;
	default:
		myTeam = Color.BLUE;
		break;
}
		direction = Bot.getRandom(0, 4);
	}
int movesUpward = 0;
	int way = getRandom(0,2)==1?1:-1;
@Override
	public int move() {
		moves++;
int x,y;
		Color ahead = Bot.getColorAtSpot(x=getX()+(int) Math.cos(direction*Math.PI/2),y=getY() + (int) Math.sin(direction*Math.PI/2));

visited[getX()][getY()]=true;
		if(movesUpward >1){
			System.out.println("called");
			movesUpward--;
			return moves%2==0?(direction+way)%4+4:(direction+way)%4;
		}
		if(movesUpward == 1){
			direction +=2;
			direction%=4;
		}
int ret = direction;
switch(moves%4){
	case 0:
		if(!ahead.equals(Color.BLACK)){
			movesUpward=6;
		way = getRandom(0,2)==1?1:-1;
		}
		ret = direction+4;
		break;
	case 1:
		ret = direction;
		break;
	case 2:
		ret =4+(direction + 1)%4;
		break;
	case 3:
		ret = 4+(direction +3)%4;
}
return ret;
	}
	
}
