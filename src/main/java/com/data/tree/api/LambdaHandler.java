package com.data.tree.api;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.data.tree.api.Model.RequestVO;
import com.data.tree.api.Model.ResponseVO;

public class LambdaHandler implements RequestHandler<RequestVO, ResponseVO> {

    @Override
    public ResponseVO handleRequest(RequestVO input, Context context) {
        return null;
    }

}
