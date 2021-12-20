package com.data.tree.api.Model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author ashish
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -157934260091545275L;
	private double latitude;
	private double longitude;

	@JsonProperty("spc_common")
	private String specCommon;
}
