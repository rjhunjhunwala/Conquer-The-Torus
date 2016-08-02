/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toroidal.domination;

/**
 *
 * @author rohan
 */
public class TrumpWithPaidAdvisors extends Bot{
int moves;
int direction;
	public TrumpWithPaidAdvisors(int team, int inX, int inY) {
		super(team, inX, inY);
direction = Bot.getRandom(0, 4);
	}

	@Override
	public int move() {
int ret = direction;
switch(++moves%4){
	case 0:
		ret = direction+4;
		break;
	case 1:
		ret = direction;
		break;
	case 2:
		ret = direction + 1;
		break;
	case 3:
		ret = direction +3;
}
return ret;
	}
	
}
