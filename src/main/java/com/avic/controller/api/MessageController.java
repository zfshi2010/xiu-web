package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.Message;
import com.avic.repository.MessageRepository;
import com.avic.util.UserLoginToken;
import com.avic.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/message")
@Service
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        MessageVo message = this.messageRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(message);
    }

    @PostMapping("/save")
    @UserLoginToken
    @Transactional
    public JsonResult save(Message message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        message.setCreateTime(nowText);
        this.messageRepository.save(message);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/index")
    @UserLoginToken
    public JsonResult index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Message> messages = messageRepository.findAll(pageRequest);
        return JsonResult.getSuccessResult(messages);
    }

}
