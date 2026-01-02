package store.book.bookstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.book.bookstore.dto.UserRegistrationRequestDto;
import store.book.bookstore.dto.UserResponseDto;
import store.book.bookstore.exception.RegistrationException;
import store.book.bookstore.mapper.UserMapper;
import store.book.bookstore.model.User;
import store.book.bookstore.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("User with email "
                    + requestDto.getEmail() + " already exists");
        }

        User user = userMapper.toModel(requestDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
