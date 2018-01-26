package com.frameone.spring.frameone.data.repository;

import com.frameone.spring.frameone.data.DataRepository;
import com.frameone.spring.frameone.data.RetrofitFactory;
import com.frameone.spring.frameone.data.entity.UserEntity;
import com.frameone.spring.frameone.data.service.LoginService;

import rx.Observable;

/**
 * Created by Administrator on 2018/1/5.
 */

public class LoginRepository extends DataRepository {

    private static final LoginRepository ourInstance = new LoginRepository();

    public static LoginRepository getInstance() {
        return ourInstance;
    }

    private LoginService loginService;

    private LoginRepository() {
        loginService = RetrofitFactory.getInstance().getService(LoginService.class);
    }

    public Observable<UserEntity> login(String username,
                                        String code, String mac, String token) {
        return transform(loginService.login(username, code, mac, token));
    }
    public void saveUser(UserEntity userEntity){

    }
}
