package store.book.bookstore.service;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.book.bookstore.dto.UserRegistrationRequestDto;
import store.book.bookstore.dto.UserResponseDto;
import store.book.bookstore.exception.RegistrationException;
import store.book.bookstore.mapper.UserMapper;
import store.book.bookstore.model.Role;
import store.book.bookstore.model.RoleName;
import store.book.bookstore.model.User;
import store.book.bookstore.repository.RoleRepository;
import store.book.bookstore.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email "
                    + requestDto.getEmail() + " already exists");
        }

        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RegistrationException("Default role not found "));
        user.setRoles(Set.of(userRole));

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
