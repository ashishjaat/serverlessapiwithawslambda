package com.data.tree.api.Model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * @author ashish
 *
 */
@Data
@Builder
public class RequestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 705882216123784477L;
	private double xCo;
	private double yCo;
	private double radius;
}
