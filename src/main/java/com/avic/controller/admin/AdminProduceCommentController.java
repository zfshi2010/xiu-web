package com.avic.controller.admin;

import com.avic.controller.BaseController;
import com.avic.repository.ProduceCommentRepository;
import com.avic.repository.ProduceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/produceComment")
public class AdminProduceCommentController extends BaseController {

    @Autowired
    private ProduceRepository produceRepository;

    @Autowired
    private ProduceCommentRepository produceCommentRepository;

    @RequestMapping("/index")
    public String index(ModelMap model, Long produceId) throws Exception{
        model.addAttribute("produceId", produceId);
        model.addAttribute("produce", produceRepository.findOne(produceId));
        return "admin/produceComment/list";
    }
}
