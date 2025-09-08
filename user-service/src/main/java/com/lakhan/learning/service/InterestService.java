package com.lakhan.learning.service;

import com.lakhan.learning.dao.InterestDao;
import com.lakhan.learning.dtos.Interest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InterestService {

    private final InterestDao interestDao;

    public InterestService(InterestDao interestDao) {
        this.interestDao = interestDao;
    }

    public List<Interest> getTop10Interests() {
        return interestDao.findTop10ByUsersCount();
    }
    //add here method to save unique interests if not present. if present ignore
    public void saveInterests(List<String> interests) {
        for (String interestName : interests) {
            if (!interestDao.existsByName(interestName)) {
                Interest interest = new Interest();
                interest.setName(interestName);
                interestDao.save(interest);
            }
        }
    }
}

