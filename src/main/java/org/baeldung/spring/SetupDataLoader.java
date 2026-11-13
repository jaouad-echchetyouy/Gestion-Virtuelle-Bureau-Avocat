package org.baeldung.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.baeldung.persistence.dao.PrivilegeRepository;
import org.baeldung.persistence.dao.RoleRepository;
import org.baeldung.persistence.dao.UserRepository;
import org.baeldung.persistence.model.Privilege;
import org.baeldung.persistence.model.Role;
import org.baeldung.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges
        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
        final Privilege passwordPrivilege = createPrivilegeIfNotFound("CHANGE_PASSWORD_PRIVILEGE");
        final Privilege clientPrivilege = createPrivilegeIfNotFound("CLIENT_PRIVILEGE");
        final Privilege avocatPrivilege = createPrivilegeIfNotFound("AVOCAT_PRIVILEGE");
        final Privilege secretairePrivilege = createPrivilegeIfNotFound("SECRETAIRE_PRIVILEGE");
        final Privilege adminPrivilege = createPrivilegeIfNotFound("ADMIN_PRIVILEGE");
        final Privilege userPrivilege = createPrivilegeIfNotFound("USER_PRIVILEGE");

// == create initial roles with specific privileges

// Role for Avocat
        final List<Privilege> avocatPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, passwordPrivilege, avocatPrivilege));
        final Role avocatRole = createRoleIfNotFound("ROLE_AVOCAT", avocatPrivileges);

// Role for Secrétaire
        final List<Privilege> secretairePrivileges = new ArrayList<>(Arrays.asList(secretairePrivilege,readPrivilege, passwordPrivilege));
        final Role secretaireRole = createRoleIfNotFound("ROLE_SECRETAIRE", secretairePrivileges);

// Role for Client
        final List<Privilege> clientPrivileges = new ArrayList<>(Arrays.asList(clientPrivilege,readPrivilege, passwordPrivilege));
        final Role clientRole = createRoleIfNotFound("ROLE_CLIENT", clientPrivileges);


        // == create initial roles
        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(adminPrivilege,readPrivilege, writePrivilege, passwordPrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(Arrays.asList(userPrivilege,readPrivilege, passwordPrivilege));
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        final Role UserRole = createRoleIfNotFound("ROLE_USER", userPrivileges);

        // == create initial user
       // createUserIfNotFound("salim@gmail.com", "soulayman","9ala", "1234", new ArrayList<Role>(Arrays.asList(UserRole)));
        createUserIfNotFound("chakirfst@gmail.com", "lo9man","chakiry", "1234", new ArrayList<Role>(Arrays.asList(UserRole)));
        createUserIfNotFound("bens@yahoo.com", "khawla", "bn", "abcd", new ArrayList<Role>(Arrays.asList(adminRole)));
        createUserIfNotFound("bilalelkhalabi93@gmail.com", "bilal", "khallabi", "1234", new ArrayList<Role>(Arrays.asList(adminRole)));
        createUserIfNotFound("kamal@yahoo.com","salamat","kamal","abcd", new ArrayList<Role>(Arrays.asList(adminRole)));
        createUserIfNotFound("idriss@yahoo.com","3issawi","idriss","abcd", new ArrayList<Role>(Arrays.asList(adminRole)));
        createUserIfNotFound("hamza@yahoo.com","hamzawi","hamza","abcd", new ArrayList<Role>(Arrays.asList(adminRole)));
        createUserIfNotFound("fatima@gmail.com","نجاح","فاطمة","abcd", new ArrayList<Role>(Arrays.asList(adminRole)));
        createUserIfNotFound("soufi@gmail.com", "hassan","chakir", "abcd", new ArrayList<Role>(Arrays.asList(UserRole)));
        createUserIfNotFound("chakirfst1@gmail.com", "lo9mann","chakiryy", "1234", new ArrayList<Role>(Arrays.asList(UserRole)));
        createUserIfNotFound("bens2@yahoo.com", "khawla2", "bn2", "1234", new ArrayList<Role>(Arrays.asList(adminRole)));

        // == create initial users and assign roles
        createUserIfNotFound("avocat@example.com", "John", "Doe", "avocatpassword", new ArrayList<Role>(Arrays.asList(avocatRole)));
        createUserIfNotFound("secretaire@example.com", "Marie", "Dupont", "secretairepassword", new ArrayList<Role>(Arrays.asList(secretaireRole)));
        createUserIfNotFound("client@example.com", "Paul1", "Martin1", "abcd", new ArrayList<Role>(Arrays.asList(clientRole)));
        createUserIfNotFound("bilal@example.com", "Paul", "Martin", "abcd", new ArrayList<Role>(Arrays.asList(clientRole)));
        createUserIfNotFound("secretaire1@example.com", "secr", "Dupontt", "1234", new ArrayList<Role>(Arrays.asList(secretaireRole)));

        alreadySetup = true;
    }

    @Transactional
    public Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    public Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    public User createUserIfNotFound( String email,  String firstName,  String lastName,  String password,  Collection<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }

}