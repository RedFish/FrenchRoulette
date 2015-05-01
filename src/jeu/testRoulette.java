package jeu;

import org.junit.Test;

public class testRoulette extends AbstractTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new testRoulette();
	}

	protected void runTests() {
		this.testExpected = 37;
        try {
    		test370000Tirage();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@Test
    public void test370000Tirage() throws InstantiationException, IllegalAccessException {
		Roulette r = new Roulette();
		r.testRoulette();
		int marge = 300;
		int tab_occurance[] = new int[37];
		for(int i=0; i< 370000; i++){
			tab_occurance[r.tirage()]++;
		}
		for(int i=0; i< tab_occurance.length; i++){
			super.assertTrue((tab_occurance[i]>10000-marge)&&(tab_occurance[i]<10000+marge),"le nombre d'occurance de "+i+": "+tab_occurance[i]+" n'appartient pas ˆ l'ensemble ["+(10000-marge)+";"+(10000+marge)+"]");
		}
    }
	

}
