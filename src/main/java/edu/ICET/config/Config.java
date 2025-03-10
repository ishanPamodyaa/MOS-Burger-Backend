package edu.ICET.config;


import edu.ICET.dto.OrderDetailsDto;
import edu.ICET.entity.OrderDetail;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ModelMapper getMapper(){

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<OrderDetail, OrderDetailsDto>() {

            @Override
            protected void configure(){
                map().setOrderId(source.getId().getOrderId());
                map().setProductId(source.getId().getProductId());
            }
        });


        return modelMapper;
    }
}
