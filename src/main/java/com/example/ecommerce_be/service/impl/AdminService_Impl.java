package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.base.NotFoundException;
import com.example.ecommerce_be.dto.AccountDTO;
import com.example.ecommerce_be.dto.AdminDTO;
import com.example.ecommerce_be.dto.CredentialsDto;
import com.example.ecommerce_be.dto.SignUpDto;
import com.example.ecommerce_be.entity.Admin;
import com.example.ecommerce_be.jwt.email.EmailSender;
import com.example.ecommerce_be.jwt.exceptions.AppException;
import com.example.ecommerce_be.mapper.AdminMapper;
import com.example.ecommerce_be.repositories.AdminRepository;
import com.example.ecommerce_be.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService_Impl implements AdminService {
    private final AdminRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AdminMapper userMapper;

    private final EmailSender emailSender;

    public AdminDTO login(CredentialsDto credentialsDto) {
        // Kiểm tra login không chứa khoảng trắng
        checkNotSpace(credentialsDto.login());

        Admin user = userRepository.findByLogin(credentialsDto.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (user.getEnable() == 0) {
            throw new AppException("Email not verified", HttpStatus.BAD_REQUEST);
        }

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public AdminDTO register(SignUpDto userDto) {

        // Kiểm tra và giới hạn độ dài của các trường
        if (isInvalidLength(userDto.userName(), 50) ||
                isInvalidLength(userDto.login(), 50)) {
            throw new AppException("Invalid input length", HttpStatus.BAD_REQUEST);
        }
        // Kiểm tra định dạng email
        if (isInvalidEmailFormat(userDto.login())) {
            throw new AppException("Invalid email format", HttpStatus.BAD_REQUEST);
        }


        if (userDto.userName().startsWith(" ") || userDto.userName().endsWith(" ") ||
                userDto.login().startsWith(" ") || userDto.login().endsWith(" ")) {
            throw new AppException("Username and login cannot have leading or trailing whitespaces", HttpStatus.BAD_REQUEST);
        }
        else {
            Optional<Admin> optionalUser = userRepository.findByLogin(userDto.login());

            if (optionalUser.isPresent()) {
                throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
            }

            Admin user = userMapper.signUpToUser(userDto);
            user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));

            // Thêm thông tin enable và lưu vào cơ sở dữ liệu
            user.setEnable(0L); // Mặc định là 0
            Admin savedUser = userRepository.save(user);

            // Gửi email xác nhận
            sendConfirmationEmail(savedUser.getLogin());

            return userMapper.toUserDto(savedUser);
        }
    }

    @Override
    public AdminDTO getAccountByAdmin(String id){
        return userMapper.toUserDto(userRepository.getAccountByUser(id).orElseThrow(() -> new NotFoundException("Product by id" + id + "not found")));
    }
    private void sendConfirmationEmail(String email) {
        String confirmationLink = "http://localhost:8080/confirm?email=" + email; // Thay đổi URL của ứng dụng của bạn
        String emailContent = "Please click the link below to confirm your email:\n" + confirmationLink;

        // Gửi email xác nhận
        emailSender.send(email, emailContent);
    }

    // Trong User_Impl (File F')
    public void confirmEmail(String email) {
        Optional<Admin> userOptional = userRepository.findByLogin(email);
        if (userOptional.isPresent()) {
            Admin user = userOptional.get();
            user.setEnable(1L); // Set trạng thái enable = 1 khi xác nhận email
            userRepository.save(user);
        } else {
            throw new AppException("User not found for email: " + email, HttpStatus.NOT_FOUND);
        }
    }


    private boolean isInvalidEmailFormat(String email) {
        // Kiểm tra định dạng email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email == null || !email.matches(emailRegex);
    }
    private boolean isInvalidLength(String input, int maxLength) {
        return input == null || input.length() > maxLength;
    }

    public AdminDTO findByLogin(String login) {
        Admin user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    private void checkNotSpace(String login) {
        if (login.contains(" ")) {
            throw new AppException("Login cannot contain spaces", HttpStatus.BAD_REQUEST);
        }
    }
}
