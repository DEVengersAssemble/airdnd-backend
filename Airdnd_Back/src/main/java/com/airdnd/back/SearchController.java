package com.airdnd.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.AirdndSearchService;
import vo.AirdndHomePictureVO;
import vo.AirdndSearchTotalVO;
import vo.AirdndSearchVO;

@Controller
public class SearchController {

	@Autowired
	AirdndSearchService airdndsearchService;
	
	@RequestMapping("/search" )
	public String check() {
		String place = "서울";
		String user_idx = "1";
		int page = 0;
		
		JSONObject res = new JSONObject();
		List<AirdndSearchVO> search_list = airdndsearchService.searchselect(place, page);
		int size = search_list.size();

		JSONObject homes = new JSONObject();
		
		for(int i = 0; i < size; i++) {
			int home_idx = search_list.get(i).getHome_idx();
			List<AirdndHomePictureVO> picture = airdndsearchService.pictureselect(home_idx);
			
			List<String> picture_arr = new ArrayList<String>();
			Map<Object, Object> homes_info = new HashMap<Object, Object>();
			Map<String, Object> latlng = new HashMap<String, Object>();

			for(int j = 0; j < picture.size(); j++) {
				
				picture_arr.add(picture.get(j).getUrl());
			}
		
			search_list.get(i).setUrl(picture_arr);
			latlng.put("lat", search_list.get(i).getLat());
			latlng.put("lng", search_list.get(i).getLng());
			
			homes_info.put("homeId", search_list.get(i).getHome_idx());
			homes_info.put("isSuperhost", search_list.get(i).getIsSuperHost());
			homes_info.put("isBookmarked", "아직안받아옴");
			homes_info.put("imageArray", search_list.get(i).getUrl());
			homes_info.put("imageCount", search_list.get(i).getUrl().size());
			homes_info.put("subTitle", search_list.get(i).getSub_title());
			homes_info.put("title", search_list.get(i).getTitle());
			homes_info.put("feature", "최대 인원 " + search_list.get(i).getFilter_max_person() + "명 . 침실 " + search_list.get(i).getFilter_bedroom() + 
					"개 . 침대 " + search_list.get(i).getFilter_bed() + "개 . 욕실 " + search_list.get(i).getFilter_bathroom() + "개");
			homes_info.put("rating", search_list.get(i).getRating());
			homes_info.put("reviewCount", search_list.get(i).getReview_num());
			homes_info.put("price", search_list.get(i).getPrice());
			homes_info.put("location", latlng.toString());

			homes.put(i, homes_info);

		}

		res.put("homes", homes);
		
		int unit_price = 0;
		List<AirdndSearchVO> unit = airdndsearchService.unitpriceselect(place);
		for(int i = 0; i < unit.size(); i++) {

			unit_price += unit.get(i).getPrice();
		}
		
		unit_price = unit_price / unit.size() ;
		System.out.println("unit_price : " + unit_price);
		
		List<AirdndSearchTotalVO> total = airdndsearchService.searchtotalselect(place);
		res.put("dataTotal", total.get(0).getData_total());
		res.put("averagePrice", total.get(0).getAverage_price());
		System.out.println("최종결과 : " + res.toString());
		
		return res.toString();
	}

	@RequestMapping(value="/initialState/location/{location}/checkIn/{checkIn}/checkOut/{checkOut}/adults/{adults}", method=RequestMethod.GET )
	@ResponseBody			// 어디검색, 몇박며칠, 인원수...
	public String check(@PathVariable String location, @PathVariable String checkIn, @PathVariable String checkOut, @PathVariable int adult) {

		try {
			location = URLEncoder.encode(location, "utf-8");
			checkIn = URLEncoder.encode(checkIn, "utf-8");
			checkOut = URLEncoder.encode(checkOut, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(location.equals("guam")) {
			location = "괌";
		}else if(location.equals("jeju")) {
			location = "제주도";
		}else {
			location = "서울";
		}
		
		int page = 0;
		
		JSONObject res = new JSONObject();
		List<AirdndSearchVO> search_list = airdndsearchService.searchselect(location, page);
		int size = search_list.size();
		JSONObject homes = new JSONObject();
		
		for(int i = 0; i < size; i++) {
			int home_idx = search_list.get(i).getHome_idx();
			
			List<AirdndHomePictureVO> picture = airdndsearchService.pictureselect(home_idx);
			List<String> picture_arr = new ArrayList<String>();
			Map<Object, Object> homes_info = new HashMap<Object, Object>();
			Map<String, Object> latlng = new HashMap<String, Object>();
			
			for(int j = 0; j < picture.size(); j++) {
				picture_arr.add(picture.get(j).getUrl());
			}
		
			search_list.get(i).setUrl(picture_arr);
			
			latlng.put("lat", search_list.get(i).getLat());
			latlng.put("lng", search_list.get(i).getLng());
			
			homes_info.put("homeId", search_list.get(i).getHome_idx());
			homes_info.put("isSuperhost", search_list.get(i).getIsSuperHost());
			homes_info.put("isBookmarked", "아직안받아옴");
			homes_info.put("imageArray", search_list.get(i).getUrl());
			homes_info.put("imageCount", search_list.get(i).getUrl().size());
			homes_info.put("subTitle", search_list.get(i).getSub_title());
			homes_info.put("title", search_list.get(i).getTitle());
			homes_info.put("feature", "최대 인원 " + search_list.get(i).getFilter_max_person() + "명 . 침실 " + search_list.get(i).getFilter_bedroom() + 
					"개 . 침대 " + search_list.get(i).getFilter_bed() + "개 . 욕실 " + search_list.get(i).getFilter_bathroom() + "개");
			homes_info.put("rating", search_list.get(i).getRating());
			homes_info.put("reviewCount", search_list.get(i).getReview_num());
			homes_info.put("price", search_list.get(i).getPrice());
			homes_info.put("location", latlng.toString());
			
			homes.put(i, homes_info);
			System.out.println("json : " + homes.toString());
		}
		res.put("homes", homes);
		
		List<AirdndSearchTotalVO> total = airdndsearchService.searchtotalselect(location);
		res.put("dataTotal", total.get(0).getData_total());
		res.put("averagePrice", total.get(0).getAverage_price());

		return res.toString();
	}
}
