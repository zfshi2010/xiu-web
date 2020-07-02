package com.avic.service;

import com.avic.entity.Message;
import com.avic.repository.MessageRepository;
import com.avic.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Transactional(readOnly = true)
    public Page<Message> index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Message> messages = messageRepository.findAll(pageRequest);
        return messages;
    }

    @Transactional(readOnly = true)
    public MessageVo detail(Long id) {
        return messageRepository.getOne(id).toVo();
    }

}
