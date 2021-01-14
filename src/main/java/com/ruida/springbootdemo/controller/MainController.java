package com.ruida.springbootdemo.controller;

import com.ruida.springbootdemo.entity.result.PojoResult;
import com.ruida.springbootdemo.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: chenjy
 * @create: 2020-10-29 13:39
 */
@RestController
public class MainController {

    @Value("${server.port}")
    private Integer port;

    @RequestMapping(value = "set",method = RequestMethod.GET)
    public String set(HttpSession session){
        session.setAttribute("user","chenjy");
        return String.valueOf(port);
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String get(HttpSession session){
        return session.getAttribute("user") + ":" + port;
    }

    @GetMapping("queryOrderStatus")
    public PojoResult queryOrderStatus(){
        PojoResult<Object> result = new PojoResult<>();
        result.setContent(OrderStatusEnum.values());
        return result;
    }
}
