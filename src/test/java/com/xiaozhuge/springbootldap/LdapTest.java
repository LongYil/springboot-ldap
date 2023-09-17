package com.xiaozhuge.springbootldap;

import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liyinlong
 * @since 2022/3/22 7:47 下午
 */
public class LdapTest {

    public static void main(String[] args) {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://10.10.103.60:389");
        contextSource.setBase("dc=harmonycloud,dc=com");
        contextSource.setUserDn("cn=Manager,dc=harmonycloud,dc=com");
        contextSource.setPassword("ldappassword");
        contextSource.afterPropertiesSet();
        LdapTemplate template = new LdapTemplate();
        template.setContextSource(contextSource);

        AndFilter andFilter = new AndFilter();
        andFilter.and(new EqualsFilter("objectClass", "inetOrgPerson"));
        andFilter.and(new LikeFilter("uid", "*"));
        andFilter.and(new LikeFilter("displayName", "*"));
        List<Map<String, String>> results = template.search("", andFilter.encode(), new DnMapper());

        System.out.println(results.size());
    }

    public static class DnMapper implements ContextMapper {
        @Override
        public Map<String, String> mapFromContext(Object ctx) {
            Map<String, String> result = new HashMap<>();
            DirContextAdapter context = (DirContextAdapter) ctx;
            Name name = context.getDn();
            result.put("dn", name.toString());
            return result;
        }
    }
}
