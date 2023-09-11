package com.example.blogproject.service;

import com.example.blogproject.model.User;
import com.example.blogproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession session;


    @Transactional
    public int 회원가입(User user){
        String userName=user.getUsername();
        User username = userRepository.findByUsername(userName);
        if(username != null){
            return 0;
        }
        userRepository.save(user);
        return 1;
    }

    @Transactional
    public User 로그인(User user){
        String userName=user.getUsername();
        String userPassword=user.getPassword();
        return userRepository.findByUsernameAndPassword(userName,userPassword);
    }
    @Transactional
    public int 중복확인(String username) {
        //String userName=user.getUsername();

        User user =userRepository.findByUsername(username);
        if(user != null){
            return 0;
        }else{
            return 1;
        }
    }
    @Transactional
    public void 회원수정(User user) {
        // 수정시에는 영속성 컨텍스트 User오브젝트를 영속화시키고, 영속화된 User오브젝트를 수정한다.
        // select 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서이다.
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
        User persistance=userRepository.findById(user.getId()).orElse(null);
        persistance.setPassword(user.getPassword());
        persistance.setEmail(user.getEmail());
        // 회원수정 함수 종료시=서비스 종료=트랙잭션종료=commit이 자동으로 된다.
        // 영속화된 persistance객체의 변화가 감지되면 더티체킹이 되어 자동으로 update문을 날려준다.
    }
    @Transactional
    public void 회원탈퇴(Long id) {
        User user=userRepository.findById(id).orElse(null);
        if(user != null) userRepository.deleteById(id);
        session.invalidate();
    }
}

