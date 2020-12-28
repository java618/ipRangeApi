package com.example.demo.services;

import com.example.demo.entities.IpRangeEntity;
import com.example.demo.hibernate.HibernateIpRangeRepo;
import com.example.demo.models.IpRangeModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
class IpRangeServiceImplTest {

    @TestConfiguration
    static class IpRangeServiceImplContextConfiguration{
        @Bean
        public IpRangeServiceImpl ipRangeService(){
            return new IpRangeServiceImpl();
        }
    }
    @Autowired
    IpRangeServiceImpl ipRangeService;

    @MockBean
    HibernateIpRangeRepo hibernateIpRangeRepo;

    @Before
    public void setUp(){
        IpRangeEntity ipRangeEntity = new IpRangeEntity();
        long ipAddrLong = 466666;
        ipRangeEntity.setLowerRange(123213);
        ipRangeEntity.setUpperRange(234324);

        Mockito.when(hibernateIpRangeRepo.findByRange(ipAddrLong))
                .thenReturn(java.util.Optional.of(ipRangeEntity));
    }

    @Test
    void findRange() {
        String ipAddr = "192.168.0.158";
        IpRangeModel ipRangeModel = ipRangeService.findRange(ipAddr);
//         Assert.assertThat();

    }

    @Test
    void addIpRange() {
    }
}