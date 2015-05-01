package combinaison;

import jeu.Numero;
import exception.MiseMaxException;

public class CombinaisonCheval extends Combinaison {
	private ChanceCheval cc;
	
	public CombinaisonCheval(ChanceCheval cc, int mise) throws MiseMaxException {
		super(60,18,mise);
		this.cc=cc;
		switch (cc) {
		case _0_1:
			getNumeros().add(new Numero(0));
			getNumeros().add(new Numero(1));
			break;
		case _0_2:
			getNumeros().add(new Numero(0));
			getNumeros().add(new Numero(2));
			break;
		case _0_3:
			getNumeros().add(new Numero(0));
			getNumeros().add(new Numero(3));
			break;
		case _1_2:
			getNumeros().add(new Numero(1));
			getNumeros().add(new Numero(2));
			break;
		case _1_4:
			getNumeros().add(new Numero(1));
			getNumeros().add(new Numero(4));
			break;
		case _2_3:
			getNumeros().add(new Numero(2));
			getNumeros().add(new Numero(3));
			break;
		case _2_5:
			getNumeros().add(new Numero(2));
			getNumeros().add(new Numero(5));
			break;
		case _3_6:
			getNumeros().add(new Numero(3));
			getNumeros().add(new Numero(6));
			break;
		case _4_5:
			getNumeros().add(new Numero(4));
			getNumeros().add(new Numero(5));
			break;
		case _4_7:
			getNumeros().add(new Numero(4));
			getNumeros().add(new Numero(7));
			break;
		case _5_6:
			getNumeros().add(new Numero(5));
			getNumeros().add(new Numero(6));
			break;
		case _5_8:
			getNumeros().add(new Numero(5));
			getNumeros().add(new Numero(8));
			break;
		case _6_9:
			getNumeros().add(new Numero(6));
			getNumeros().add(new Numero(9));
			break;
		case _7_8:
			getNumeros().add(new Numero(7));
			getNumeros().add(new Numero(8));
			break;
		case _7_10:
			getNumeros().add(new Numero(7));
			getNumeros().add(new Numero(10));
			break;
		case _8_9:
			getNumeros().add(new Numero(8));
			getNumeros().add(new Numero(9));
			break;
		case _8_11:
			getNumeros().add(new Numero(8));
			getNumeros().add(new Numero(11));
			break;
		case _9_12:
			getNumeros().add(new Numero(8));
			getNumeros().add(new Numero(12));
			break;
		case _10_11:
			getNumeros().add(new Numero(10));
			getNumeros().add(new Numero(11));
			break;
		case _10_13:
			getNumeros().add(new Numero(10));
			getNumeros().add(new Numero(13));
			break;
		case _11_12:
			getNumeros().add(new Numero(11));
			getNumeros().add(new Numero(12));
			break;
		case _11_14:
			getNumeros().add(new Numero(11));
			getNumeros().add(new Numero(14));
			break;
		case _12_15:
			getNumeros().add(new Numero(12));
			getNumeros().add(new Numero(15));
			break;
		case _13_14:
			getNumeros().add(new Numero(13));
			getNumeros().add(new Numero(14));
			break;
		case _13_16:
			getNumeros().add(new Numero(13));
			getNumeros().add(new Numero(16));
			break;
		case _14_15:
			getNumeros().add(new Numero(14));
			getNumeros().add(new Numero(15));
			break;
		case _14_17:
			getNumeros().add(new Numero(14));
			getNumeros().add(new Numero(17));
			break;
		case _15_18:
			getNumeros().add(new Numero(15));
			getNumeros().add(new Numero(18));
			break;
		case _16_17:
			getNumeros().add(new Numero(16));
			getNumeros().add(new Numero(17));
			break;
		case _16_19:
			getNumeros().add(new Numero(16));
			getNumeros().add(new Numero(19));
			break;
		case _17_18:
			getNumeros().add(new Numero(17));
			getNumeros().add(new Numero(18));
			break;
		case _17_20:
			getNumeros().add(new Numero(17));
			getNumeros().add(new Numero(20));
			break;
		case _18_21:
			getNumeros().add(new Numero(18));
			getNumeros().add(new Numero(21));
			break;
		case _19_20:
			getNumeros().add(new Numero(19));
			getNumeros().add(new Numero(20));
			break;
		case _19_22:
			getNumeros().add(new Numero(19));
			getNumeros().add(new Numero(22));
			break;
		case _20_21:
			getNumeros().add(new Numero(20));
			getNumeros().add(new Numero(21));
			break;
		case _20_23:
			getNumeros().add(new Numero(20));
			getNumeros().add(new Numero(23));
			break;
		case _21_24:
			getNumeros().add(new Numero(21));
			getNumeros().add(new Numero(24));
			break;
		case _22_23:
			getNumeros().add(new Numero(22));
			getNumeros().add(new Numero(23));
			break;
		case _22_25:
			getNumeros().add(new Numero(22));
			getNumeros().add(new Numero(25));
			break;
		case _23_24:
			getNumeros().add(new Numero(23));
			getNumeros().add(new Numero(24));
			break;
		case _23_26:
			getNumeros().add(new Numero(23));
			getNumeros().add(new Numero(26));
			break;
		case _24_27:
			getNumeros().add(new Numero(24));
			getNumeros().add(new Numero(27));
			break;
		case _25_26:
			getNumeros().add(new Numero(25));
			getNumeros().add(new Numero(26));
			break;
		case _25_28:
			getNumeros().add(new Numero(25));
			getNumeros().add(new Numero(28));
			break;
		case _26_27:
			getNumeros().add(new Numero(26));
			getNumeros().add(new Numero(27));
			break;
		case _26_29:
			getNumeros().add(new Numero(26));
			getNumeros().add(new Numero(29));
			break;
		case _27_30:
			getNumeros().add(new Numero(27));
			getNumeros().add(new Numero(30));
			break;
		case _28_29:
			getNumeros().add(new Numero(28));
			getNumeros().add(new Numero(29));
			break;
		case _28_31:
			getNumeros().add(new Numero(28));
			getNumeros().add(new Numero(31));
			break;
		case _29_30:
			getNumeros().add(new Numero(29));
			getNumeros().add(new Numero(30));
			break;
		case _29_32:
			getNumeros().add(new Numero(29));
			getNumeros().add(new Numero(32));
			break;
		case _30_33:
			getNumeros().add(new Numero(30));
			getNumeros().add(new Numero(33));
			break;
		case _31_32:
			getNumeros().add(new Numero(31));
			getNumeros().add(new Numero(32));
			break;
		case _31_34:
			getNumeros().add(new Numero(31));
			getNumeros().add(new Numero(34));
			break;
		case _32_33:
			getNumeros().add(new Numero(32));
			getNumeros().add(new Numero(33));
			break;
		case _32_35:
			getNumeros().add(new Numero(32));
			getNumeros().add(new Numero(35));
			break;
		case _33_36:
			getNumeros().add(new Numero(33));
			getNumeros().add(new Numero(36));
			break;
		case _34_35:
			getNumeros().add(new Numero(34));
			getNumeros().add(new Numero(35));
			break;
		case _35_36:
			getNumeros().add(new Numero(35));
			getNumeros().add(new Numero(36));
			break;
		default:
			System.out.println("ChanceCheval inconnue");
			break;
		}
	}
	
	public String toString(){
		switch (cc) {
		case _0_1:
			return "Cheval (0 et 1)";
		case _0_2:
			return "Cheval (0 et 2)";
		case _0_3:
			return "Cheval (0 et 3)";
		case _1_2:
			return "Cheval (1 et 2)";
		case _1_4:
			return "Cheval (1 et 4)";
		case _2_3:
			return "Cheval (2 et 3)";
		case _2_5:
			return "Cheval (2 et 5)";
		case _3_6:
			return "Cheval (3 et 6)";
		case _4_5:
			return "Cheval (4 et 5)";
		case _4_7:
			return "Cheval (4 et 7)";
		case _5_6:
			return "Cheval (5 et 6)";
		case _5_8:
			return "Cheval (5 et 8)";
		case _6_9:
			return "Cheval (6 et 9)";
		case _7_8:
			return "Cheval (7 et 8)";
		case _7_10:
			return "Cheval (7 et 10)";
		case _8_9:
			return "Cheval (8 et 9)";
		case _8_11:
			return "Cheval (8 et 11)";
		case _9_12:
			return "Cheval (9 et 12)";
		case _10_11:
			return "Cheval (10 et 11)";
		case _10_13:
			return "Cheval (10 et 13)";
		case _11_12:
			return "Cheval (11 et 12)";
		case _11_14:
			return "Cheval (11 et 14)";
		case _12_15:
			return "Cheval (12 et 15)";
		case _13_14:
			return "Cheval (13 et 14)";
		case _13_16:
			return "Cheval (13 et 16)";
		case _14_15:
			return "Cheval (14 et 15)";
		case _14_17:
			return "Cheval (14 et 17)";
		case _15_18:
			return "Cheval (15 et 18)";
		case _16_17:
			return "Cheval (16 et 17)";
		case _16_19:
			return "Cheval (16 et 19)";
		case _17_18:
			return "Cheval (17 et 18)";
		case _17_20:
			return "Cheval (17 et 20)";
		case _18_21:
			return "Cheval (18 et 21)";
		case _19_20:
			return "Cheval (19 et 20)";
		case _19_22:
			return "Cheval (19 et 22)";
		case _20_21:
			return "Cheval (20 et 21)";
		case _20_23:
			return "Cheval (20 et 23)";
		case _21_24:
			return "Cheval (21 et 24)";
		case _22_23:
			return "Cheval (22 et 23)";
		case _22_25:
			return "Cheval (22 et 25)";
		case _23_24:
			return "Cheval (23 et 24)";
		case _23_26:
			return "Cheval (23 et 26)";
		case _24_27:
			return "Cheval (24 et 27)";
		case _25_26:
			return "Cheval (25 et 26)";
		case _25_28:
			return "Cheval (25 et 28)";
		case _26_27:
			return "Cheval (26 et 27)";
		case _26_29:
			return "Cheval (26 et 29)";
		case _27_30:
			return "Cheval (27 et 30)";
		case _28_29:
			return "Cheval (28 et 29)";
		case _28_31:
			return "Cheval (28 et 31)";
		case _29_30:
			return "Cheval (29 et 30)";
		case _29_32:
			return "Cheval (29 et 32)";
		case _30_33:
			return "Cheval (30 et 33)";
		case _31_32:
			return "Cheval (31 et 32)";
		case _31_34:
			return "Cheval (31 et 34)";
		case _32_33:
			return "Cheval (32 et 33)";
		case _32_35:
			return "Cheval (32 et 35)";
		case _33_36:
			return "Cheval (33 et 36)";
		case _34_35:
			return "Cheval (34 et 35)";
		case _35_36:
			return "Cheval (35 et 36)";
		default:
			return "ChanceCheval inconnue";
		}
	}
}
