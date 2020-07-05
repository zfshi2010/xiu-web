package com.avic.controller.api;

import com.avic.common.JsonResult;
import com.avic.entity.ContactWay;
import com.avic.entity.User;
import com.avic.repository.UserRepository;
import com.avic.util.UserLoginToken;
import com.avic.vo.UserVo;
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
@RequestMapping("/api/user")
@Service
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public JsonResult login(@RequestBody User user) {
        User user1 = userRepository.findByUsername(user.getUsername());
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/register")
    public JsonResult register(@RequestBody User user) {
        this.userRepository.save(user);
        return JsonResult.getSuccessResult();
    }

    @GetMapping("/detail")
    @UserLoginToken
    @Transactional(readOnly = true)
    public JsonResult detail(Long id) {
        UserVo user = this.userRepository.getOne(id).toVo();
        return JsonResult.getSuccessResult(user);
    }

    @PostMapping("/save")
    @Transactional
    public JsonResult save(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        user.setCreateTime(nowText);
        this.userRepository.save(user);
        return JsonResult.getSuccessResult();
    }

    @PostMapping("/update")
    @Transactional
    public JsonResult update(User user) {
        if (user.getId() == null || user.getId() == 0L) {
            return JsonResult.getErrorResult("请传递要修改的用户id!");
        }
        this.userRepository.save(user);
        return JsonResult.getSuccessResult();
    }


    @DeleteMapping("/delete")
    @Transactional
    public JsonResult delete(Long id) {
        this.userRepository.delete(id);
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
        Page<User> users = userRepository.findAll(pageRequest);
        return JsonResult.getSuccessResult(users);
    }

}
