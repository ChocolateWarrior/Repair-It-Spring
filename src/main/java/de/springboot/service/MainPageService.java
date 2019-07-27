package de.springboot.service;

import de.springboot.model.RepairRequest;
import de.springboot.model.RequestState;
import de.springboot.model.User;
import de.springboot.repository.RequestRepository;
import de.springboot.repository.UserRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MainPageService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public MainPageService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public List<RepairRequest> getRequestsByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findByUser(user);
    }

    public boolean hasMasterRequest(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return user.getMasterRequest() != null;
    }

    public RepairRequest getRequestByMaster(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findById(user.getMasterRequest().getId());
    }

    public BigDecimal getUserBalance(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return user.getBalance();
    }

    public void addToUserBalance(BigDecimal value){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        if(user.getBalance() != null)
            user.setBalance(user.getBalance().add(value));
        else
            user.setBalance(value);
        userRepository.save(user);
    }

    public void setPurchase(BigDecimal price, int requestId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        user.setBalance(user.getBalance().subtract(price));
        userRepository.save(user);

        RepairRequest request = requestRepository.findById(requestId);
        request.setState(RequestState.PAID);
        requestRepository.save(request);

    }

    public void setRequestComment(String comment, int requestId){
        RepairRequest request = requestRepository.findById(requestId);
        request.setComment(comment);
        requestRepository.save(request);
    }
//    public List<RepairRequest> getRequestsByMaster(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(authentication.getName());
//        Set<RepairRequest> masterRequests= new HashSet<>()
//
//        return requestRepository.findById(user.getMasterRequests());
//    }

}
