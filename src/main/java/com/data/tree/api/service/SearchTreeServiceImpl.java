package com.data.tree.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.data.tree.api.Model.RequestVO;
import com.data.tree.api.Model.ResponseVO;
import com.data.tree.api.configuration.YAMLConfig;
import com.data.tree.api.helper.SearchTreeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @author ashish
 *
 */
@Service
public class SearchTreeServiceImpl {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private YAMLConfig yamlConfig;
	

	public Map<String, Integer> searchTree(RequestVO searchRequestVO) {
		List<ResponseVO> treeList = null;
		Map<String, Integer> responseMap = new HashMap<>();
		String endpoint = yamlConfig.getUrl();

		// the given api supports the SOQL
		var urlString = endpoint + "?$query= SELECT latitude, longitude, spc_common WHERE "
				+ "(spc_common IS NOT NULL) AND " + "(latitude between %s and %s) AND "
				+ "(longitude between %s and %s)";
		
		String url = SearchTreeHelper.prepareUrl(searchRequestVO, urlString);
		ParameterizedTypeReference<List<ResponseVO>> responseType = new ParameterizedTypeReference<List<ResponseVO>>() {
		};

		//hitting the api to get the response
		ResponseEntity<List<ResponseVO>> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

		treeList = response.getBody();
		treeList.stream().filter(resVO -> SearchTreeHelper.withInRadius(resVO, searchRequestVO))
				.forEach(res -> responseMap.compute(res.getSpecCommon(), (k, v) -> v == null ? 1 : v + 1));
		return responseMap;
	}
	
	
	
}
