package com.example.ecommerce_be.service.impl;

import com.example.ecommerce_be.base.NotFoundException;
import com.example.ecommerce_be.constants.Constants;
import com.example.ecommerce_be.dto.AccountDTO;
import com.example.ecommerce_be.dto.Product_DTO;
import com.example.ecommerce_be.entity.Account;
import com.example.ecommerce_be.entity.Bill;
import com.example.ecommerce_be.entity.BillDetail;
import com.example.ecommerce_be.exception.UserAlreadyExistException;
import com.example.ecommerce_be.mapper.AccoutMapper;
import com.example.ecommerce_be.repositories.AccountRepository;
import com.example.ecommerce_be.repositories.BillDetailRepository;
import com.example.ecommerce_be.repositories.BillRepository;
import com.example.ecommerce_be.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService_impl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private AccoutMapper accoutMapper;

    //@Autowired
    //private ColorRepository colorRepository;
    @Override
    public List<AccountDTO> getListAccount() {
        return accoutMapper.toDtos(accountRepository.getAllAccount());
    }
    @Override
    public AccountDTO getAccountByUser(String id){
        return accoutMapper.toDto(accountRepository.getAccountByUser(id).orElseThrow(() -> new NotFoundException("Product by id" + id + "not found")));
    }


    @Override
    @Transactional
    public AccountDTO addNewAccount(AccountDTO accountDTO) {
        String email = accountDTO.getEmail();
        if (accountRepository.findByEmail(email) != null) {
            throw new UserAlreadyExistException("Email already exists");
        }
        Account account = accoutMapper.toEntity(accountDTO);
        account.setUser(accountDTO.getUser());
        account.setPass(accountDTO.getPass());
        account.setName(accountDTO.getName());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setEmail(accountDTO.getEmail());
        account.setAddress(accountDTO.getAddress());
        return accoutMapper.toDto(accountRepository.save(account));

    }



    @Transactional
    public AccountDTO updateAccount(AccountDTO accountDTO) {
        // dùng modal mapper
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(AccountDTO.class, Account.class)
                .setProvider(p -> accountRepository.findById(accountDTO.getId()).orElseThrow(NoResultException::new));
        Account account = mapper.map(accountDTO, Account.class);

        // Lưu lại thông tin sản phẩm
        Account updatedAccount = accountRepository.save(account);

        return accoutMapper.toDto(accountRepository.save(updatedAccount));

    }



//    @Transactional
//    public void deleteAccountById(Long id) {
//        // Tìm đối tượng thực thể trong cơ sở dữ liệu
//        Account account = accountRepository.findById(id)
//                .orElseThrow(() -> new NoResultException("Không tìm tài khoản phẩm với ID: " + id));
//        // Xóa đối tượng thực thể khỏi cơ sở dữ liệu
//        accountRepository.delete(account);
//    }



    //cái này xóa đc 1 bảng
    @Transactional
    public void deleteAccountById(Long id) {
        // Tìm đối tượng thực thể trong cơ sở dữ liệu
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new NoResultException("Không tìm tài khoản với ID: " + id));

        // Lấy thông tin email của tài khoản sắp bị xóa
        String emailToDelete = account.getEmail();

        // Xóa dữ liệu từ bảng Bill liên quan
        List<Bill> billsToDelete = billRepository.getBillByEmail(emailToDelete);
        billRepository.deleteAll(billsToDelete);

        // Xóa dữ liệu từ bảng BillDetail liên quan
        for (Bill bill : billsToDelete) {
            List<BillDetail> billDetailsToDelete = billDetailRepository.findByBillID(bill.getId());
            billDetailRepository.deleteAll(billDetailsToDelete);
        }

        // Xóa tài khoản
        accountRepository.delete(account);
    }



    public boolean checkLogin(String user, String pass) {
        Account account = accountRepository.findByUsername(user);
        if (account != null && account.getPass().equals(pass)) {
            return true; // đăng nhập thành công
        } else {
            return false; // tài khoản hoặc mật khẩu không đúng
        }
    }
}
