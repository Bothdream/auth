package com.zte.auth.exceptionhandler;

import com.zte.auth.common.ServiceData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应处理类
 * 作用：在数据返回给前端前，进行拦击，做最后一步数据处理
 */
@Component
@RestControllerAdvice
public class GlobalResponseAdvice implements ResponseBodyAdvice {
    /**
     * 支持哪些方法和参数
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //此处进行拦击以后，openapi 将无法使用
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        //对所有返回前端的请求加上头信息
        serverHttpResponse.getHeaders().set("name","zsq");
        //返回的结果是ServiceData类型，直接返回
        if ( o instanceof ServiceData) {
            return o;
        }
        //否则包裹一层
        ServiceData<Object> res = new ServiceData<>();
        res.setBo(o);
        return res;
    }
}
