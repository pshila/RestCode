package requests;

public class AllRequests {
	

	public static String placePostData() {
		String d="/maps/api/place/add/json";
		return d;
		
	}
	

	public static String placeDeleteData() {
		String d="/maps/api/place/delete/json";
		return d;
		
	}
	public static String getPlaceData() {
		String d="/maps/api/place/nearbysearch/json";
		return d;
		
		
	}

}
