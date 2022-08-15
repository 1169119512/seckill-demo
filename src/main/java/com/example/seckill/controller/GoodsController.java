package com.example.seckill.controller;

import com.example.seckill.pojo.User;
import com.example.seckill.service.IGoodsService;
import com.example.seckill.service.IUserService;
import com.example.seckill.vo.GoodsVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 1000线程循环10次测试3次
 *   1000线程循环10次
 *  Windows 优化前QPS:1211.8
 *          缓存优化QPS:2293.8
 *   Linux   优化前QPS:155.1
 *
 *
 *
 * 展示商品列表
 *
 *    
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value="/toList", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String toList( Model model,User user, HttpServletRequest request, HttpServletResponse response ) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsList");
        if(!StringUtils.isEmpty(html)){
            return html;
        }
//        if(StringUtils.isEmpty(ticket)){
//            return "login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.getUserByCookie(request,response,ticket);
//        if(null == user){
//            return "login";
//        }
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        WebContext webContext = new WebContext(request,response,request.getServletContext(),request.getLocale(), model.asMap());

         html = thymeleafViewResolver.getTemplateEngine().process("goodsList", webContext);
        if(!StringUtils.isEmpty(html)){
            valueOperations.set("goodsList",html,60, TimeUnit.SECONDS);

        }
        return html;
    }

    /**
     *
     * 跳转商品详情页
     * @param goodsId
     * @return
     */
    @GetMapping(value = "/toDetail/{goodsId}",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String toDetail(Model model,User user, @PathVariable Long goodsId, HttpServletRequest request, HttpServletResponse response){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String html = (String) valueOperations.get("goodsDetail:" + goodsId);
        if(!StringUtils.isEmpty(html)){
            return html;
        }
        model.addAttribute("user",user);
        model.addAttribute("goods",goodsService.findGoodsVoByGoodsId(goodsId));
        WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
         html = thymeleafViewResolver.getTemplateEngine().process("goodsDetail",webContext);
         if(!StringUtils.isEmpty(html)){
             valueOperations.set("goodsDetail:" + goodsId,html,60,TimeUnit.SECONDS);
         }
        return html;
    }

}
