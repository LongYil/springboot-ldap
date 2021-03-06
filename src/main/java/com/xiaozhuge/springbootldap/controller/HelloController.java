package com.xiaozhuge.springbootldap.controller;

import java.util.List;

import com.xiaozhuge.springbootldap.domain.NormalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;

@RestController
public class HelloController {

    @Autowired
    LdapTemplate ldap;

    @GetMapping
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/search")
    public String searchById(@RequestParam String uid) {
        AttributesMapper<String> mapper = (attrs) -> attrs.get("cn").get().toString();
        List<String> list = this.ldap.search("ou=people", "uid=" + uid, mapper);
        StringBuffer sbf = new StringBuffer();
        list.forEach(item -> {
            System.out.println(item);
            sbf.append(item);
        });
        return sbf.toString();
    }

    @GetMapping("/listAll")
    public String listAll() {
        List<String> list = this.ldap.list("ou=people");
        StringBuffer sbf = new StringBuffer();
        list.forEach(item -> {
            sbf.append(item + "\n\r");
            System.out.println(item);
        });
        return sbf.toString();
    }

    @GetMapping("/add")
    public String add(@RequestParam String name, @RequestParam String password) {
        Name dn = LdapNameBuilder.newInstance()
                .add("ou", "people")
                .add("cn", name)
                .build();
        DirContextAdapter context = new DirContextAdapter(dn);
        context.setAttributeValues("objectclass", new String[]{"top", "person"});
        context.setAttributeValue("sn", name);
        context.setAttributeValue("userPassword", password);
        this.ldap.bind(context);
        return "success";
    }

    @GetMapping("/update")
    public String update(@RequestParam String name, @RequestParam String password) {
        NormalUser normalUser = new NormalUser();
        normalUser.setFullName("hhh");
        normalUser.setUid("bob");
        this.ldap.update(normalUser);
        return "success";
    }


}
