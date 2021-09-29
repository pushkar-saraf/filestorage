package com.assessment.storage.consumer;

import com.assessment.storage.internal.DataDTO;
import com.assessment.storage.internal.DataDTOBuf;
import com.assessment.storage.service.FileType;
import com.assessment.storage.service.StorageService;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class FilesListener {

    @Autowired
    StorageService storageService;

    @Bean
    public Queue myQueue() {
        return new Queue("files", false);
    }


    @RabbitListener(queues = "files")
    public void listen(String in) {
        try {
            DataDTOBuf.Data data = DataDTOBuf.Data.parseFrom(ByteString.copyFromUtf8(in));
            DataDTO dataDTO = new DataDTO();
            String[] dobArgs = data.getDob().split(",");
            dataDTO.setDob(LocalDate.of(Integer.parseInt(dobArgs[2]), Integer.parseInt(dobArgs[1]), Integer.parseInt(dobArgs[0])));
            dataDTO.setAge(data.getAge());
            dataDTO.setSalary(BigDecimal.valueOf(data.getSalary()));
            dataDTO.setName(data.getName());
            storageService.save(dataDTO, FileType.valueOf(data.getFormat()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
