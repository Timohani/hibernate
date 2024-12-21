package org.timowa;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.timowa.dao.CompanyRepository;
import org.timowa.dao.UserRepository;
import org.timowa.mapper.CompanyReadMapper;
import org.timowa.mapper.UserCreateMapper;
import org.timowa.mapper.UserReadMapper;
import org.timowa.service.UserService;
import org.timowa.util.HibernateUtil;

import java.lang.reflect.Proxy;

@Slf4j
public class HibernateRunner {
    public static void main(String[] args) {
        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(),
                     new Class[]{Session.class},
                     ((proxy, method, args1) ->
                             method.invoke(sessionFactory.getCurrentSession(), args1)))) {
            session.beginTransaction();

            var companyReadMapper = new CompanyReadMapper();
            var userReadMapper = new UserReadMapper(companyReadMapper);
            var companyRepository = new CompanyRepository(session);
            var userCreateMapper = new UserCreateMapper(companyRepository);

            var userRepository = new UserRepository(session);
            var userService = new UserService(userRepository, userReadMapper, userCreateMapper);

            userService.findById(4L).ifPresent(System.out::println);

            session.getTransaction().commit();
        }
    }
}
