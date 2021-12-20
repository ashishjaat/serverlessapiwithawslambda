package com.data.tree.api.helper;

import com.data.tree.api.Model.RequestVO;
import com.data.tree.api.Model.ResponseVO;


/**
 * @author ashish
 *
 */
public class SearchTreeHelper {


	public static boolean withInRadius(ResponseVO treeVO, RequestVO searchVO) {
		double radius = searchVO.getRadius();

		double latitudeDiff = Math.abs(treeVO.getLatitude() - searchVO.getXCo());
		double longitudeDiff = Math.abs(treeVO.getLongitude() - searchVO.getYCo());

		return radius >= (Math.sqrt(latitudeDiff * latitudeDiff + longitudeDiff * longitudeDiff));
	}

	public static String prepareUrl(RequestVO searchVO, String url) {
		
		// number of km per degree = ~111km (111.32 in google maps)
		// 1km in degree = 1 / 111.32km = 0.0089
		// 1m in degree = 0.0089 / 1000 = 0.0000089
		//double coef = meters * 0.0000089;
		//double new_lat = my_lat + coef;
		// pi / 180 = 0.018
		//double new_long =coef / Math.cos(my_lat * 0.018);
		
		
		// used the above formula for calculation 
		double diffX = searchVO.getRadius() * 0.0000089;
		double x1 = searchVO.getXCo() + diffX;
		double x2 = searchVO.getXCo() - diffX;
		double diffY = diffX / Math.cos(searchVO.getXCo() * 0.018);
		double y1 = searchVO.getYCo() + diffY;
		double y2 = searchVO.getYCo() - diffY;

		return String.format(url, x2, x1, y2, y1);
	}

	public static void mapErrors(Exception e) {
		
	}
	
	
	
	
}
