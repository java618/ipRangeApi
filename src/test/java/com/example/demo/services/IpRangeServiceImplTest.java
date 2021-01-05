package com.example.demo.services;

import com.example.demo.entities.IpRangeEntity;
import com.example.demo.hibernate.HibernateIpRangeRepo;
import com.example.demo.models.IpRangeModel;
import com.example.demo.repositories.IpRangeRepository;
import com.example.demo.utils.IpConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
class IpRangeServiceImplTest {

    private IpRangeServiceImpl ipRangeService;
    private IpConverter ipConverter;
    private HibernateIpRangeRepo hibernateIpRangeRepo;
    private IpRangeRepository ipRangeRepository;



    @BeforeEach
    public void setUp(){

        hibernateIpRangeRepo = mock(HibernateIpRangeRepo.class);
        ipRangeRepository = mock(IpRangeRepository.class);
        ipConverter = new IpConverter();
        ipRangeService = new IpRangeServiceImpl(ipRangeRepository, ipConverter, hibernateIpRangeRepo);
    }

    @Test
    void findRange() {

        String ipAddr = "192.168.0.158";
        IpRangeModel ipRangeModel = new IpRangeModel();
        ipRangeModel.setLowerIp("192.168.0.0");
        ipRangeModel.setUpperIp("192.168.2.255");

        long ipAddrLong = ipConverter.ipToLong(ipAddr);

        IpRangeEntity ipRangeEntity = new IpRangeEntity();
        ipRangeEntity.setLowerRange(ipConverter.ipToLong(ipRangeModel.getLowerIp()));
        ipRangeEntity.setUpperRange(ipConverter.ipToLong(ipRangeModel.getUpperIp()));

        when(hibernateIpRangeRepo.findByRange(ipAddrLong)).thenReturn(java.util.Optional.of(ipRangeEntity));

        IpRangeModel found = ipRangeService.findRange(ipAddr);


        assertThat(found.getLowerIp()).isEqualTo(ipRangeModel.getLowerIp());
        assertThat(found.getUpperIp()).isEqualTo(ipRangeModel.getUpperIp());


    }

    @Test
    void addIpRange() {
        IpRangeModel ipRangeModel = new IpRangeModel();
        ipRangeModel.setLowerIp("192.168.0.0");
        ipRangeModel.setUpperIp("192.168.2.255");

        IpRangeEntity ipRangeEntity = new IpRangeEntity();
        ipRangeEntity.setLowerRange(ipConverter.ipToLong(ipRangeModel.getLowerIp()));
        ipRangeEntity.setUpperRange(ipConverter.ipToLong(ipRangeModel.getUpperIp()));

        when(ipRangeRepository.save(ipRangeEntity)).thenReturn(ipRangeEntity);

        ResponseEntity<String> found = ipRangeService.addIpRange(ipRangeModel);

        assertThat(found.getBody()).isEqualTo("Ip Range successfully created");
    }
}