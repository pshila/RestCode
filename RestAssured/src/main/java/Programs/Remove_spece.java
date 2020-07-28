package Programs;

public class Remove_spece {
	      static String s="    my name is shila  ";
	public static void main(String[] args) {
		
		System.out.println(removeSpace(s));

		
	}
	public static String removeSpace(String s) {
	    String withoutspaces = "";
	    for (int i = 0; i < s.length(); i++) {
	        if (s.charAt(i) != ' ')
	            withoutspaces += s.charAt(i);

	    }
	    return withoutspaces;

	}

}
