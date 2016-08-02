/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toroidal.domination;

import java.awt.Color;
import java.util.Stack;
import static toroidal.domination.Bot.getRandom;

/**
 *
 * @author rohan
 */
public class FloodFillBot extends Bot {

	Stack<Integer> past = new Stack<>();
	int moves;
	int direction;
	int moveLeftTime;
	private final Color myTeam;
	boolean[][] visited = new boolean[Bot.getMap().length][Bot.getMap()[0].length];

	public FloodFillBot(int team, int inX, int inY) {
		super(team, inX, inY);
		switch (team) {
			case 0:
				myTeam = Color.red;
				break;
			case 1:
				myTeam = Color.GREEN;
				break;
			default:
				myTeam = Color.BLUE;
				break;
		}
		direction = Bot.getRandom(0, 4);
	}
	int movesUpward = 0;
	int way = getRandom(0, 2) == 1 ? 1 : -1;

	@Override
	public int move() {

			moves++;
			if (moves % 2 == 0) {
				return past.isEmpty()?4:past.peek();
			} else {
				int x = Bot.getRandom(1,3);
				for (int j = 0; j < 4; j++){
int i = (x+j)%4;
					int tX = getX() + (int) Math.cos(i * Math.PI / 2);
					int tY = getY() + (int) Math.sin(i * Math.PI / 2);
					if(Bot.getColorAtSpot(tX, tY).equals(Color.black)){
					past.push(i);
					return i + 4;
					}
					}
				return (past.isEmpty()?0  :past.pop() + 2) % 4;
			}
	
	}
}
