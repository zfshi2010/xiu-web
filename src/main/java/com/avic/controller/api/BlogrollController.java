package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.Blogroll;
import com.avic.repository.BlogrollRepository;
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
@RequestMapping("/api/blogroll")
@Service
public class BlogrollController {

    @Autowired
    BlogrollRepository blogrollRepository;

    @PostMapping("/save")
    @Transactional
    public JsonResult save(Blogroll blogroll) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        blogroll.setCreateTime(nowText);
        this.blogrollRepository.save(blogroll);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(Blogroll blogroll) {
        this.blogrollRepository.save(blogroll);
        return JsonResult.getSuccessResult();
    }


    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.blogrollRepository.delete(id);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/index")
    public JsonResult index(Integer page, Integer size) {
        if (null == page) {
            page = 0;
        }
        if (null == size) {
            size = 10;
        }
        PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "id");
        Page<Blogroll> blogrolls = blogrollRepository.findAll(pageRequest);
        return JsonResult.getSuccessResult(blogrolls);
    }

}
