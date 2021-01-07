package com.example.demo.services;

import com.example.demo.entities.IpRangeEntity;
import com.example.demo.hibernate.HibernateIpRangeRepo;
import com.example.demo.models.IpRangeModel;
import com.example.demo.repositories.IpRangeRepository;
import com.example.demo.utils.IpConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class  IpRangeServiceImpl {

    public IpRangeServiceImpl(IpRangeRepository ipRangeRepository, IpConverter ipConverter, HibernateIpRangeRepo hibernateIpRangeRepo) {
        this.ipRangeRepository = ipRangeRepository;
        this.ipConverter = ipConverter;
        this.hibernateIpRangeRepo = hibernateIpRangeRepo;
    }

    @Autowired
    IpRangeRepository ipRangeRepository;

    @Autowired
    IpConverter ipConverter;

    @Autowired
    HibernateIpRangeRepo hibernateIpRangeRepo;

    public Object findRange(String ipRange){

        if (ipConverter.validate(ipRange)){

            long ipLongValue = ipConverter.ipToLong(ipRange);
            Optional<IpRangeEntity> inDb = hibernateIpRangeRepo.findByRange(ipLongValue);
            IpRangeModel ipRangeModel = new IpRangeModel();
            ipRangeModel.setLowerIp(ipConverter.longToIp(inDb.get().getLowerRange()));
            ipRangeModel.setUpperIp(ipConverter.longToIp(inDb.get().getUpperRange()));
            Optional<IpRangeModel> ipmOp = Optional.of(ipRangeModel);
//        return inDb.orElseThrow(RuntimeException::new);
            return ipmOp.orElseThrow(RuntimeException::new);
        }else{
            return "Ip address is not correct";
        }
    }

    public ResponseEntity<String> addIpRange(IpRangeModel ipRangeModel){

        String info;

        if (ipConverter.validate(ipRangeModel.getLowerIp()) && ipConverter.validate(ipRangeModel.getUpperIp())) {

            long lowerRangeLongValue = ipConverter.ipToLong(ipRangeModel.getLowerIp());
            long upperRangeLongValue = ipConverter.ipToLong(ipRangeModel.getUpperIp());

            if (hibernateIpRangeRepo.findByRange(lowerRangeLongValue).isEmpty() ||
                    hibernateIpRangeRepo.findByRange(upperRangeLongValue).isEmpty()) {

                IpRangeEntity ipRangeEntity = new IpRangeEntity();
                ipRangeEntity.setLowerRange(lowerRangeLongValue);
                ipRangeEntity.setUpperRange(upperRangeLongValue);
                ipRangeRepository.save(ipRangeEntity);

                info = "Ip Range successfully created";
            } else {
                info = "Ip Range is not correct";
            }
        }else {
            info = "Ip addresses is not correct";
        }

        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
