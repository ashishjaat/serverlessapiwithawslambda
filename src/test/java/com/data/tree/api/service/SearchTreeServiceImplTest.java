package com.data.tree.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.data.tree.api.App;
import com.data.tree.api.Model.RequestVO;
import com.data.tree.api.Model.ResponseVO;
import com.data.tree.api.configuration.YAMLConfig;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = App.class)
public class SearchTreeServiceImplTest {
	
	@InjectMocks
	SearchTreeServiceImpl searchTreeService;
	
	private RequestVO reqVO;
	
	@Mock
    private RestTemplate restTemplate;
	
	@Mock
	private YAMLConfig yamlConfig;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);	
		reqVO = RequestVO
				.builder()
				.xCo(40.72309177)
				.yCo(-73.84421522)
				.radius(300.25)
				.build();
	}
	

	@Test
	void testSearchTree() {	
		 Mockito.when(yamlConfig.getUrl()).thenReturn("https://data.cityofnewyork.us/resource/uvpi-gqnh.json");
        //define the entity you want the exchange to return
		 List<ResponseVO> treeList = new ArrayList<ResponseVO>();
		ResponseVO respVO = new ResponseVO();
		respVO.setSpecCommon("cherry");
		treeList.add(respVO);
		ResponseVO respVO1 = new ResponseVO();
		respVO1.setSpecCommon("cherry");
		treeList.add(respVO1);
		ResponseVO respVO2 = new ResponseVO();
		respVO2.setSpecCommon("red maple");
		treeList.add(respVO2);
		ResponseVO respVO3 = new ResponseVO();
		respVO3.setSpecCommon("red maple");
		treeList.add(respVO3);
		ResponseVO respVO4 = new ResponseVO();
		respVO4.setSpecCommon("honeylocust");
		treeList.add(respVO4);
        ResponseEntity<List<ResponseVO>> myEntity = new ResponseEntity<List<ResponseVO>>(treeList, HttpStatus.OK);
        ParameterizedTypeReference<List<ResponseVO>> responseType = new ParameterizedTypeReference<List<ResponseVO>>() {
		};
        Mockito.when(restTemplate.exchange(
        	ArgumentMatchers.contains("/resource/uvpi-gqnh.json"),
            ArgumentMatchers.eq(HttpMethod.GET),
            ArgumentMatchers.eq(null),
            ArgumentMatchers.eq(responseType))
        ).thenReturn(myEntity);
       

        Map<String, Integer> responseMap = searchTreeService.searchTree(reqVO);
        Assert.assertNotNull(responseMap);
	}
	
	

}
